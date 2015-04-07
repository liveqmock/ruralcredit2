
package com.creditease.rc.app.orgams;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.creditease.rc.app.orgams package. 
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

    private final static QName _BorrowMatchingReq_QNAME = new QName("http://service.tradeplatform.ws.component.creditease.com/", "borrowMatchingReq");
    private final static QName _UnMatchingResponse_QNAME = new QName("http://service.tradeplatform.ws.component.creditease.com/", "unMatchingResponse");
    private final static QName _UnMatching_QNAME = new QName("http://service.tradeplatform.ws.component.creditease.com/", "unMatching");
    private final static QName _MatchConfirmResponse_QNAME = new QName("http://service.tradeplatform.ws.component.creditease.com/", "matchConfirmResponse");
    private final static QName _BorrowMatchingReqResponse_QNAME = new QName("http://service.tradeplatform.ws.component.creditease.com/", "borrowMatchingReqResponse");
    private final static QName _MatchConfirm_QNAME = new QName("http://service.tradeplatform.ws.component.creditease.com/", "matchConfirm");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.creditease.rc.app.orgams
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CashFlowVo }
     * 
     */
    public CashFlowVo createCashFlowVo() {
        return new CashFlowVo();
    }

    /**
     * Create an instance of {@link MatchConfirmReq }
     * 
     */
    public MatchConfirmReq createMatchConfirmReq() {
        return new MatchConfirmReq();
    }

    /**
     * Create an instance of {@link MatchBorrowReqVo }
     * 
     */
    public MatchBorrowReqVo createMatchBorrowReqVo() {
        return new MatchBorrowReqVo();
    }

    /**
     * Create an instance of {@link MatchConfirm }
     * 
     */
    public MatchConfirm createMatchConfirm() {
        return new MatchConfirm();
    }

    /**
     * Create an instance of {@link MapConvertor }
     * 
     */
    public MapConvertor createMapConvertor() {
        return new MapConvertor();
    }

    /**
     * Create an instance of {@link MapEntry }
     * 
     */
    public MapEntry createMapEntry() {
        return new MapEntry();
    }

    /**
     * Create an instance of {@link UnMatching }
     * 
     */
    public UnMatching createUnMatching() {
        return new UnMatching();
    }

    /**
     * Create an instance of {@link BorrowMatchingReqResponse }
     * 
     */
    public BorrowMatchingReqResponse createBorrowMatchingReqResponse() {
        return new BorrowMatchingReqResponse();
    }

    /**
     * Create an instance of {@link MatchBorrowResVo }
     * 
     */
    public MatchBorrowResVo createMatchBorrowResVo() {
        return new MatchBorrowResVo();
    }

    /**
     * Create an instance of {@link UnMatchingResponse }
     * 
     */
    public UnMatchingResponse createUnMatchingResponse() {
        return new UnMatchingResponse();
    }

    /**
     * Create an instance of {@link BaseRequest }
     * 
     */
    public BaseRequest createBaseRequest() {
        return new BaseRequest();
    }

    /**
     * Create an instance of {@link MatchConfirmResponse }
     * 
     */
    public MatchConfirmResponse createMatchConfirmResponse() {
        return new MatchConfirmResponse();
    }

    /**
     * Create an instance of {@link BorrowMatchingReq }
     * 
     */
    public BorrowMatchingReq createBorrowMatchingReq() {
        return new BorrowMatchingReq();
    }

    /**
     * Create an instance of {@link UnMatchingReqVo }
     * 
     */
    public UnMatchingReqVo createUnMatchingReqVo() {
        return new UnMatchingReqVo();
    }

    /**
     * Create an instance of {@link TradeDealVo }
     * 
     */
    public TradeDealVo createTradeDealVo() {
        return new TradeDealVo();
    }

    /**
     * Create an instance of {@link BaseResponse }
     * 
     */
    public BaseResponse createBaseResponse() {
        return new BaseResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BorrowMatchingReq }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.tradeplatform.ws.component.creditease.com/", name = "borrowMatchingReq")
    public JAXBElement<BorrowMatchingReq> createBorrowMatchingReq(BorrowMatchingReq value) {
        return new JAXBElement<BorrowMatchingReq>(_BorrowMatchingReq_QNAME, BorrowMatchingReq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnMatchingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.tradeplatform.ws.component.creditease.com/", name = "unMatchingResponse")
    public JAXBElement<UnMatchingResponse> createUnMatchingResponse(UnMatchingResponse value) {
        return new JAXBElement<UnMatchingResponse>(_UnMatchingResponse_QNAME, UnMatchingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnMatching }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.tradeplatform.ws.component.creditease.com/", name = "unMatching")
    public JAXBElement<UnMatching> createUnMatching(UnMatching value) {
        return new JAXBElement<UnMatching>(_UnMatching_QNAME, UnMatching.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MatchConfirmResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.tradeplatform.ws.component.creditease.com/", name = "matchConfirmResponse")
    public JAXBElement<MatchConfirmResponse> createMatchConfirmResponse(MatchConfirmResponse value) {
        return new JAXBElement<MatchConfirmResponse>(_MatchConfirmResponse_QNAME, MatchConfirmResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BorrowMatchingReqResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.tradeplatform.ws.component.creditease.com/", name = "borrowMatchingReqResponse")
    public JAXBElement<BorrowMatchingReqResponse> createBorrowMatchingReqResponse(BorrowMatchingReqResponse value) {
        return new JAXBElement<BorrowMatchingReqResponse>(_BorrowMatchingReqResponse_QNAME, BorrowMatchingReqResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MatchConfirm }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.tradeplatform.ws.component.creditease.com/", name = "matchConfirm")
    public JAXBElement<MatchConfirm> createMatchConfirm(MatchConfirm value) {
        return new JAXBElement<MatchConfirm>(_MatchConfirm_QNAME, MatchConfirm.class, null, value);
    }

}
