package com.web.auction.mapper;

import java.util.List;

import com.web.auction.pojo.Auction;
import com.web.auction.pojo.AuctionCustom;

public interface AuctionCustomMapper {
	
	
public Auction findAuctionAndRecordById(int auctionid);

public List<AuctionCustom> findAuctionByEndtime();


public List<Auction> findAuctionByNoEndtime();

}
