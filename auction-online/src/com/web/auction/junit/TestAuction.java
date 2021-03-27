package com.web.auction.junit;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.web.auction.mapper.AuctionMapper;
import com.web.auction.pojo.Auction;
import com.web.auction.pojo.AuctionCustom;
import com.web.auction.pojo.Auctionrecord;
import com.web.auction.service.AuctionService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml","classpath:spring/springmvc.xml"})
public class TestAuction {
	
	@Autowired
	private AuctionService auctionService;
	
	@Autowired
	
	private AuctionMapper auctionMapper;
	
	@Test
	public void testQuery(){
		
Auction auction=auctionService.findAuctionAndRecordById(19);


System.out.println(auction.getAuctionname()+","+auction.getAuctionstartprice()+","+auction.getAuctionpic());

List <Auctionrecord> recordList=auction.getAuctionrecordList();

for(Auctionrecord record:recordList){
	System.out.println(record.getAuctiontime()+","+record.getAuctionprice()+","+record.getUser().getUsername());
	
	
}
	}
	
	@Test
	public void testQuery2(){
		
				
				
			List<AuctionCustom>	 auctionCustom=auctionService.findAuctionByEndtime();
		
			for (AuctionCustom auctionCustom2 : auctionCustom) {
			System.out.println(	auctionCustom2.getAuctionname()  +","+auctionCustom2.getAuctionname());
			}
	}
	
	@Test
	public void testQuery3(){
		
		            List<Auction>  auctionCustom1=     auctionService.findAuctionByNoEndtime();
		            
		      for(Auction auctionCustom2:auctionCustom1){
		    	  
		    	  System.out.println(auctionCustom2.getAuctionname());
		      }
		      
	}
	

}
