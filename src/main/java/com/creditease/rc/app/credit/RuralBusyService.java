
package com.creditease.rc.app.credit;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "RuralBusyService", targetNamespace = "http://www.creditease.cn/RuralBusyService/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface RuralBusyService {


    /**
     * 
     * @param parameters
     * @return
     *     returns com.creditease.rc.app.credit.ClientApplyResponse
     */
    @WebMethod
    @WebResult(name = "ClientApplyResponse", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
    public ClientApplyResponse clientApply(
        @WebParam(name = "ClientApplyRequest", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
        ClientApplyRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.creditease.rc.app.credit.ReturnSchemeResponse
     */
    @WebMethod
    @WebResult(name = "ReturnSchemeResponse", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
    public ReturnSchemeResponse returnScheme(
        @WebParam(name = "ReturnSchemeRequest", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
        ReturnSchemeRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.creditease.rc.app.credit.OverdueInfoResponse
     */
    @WebMethod
    @WebResult(name = "OverdueInfoResponse", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
    public OverdueInfoResponse overdueInfo(
        @WebParam(name = "OverdueInfoRequest", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
        OverdueInfoRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.creditease.rc.app.credit.ReturnAmountResponse
     */
    @WebMethod
    @WebResult(name = "ReturnAmountResponse", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
    public ReturnAmountResponse returnAmount(
        @WebParam(name = "ReturnAmountRequest", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
        ReturnAmountRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.creditease.rc.app.credit.ReserveReturnResponse
     */
    @WebMethod
    @WebResult(name = "ReserveReturnResponse", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
    public ReserveReturnResponse reserveReturn(
        @WebParam(name = "ReserveReturnRequest", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
        ReserveReturnRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.creditease.rc.app.credit.ModifySellerResponse
     */
    @WebMethod
    @WebResult(name = "ModifySellerResponse", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
    public ModifySellerResponse modifySeller(
        @WebParam(name = "ModifySellerRequest", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
        ModifySellerRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.creditease.rc.app.credit.ChgReturnTypeResponse
     */
    @WebMethod
    @WebResult(name = "ChgReturnTypeResponse", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
    public ChgReturnTypeResponse chgReturnType(
        @WebParam(name = "ChgReturnTypeRequest", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
        ChgReturnTypeRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.creditease.rc.app.credit.QyReserveResultResponse
     */
    @WebMethod
    @WebResult(name = "QyReserveResultResponse", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
    public QyReserveResultResponse qyReserveSearch(
        @WebParam(name = "QyReserveResultRequest", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
        QyReserveResultRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.creditease.rc.app.credit.QyClientApplyResponse
     */
    @WebMethod
    @WebResult(name = "QyClientApplyResponse", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
    public QyClientApplyResponse qyClientApply(
        @WebParam(name = "QyClientApplyRequest", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
        QyClientApplyRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.creditease.rc.app.credit.BankListResponse
     */
    @WebMethod(operationName = "BankList")
    @WebResult(name = "BankListResponse", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
    public BankListResponse bankList(
        @WebParam(name = "BankListRequest", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
        BankListRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.creditease.rc.app.credit.GetHistoryDataResponse
     */
    @WebMethod
    @WebResult(name = "getHistoryDataResponse", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
    public GetHistoryDataResponse getHistoryData(
        @WebParam(name = "getHistoryDataRequest", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
        GetHistoryDataRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.creditease.rc.app.credit.LoanBalanceDataResponse
     */
    @WebMethod
    @WebResult(name = "LoanBalanceDataResponse", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
    public LoanBalanceDataResponse loanBalanceData(
        @WebParam(name = "LoanBalanceDataRequest", targetNamespace = "http://www.creditease.cn/RuralBusyService/", partName = "parameters")
        LoanBalanceDataRequest parameters);

}
