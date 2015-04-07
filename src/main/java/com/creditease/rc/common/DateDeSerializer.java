package com.creditease.rc.common;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
/**
 * 
 * DateDeSerializer.java
 * @author yifengwang
 * 2012-12-12 下午01:44:58
 * @version V2.0
 */
public class DateDeSerializer extends JsonDeserializer<Date>{
	
	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		String dateString = parser.getText();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

}
