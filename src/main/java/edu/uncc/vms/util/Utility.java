package edu.uncc.vms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Utility {

	private static final Logger logger = Logger.getLogger(Utility.class);
	public boolean isEmpty(String str) {
		if (str != null) {
			if (str.length() == 0)
				return true;
			else
				return false;
		}
		return true;
	}
	
	public int checkDate(String date) throws ParseException
	{
		String format= "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date inDate = sdf.parse(date);
		
		Calendar calender = Calendar.getInstance();
		Date currentdate = calender.getTime();
		
		logger.debug("inDate="+inDate);
		logger.debug("currentdate="+currentdate);
		if(inDate.before(currentdate))
			return -1;
		else if(inDate.after(currentdate))
			return 1;
		return 0;
	}
	
	
	public static void main(String args[]) throws ParseException
	{
		Utility u = new Utility();
		String date = "2015-05-12";
		int status = u.checkDate(date);
		System.out.println("status="+status);
	}
}
