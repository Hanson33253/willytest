package com.web.auction.utils;




//商品竞价错误的异常类
public class AuctionPriceException extends Exception {
private String message;




public AuctionPriceException(String message) {
	super(message);
	this.message = message;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}




}
