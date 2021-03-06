
package com.creditease.rc.app.orgams;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "MatchWebService", targetNamespace = "http://service.tradeplatform.ws.component.creditease.com/")
public interface MatchWebService {


    /**
     * 
     * @param request
     * @return
     *     returns com.creditease.rc.app.orgams.BaseResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "unMatching", targetNamespace = "http://service.tradeplatform.ws.component.creditease.com/", className = "com.creditease.rc.app.orgams.UnMatching")
    @ResponseWrapper(localName = "unMatchingResponse", targetNamespace = "http://service.tradeplatform.ws.component.creditease.com/", className = "com.creditease.rc.app.orgams.UnMatchingResponse")
    public BaseResponse unMatching(
        @WebParam(name = "request", targetNamespace = "")
        UnMatchingReqVo request);

    /**
     * 
     * @param request
     * @return
     *     returns com.creditease.rc.app.orgams.MatchBorrowResVo
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "borrowMatchingReq", targetNamespace = "http://service.tradeplatform.ws.component.creditease.com/", className = "com.creditease.rc.app.orgams.BorrowMatchingReq")
    @ResponseWrapper(localName = "borrowMatchingReqResponse", targetNamespace = "http://service.tradeplatform.ws.component.creditease.com/", className = "com.creditease.rc.app.orgams.BorrowMatchingReqResponse")
    public MatchBorrowResVo borrowMatchingReq(
        @WebParam(name = "request", targetNamespace = "")
        MatchBorrowReqVo request);

    /**
     * 
     * @param request
     * @return
     *     returns com.creditease.rc.app.orgams.BaseResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "matchConfirm", targetNamespace = "http://service.tradeplatform.ws.component.creditease.com/", className = "com.creditease.rc.app.orgams.MatchConfirm")
    @ResponseWrapper(localName = "matchConfirmResponse", targetNamespace = "http://service.tradeplatform.ws.component.creditease.com/", className = "com.creditease.rc.app.orgams.MatchConfirmResponse")
    public BaseResponse matchConfirm(
        @WebParam(name = "request", targetNamespace = "")
        MatchConfirmReq request);

}
