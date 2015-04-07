
/*
 * 
 */

package com.creditease.rc.app.orgams2;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.9
 * Wed Sep 03 10:56:53 CST 2014
 * Generated source version: 2.2.9
 * 
 */


@WebServiceClient(name = "ICPWebServiceService", 
                  wsdlLocation = "file:/E:/ruralcredit2/src/main/java/com/creditease/rc/app/orgams2/ICPWS.wsdl",
                  targetNamespace = "http://service.icp.ws.component.creditease.com/") 
public class ICPWebServiceService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://service.icp.ws.component.creditease.com/", "ICPWebServiceService");
    public final static QName ICPWebServicePort = new QName("http://service.icp.ws.component.creditease.com/", "ICPWebServicePort");
    static {
        URL url = null;
        try {
            url = new URL("file:/E:/ruralcredit2/src/main/java/com/creditease/rc/app/orgams2/ICPWS.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:/E:/ruralcredit2/src/main/java/com/creditease/rc/app/orgams2/ICPWS.wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public ICPWebServiceService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ICPWebServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ICPWebServiceService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     * 
     * @return
     *     returns ICPWebService
     */
    @WebEndpoint(name = "ICPWebServicePort")
    public ICPWebService getICPWebServicePort() {
        return super.getPort(ICPWebServicePort, ICPWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ICPWebService
     */
    @WebEndpoint(name = "ICPWebServicePort")
    public ICPWebService getICPWebServicePort(WebServiceFeature... features) {
        return super.getPort(ICPWebServicePort, ICPWebService.class);
    }

}
