package com.web.auction.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.auction.pojo.Auction;
import com.web.auction.pojo.AuctionCustom;
import com.web.auction.pojo.Auctionrecord;
import com.web.auction.pojo.User;
import com.web.auction.service.AuctionService;

@Controller
public class AuctionController {

	@Autowired
	private AuctionService auctionService;
	public static final int PAGE_SIZE = 10;

	@RequestMapping("/queryAuctions")
	public ModelAndView queryAuctions(
			
		
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@ModelAttribute("condition")Auction auction) {
		
		ModelAndView mv=new ModelAndView();
		
		// 分页拦截,重构sql
		PageHelper.startPage(pageNum, PAGE_SIZE);

		List<Auction> list = auctionService.queryAuctions(auction);
		//构建分页bean  
		PageInfo  pageInfo=new PageInfo<>(list);
		mv.addObject("auctionList",list);
		mv.addObject("page",pageInfo);
		mv.setViewName("index");
		//postHandle拦截器在这里执行
		return mv;//aftercompletetion

	}

	
	@RequestMapping("/toDetail/{id}")
	public ModelAndView toDetail(@PathVariable int id ){
		
		
		
		//按竞拍后查看记录
		ModelAndView mv=new ModelAndView();
		
		Auction auction=auctionService.findAuctionAndRecordById(id);
		mv.addObject("auctionDetail",auction);
		mv.setViewName("auctionDetail");
		return mv;
		
		 
		
	}
	
	@RequestMapping("/saveAuctionRecord")
	public String  saveAuctionRecord(Auctionrecord record,HttpSession session,Model model) throws Exception{
//	
//		try {
//			record.setAuctiontime(new Date());
//			User user=(User) session.getAttribute("user");
//			record.setUserid(user.getUserid());
//			auctionService.addAuctionRecord(record);
//			
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			model.addAttribute("errorMsg",e.getMessage());
//			System.out.println("............"+record.getAuctionid());
//			return "error";
//			
//			//e.printStackTrace();
//		}
//	
//		return "redirect:/toDetail/"+record.getAuctionid();
//		
		
		//这个是竞拍   就是出价了
		
		
		
			record.setAuctiontime(new Date());
			User user=(User) session.getAttribute("user");
			record.setUserid(user.getUserid());
			auctionService.addAuctionRecord(record);
			// TODO Auto-generated catch block
			System.out.println("............"+record.getAuctionid());
			
			
			//e.printStackTrace();
		
	
		return "redirect:/toDetail/"+record.getAuctionid();
		
		
	}
	
	
	@RequestMapping("/toAuctionnResult")
	public ModelAndView toAuctionResult(){
	ModelAndView mv=new ModelAndView();
	
	List<AuctionCustom>endtimeList=	auctionService.findAuctionByEndtime();
	List<Auction>noendtimeList=auctionService.findAuctionByNoEndtime();
	mv.addObject("auctionCustomList", endtimeList);
	mv.addObject("auctionList", noendtimeList);
	mv.setViewName("auctionResult");
		
		return mv;
		//查询竞拍结束的和还没竞拍中的
	}
	
	
	
	@RequestMapping("/publishAuctions")
	public String publishAuctins(Auction auction,MultipartFile pic,HttpSession session){
		//新增商品啊
		//另存二进制的文件 s,保存到pic的tomcat的目录的绝对路径	
		try {
			String path=session.getServletContext().getRealPath("pic");
			File targetFile=new File(path,pic.getOriginalFilename());
			auction.setAuctionpic(pic.getOriginalFilename());
			auction.setAuctionpictype(pic.getContentType());
			auctionService.addAuction(auction);
			pic.transferTo(targetFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "redirect:/queryAuctions";
		
		
	}
	
	
	@RequestMapping("/removeAuction/{auctionid}")
	public String removeAuction(@PathVariable int auctionid){
		auctionService.removeAuction(auctionid);
		
		return "redirect:/queryAuctions";
		
		
	}
	
	
	@RequestMapping("/toUpdate/{auctionid}")
	public ModelAndView toUpdate(@PathVariable int auctionid) {
		Auction auction = auctionService.findAuctionById(auctionid);
		ModelAndView mv = new ModelAndView();
		mv.addObject("auction", auction);
		mv.setViewName("updateAuction");
		return mv;
		//查找要修改的那个商品的id然后查询
	}
	
	@RequestMapping("/submitUpdateAuction")
	public String submitUpdateAuction(Auction auction,MultipartFile pic,HttpSession session) {
		//修改商品
		try {
			if (pic.getSize() > 0) {
				String path = session.getServletContext().getRealPath("images");
				//先删除原来的图片
				File oldFile = new File(path, auction.getAuctionpic());
				if (oldFile.exists()) {
					oldFile.delete();
				}
				
				File targetFile = new File(path, pic.getOriginalFilename());
				pic.transferTo(targetFile);		
				
				//修改auction的属性
				auction.setAuctionpic(pic.getOriginalFilename());
				auction.setAuctionpictype(pic.getContentType());
			}
			//2. 持久化aution对象
			auctionService.updateAuction(auction);
		}  catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/queryAuctions";
		
	}
	
}
