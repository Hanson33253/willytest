package com.web.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.auction.mapper.AuctionCustomMapper;
import com.web.auction.mapper.AuctionMapper;
import com.web.auction.mapper.AuctionrecordMapper;
import com.web.auction.pojo.Auction;
import com.web.auction.pojo.AuctionCustom;
import com.web.auction.pojo.AuctionExample;
import com.web.auction.pojo.Auctionrecord;
import com.web.auction.pojo.AuctionrecordExample;
import com.web.auction.service.AuctionService;
import com.web.auction.utils.AuctionPriceException;

@Service
public class AuctionSeviceImpl implements AuctionService {

	@Autowired
	private AuctionMapper auctionMapper;

	@Autowired
	private AuctionCustomMapper auctionCustomMapper;

	@Autowired
	
	private AuctionrecordMapper recordMapper;
	
	@Override
	public List<Auction> queryAuctions(Auction auction) {
		// TODO Auto-generated method stub

		AuctionExample example = new AuctionExample();
		AuctionExample.Criteria criteria = example.createCriteria();

		if (auction != null) {
			// 1.商品名称 用模糊查询

			if (auction.getAuctionname() != null && !"".equals(auction.getAuctionname())) {
				criteria.andAuctionnameLike("%" + auction.getAuctionname() + "%");

			}

			// 2.商品描述 模糊

			if (auction.getAuctiondesc() != null && !"".equals(auction.getAuctiondesc())) {
				criteria.andAuctiondescLike("%" + auction.getAuctiondesc() + "%");

			}

			// 3。起拍时间 >

			if (auction.getAuctionstarttime() != null) {
				criteria.andAuctionstarttimeGreaterThan(auction.getAuctionstarttime());

			}
			// 4结束时间<

			if (auction.getAuctionendtime() != null) {

				criteria.andAuctionendtimeLessThan(auction.getAuctionendtime());

			}
			// 5起拍价>

			if (auction.getAuctionstartprice() != null) {
				criteria.andAuctionstartpriceGreaterThan(auction.getAuctionstartprice());

			}

		}
		//按开始日期降序排列
		example.setOrderByClause("auctionstarttime desc");
		List<Auction> list = auctionMapper.selectByExample(example);

		return list;
		

	}

	@Override
	public Auction findAuctionAndRecordById(int auctionid) {
		// TODO Auto-generated method stub
		return auctionCustomMapper.findAuctionAndRecordById(auctionid);
	}
	
	
	
	
//	竞拍时间不能过期
//	首次竞价，价格不能低于起拍价
//	后续竞价，价格是所有竞价的最高价
	@Override
	public void addAuctionRecord(Auctionrecord record) throws AuctionPriceException  {
		// TODO Auto-generated method stub
		 
		//auctionMapper.selectByPrimaryKey(auctionid)
		
		Auction auction=auctionCustomMapper.findAuctionAndRecordById(record.getAuctionid());
		if(auction.getAuctionendtime().after(new Date()) == false){
			throw new AuctionPriceException("竞拍时间已经结束");
			
		}else {
			if (auction.getAuctionrecordList()!=null&&auction.getAuctionrecordList().size()>0) {
			Auctionrecord maxRecord=auction.getAuctionrecordList().get(0);
				if(record.getAuctionprice()<maxRecord.getAuctionprice()){
					throw new AuctionPriceException("价格必须是所有竞价的最高价");
				}
			}else  {
				
				
				if(record.getAuctionprice()<auction.getAuctionstartprice()){
					throw new AuctionPriceException("价格不能低于拍价");
					
				}
			}
		}
		recordMapper.insert(record);
		
		
	}

	@Override
	public List<AuctionCustom> findAuctionByEndtime() {
		// TODO Auto-generated method stub
		return auctionCustomMapper.findAuctionByEndtime();
	}

	@Override
	public List<Auction> findAuctionByNoEndtime() {
		// TODO Auto-generated method stub
		return auctionCustomMapper.findAuctionByNoEndtime();
	}

	@Override
	public void addAuction(Auction auction) {
		// TODO Auto-generated method stub
		
		auctionMapper.insert(auction);
		
	}

	@Override
	public void removeAuction(int auctionid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				AuctionrecordExample example=new AuctionrecordExample();
				AuctionrecordExample.Criteria criteria=example.createCriteria();
				criteria.andAuctionidEqualTo(auctionid);
				recordMapper.deleteByExample(example);
				auctionMapper.deleteByPrimaryKey(auctionid);
				
	}

	@Override
	public void updateAuction(Auction auction) {
		// TODO Auto-generated method stub
		auctionMapper.updateByPrimaryKey(auction);
	}

	@Override
	public Auction findAuctionById(int id) {
		// TODO Auto-generated method stub
		return auctionMapper.selectByPrimaryKey(id);
	
	}
	

}
