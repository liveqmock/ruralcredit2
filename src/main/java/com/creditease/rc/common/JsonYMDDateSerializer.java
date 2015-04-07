/* 
 * Copyright (C) 2006-2012 普信恒业科技发展（北京）有限公司.
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
============================================================
 * FileName: JsonDateSerializer.java 
 * Created: [2012-10-6 上午09:20:45] by 姚鑫 
 * $Id$
 * $Revision$
 * $Author$
 * $Date$
============================================================ 
 * ProjectName: ruralcredit2 
 * Description: 
==========================================================*/
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
 * Description: 使用Ajax方式回显年月日格式日期
 * @author 姚鑫
 * @version 2.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2012-10-6      姚鑫                      2.0         2.0 Version 
 * </pre>
 */

@Component 
public class JsonYMDDateSerializer extends JsonSerializer<Date>{ 
	   private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 

	    @Override 
	    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) 
	            throws IOException, JsonProcessingException { 

	        String formattedDate = dateFormat.format(date); 

	        gen.writeString(formattedDate); 
	    } 
}
