
package com.creditease.rc.app.pdf;

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
@WebService(name = "ceBorrowingProductsWS", targetNamespace = "http://www.creditease.cn")
public interface CeBorrowingProductsWS {


    /**
     * 
     * @param exhibtionCalcReq
     * @return
     *     returns com.creditease.rc.app.pdf.ExhibtionCalcResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ceExhibtionCalc", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.CeExhibtionCalc")
    @ResponseWrapper(localName = "ceExhibtionCalcResponse", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.CeExhibtionCalcResponse")
    public ExhibtionCalcResult ceExhibtionCalc(
        @WebParam(name = "exhibtionCalcReq", targetNamespace = "")
        ExhibtionCalcReq exhibtionCalcReq);

    /**
     * 
     * @param overdueChargeReq
     * @return
     *     returns com.creditease.rc.app.pdf.OverdueChargeReqResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ceOverdueChargeReq", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.CeOverdueChargeReq")
    @ResponseWrapper(localName = "ceOverdueChargeReqResponse", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.CeOverdueChargeReqResponse")
    public OverdueChargeReqResult ceOverdueChargeReq(
        @WebParam(name = "overdueChargeReq", targetNamespace = "")
        OverdueChargeReq overdueChargeReq);

    /**
     * 
     * @param proListParam
     * @return
     *     returns com.creditease.rc.app.pdf.QueryProByDepartResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "queryProByDepart", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.QueryProByDepart")
    @ResponseWrapper(localName = "queryProByDepartResponse", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.QueryProByDepartResponse")
    public QueryProByDepartResult queryProByDepart(
        @WebParam(name = "proListParam", targetNamespace = "")
        ProListParam proListParam);

    /**
     * 
     * @param contractReqParam
     * @return
     *     returns com.creditease.rc.app.pdf.AgreementDownloadResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ceAgreementDownloadReq", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.CeAgreementDownloadReq")
    @ResponseWrapper(localName = "ceAgreementDownloadReqResponse", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.CeAgreementDownloadReqResponse")
    public AgreementDownloadResult ceAgreementDownloadReq(
        @WebParam(name = "contractReqParam", targetNamespace = "")
        ContractReqParam contractReqParam);

    /**
     * 
     * @param paymentTypeCalcReq
     * @return
     *     returns com.creditease.rc.app.pdf.PaymentTypeCalcResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "cePaymentTypeCalc", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.CePaymentTypeCalc")
    @ResponseWrapper(localName = "cePaymentTypeCalcResponse", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.CePaymentTypeCalcResponse")
    public PaymentTypeCalcResult cePaymentTypeCalc(
        @WebParam(name = "paymentTypeCalcReq", targetNamespace = "")
        PaymentTypeCalcReq paymentTypeCalcReq);

    /**
     * 
     * @param bc
     * @return
     *     returns com.creditease.rc.app.pdf.BatchPicture
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "batchEncodeQRCode", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.BatchEncodeQRCode")
    @ResponseWrapper(localName = "batchEncodeQRCodeResponse", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.BatchEncodeQRCodeResponse")
    public BatchPicture batchEncodeQRCode(
        @WebParam(name = "bc", targetNamespace = "")
        BatchContentReq bc);

    /**
     * 
     * @param repaymentPlanReq
     * @return
     *     returns com.creditease.rc.app.pdf.RepaymentPlanReqResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ceRepaymentPlanReq", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.CeRepaymentPlanReq")
    @ResponseWrapper(localName = "ceRepaymentPlanReqResponse", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.CeRepaymentPlanReqResponse")
    public RepaymentPlanReqResult ceRepaymentPlanReq(
        @WebParam(name = "repaymentPlanReq", targetNamespace = "")
        RepaymentPlanReq repaymentPlanReq);

    /**
     * 
     * @return
     *     returns com.creditease.rc.app.pdf.QueryProInfoResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "queryProducts", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.QueryProducts")
    @ResponseWrapper(localName = "queryProductsResponse", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.QueryProductsResponse")
    public QueryProInfoResult queryProducts();

    /**
     * 
     * @param regionSign
     * @param url
     * @return
     *     returns com.creditease.rc.app.pdf.Content
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "decodeQRCode", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.DecodeQRCode")
    @ResponseWrapper(localName = "decodeQRCodeResponse", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.DecodeQRCodeResponse")
    public Content decodeQRCode(
        @WebParam(name = "url", targetNamespace = "")
        String url,
        @WebParam(name = "regionSign", targetNamespace = "")
        String regionSign);

    /**
     * 
     * @param content
     * @return
     *     returns com.creditease.rc.app.pdf.Picture
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "encodeQRCode", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.EncodeQRCode")
    @ResponseWrapper(localName = "encodeQRCodeResponse", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.EncodeQRCodeResponse")
    public Picture encodeQRCode(
        @WebParam(name = "content", targetNamespace = "")
        String content);

    /**
     * 
     * @param p2PContractReqParam
     * @return
     *     returns com.creditease.rc.app.pdf.AgreementDownloadResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ceP2PAgreementDownloadReq", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.CeP2PAgreementDownloadReq")
    @ResponseWrapper(localName = "ceP2PAgreementDownloadReqResponse", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.CeP2PAgreementDownloadReqResponse")
    public AgreementDownloadResult ceP2PAgreementDownloadReq(
        @WebParam(name = "p2PContractReqParam", targetNamespace = "")
        P2PContractReqParam p2PContractReqParam);

    /**
     * 
     * @param paymentAmountReq
     * @return
     *     returns com.creditease.rc.app.pdf.PaymentAmountReqResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "cePaymentAmountReq", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.CePaymentAmountReq")
    @ResponseWrapper(localName = "cePaymentAmountReqResponse", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.CePaymentAmountReqResponse")
    public PaymentAmountReqResult cePaymentAmountReq(
        @WebParam(name = "paymentAmountReq", targetNamespace = "")
        PaymentAmountReq paymentAmountReq);

    /**
     * 
     * @param bp
     * @return
     *     returns com.creditease.rc.app.pdf.BatchContent
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "batchDecodeQRCode", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.BatchDecodeQRCode")
    @ResponseWrapper(localName = "batchDecodeQRCodeResponse", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.BatchDecodeQRCodeResponse")
    public BatchContent batchDecodeQRCode(
        @WebParam(name = "bp", targetNamespace = "")
        BatchPictureReq bp);

    /**
     * 
     * @param rateReqParam
     * @return
     *     returns com.creditease.rc.app.pdf.RateReqResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ceCalculatedBorrowingRateReq", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.CeCalculatedBorrowingRateReq")
    @ResponseWrapper(localName = "ceCalculatedBorrowingRateReqResponse", targetNamespace = "http://www.creditease.cn", className = "com.creditease.rc.app.pdf.CeCalculatedBorrowingRateReqResponse")
    public RateReqResult ceCalculatedBorrowingRateReq(
        @WebParam(name = "rateReqParam", targetNamespace = "")
        RateReqParam rateReqParam);

}