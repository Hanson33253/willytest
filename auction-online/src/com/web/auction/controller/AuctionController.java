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
		
		// ��ҳ����,�ع�sql
		PageHelper.startPage(pageNum, PAGE_SIZE);

		List<Auction> list = auctionService.queryAuctions(auction);
		//������ҳbean  
		PageInfo  pageInfo=new PageInfo<>(list);
		mv.addObject("auctionList",list);
		mv.addObject("page",pageInfo);
		mv.setViewName("index");
		//postHandle������������ִ��
		return mv;//aftercompletetion

	}

	
	@RequestMapping("/toDetail/{id}")
	public ModelAndView toDetail(@PathVariable int id ){
		
		
		
		//�����ĺ�鿴��¼
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
		
		//����Ǿ���   ���ǳ�����
		
		
		
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
		//��ѯ���Ľ����ĺͻ�û�����е�
	}
	
	
	
	@RequestMapping("/publishAuctions")
	public String publishAuctins(Auction auction,MultipartFile pic,HttpSession session){
		//������Ʒ��
		//�������Ƶ��ļ� s,���浽pic��tomcat��Ŀ¼�ľ���·��	
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
		//����Ҫ�޸ĵ��Ǹ���Ʒ��idȻ���ѯ
	}
	
	@RequestMapping("/submitUpdateAuction")
	public String submitUpdateAuction(Auction auction,MultipartFile pic,HttpSession session) {
		//�޸���Ʒ
		try {
			if (pic.getSize() > 0) {
				String path = session.getServletContext().getRealPath("images");
				//��ɾ��ԭ����ͼƬ
				File oldFile = new File(path, auction.getAuctionpic());
				if (oldFile.exists()) {
					oldFile.delete();
				}
				
				File targetFile = new File(path, pic.getOriginalFilename());
				pic.transferTo(targetFile);		
				
				//�޸�auction������
				auction.setAuctionpic(pic.getOriginalFilename());
				auction.setAuctionpictype(pic.getContentType());
			}
			//2. �־û�aution����
			auctionService.updateAuction(auction);
		}  catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/queryAuctions";
		
	}
	
}
