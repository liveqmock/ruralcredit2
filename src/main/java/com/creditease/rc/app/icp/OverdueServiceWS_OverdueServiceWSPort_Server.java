
package com.creditease.rc.app.icp;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.2.9
 * Mon Jan 12 11:34:12 CST 2015
 * Generated source version: 2.2.9
 * 
 */
 
public class OverdueServiceWS_OverdueServiceWSPort_Server{

    protected OverdueServiceWS_OverdueServiceWSPort_Server() throws Exception {
        System.out.println("Starting Server");
        Object implementor = new OverdueServiceWSImpl();
        String address = "http://10.100.30.214:7001/ICPlatform/services/OverdueServiceWS";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws Exception { 
        new OverdueServiceWS_OverdueServiceWSPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}