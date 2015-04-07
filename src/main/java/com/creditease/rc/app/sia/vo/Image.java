package com.creditease.rc.app.sia.vo;

/**
 * @author mw
 * 
 * 上传资料
 */
public class Image extends Entity {

	private static final long serialVersionUID = -226542914349809725L;

	private Integer id;

	private int applyId;// 申请表id

	private int clientId;// 客户id

	private int paperId;// 资料id

	private int sequence;// 序号

	private String imageName;// 资料名称

	private String breviaryImage1;// 路径1

	private String breviaryImage2;// 路径2

	private String isComplementarity;// 是否是补充资料

	private int isMeet;

	private String originallyImage;//

	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getApplyId() {
		return applyId;
	}

	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getPaperId() {
		return paperId;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getBreviaryImage1() {
		return breviaryImage1;
	}

	public void setBreviaryImage1(String breviaryImage1) {
		this.breviaryImage1 = breviaryImage1;
	}

	public String getBreviaryImage2() {
		return breviaryImage2;
	}

	public void setBreviaryImage2(String breviaryImage2) {
		this.breviaryImage2 = breviaryImage2;
	}

	public String getIsComplementarity() {
		return isComplementarity;
	}

	public void setIsComplementarity(String isComplementarity) {
		this.isComplementarity = isComplementarity;
	}

	public int getIsMeet() {
		return isMeet;
	}

	public void setIsMeet(int isMeet) {
		this.isMeet = isMeet;
	}

	public String getOriginallyImage() {
		return originallyImage;
	}

	public void setOriginallyImage(String originallyImage) {
		this.originallyImage = originallyImage;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
