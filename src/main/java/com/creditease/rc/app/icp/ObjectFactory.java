
package com.creditease.rc.app.icp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.creditease.rc.app.icp package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetOverdueInfoResponse_QNAME = new QName("http://service.server.ws.icp.creditease.com/", "getOverdueInfoResponse");
    private final static QName _GetOverdueInfo_QNAME = new QName("http://service.server.ws.icp.creditease.com/", "getOverdueInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.creditease.rc.app.icp
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OverdueMicroInfoReq }
     * 
     */
    public OverdueMicroInfoReq createOverdueMicroInfoReq() {
        return new OverdueMicroInfoReq();
    }

    /**
     * Create an instance of {@link GetOverdueInfo }
     * 
     */
    public GetOverdueInfo createGetOverdueInfo() {
        return new GetOverdueInfo();
    }

    /**
     * Create an instance of {@link OverdueMicroInfoRes }
     * 
     */
    public OverdueMicroInfoRes createOverdueMicroInfoRes() {
        return new OverdueMicroInfoRes();
    }

    /**
     * Create an instance of {@link GetOverdueInfoResponse }
     * 
     */
    public GetOverdueInfoResponse createGetOverdueInfoResponse() {
        return new GetOverdueInfoResponse();
    }

    /**
     * Create an instance of {@link OverdueMicroInfoDTO }
     * 
     */
    public OverdueMicroInfoDTO createOverdueMicroInfoDTO() {
        return new OverdueMicroInfoDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOverdueInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.ws.icp.creditease.com/", name = "getOverdueInfoResponse")
    public JAXBElement<GetOverdueInfoResponse> createGetOverdueInfoResponse(GetOverdueInfoResponse value) {
        return new JAXBElement<GetOverdueInfoResponse>(_GetOverdueInfoResponse_QNAME, GetOverdueInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOverdueInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.ws.icp.creditease.com/", name = "getOverdueInfo")
    public JAXBElement<GetOverdueInfo> createGetOverdueInfo(GetOverdueInfo value) {
        return new JAXBElement<GetOverdueInfo>(_GetOverdueInfo_QNAME, GetOverdueInfo.class, null, value);
    }

}
