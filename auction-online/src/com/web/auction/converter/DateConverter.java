package com.web.auction.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {
	
	//这个类是日期转换类

	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	
	
	@Override
	public Date convert(String time) {
		// TODO Auto-generated method stub
		try {
		return	sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}


	
}
