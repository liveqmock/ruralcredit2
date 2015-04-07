package com.creditease.rc.app.sia.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextTool implements ApplicationContextAware { 
        private static ApplicationContext context;
       
        public static ApplicationContext getApplicationContext() { 
                return context; 
        }
        @Override
        public void setApplicationContext(ApplicationContext context) throws BeansException {
            this.context=context;
        } 
}
