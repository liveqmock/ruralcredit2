package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDHMDDateSerializer;

/**
 * @author liuli
 * 201305-14
 * 系统公告
 *
 */
public class SystemAnnouncement implements Serializable{
	
	private Integer systemAnnouncementId;
	private String title;					//公告标题
	private String content;					//公告内容
	private Integer imageNum;				//用于判断该数据是否已经上传图片
	private String attachmentId;			//附件id 格式：systemAnnouncementId+xtgg(系统公告首字母)
	private Date createTime;				//创建时间
	private Date updateTime;				//更新时间
	private String titleFlag;
	private String number;
	
	public Integer getSystemAnnouncementId() {
		return systemAnnouncementId;
	}
	public void setSystemAnnouncementId(Integer systemAnnouncementId) {
		this.systemAnnouncementId = systemAnnouncementId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	@JsonSerialize(using = JsonYMDHMDDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@JsonSerialize(using = JsonYMDHMDDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getImageNum() {
		return imageNum;
	}
	public void setImageNum(Integer imageNum) {
		this.imageNum = imageNum;
	}
	public String getTitleFlag() {
		return titleFlag;
	}
	public void setTitleFlag(String titleFlag) {
		this.titleFlag = titleFlag;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
}
