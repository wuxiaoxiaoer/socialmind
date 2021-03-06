package edu.xjtsoft.base.mvc;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePropertyEditor extends PropertyEditorSupport {

	private Date date;
	
	@Override
	public Object getValue() {
		return date;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		//DateUtils.parseDate(text, new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"});
		SimpleDateFormat df = new SimpleDateFormat();
		if(text.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
			df.applyPattern("yyyy-MM-dd");
		} else if(text.matches("^\\d{4}/\\d{1,2}/\\d{1,2}$")) {
			df.applyPattern("yyyy/MM/dd");			
		} else if(text.matches("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}$")) {
			df.applyPattern("yyyy-MM-dd HH:mm");
		} else if(text.matches("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
			df.applyPattern("yyyy-MM-dd HH:mm:ss");
		} else {
			throw new IllegalArgumentException("日期类型不满足指定规则!");
		}
		try {
			date = df.parse(text);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
