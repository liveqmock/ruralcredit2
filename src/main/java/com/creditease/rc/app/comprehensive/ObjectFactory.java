
package com.creditease.rc.app.comprehensive;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.creditease.rc.app.comprehensive package. 
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

    private final static QName _ChangeProposerIdNotice_QNAME = new QName("http://service.server.ws.icp.creditease.com/", "changeProposerIdNotice");
    private final static QName _ChangeProposerIdNoticeResponse_QNAME = new QName("http://service.server.ws.icp.creditease.com/", "changeProposerIdNoticeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.creditease.rc.app.comprehensive
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DerateProposerIdInfoNoticeDTO }
     * 
     */
    public DerateProposerIdInfoNoticeDTO createDerateProposerIdInfoNoticeDTO() {
        return new DerateProposerIdInfoNoticeDTO();
    }

    /**
     * Create an instance of {@link DerateProposerIdInfoNoticeRes }
     * 
     */
    public DerateProposerIdInfoNoticeRes createDerateProposerIdInfoNoticeRes() {
        return new DerateProposerIdInfoNoticeRes();
    }

    /**
     * Create an instance of {@link ChangeProposerIdNoticeResponse }
     * 
     */
    public ChangeProposerIdNoticeResponse createChangeProposerIdNoticeResponse() {
        return new ChangeProposerIdNoticeResponse();
    }

    /**
     * Create an instance of {@link DerateProposerIdInfoNoticeReq }
     * 
     */
    public DerateProposerIdInfoNoticeReq createDerateProposerIdInfoNoticeReq() {
        return new DerateProposerIdInfoNoticeReq();
    }

    /**
     * Create an instance of {@link ChangeProposerIdNotice }
     * 
     */
    public ChangeProposerIdNotice createChangeProposerIdNotice() {
        return new ChangeProposerIdNotice();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeProposerIdNotice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.ws.icp.creditease.com/", name = "changeProposerIdNotice")
    public JAXBElement<ChangeProposerIdNotice> createChangeProposerIdNotice(ChangeProposerIdNotice value) {
        return new JAXBElement<ChangeProposerIdNotice>(_ChangeProposerIdNotice_QNAME, ChangeProposerIdNotice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeProposerIdNoticeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.ws.icp.creditease.com/", name = "changeProposerIdNoticeResponse")
    public JAXBElement<ChangeProposerIdNoticeResponse> createChangeProposerIdNoticeResponse(ChangeProposerIdNoticeResponse value) {
        return new JAXBElement<ChangeProposerIdNoticeResponse>(_ChangeProposerIdNoticeResponse_QNAME, ChangeProposerIdNoticeResponse.class, null, value);
    }

}
