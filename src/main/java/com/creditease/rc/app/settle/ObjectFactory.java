package com.creditease.rc.app.settle;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.creditease.rc.app.settle package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _BatchPaymentResultQuery_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"batchPaymentResultQuery");
	private final static QName _SetReceiptFlowBizMgrResponse_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"setReceiptFlowBizMgrResponse");
	private final static QName _SingleRtReceipt_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"singleRtReceipt");
	private final static QName _SingleReceiptResultQuery_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"singleReceiptResultQuery");
	private final static QName _SetReceiptBillBizMgrResponse_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"setReceiptBillBizMgrResponse");
	private final static QName _SingleUndo_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"singleUndo");
	private final static QName _SingleRtReceiptResponse_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"singleRtReceiptResponse");
	private final static QName _BatchPaymentResultQueryResponse_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"batchPaymentResultQueryResponse");
	private final static QName _SinglePaymentResultQuery_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"singlePaymentResultQuery");
	private final static QName _SingleReceiptResultQueryResponse_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"singleReceiptResultQueryResponse");
	private final static QName _SetReceiptFlowBizMgr_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"setReceiptFlowBizMgr");
	private final static QName _BatchReceipt_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"batchReceipt");
	private final static QName _BatchPaymentResponse_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"batchPaymentResponse");
	private final static QName _BatchReceiptResultQuery_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"batchReceiptResultQuery");
	private final static QName _ModifyPaymentBill_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"modifyPaymentBill");
	private final static QName _SinglePaymentResultQueryResponse_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"singlePaymentResultQueryResponse");
	private final static QName _BatchReceiptResultQueryResponse_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"batchReceiptResultQueryResponse");
	private final static QName _ModifyReceiptBillResponse_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"modifyReceiptBillResponse");
	private final static QName _BatchPayment_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"batchPayment");
	private final static QName _SetReceiptBillBizMgr_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"setReceiptBillBizMgr");
	private final static QName _BatchReceiptResponse_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"batchReceiptResponse");
	private final static QName _ModifyPaymentBillResponse_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"modifyPaymentBillResponse");
	private final static QName _ModifyReceiptBill_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"modifyReceiptBill");
	private final static QName _SingleUndoResponse_QNAME = new QName(
			"http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/",
			"singleUndoResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.creditease.rc.app.settle
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link CeUndoRes }
	 * 
	 */
	public CeUndoRes createCeUndoRes() {
		return new CeUndoRes();
	}

	/**
	 * Create an instance of {@link CePaymentResultQueryRes }
	 * 
	 */
	public CePaymentResultQueryRes createCePaymentResultQueryRes() {
		return new CePaymentResultQueryRes();
	}

	/**
	 * Create an instance of {@link ModifyReceiptBill }
	 * 
	 */
	public ModifyReceiptBill createModifyReceiptBill() {
		return new ModifyReceiptBill();
	}

	/**
	 * Create an instance of {@link CeBatchPaymentReq }
	 * 
	 */
	public CeBatchPaymentReq createCeBatchPaymentReq() {
		return new CeBatchPaymentReq();
	}

	/**
	 * Create an instance of {@link CeReceiptResultQueryRes }
	 * 
	 */
	public CeReceiptResultQueryRes createCeReceiptResultQueryRes() {
		return new CeReceiptResultQueryRes();
	}

	/**
	 * Create an instance of {@link BatchReceipt }
	 * 
	 */
	public BatchReceipt createBatchReceipt() {
		return new BatchReceipt();
	}

	/**
	 * Create an instance of {@link SinglePaymentResultQueryResponse }
	 * 
	 */
	public SinglePaymentResultQueryResponse createSinglePaymentResultQueryResponse() {
		return new SinglePaymentResultQueryResponse();
	}

	/**
	 * Create an instance of {@link CeSingleRtReceiptReq }
	 * 
	 */
	public CeSingleRtReceiptReq createCeSingleRtReceiptReq() {
		return new CeSingleRtReceiptReq();
	}

	/**
	 * Create an instance of {@link ModifyPaymentBill }
	 * 
	 */
	public ModifyPaymentBill createModifyPaymentBill() {
		return new ModifyPaymentBill();
	}

	/**
	 * Create an instance of {@link BatchPaymentResultQuery }
	 * 
	 */
	public BatchPaymentResultQuery createBatchPaymentResultQuery() {
		return new BatchPaymentResultQuery();
	}

	/**
	 * Create an instance of {@link CeUndoReq }
	 * 
	 */
	public CeUndoReq createCeUndoReq() {
		return new CeUndoReq();
	}

	/**
	 * Create an instance of {@link SetReceiptBillBizMgrResponse }
	 * 
	 */
	public SetReceiptBillBizMgrResponse createSetReceiptBillBizMgrResponse() {
		return new SetReceiptBillBizMgrResponse();
	}

	/**
	 * Create an instance of {@link ModifyPaymentBillResponse }
	 * 
	 */
	public ModifyPaymentBillResponse createModifyPaymentBillResponse() {
		return new ModifyPaymentBillResponse();
	}

	/**
	 * Create an instance of {@link CeModifyPaymentReq }
	 * 
	 */
	public CeModifyPaymentReq createCeModifyPaymentReq() {
		return new CeModifyPaymentReq();
	}

	/**
	 * Create an instance of {@link CeReceiptResultQueryReq }
	 * 
	 */
	public CeReceiptResultQueryReq createCeReceiptResultQueryReq() {
		return new CeReceiptResultQueryReq();
	}

	/**
	 * Create an instance of {@link SinglePaymentResultQuery }
	 * 
	 */
	public SinglePaymentResultQuery createSinglePaymentResultQuery() {
		return new SinglePaymentResultQuery();
	}

	/**
	 * Create an instance of {@link BatchPayment }
	 * 
	 */
	public BatchPayment createBatchPayment() {
		return new BatchPayment();
	}

	/**
	 * Create an instance of {@link SetReceiptFlowBizMgr }
	 * 
	 */
	public SetReceiptFlowBizMgr createSetReceiptFlowBizMgr() {
		return new SetReceiptFlowBizMgr();
	}

	/**
	 * Create an instance of {@link SetReceiptFlowBizMgrResponse }
	 * 
	 */
	public SetReceiptFlowBizMgrResponse createSetReceiptFlowBizMgrResponse() {
		return new SetReceiptFlowBizMgrResponse();
	}

	/**
	 * Create an instance of {@link SingleRtReceipt }
	 * 
	 */
	public SingleRtReceipt createSingleRtReceipt() {
		return new SingleRtReceipt();
	}

	/**
	 * Create an instance of {@link ReceiptBillBizMgr }
	 * 
	 */
	public ReceiptBillBizMgr createReceiptBillBizMgr() {
		return new ReceiptBillBizMgr();
	}

	/**
	 * Create an instance of {@link BatchReceiptResponse }
	 * 
	 */
	public BatchReceiptResponse createBatchReceiptResponse() {
		return new BatchReceiptResponse();
	}

	/**
	 * Create an instance of {@link SingleReceiptResultQueryResponse }
	 * 
	 */
	public SingleReceiptResultQueryResponse createSingleReceiptResultQueryResponse() {
		return new SingleReceiptResultQueryResponse();
	}

	/**
	 * Create an instance of {@link SingleRtReceiptResponse }
	 * 
	 */
	public SingleRtReceiptResponse createSingleRtReceiptResponse() {
		return new SingleRtReceiptResponse();
	}

	/**
	 * Create an instance of {@link CeBatchReceiptReq }
	 * 
	 */
	public CeBatchReceiptReq createCeBatchReceiptReq() {
		return new CeBatchReceiptReq();
	}

	/**
	 * Create an instance of {@link CeSingleRtReceiptRes }
	 * 
	 */
	public CeSingleRtReceiptRes createCeSingleRtReceiptRes() {
		return new CeSingleRtReceiptRes();
	}

	/**
	 * Create an instance of {@link ModifyReceiptBillResponse }
	 * 
	 */
	public ModifyReceiptBillResponse createModifyReceiptBillResponse() {
		return new ModifyReceiptBillResponse();
	}

	/**
	 * Create an instance of {@link SingleUndoResponse }
	 * 
	 */
	public SingleUndoResponse createSingleUndoResponse() {
		return new SingleUndoResponse();
	}

	/**
	 * Create an instance of {@link BatchPaymentResultQueryResponse }
	 * 
	 */
	public BatchPaymentResultQueryResponse createBatchPaymentResultQueryResponse() {
		return new BatchPaymentResultQueryResponse();
	}

	/**
	 * Create an instance of {@link SetReceiptBillBizMgr }
	 * 
	 */
	public SetReceiptBillBizMgr createSetReceiptBillBizMgr() {
		return new SetReceiptBillBizMgr();
	}

	/**
	 * Create an instance of {@link SingleReceiptResultQuery }
	 * 
	 */
	public SingleReceiptResultQuery createSingleReceiptResultQuery() {
		return new SingleReceiptResultQuery();
	}

	/**
	 * Create an instance of {@link CePaymentResultQueryReq }
	 * 
	 */
	public CePaymentResultQueryReq createCePaymentResultQueryReq() {
		return new CePaymentResultQueryReq();
	}

	/**
	 * Create an instance of {@link BatchReceiptResultQueryResponse }
	 * 
	 */
	public BatchReceiptResultQueryResponse createBatchReceiptResultQueryResponse() {
		return new BatchReceiptResultQueryResponse();
	}

	/**
	 * Create an instance of {@link CeRespEnv }
	 * 
	 */
	public CeRespEnv createCeRespEnv() {
		return new CeRespEnv();
	}

	/**
	 * Create an instance of {@link SingleUndo }
	 * 
	 */
	public SingleUndo createSingleUndo() {
		return new SingleUndo();
	}

	/**
	 * Create an instance of {@link CeModifyReceiptReq }
	 * 
	 */
	public CeModifyReceiptReq createCeModifyReceiptReq() {
		return new CeModifyReceiptReq();
	}

	/**
	 * Create an instance of {@link BatchPaymentResponse }
	 * 
	 */
	public BatchPaymentResponse createBatchPaymentResponse() {
		return new BatchPaymentResponse();
	}

	/**
	 * Create an instance of {@link BatchReceiptResultQuery }
	 * 
	 */
	public BatchReceiptResultQuery createBatchReceiptResultQuery() {
		return new BatchReceiptResultQuery();
	}

	/**
	 * Create an instance of {@link CeBatchPaymentRes }
	 * 
	 */
	public CeBatchPaymentRes createCeBatchPaymentRes() {
		return new CeBatchPaymentRes();
	}

	/**
	 * Create an instance of {@link CeBatchReceiptRes }
	 * 
	 */
	public CeBatchReceiptRes createCeBatchReceiptRes() {
		return new CeBatchReceiptRes();
	}

	/**
	 * Create an instance of {@link ReceiptFlowBizMgr }
	 * 
	 */
	public ReceiptFlowBizMgr createReceiptFlowBizMgr() {
		return new ReceiptFlowBizMgr();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link BatchPaymentResultQuery }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "batchPaymentResultQuery")
	public JAXBElement<BatchPaymentResultQuery> createBatchPaymentResultQuery(
			BatchPaymentResultQuery value) {
		return new JAXBElement<BatchPaymentResultQuery>(
				_BatchPaymentResultQuery_QNAME, BatchPaymentResultQuery.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SetReceiptFlowBizMgrResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "setReceiptFlowBizMgrResponse")
	public JAXBElement<SetReceiptFlowBizMgrResponse> createSetReceiptFlowBizMgrResponse(
			SetReceiptFlowBizMgrResponse value) {
		return new JAXBElement<SetReceiptFlowBizMgrResponse>(
				_SetReceiptFlowBizMgrResponse_QNAME,
				SetReceiptFlowBizMgrResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SingleRtReceipt }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "singleRtReceipt")
	public JAXBElement<SingleRtReceipt> createSingleRtReceipt(
			SingleRtReceipt value) {
		return new JAXBElement<SingleRtReceipt>(_SingleRtReceipt_QNAME,
				SingleRtReceipt.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SingleReceiptResultQuery }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "singleReceiptResultQuery")
	public JAXBElement<SingleReceiptResultQuery> createSingleReceiptResultQuery(
			SingleReceiptResultQuery value) {
		return new JAXBElement<SingleReceiptResultQuery>(
				_SingleReceiptResultQuery_QNAME,
				SingleReceiptResultQuery.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SetReceiptBillBizMgrResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "setReceiptBillBizMgrResponse")
	public JAXBElement<SetReceiptBillBizMgrResponse> createSetReceiptBillBizMgrResponse(
			SetReceiptBillBizMgrResponse value) {
		return new JAXBElement<SetReceiptBillBizMgrResponse>(
				_SetReceiptBillBizMgrResponse_QNAME,
				SetReceiptBillBizMgrResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SingleUndo }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "singleUndo")
	public JAXBElement<SingleUndo> createSingleUndo(SingleUndo value) {
		return new JAXBElement<SingleUndo>(_SingleUndo_QNAME, SingleUndo.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SingleRtReceiptResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "singleRtReceiptResponse")
	public JAXBElement<SingleRtReceiptResponse> createSingleRtReceiptResponse(
			SingleRtReceiptResponse value) {
		return new JAXBElement<SingleRtReceiptResponse>(
				_SingleRtReceiptResponse_QNAME, SingleRtReceiptResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link BatchPaymentResultQueryResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "batchPaymentResultQueryResponse")
	public JAXBElement<BatchPaymentResultQueryResponse> createBatchPaymentResultQueryResponse(
			BatchPaymentResultQueryResponse value) {
		return new JAXBElement<BatchPaymentResultQueryResponse>(
				_BatchPaymentResultQueryResponse_QNAME,
				BatchPaymentResultQueryResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SinglePaymentResultQuery }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "singlePaymentResultQuery")
	public JAXBElement<SinglePaymentResultQuery> createSinglePaymentResultQuery(
			SinglePaymentResultQuery value) {
		return new JAXBElement<SinglePaymentResultQuery>(
				_SinglePaymentResultQuery_QNAME,
				SinglePaymentResultQuery.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SingleReceiptResultQueryResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "singleReceiptResultQueryResponse")
	public JAXBElement<SingleReceiptResultQueryResponse> createSingleReceiptResultQueryResponse(
			SingleReceiptResultQueryResponse value) {
		return new JAXBElement<SingleReceiptResultQueryResponse>(
				_SingleReceiptResultQueryResponse_QNAME,
				SingleReceiptResultQueryResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SetReceiptFlowBizMgr }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "setReceiptFlowBizMgr")
	public JAXBElement<SetReceiptFlowBizMgr> createSetReceiptFlowBizMgr(
			SetReceiptFlowBizMgr value) {
		return new JAXBElement<SetReceiptFlowBizMgr>(
				_SetReceiptFlowBizMgr_QNAME, SetReceiptFlowBizMgr.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link BatchReceipt }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "batchReceipt")
	public JAXBElement<BatchReceipt> createBatchReceipt(BatchReceipt value) {
		return new JAXBElement<BatchReceipt>(_BatchReceipt_QNAME,
				BatchReceipt.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link BatchPaymentResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "batchPaymentResponse")
	public JAXBElement<BatchPaymentResponse> createBatchPaymentResponse(
			BatchPaymentResponse value) {
		return new JAXBElement<BatchPaymentResponse>(
				_BatchPaymentResponse_QNAME, BatchPaymentResponse.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link BatchReceiptResultQuery }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "batchReceiptResultQuery")
	public JAXBElement<BatchReceiptResultQuery> createBatchReceiptResultQuery(
			BatchReceiptResultQuery value) {
		return new JAXBElement<BatchReceiptResultQuery>(
				_BatchReceiptResultQuery_QNAME, BatchReceiptResultQuery.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ModifyPaymentBill }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "modifyPaymentBill")
	public JAXBElement<ModifyPaymentBill> createModifyPaymentBill(
			ModifyPaymentBill value) {
		return new JAXBElement<ModifyPaymentBill>(_ModifyPaymentBill_QNAME,
				ModifyPaymentBill.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SinglePaymentResultQueryResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "singlePaymentResultQueryResponse")
	public JAXBElement<SinglePaymentResultQueryResponse> createSinglePaymentResultQueryResponse(
			SinglePaymentResultQueryResponse value) {
		return new JAXBElement<SinglePaymentResultQueryResponse>(
				_SinglePaymentResultQueryResponse_QNAME,
				SinglePaymentResultQueryResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link BatchReceiptResultQueryResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "batchReceiptResultQueryResponse")
	public JAXBElement<BatchReceiptResultQueryResponse> createBatchReceiptResultQueryResponse(
			BatchReceiptResultQueryResponse value) {
		return new JAXBElement<BatchReceiptResultQueryResponse>(
				_BatchReceiptResultQueryResponse_QNAME,
				BatchReceiptResultQueryResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ModifyReceiptBillResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "modifyReceiptBillResponse")
	public JAXBElement<ModifyReceiptBillResponse> createModifyReceiptBillResponse(
			ModifyReceiptBillResponse value) {
		return new JAXBElement<ModifyReceiptBillResponse>(
				_ModifyReceiptBillResponse_QNAME,
				ModifyReceiptBillResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link BatchPayment }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "batchPayment")
	public JAXBElement<BatchPayment> createBatchPayment(BatchPayment value) {
		return new JAXBElement<BatchPayment>(_BatchPayment_QNAME,
				BatchPayment.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SetReceiptBillBizMgr }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "setReceiptBillBizMgr")
	public JAXBElement<SetReceiptBillBizMgr> createSetReceiptBillBizMgr(
			SetReceiptBillBizMgr value) {
		return new JAXBElement<SetReceiptBillBizMgr>(
				_SetReceiptBillBizMgr_QNAME, SetReceiptBillBizMgr.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link BatchReceiptResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "batchReceiptResponse")
	public JAXBElement<BatchReceiptResponse> createBatchReceiptResponse(
			BatchReceiptResponse value) {
		return new JAXBElement<BatchReceiptResponse>(
				_BatchReceiptResponse_QNAME, BatchReceiptResponse.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ModifyPaymentBillResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "modifyPaymentBillResponse")
	public JAXBElement<ModifyPaymentBillResponse> createModifyPaymentBillResponse(
			ModifyPaymentBillResponse value) {
		return new JAXBElement<ModifyPaymentBillResponse>(
				_ModifyPaymentBillResponse_QNAME,
				ModifyPaymentBillResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ModifyReceiptBill }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "modifyReceiptBill")
	public JAXBElement<ModifyReceiptBill> createModifyReceiptBill(
			ModifyReceiptBill value) {
		return new JAXBElement<ModifyReceiptBill>(_ModifyReceiptBill_QNAME,
				ModifyReceiptBill.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SingleUndoResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.ce.handler.finalpayment.payplatform.app.creditease.com/", name = "singleUndoResponse")
	public JAXBElement<SingleUndoResponse> createSingleUndoResponse(
			SingleUndoResponse value) {
		return new JAXBElement<SingleUndoResponse>(_SingleUndoResponse_QNAME,
				SingleUndoResponse.class, null, value);
	}

}
