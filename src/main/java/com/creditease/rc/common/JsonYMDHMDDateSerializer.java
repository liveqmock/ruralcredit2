package com.creditease.rc.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;


/**
 * 
 * cs
 * Title:JsonYMDHMDDateSerializer.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-6上午10:14:35
 * @author wyf
 * @version v2.0
 */
@Component 
public class JsonYMDHMDDateSerializer extends JsonSerializer<Date>{ 
	   private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

	    @Override 
	    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) 
	            throws IOException, JsonProcessingException { 

	        String formattedDate = dateFormat.format(date); 

	        gen.writeString(formattedDate); 
	    } 
}
