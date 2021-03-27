package com.web.auction.service;

import java.util.List;

import com.web.auction.pojo.Auction;
import com.web.auction.pojo.AuctionCustom;
import com.web.auction.pojo.Auctionrecord;

public interface AuctionService {
	
	public void addAuctionRecord(Auctionrecord record)throws Exception ;
	
	public List<Auction>queryAuctions(Auction auction);
	
	public Auction findAuctionAndRecordById(int auctionid);
	
	
	
	public List<AuctionCustom> findAuctionByEndtime();


	public List<Auction> findAuctionByNoEndtime();
	
	
	public void addAuction(Auction auction);
	
	public void removeAuction(int auctionid);
	
	public void updateAuction(Auction auction);
	
	public Auction findAuctionById(int id);  //µ•±Ì≤È—Ø
	
}
