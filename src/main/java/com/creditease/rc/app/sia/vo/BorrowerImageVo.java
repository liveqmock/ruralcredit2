package com.creditease.rc.app.sia.vo;
/**
 * 
 * @author Administrator
 * 客户图像表
 */
public class BorrowerImageVo extends Entity{
	private static final long serialVersionUID = 7091272849108648746L;

	private Long borrowerInfoId;//借款人id
	private Long imageId;	//资料id
	private Long seqeno;	//序号
	private String imageName;//资料名称
	private String path;	//路径1
	private String path2;	//路径2
	private String isAdd;	//是否是补充资料
	private String originallyImage;//原图片路径
	private String remark;	//备注
	private String isMeet;	//isMeet
	private Long creater;	//创建人
	private Long updaterPeople;//更新人
	public Long getBorrowerInfoId() {
		return borrowerInfoId;
	}
	public void setBorrowerInfoId(Long borrowerInfoId) {
		this.borrowerInfoId = borrowerInfoId;
	}
	public Long getImageId() {
		return imageId;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	public Long getSeqeno() {
		return seqeno;
	}
	public void setSeqeno(Long seqeno) {
		this.seqeno = seqeno;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPath2() {
		return path2;
	}
	public void setPath2(String path2) {
		this.path2 = path2;
	}
	public String getIsAdd() {
		return isAdd;
	}
	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
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
	public String getIsMeet() {
		return isMeet;
	}
	public void setIsMeet(String isMeet) {
		this.isMeet = isMeet;
	}
	public Long getCreater() {
		return creater;
	}
	public void setCreater(Long creater) {
		this.creater = creater;
	}
	public Long getUpdaterPeople() {
		return updaterPeople;
	}
	public void setUpdaterPeople(Long updaterPeople) {
		this.updaterPeople = updaterPeople;
	}
	
}

