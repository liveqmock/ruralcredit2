package com.creditease.rc.domain;
/**
 * 
 * OtherFileUpload.java
 * @author yifengwang
 * 2012-12-24 下午03:44:57
 * @version V2.0
 */
public class OtherFileUpload {
	
	private Integer otherFileUploadId;
	private String uploadFileName;
	private String fileType;
	private String uploadDependClass;
	private String fileSubType;
	private Integer uploadDependId;
	
	public Integer getOtherFileUploadId() {
		return otherFileUploadId;
	}
	public void setOtherFileUploadId(Integer otherFileUploadId) {
		this.otherFileUploadId = otherFileUploadId;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getUploadDependClass() {
		return uploadDependClass;
	}
	public void setUploadDependClass(String uploadDependClass) {
		this.uploadDependClass = uploadDependClass;
	}
	public String getFileSubType() {
		return fileSubType;
	}
	public void setFileSubType(String fileSubType) {
		this.fileSubType = fileSubType;
	}
	public Integer getUploadDependId() {
		return uploadDependId;
	}
	public void setUploadDependId(Integer uploadDependId) {
		this.uploadDependId = uploadDependId;
	}
}
