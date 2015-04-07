package com.creditease.rc.framework.exception.interceptor;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.springframework.stereotype.Component;
/**
 * CXF向后兼容性(backwards compatibility)解决方案
 * 当在CXF服务端pojo中添加一个新属性后，未经重新generate的客户端会在调用该web service时报UnmarshalException: unexpected element
 * 为了避免反复重新生成客户端代理类，我们必须想一个绕过该Validation的方式
 *
 * @author 韩大年
 * @version 1.0
 */
@Component
public class IgnoreUnexpectedElementsHandler  implements ValidationEventHandler {
    
    public boolean handleEvent(ValidationEvent event) {
      return event.getMessage().startsWith("unexpected element (");
    }
}