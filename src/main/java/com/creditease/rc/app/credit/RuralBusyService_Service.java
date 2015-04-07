
package com.creditease.rc.app.credit;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
* <p>
* An example of how this class may be used:
* <pre>
* RuralBusyService service = new RuralBusyService();
* RuralBusyService portType = service.getRuralBusyService();
* portType.clientApply(...);
* </pre>
* </p>
 * 
 */
@WebServiceClient(name = "RuralBusyService", targetNamespace = "http://www.creditease.cn/RuralBusyService/", wsdlLocation = "http://10.100.30.53/creditrelease/ruralbusy/RuralBusyService.php?wsdl")
public class RuralBusyService_Service
    extends Service
{

    private final static URL RURALBUSYSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.creditease.rc.app.credit.RuralBusyService_Service.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.creditease.rc.app.credit.RuralBusyService_Service.class.getResource(".");
            url = new URL(baseUrl, "http://10.100.30.53/creditrelease/ruralbusy/RuralBusyService.php?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://10.100.30.53/creditrelease/ruralbusy/RuralBusyService.php?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        RURALBUSYSERVICE_WSDL_LOCATION = url;
    }

    public RuralBusyService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RuralBusyService_Service() {
        super(RURALBUSYSERVICE_WSDL_LOCATION, new QName("http://www.creditease.cn/RuralBusyService/", "RuralBusyService"));
    }

    /**
     * 
     * @return
     *     returns RuralBusyService
     */
    @WebEndpoint(name = "RuralBusyService")
    public RuralBusyService getRuralBusyService() {
        return super.getPort(new QName("http://www.creditease.cn/RuralBusyService/", "RuralBusyService"), RuralBusyService.class);
    }

}