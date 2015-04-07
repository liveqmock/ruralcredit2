package com.creditease.rc.app.sia.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author hanjf
 * 借款人相关教育信息
 */
public class BorrowerEducationVo extends Entity{
	private static final long serialVersionUID = -5976502214482031590L;
	
	private Long borrowerInfoId;	//借款人ID 
	private Long educationId;    //教育ID  
	private Long trainId;        //合作商家id(即经销商名称)
	private BigDecimal totalFee;	//培训总费用      
	private BigDecimal alreadyPayFee;//目前已支付
	private String organizationName;//机构名称  
	private String organizationCode;//机构编码 
	private String consulterName;	//咨询老师     
	private String consulterMobile;	//咨询老师手机   
	private String campus;			//校区             
	private String organizationType;//1 消费类培训机构、2 就业类培训机构' 
	private String trainCourse;		//培训课程       
	private BigDecimal trainTerm;	//培训期长     
	private String studentCardid;	//学生证号     
	private Date graduateDate;		//毕业时间        
	private Date openDate;			//开课时间            
	private String majorName;		//1 在校学生； 2 在职；  无业         
	private String schollName;		//学校名称        
	private Date enterDate;			//入学时间          
	private String education;		//1 初中及以下； 2 高中； 3 专科；  4 本科； 5 硕士  6 博士          
	private String basicCase;		//工作或就学情况           
	private Long creater;		//创建人
	private Long updaterPeople;	//更新人
	private Date updateTime;		//最后更新时间          
	private Date createTime;		//新增时间
	public Long getBorrowerInfoId() {
		return borrowerInfoId;
	}
	public void setBorrowerInfoId(Long borrowerInfoId) {
		this.borrowerInfoId = borrowerInfoId;
	}
	public Long getEducationId() {
		return educationId;
	}
	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}
	public Long getTrainId() {
		return trainId;
	}
	public void setTrainId(Long trainId) {
		this.trainId = trainId;
	}
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public BigDecimal getAlreadyPayFee() {
		return alreadyPayFee;
	}
	public void setAlreadyPayFee(BigDecimal alreadyPayFee) {
		this.alreadyPayFee = alreadyPayFee;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	public String getConsulterName() {
		return consulterName;
	}
	public void setConsulterName(String consulterName) {
		this.consulterName = consulterName;
	}
	public String getConsulterMobile() {
		return consulterMobile;
	}
	public void setConsulterMobile(String consulterMobile) {
		this.consulterMobile = consulterMobile;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	public String getOrganizationType() {
		return organizationType;
	}
	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}
	public String getTrainCourse() {
		return trainCourse;
	}
	public void setTrainCourse(String trainCourse) {
		this.trainCourse = trainCourse;
	}
	public BigDecimal getTrainTerm() {
		return trainTerm;
	}
	public void setTrainTerm(BigDecimal trainTerm) {
		this.trainTerm = trainTerm;
	}
	public String getStudentCardid() {
		return studentCardid;
	}
	public void setStudentCardid(String studentCardid) {
		this.studentCardid = studentCardid;
	}
	public Date getGraduateDate() {
		return graduateDate;
	}
	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getSchollName() {
		return schollName;
	}
	public void setSchollName(String schollName) {
		this.schollName = schollName;
	}
	public Date getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getBasicCase() {
		return basicCase;
	}
	public void setBasicCase(String basicCase) {
		this.basicCase = basicCase;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
