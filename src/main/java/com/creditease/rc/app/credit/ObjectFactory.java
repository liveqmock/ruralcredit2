
package com.creditease.rc.app.credit;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.creditease.rc.app.credit package. 
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

    private final static QName _BankListRequest_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "BankListRequest");
    private final static QName _ReturnAmountRequest_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "ReturnAmountRequest");
    private final static QName _LoanBalanceDataRequest_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "LoanBalanceDataRequest");
    private final static QName _ClientApplyRequest_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "ClientApplyRequest");
    private final static QName _ModifySellerRequest_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "ModifySellerRequest");
    private final static QName _GetHistoryDataRequest_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "getHistoryDataRequest");
    private final static QName _QyReserveResultResponse_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "QyReserveResultResponse");
    private final static QName _ReserveReturnResponse_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "ReserveReturnResponse");
    private final static QName _ChgReturnTypeRequest_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "ChgReturnTypeRequest");
    private final static QName _QyReserveResultRequest_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "QyReserveResultRequest");
    private final static QName _ReturnSchemeRequest_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "ReturnSchemeRequest");
    private final static QName _LoanBalanceDataResponse_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "LoanBalanceDataResponse");
    private final static QName _ClientApplyResponse_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "ClientApplyResponse");
    private final static QName _ReturnAmountResponse_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "ReturnAmountResponse");
    private final static QName _ReturnSchemeResponse_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "ReturnSchemeResponse");
    private final static QName _ChgReturnTypeResponse_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "ChgReturnTypeResponse");
    private final static QName _BankListResponse_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "BankListResponse");
    private final static QName _OverdueInfoResponse_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "OverdueInfoResponse");
    private final static QName _QyClientApplyRequest_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "QyClientApplyRequest");
    private final static QName _GetHistoryDataResponse_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "getHistoryDataResponse");
    private final static QName _ReserveReturnRequest_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "ReserveReturnRequest");
    private final static QName _QyClientApplyResponse_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "QyClientApplyResponse");
    private final static QName _OverdueInfoRequest_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "OverdueInfoRequest");
    private final static QName _ModifySellerResponse_QNAME = new QName("http://www.creditease.cn/RuralBusyService/", "ModifySellerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.creditease.rc.app.credit
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QyapplyListDTO }
     * 
     */
    public QyapplyListDTO createQyapplyListDTO() {
        return new QyapplyListDTO();
    }

    /**
     * Create an instance of {@link ReturnDerateListDTO }
     * 
     */
    public ReturnDerateListDTO createReturnDerateListDTO() {
        return new ReturnDerateListDTO();
    }

    /**
     * Create an instance of {@link LoanBalanceDataResult.ContractList }
     * 
     */
    public LoanBalanceDataResult.ContractList createLoanBalanceDataResultContractList() {
        return new LoanBalanceDataResult.ContractList();
    }

    /**
     * Create an instance of {@link BankListResult }
     * 
     */
    public BankListResult createBankListResult() {
        return new BankListResult();
    }

    /**
     * Create an instance of {@link ApplyIdListDTO.ApplyDateList }
     * 
     */
    public ApplyIdListDTO.ApplyDateList createApplyIdListDTOApplyDateList() {
        return new ApplyIdListDTO.ApplyDateList();
    }

    /**
     * Create an instance of {@link ModifySellerRequest.ApplyList }
     * 
     */
    public ModifySellerRequest.ApplyList createModifySellerRequestApplyList() {
        return new ModifySellerRequest.ApplyList();
    }

    /**
     * Create an instance of {@link AppointPeriodScheduleDTO }
     * 
     */
    public AppointPeriodScheduleDTO createAppointPeriodScheduleDTO() {
        return new AppointPeriodScheduleDTO();
    }

    /**
     * Create an instance of {@link LoanBalanceDataResult }
     * 
     */
    public LoanBalanceDataResult createLoanBalanceDataResult() {
        return new LoanBalanceDataResult();
    }

    /**
     * Create an instance of {@link ClientApplyRequest.PaymentTypeList }
     * 
     */
    public ClientApplyRequest.PaymentTypeList createClientApplyRequestPaymentTypeList() {
        return new ClientApplyRequest.PaymentTypeList();
    }

    /**
     * Create an instance of {@link AppointPeriodScheduleDTO.PeriodChargeList }
     * 
     */
    public AppointPeriodScheduleDTO.PeriodChargeList createAppointPeriodScheduleDTOPeriodChargeList() {
        return new AppointPeriodScheduleDTO.PeriodChargeList();
    }

    /**
     * Create an instance of {@link AppointScheduleDTO.ApsList }
     * 
     */
    public AppointScheduleDTO.ApsList createAppointScheduleDTOApsList() {
        return new AppointScheduleDTO.ApsList();
    }

    /**
     * Create an instance of {@link ClientApplyRequest }
     * 
     */
    public ClientApplyRequest createClientApplyRequest() {
        return new ClientApplyRequest();
    }

    /**
     * Create an instance of {@link ActualPeriodScheduleDTO.OverdueChargeList }
     * 
     */
    public ActualPeriodScheduleDTO.OverdueChargeList createActualPeriodScheduleDTOOverdueChargeList() {
        return new ActualPeriodScheduleDTO.OverdueChargeList();
    }

    /**
     * Create an instance of {@link RSellIdListDTO.ApplyIdList }
     * 
     */
    public RSellIdListDTO.ApplyIdList createRSellIdListDTOApplyIdList() {
        return new RSellIdListDTO.ApplyIdList();
    }

    /**
     * Create an instance of {@link OverdueInfoResult.ApplyList }
     * 
     */
    public OverdueInfoResult.ApplyList createOverdueInfoResultApplyList() {
        return new OverdueInfoResult.ApplyList();
    }

    /**
     * Create an instance of {@link LoanBalanceDataRequest }
     * 
     */
    public LoanBalanceDataRequest createLoanBalanceDataRequest() {
        return new LoanBalanceDataRequest();
    }

    /**
     * Create an instance of {@link AppointChargeInfoDTO }
     * 
     */
    public AppointChargeInfoDTO createAppointChargeInfoDTO() {
        return new AppointChargeInfoDTO();
    }

    /**
     * Create an instance of {@link ModifySellerResponse }
     * 
     */
    public ModifySellerResponse createModifySellerResponse() {
        return new ModifySellerResponse();
    }

    /**
     * Create an instance of {@link ReturnIdListDTO }
     * 
     */
    public ReturnIdListDTO createReturnIdListDTO() {
        return new ReturnIdListDTO();
    }

    /**
     * Create an instance of {@link QyClientApplyResponse.QyapplyList }
     * 
     */
    public QyClientApplyResponse.QyapplyList createQyClientApplyResponseQyapplyList() {
        return new QyClientApplyResponse.QyapplyList();
    }

    /**
     * Create an instance of {@link AllAheadScheduleDTO }
     * 
     */
    public AllAheadScheduleDTO createAllAheadScheduleDTO() {
        return new AllAheadScheduleDTO();
    }

    /**
     * Create an instance of {@link AppointScheduleDTO.TotalOverdueChargeList }
     * 
     */
    public AppointScheduleDTO.TotalOverdueChargeList createAppointScheduleDTOTotalOverdueChargeList() {
        return new AppointScheduleDTO.TotalOverdueChargeList();
    }

    /**
     * Create an instance of {@link ReturnAmountResult.AppointSchedule }
     * 
     */
    public ReturnAmountResult.AppointSchedule createReturnAmountResultAppointSchedule() {
        return new ReturnAmountResult.AppointSchedule();
    }

    /**
     * Create an instance of {@link QyReserveResultRequest.ReserveList }
     * 
     */
    public QyReserveResultRequest.ReserveList createQyReserveResultRequestReserveList() {
        return new QyReserveResultRequest.ReserveList();
    }

    /**
     * Create an instance of {@link ActualChargeInfoDTO }
     * 
     */
    public ActualChargeInfoDTO createActualChargeInfoDTO() {
        return new ActualChargeInfoDTO();
    }

    /**
     * Create an instance of {@link OverdueChargeInfoDTO }
     * 
     */
    public OverdueChargeInfoDTO createOverdueChargeInfoDTO() {
        return new OverdueChargeInfoDTO();
    }

    /**
     * Create an instance of {@link QyReserveResultRequest }
     * 
     */
    public QyReserveResultRequest createQyReserveResultRequest() {
        return new QyReserveResultRequest();
    }

    /**
     * Create an instance of {@link ChargeDiscountInfoDTO }
     * 
     */
    public ChargeDiscountInfoDTO createChargeDiscountInfoDTO() {
        return new ChargeDiscountInfoDTO();
    }

    /**
     * Create an instance of {@link QyReserveResultResponse }
     * 
     */
    public QyReserveResultResponse createQyReserveResultResponse() {
        return new QyReserveResultResponse();
    }

    /**
     * Create an instance of {@link PaymentTypeConfigDTO.PaymentTypeParams }
     * 
     */
    public PaymentTypeConfigDTO.PaymentTypeParams createPaymentTypeConfigDTOPaymentTypeParams() {
        return new PaymentTypeConfigDTO.PaymentTypeParams();
    }

    /**
     * Create an instance of {@link ApplyIdListDTO }
     * 
     */
    public ApplyIdListDTO createApplyIdListDTO() {
        return new ApplyIdListDTO();
    }

    /**
     * Create an instance of {@link ReturnSchemeResult.ApsList }
     * 
     */
    public ReturnSchemeResult.ApsList createReturnSchemeResultApsList() {
        return new ReturnSchemeResult.ApsList();
    }

    /**
     * Create an instance of {@link OverdueInfoResponse }
     * 
     */
    public OverdueInfoResponse createOverdueInfoResponse() {
        return new OverdueInfoResponse();
    }

    /**
     * Create an instance of {@link ActualPeriodScheduleDTO.PeriodChargeList }
     * 
     */
    public ActualPeriodScheduleDTO.PeriodChargeList createActualPeriodScheduleDTOPeriodChargeList() {
        return new ActualPeriodScheduleDTO.PeriodChargeList();
    }

    /**
     * Create an instance of {@link ReturnAmountResponse }
     * 
     */
    public ReturnAmountResponse createReturnAmountResponse() {
        return new ReturnAmountResponse();
    }

    /**
     * Create an instance of {@link ClientApplyRequest.ContactList }
     * 
     */
    public ClientApplyRequest.ContactList createClientApplyRequestContactList() {
        return new ClientApplyRequest.ContactList();
    }

    /**
     * Create an instance of {@link ReserveListDTO }
     * 
     */
    public ReserveListDTO createReserveListDTO() {
        return new ReserveListDTO();
    }

    /**
     * Create an instance of {@link ApplyDateListDTO }
     * 
     */
    public ApplyDateListDTO createApplyDateListDTO() {
        return new ApplyDateListDTO();
    }

    /**
     * Create an instance of {@link ApplyListDTO }
     * 
     */
    public ApplyListDTO createApplyListDTO() {
        return new ApplyListDTO();
    }

    /**
     * Create an instance of {@link ApplyInfoListDTO.ContactInfoList }
     * 
     */
    public ApplyInfoListDTO.ContactInfoList createApplyInfoListDTOContactInfoList() {
        return new ApplyInfoListDTO.ContactInfoList();
    }

    /**
     * Create an instance of {@link ClientApplyRequest.FrontChargeDiscountList }
     * 
     */
    public ClientApplyRequest.FrontChargeDiscountList createClientApplyRequestFrontChargeDiscountList() {
        return new ClientApplyRequest.FrontChargeDiscountList();
    }

    /**
     * Create an instance of {@link LoanBalanceDataResponse }
     * 
     */
    public LoanBalanceDataResponse createLoanBalanceDataResponse() {
        return new LoanBalanceDataResponse();
    }

    /**
     * Create an instance of {@link ReturnSchemeResult }
     * 
     */
    public ReturnSchemeResult createReturnSchemeResult() {
        return new ReturnSchemeResult();
    }

    /**
     * Create an instance of {@link GetHistoryDataRequest }
     * 
     */
    public GetHistoryDataRequest createGetHistoryDataRequest() {
        return new GetHistoryDataRequest();
    }

    /**
     * Create an instance of {@link ApplyInfoListDTO }
     * 
     */
    public ApplyInfoListDTO createApplyInfoListDTO() {
        return new ApplyInfoListDTO();
    }

    /**
     * Create an instance of {@link BankListRequest }
     * 
     */
    public BankListRequest createBankListRequest() {
        return new BankListRequest();
    }

    /**
     * Create an instance of {@link WSResult }
     * 
     */
    public WSResult createWSResult() {
        return new WSResult();
    }

    /**
     * Create an instance of {@link AppointScheduleDTO.TotalPeriodChargeList }
     * 
     */
    public AppointScheduleDTO.TotalPeriodChargeList createAppointScheduleDTOTotalPeriodChargeList() {
        return new AppointScheduleDTO.TotalPeriodChargeList();
    }

    /**
     * Create an instance of {@link ModifySellerRequest }
     * 
     */
    public ModifySellerRequest createModifySellerRequest() {
        return new ModifySellerRequest();
    }

    /**
     * Create an instance of {@link QyReserveResult }
     * 
     */
    public QyReserveResult createQyReserveResult() {
        return new QyReserveResult();
    }

    /**
     * Create an instance of {@link PaymentTypeParamDTO }
     * 
     */
    public PaymentTypeParamDTO createPaymentTypeParamDTO() {
        return new PaymentTypeParamDTO();
    }

    /**
     * Create an instance of {@link RSellIdListDTO }
     * 
     */
    public RSellIdListDTO createRSellIdListDTO() {
        return new RSellIdListDTO();
    }

    /**
     * Create an instance of {@link ReturnAmountResult }
     * 
     */
    public ReturnAmountResult createReturnAmountResult() {
        return new ReturnAmountResult();
    }

    /**
     * Create an instance of {@link ReserveReturnResponse.ReturnIdList }
     * 
     */
    public ReserveReturnResponse.ReturnIdList createReserveReturnResponseReturnIdList() {
        return new ReserveReturnResponse.ReturnIdList();
    }

    /**
     * Create an instance of {@link ReturnListDTO }
     * 
     */
    public ReturnListDTO createReturnListDTO() {
        return new ReturnListDTO();
    }

    /**
     * Create an instance of {@link ContactListDTO }
     * 
     */
    public ContactListDTO createContactListDTO() {
        return new ContactListDTO();
    }

    /**
     * Create an instance of {@link ReserveReturnResponse }
     * 
     */
    public ReserveReturnResponse createReserveReturnResponse() {
        return new ReserveReturnResponse();
    }

    /**
     * Create an instance of {@link ReturnSchemeResponse }
     * 
     */
    public ReturnSchemeResponse createReturnSchemeResponse() {
        return new ReturnSchemeResponse();
    }

    /**
     * Create an instance of {@link PaymentTypeConfigDTO }
     * 
     */
    public PaymentTypeConfigDTO createPaymentTypeConfigDTO() {
        return new PaymentTypeConfigDTO();
    }

    /**
     * Create an instance of {@link ApplyListsDTO.RSellIdList }
     * 
     */
    public ApplyListsDTO.RSellIdList createApplyListsDTORSellIdList() {
        return new ApplyListsDTO.RSellIdList();
    }

    /**
     * Create an instance of {@link BankListResponse }
     * 
     */
    public BankListResponse createBankListResponse() {
        return new BankListResponse();
    }

    /**
     * Create an instance of {@link ActualPeriodScheduleDTO }
     * 
     */
    public ActualPeriodScheduleDTO createActualPeriodScheduleDTO() {
        return new ActualPeriodScheduleDTO();
    }

    /**
     * Create an instance of {@link ContactInfoListDTO }
     * 
     */
    public ContactInfoListDTO createContactInfoListDTO() {
        return new ContactInfoListDTO();
    }

    /**
     * Create an instance of {@link ReserveIdListDTO }
     * 
     */
    public ReserveIdListDTO createReserveIdListDTO() {
        return new ReserveIdListDTO();
    }

    /**
     * Create an instance of {@link ReturnAmountResult.AllAheadSchedule }
     * 
     */
    public ReturnAmountResult.AllAheadSchedule createReturnAmountResultAllAheadSchedule() {
        return new ReturnAmountResult.AllAheadSchedule();
    }

    /**
     * Create an instance of {@link ReturnAmountRequest }
     * 
     */
    public ReturnAmountRequest createReturnAmountRequest() {
        return new ReturnAmountRequest();
    }

    /**
     * Create an instance of {@link OverdueInfoResult }
     * 
     */
    public OverdueInfoResult createOverdueInfoResult() {
        return new OverdueInfoResult();
    }

    /**
     * Create an instance of {@link BankListResult.BankList }
     * 
     */
    public BankListResult.BankList createBankListResultBankList() {
        return new BankListResult.BankList();
    }

    /**
     * Create an instance of {@link ReturnSchemeRequest }
     * 
     */
    public ReturnSchemeRequest createReturnSchemeRequest() {
        return new ReturnSchemeRequest();
    }

    /**
     * Create an instance of {@link QycontactListDTO }
     * 
     */
    public QycontactListDTO createQycontactListDTO() {
        return new QycontactListDTO();
    }

    /**
     * Create an instance of {@link ApplyListsDTO }
     * 
     */
    public ApplyListsDTO createApplyListsDTO() {
        return new ApplyListsDTO();
    }

    /**
     * Create an instance of {@link ReserveReturnRequest }
     * 
     */
    public ReserveReturnRequest createReserveReturnRequest() {
        return new ReserveReturnRequest();
    }

    /**
     * Create an instance of {@link OfficeIdListDTO.SellIdList }
     * 
     */
    public OfficeIdListDTO.SellIdList createOfficeIdListDTOSellIdList() {
        return new OfficeIdListDTO.SellIdList();
    }

    /**
     * Create an instance of {@link AppointScheduleDTO }
     * 
     */
    public AppointScheduleDTO createAppointScheduleDTO() {
        return new AppointScheduleDTO();
    }

    /**
     * Create an instance of {@link OverdueInfoRequest.OfficeIdList }
     * 
     */
    public OverdueInfoRequest.OfficeIdList createOverdueInfoRequestOfficeIdList() {
        return new OverdueInfoRequest.OfficeIdList();
    }

    /**
     * Create an instance of {@link ChgReturnTypeResponse }
     * 
     */
    public ChgReturnTypeResponse createChgReturnTypeResponse() {
        return new ChgReturnTypeResponse();
    }

    /**
     * Create an instance of {@link BankListDTO }
     * 
     */
    public BankListDTO createBankListDTO() {
        return new BankListDTO();
    }

    /**
     * Create an instance of {@link ReturnObj }
     * 
     */
    public ReturnObj createReturnObj() {
        return new ReturnObj();
    }

    /**
     * Create an instance of {@link OfficeIdListDTO }
     * 
     */
    public OfficeIdListDTO createOfficeIdListDTO() {
        return new OfficeIdListDTO();
    }

    /**
     * Create an instance of {@link ClientApplyResponse }
     * 
     */
    public ClientApplyResponse createClientApplyResponse() {
        return new ClientApplyResponse();
    }

    /**
     * Create an instance of {@link ListDTO }
     * 
     */
    public ListDTO createListDTO() {
        return new ListDTO();
    }

    /**
     * Create an instance of {@link ModifySellerResponse.ApplyInfoList }
     * 
     */
    public ModifySellerResponse.ApplyInfoList createModifySellerResponseApplyInfoList() {
        return new ModifySellerResponse.ApplyInfoList();
    }

    /**
     * Create an instance of {@link QyClientApplyResponse }
     * 
     */
    public QyClientApplyResponse createQyClientApplyResponse() {
        return new QyClientApplyResponse();
    }

    /**
     * Create an instance of {@link GetHistoryDataResponse }
     * 
     */
    public GetHistoryDataResponse createGetHistoryDataResponse() {
        return new GetHistoryDataResponse();
    }

    /**
     * Create an instance of {@link AppointPeriodScheduleDTO.OverdueChargeList }
     * 
     */
    public AppointPeriodScheduleDTO.OverdueChargeList createAppointPeriodScheduleDTOOverdueChargeList() {
        return new AppointPeriodScheduleDTO.OverdueChargeList();
    }

    /**
     * Create an instance of {@link QyReserveResult.ReserveList }
     * 
     */
    public QyReserveResult.ReserveList createQyReserveResultReserveList() {
        return new QyReserveResult.ReserveList();
    }

    /**
     * Create an instance of {@link ChgReturnTypeRequest }
     * 
     */
    public ChgReturnTypeRequest createChgReturnTypeRequest() {
        return new ChgReturnTypeRequest();
    }

    /**
     * Create an instance of {@link QyClientApplyRequest }
     * 
     */
    public QyClientApplyRequest createQyClientApplyRequest() {
        return new QyClientApplyRequest();
    }

    /**
     * Create an instance of {@link ReserveReturnRequest.ReturnList }
     * 
     */
    public ReserveReturnRequest.ReturnList createReserveReturnRequestReturnList() {
        return new ReserveReturnRequest.ReturnList();
    }

    /**
     * Create an instance of {@link QyOverdueListObj }
     * 
     */
    public QyOverdueListObj createQyOverdueListObj() {
        return new QyOverdueListObj();
    }

    /**
     * Create an instance of {@link ClientApplyRequest.PeriodChargeDiscountList }
     * 
     */
    public ClientApplyRequest.PeriodChargeDiscountList createClientApplyRequestPeriodChargeDiscountList() {
        return new ClientApplyRequest.PeriodChargeDiscountList();
    }

    /**
     * Create an instance of {@link ReturnSchemeResult.ReturnDerateList }
     * 
     */
    public ReturnSchemeResult.ReturnDerateList createReturnSchemeResultReturnDerateList() {
        return new ReturnSchemeResult.ReturnDerateList();
    }

    /**
     * Create an instance of {@link QyapplyListDTO.QycontactList }
     * 
     */
    public QyapplyListDTO.QycontactList createQyapplyListDTOQycontactList() {
        return new QyapplyListDTO.QycontactList();
    }

    /**
     * Create an instance of {@link ContractObj.QyOverdueList }
     * 
     */
    public ContractObj.QyOverdueList createContractObjQyOverdueList() {
        return new ContractObj.QyOverdueList();
    }

    /**
     * Create an instance of {@link SellIdListDTO }
     * 
     */
    public SellIdListDTO createSellIdListDTO() {
        return new SellIdListDTO();
    }

    /**
     * Create an instance of {@link ContractObj }
     * 
     */
    public ContractObj createContractObj() {
        return new ContractObj();
    }

    /**
     * Create an instance of {@link OverdueInfoRequest }
     * 
     */
    public OverdueInfoRequest createOverdueInfoRequest() {
        return new OverdueInfoRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BankListRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "BankListRequest")
    public JAXBElement<BankListRequest> createBankListRequest(BankListRequest value) {
        return new JAXBElement<BankListRequest>(_BankListRequest_QNAME, BankListRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnAmountRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "ReturnAmountRequest")
    public JAXBElement<ReturnAmountRequest> createReturnAmountRequest(ReturnAmountRequest value) {
        return new JAXBElement<ReturnAmountRequest>(_ReturnAmountRequest_QNAME, ReturnAmountRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoanBalanceDataRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "LoanBalanceDataRequest")
    public JAXBElement<LoanBalanceDataRequest> createLoanBalanceDataRequest(LoanBalanceDataRequest value) {
        return new JAXBElement<LoanBalanceDataRequest>(_LoanBalanceDataRequest_QNAME, LoanBalanceDataRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClientApplyRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "ClientApplyRequest")
    public JAXBElement<ClientApplyRequest> createClientApplyRequest(ClientApplyRequest value) {
        return new JAXBElement<ClientApplyRequest>(_ClientApplyRequest_QNAME, ClientApplyRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifySellerRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "ModifySellerRequest")
    public JAXBElement<ModifySellerRequest> createModifySellerRequest(ModifySellerRequest value) {
        return new JAXBElement<ModifySellerRequest>(_ModifySellerRequest_QNAME, ModifySellerRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHistoryDataRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "getHistoryDataRequest")
    public JAXBElement<GetHistoryDataRequest> createGetHistoryDataRequest(GetHistoryDataRequest value) {
        return new JAXBElement<GetHistoryDataRequest>(_GetHistoryDataRequest_QNAME, GetHistoryDataRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QyReserveResultResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "QyReserveResultResponse")
    public JAXBElement<QyReserveResultResponse> createQyReserveResultResponse(QyReserveResultResponse value) {
        return new JAXBElement<QyReserveResultResponse>(_QyReserveResultResponse_QNAME, QyReserveResultResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReserveReturnResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "ReserveReturnResponse")
    public JAXBElement<ReserveReturnResponse> createReserveReturnResponse(ReserveReturnResponse value) {
        return new JAXBElement<ReserveReturnResponse>(_ReserveReturnResponse_QNAME, ReserveReturnResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChgReturnTypeRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "ChgReturnTypeRequest")
    public JAXBElement<ChgReturnTypeRequest> createChgReturnTypeRequest(ChgReturnTypeRequest value) {
        return new JAXBElement<ChgReturnTypeRequest>(_ChgReturnTypeRequest_QNAME, ChgReturnTypeRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QyReserveResultRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "QyReserveResultRequest")
    public JAXBElement<QyReserveResultRequest> createQyReserveResultRequest(QyReserveResultRequest value) {
        return new JAXBElement<QyReserveResultRequest>(_QyReserveResultRequest_QNAME, QyReserveResultRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnSchemeRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "ReturnSchemeRequest")
    public JAXBElement<ReturnSchemeRequest> createReturnSchemeRequest(ReturnSchemeRequest value) {
        return new JAXBElement<ReturnSchemeRequest>(_ReturnSchemeRequest_QNAME, ReturnSchemeRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoanBalanceDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "LoanBalanceDataResponse")
    public JAXBElement<LoanBalanceDataResponse> createLoanBalanceDataResponse(LoanBalanceDataResponse value) {
        return new JAXBElement<LoanBalanceDataResponse>(_LoanBalanceDataResponse_QNAME, LoanBalanceDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClientApplyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "ClientApplyResponse")
    public JAXBElement<ClientApplyResponse> createClientApplyResponse(ClientApplyResponse value) {
        return new JAXBElement<ClientApplyResponse>(_ClientApplyResponse_QNAME, ClientApplyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnAmountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "ReturnAmountResponse")
    public JAXBElement<ReturnAmountResponse> createReturnAmountResponse(ReturnAmountResponse value) {
        return new JAXBElement<ReturnAmountResponse>(_ReturnAmountResponse_QNAME, ReturnAmountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnSchemeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "ReturnSchemeResponse")
    public JAXBElement<ReturnSchemeResponse> createReturnSchemeResponse(ReturnSchemeResponse value) {
        return new JAXBElement<ReturnSchemeResponse>(_ReturnSchemeResponse_QNAME, ReturnSchemeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChgReturnTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "ChgReturnTypeResponse")
    public JAXBElement<ChgReturnTypeResponse> createChgReturnTypeResponse(ChgReturnTypeResponse value) {
        return new JAXBElement<ChgReturnTypeResponse>(_ChgReturnTypeResponse_QNAME, ChgReturnTypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BankListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "BankListResponse")
    public JAXBElement<BankListResponse> createBankListResponse(BankListResponse value) {
        return new JAXBElement<BankListResponse>(_BankListResponse_QNAME, BankListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OverdueInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "OverdueInfoResponse")
    public JAXBElement<OverdueInfoResponse> createOverdueInfoResponse(OverdueInfoResponse value) {
        return new JAXBElement<OverdueInfoResponse>(_OverdueInfoResponse_QNAME, OverdueInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QyClientApplyRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "QyClientApplyRequest")
    public JAXBElement<QyClientApplyRequest> createQyClientApplyRequest(QyClientApplyRequest value) {
        return new JAXBElement<QyClientApplyRequest>(_QyClientApplyRequest_QNAME, QyClientApplyRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHistoryDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "getHistoryDataResponse")
    public JAXBElement<GetHistoryDataResponse> createGetHistoryDataResponse(GetHistoryDataResponse value) {
        return new JAXBElement<GetHistoryDataResponse>(_GetHistoryDataResponse_QNAME, GetHistoryDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReserveReturnRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "ReserveReturnRequest")
    public JAXBElement<ReserveReturnRequest> createReserveReturnRequest(ReserveReturnRequest value) {
        return new JAXBElement<ReserveReturnRequest>(_ReserveReturnRequest_QNAME, ReserveReturnRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QyClientApplyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "QyClientApplyResponse")
    public JAXBElement<QyClientApplyResponse> createQyClientApplyResponse(QyClientApplyResponse value) {
        return new JAXBElement<QyClientApplyResponse>(_QyClientApplyResponse_QNAME, QyClientApplyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OverdueInfoRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "OverdueInfoRequest")
    public JAXBElement<OverdueInfoRequest> createOverdueInfoRequest(OverdueInfoRequest value) {
        return new JAXBElement<OverdueInfoRequest>(_OverdueInfoRequest_QNAME, OverdueInfoRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifySellerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.creditease.cn/RuralBusyService/", name = "ModifySellerResponse")
    public JAXBElement<ModifySellerResponse> createModifySellerResponse(ModifySellerResponse value) {
        return new JAXBElement<ModifySellerResponse>(_ModifySellerResponse_QNAME, ModifySellerResponse.class, null, value);
    }

}
