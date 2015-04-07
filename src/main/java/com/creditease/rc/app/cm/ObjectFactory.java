package com.creditease.rc.app.cm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.creditease.rc.app.cm package.
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

	private final static QName _GetImgBorrowAmount_QNAME = new QName(
			"http://service.creditease/", "getImgBorrowAmount");
	private final static QName _GetImgLenderAmount_QNAME = new QName(
			"http://service.creditease/", "getImgLenderAmount");
	private final static QName _GetIsExistClientID_QNAME = new QName(
			"http://service.creditease/", "getIsExistClientID");
	private final static QName _GetImgBorrowAmountResponse_QNAME = new QName(
			"http://service.creditease/", "getImgBorrowAmountResponse");
	private final static QName _GetImgLenderAmountResponse_QNAME = new QName(
			"http://service.creditease/", "getImgLenderAmountResponse");
	private final static QName _GetImgAmountResponse_QNAME = new QName(
			"http://service.creditease/", "getImgAmountResponse");
	private final static QName _GetIsExistClientIDResponse_QNAME = new QName(
			"http://service.creditease/", "getIsExistClientIDResponse");
	private final static QName _AchieveIntegrityTypeFilesPath_QNAME = new QName(
			"http://service.creditease/", "achieveIntegrityTypeFilesPath");
	private final static QName _AchieveIntegrityTypeFilesPathResponse_QNAME = new QName(
			"http://service.creditease/",
			"achieveIntegrityTypeFilesPathResponse");
	private final static QName _GetImgAmount_QNAME = new QName(
			"http://service.creditease/", "getImgAmount");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.creditease.rc.app.cm
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link GetIsExistClientID }
	 * 
	 */
	public GetIsExistClientID createGetIsExistClientID() {
		return new GetIsExistClientID();
	}

	/**
	 * Create an instance of {@link GetIsExistClientIDResponse }
	 * 
	 */
	public GetIsExistClientIDResponse createGetIsExistClientIDResponse() {
		return new GetIsExistClientIDResponse();
	}

	/**
	 * Create an instance of {@link GetImgBorrowAmountResponse }
	 * 
	 */
	public GetImgBorrowAmountResponse createGetImgBorrowAmountResponse() {
		return new GetImgBorrowAmountResponse();
	}

	/**
	 * Create an instance of {@link AchieveIntegrityTypeFilesPathResponse }
	 * 
	 */
	public AchieveIntegrityTypeFilesPathResponse createAchieveIntegrityTypeFilesPathResponse() {
		return new AchieveIntegrityTypeFilesPathResponse();
	}

	/**
	 * Create an instance of {@link GetImgLenderAmount }
	 * 
	 */
	public GetImgLenderAmount createGetImgLenderAmount() {
		return new GetImgLenderAmount();
	}

	/**
	 * Create an instance of {@link GetImgAmount }
	 * 
	 */
	public GetImgAmount createGetImgAmount() {
		return new GetImgAmount();
	}

	/**
	 * Create an instance of {@link GetImgLenderAmountResponse }
	 * 
	 */
	public GetImgLenderAmountResponse createGetImgLenderAmountResponse() {
		return new GetImgLenderAmountResponse();
	}

	/**
	 * Create an instance of {@link GetImgBorrowAmount }
	 * 
	 */
	public GetImgBorrowAmount createGetImgBorrowAmount() {
		return new GetImgBorrowAmount();
	}

	/**
	 * Create an instance of {@link AchieveIntegrityTypeFilesPath }
	 * 
	 */
	public AchieveIntegrityTypeFilesPath createAchieveIntegrityTypeFilesPath() {
		return new AchieveIntegrityTypeFilesPath();
	}

	/**
	 * Create an instance of {@link GetImgAmountResponse }
	 * 
	 */
	public GetImgAmountResponse createGetImgAmountResponse() {
		return new GetImgAmountResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link GetImgBorrowAmount }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.creditease/", name = "getImgBorrowAmount")
	public JAXBElement<GetImgBorrowAmount> createGetImgBorrowAmount(
			GetImgBorrowAmount value) {
		return new JAXBElement<GetImgBorrowAmount>(_GetImgBorrowAmount_QNAME,
				GetImgBorrowAmount.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link GetImgLenderAmount }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.creditease/", name = "getImgLenderAmount")
	public JAXBElement<GetImgLenderAmount> createGetImgLenderAmount(
			GetImgLenderAmount value) {
		return new JAXBElement<GetImgLenderAmount>(_GetImgLenderAmount_QNAME,
				GetImgLenderAmount.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link GetIsExistClientID }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.creditease/", name = "getIsExistClientID")
	public JAXBElement<GetIsExistClientID> createGetIsExistClientID(
			GetIsExistClientID value) {
		return new JAXBElement<GetIsExistClientID>(_GetIsExistClientID_QNAME,
				GetIsExistClientID.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link GetImgBorrowAmountResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.creditease/", name = "getImgBorrowAmountResponse")
	public JAXBElement<GetImgBorrowAmountResponse> createGetImgBorrowAmountResponse(
			GetImgBorrowAmountResponse value) {
		return new JAXBElement<GetImgBorrowAmountResponse>(
				_GetImgBorrowAmountResponse_QNAME,
				GetImgBorrowAmountResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link GetImgLenderAmountResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.creditease/", name = "getImgLenderAmountResponse")
	public JAXBElement<GetImgLenderAmountResponse> createGetImgLenderAmountResponse(
			GetImgLenderAmountResponse value) {
		return new JAXBElement<GetImgLenderAmountResponse>(
				_GetImgLenderAmountResponse_QNAME,
				GetImgLenderAmountResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link GetImgAmountResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.creditease/", name = "getImgAmountResponse")
	public JAXBElement<GetImgAmountResponse> createGetImgAmountResponse(
			GetImgAmountResponse value) {
		return new JAXBElement<GetImgAmountResponse>(
				_GetImgAmountResponse_QNAME, GetImgAmountResponse.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link GetIsExistClientIDResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.creditease/", name = "getIsExistClientIDResponse")
	public JAXBElement<GetIsExistClientIDResponse> createGetIsExistClientIDResponse(
			GetIsExistClientIDResponse value) {
		return new JAXBElement<GetIsExistClientIDResponse>(
				_GetIsExistClientIDResponse_QNAME,
				GetIsExistClientIDResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link AchieveIntegrityTypeFilesPath }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.creditease/", name = "achieveIntegrityTypeFilesPath")
	public JAXBElement<AchieveIntegrityTypeFilesPath> createAchieveIntegrityTypeFilesPath(
			AchieveIntegrityTypeFilesPath value) {
		return new JAXBElement<AchieveIntegrityTypeFilesPath>(
				_AchieveIntegrityTypeFilesPath_QNAME,
				AchieveIntegrityTypeFilesPath.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link AchieveIntegrityTypeFilesPathResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.creditease/", name = "achieveIntegrityTypeFilesPathResponse")
	public JAXBElement<AchieveIntegrityTypeFilesPathResponse> createAchieveIntegrityTypeFilesPathResponse(
			AchieveIntegrityTypeFilesPathResponse value) {
		return new JAXBElement<AchieveIntegrityTypeFilesPathResponse>(
				_AchieveIntegrityTypeFilesPathResponse_QNAME,
				AchieveIntegrityTypeFilesPathResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetImgAmount }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.creditease/", name = "getImgAmount")
	public JAXBElement<GetImgAmount> createGetImgAmount(GetImgAmount value) {
		return new JAXBElement<GetImgAmount>(_GetImgAmount_QNAME,
				GetImgAmount.class, null, value);
	}

}
