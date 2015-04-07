package com.creditease.rc.app.sia.vo;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * @author hanjf
 * 借款人房产信息
 */
public class BorrowerHouseVo extends Entity {
	private static final long serialVersionUID = 1984438362968583718L;
	
	private Long borrowerInfoId;	//借款人ID                                                     
	private Long houseId;	    //房产ID                                                               
	private String relation;		//户主与本人关系                                                       
	private String mateHasHouse;	//配偶名下有无房产。0[无], 1[有]                               
	private BigDecimal monthRepayment;//月还款额                                                     
	private String borrowSort;		//贷款类型。0[商业贷款], 1[公积金贷款], 2[混合贷款]            
	private String hasHousingLoan;	//有无房贷。0[无], 1[有]                                       
	private Date buyDate;			//房产购买时间                                                         
	private String liveSituation;	//房产（本市居住情况）                                         
	private String state;			//房产状况（本市房产状况）                                             
	private String houseOwnerType;	//产权人类型                                                   
	private String houseOwnerTypeDesc;//产权人类型其他项备注                                 
	private String isSameWithApplay; //是否同申请表地址相同                                 
	private String isSameWithLive;	 //房产地址与现住址是否相同                                     
	private String liveCase;		//居住情况                                                             
	private BigDecimal rentalFee;	//租房月租金                                                           
	private String houseName;		//小区名称                                                             
	private BigDecimal houseCase;	//房产状况1                                                            
	private String houseState;		//房产情况(对应字典：CUSTOMER_HOURSE)1[有房无贷款]；2[无房]；3[有
	private String houseNature;		//单选框 个人；售后公房                                        
	private String housePurpose;	//住宅；经营用房 单选框                                        
	private String nature;			//商业按揭房；无按揭购房；公积金购房                                   
	private Long roomNum;		//号室（）号                                                           
	private Long roomNum1;		//号室                                                                 
	private Short theFloor;		//第x层                                                                
	private Short totalFloor;		//共x层                                                        
	private String cohabitRela;		//同住者关系。1[父母], 2[配偶子女], 3[朋友], 4[独居], 5[其他]  
	private String relationship;	//与其他产权人关系。0[子女], 1[父母], 2[夫妻]                  
	private String otherHouseOwnerTypedesc;//与其他产权人关系说明                                 
	private String type;			//房屋类型                                                             
	private BigDecimal monthPay;    //房产状况选有房有贷款时的月供                                         
	private BigDecimal houseArea;   //建筑面积／房产面积                                                   
	private String province;        //房屋地址省                                                           
	private String city;            //房屋地址市                                                           
	private String area;            //房屋地址区                                                           
	private String address;         //房屋坐落地址                                                         
	private String province2;       //房屋地址省2                                                          
	private String city2;           //房屋地址市2                                                          
	private String area2;           //房屋地址区2                                                          
	private String zipCode;         //房屋所在邮政编码                                                     
	private String houseTypeDesc;  	//房产类型其它项备注                                           
	private String address2;        //房屋坐落地址2                                                        
	private String structure;       //房屋结构                                                             
	private String completeYear;    //竣工年限                                                     
	private String righterCertno;   //房屋产权证号                                                 
	private String righterCertno2;  //房屋产权证号2                                                
	private String bankLoanCase;   	//银行贷款情况                                                 
	private String loanBank;        //贷款银行                                                             
	private BigDecimal loanAmount;  //贷款金额                                                     
	private String pledgeCase;      //房产抵押情况  0：否  1：一次抵押   2：二次抵押               
	private String righter;         //产权人                                                               
	private Short righterNum;     //产权人数                                                     
	private Short validNum;       //提供有效房产数量                                                     
	private String liveNature;      //现居住性质                                                   
	private String memo;            //注释                                                                 
	private Long creater;        //创建人                                                               
	private Long updaterPeople;  //更新人                                                       
	private Date createTime;        //创建时间                                                     
	private Date updateTime;	    //最近更新时间     
	public Long getBorrowerInfoId() {
		return borrowerInfoId;
	}
	public void setBorrowerInfoId(Long borrowerInfoId) {
		this.borrowerInfoId = borrowerInfoId;
	}
	public Long getHouseId() {
		return houseId;
	}
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getMateHasHouse() {
		return mateHasHouse;
	}
	public void setMateHasHouse(String mateHasHouse) {
		this.mateHasHouse = mateHasHouse;
	}
	public BigDecimal getMonthRepayment() {
		return monthRepayment;
	}
	public void setMonthRepayment(BigDecimal monthRepayment) {
		this.monthRepayment = monthRepayment;
	}
	public String getBorrowSort() {
		return borrowSort;
	}
	public void setBorrowSort(String borrowSort) {
		this.borrowSort = borrowSort;
	}
	public String getHasHousingLoan() {
		return hasHousingLoan;
	}
	public void setHasHousingLoan(String hasHousingLoan) {
		this.hasHousingLoan = hasHousingLoan;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public String getLiveSituation() {
		return liveSituation;
	}
	public void setLiveSituation(String liveSituation) {
		this.liveSituation = liveSituation;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getHouseOwnerType() {
		return houseOwnerType;
	}
	public void setHouseOwnerType(String houseOwnerType) {
		this.houseOwnerType = houseOwnerType;
	}
	public String getHouseOwnerTypeDesc() {
		return houseOwnerTypeDesc;
	}
	public void setHouseOwnerTypeDesc(String houseOwnerTypeDesc) {
		this.houseOwnerTypeDesc = houseOwnerTypeDesc;
	}
	public String getIsSameWithApplay() {
		return isSameWithApplay;
	}
	public void setIsSameWithApplay(String isSameWithApplay) {
		this.isSameWithApplay = isSameWithApplay;
	}
	public String getIsSameWithLive() {
		return isSameWithLive;
	}
	public void setIsSameWithLive(String isSameWithLive) {
		this.isSameWithLive = isSameWithLive;
	}
	public String getLiveCase() {
		return liveCase;
	}
	public void setLiveCase(String liveCase) {
		this.liveCase = liveCase;
	}
	public BigDecimal getRentalFee() {
		return rentalFee;
	}
	public void setRentalFee(BigDecimal rentalFee) {
		this.rentalFee = rentalFee;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public BigDecimal getHouseCase() {
		return houseCase;
	}
	public void setHouseCase(BigDecimal houseCase) {
		this.houseCase = houseCase;
	}
	public String getHouseState() {
		return houseState;
	}
	public void setHouseState(String houseState) {
		this.houseState = houseState;
	}
	public String getHouseNature() {
		return houseNature;
	}
	public void setHouseNature(String houseNature) {
		this.houseNature = houseNature;
	}
	public String getHousePurpose() {
		return housePurpose;
	}
	public void setHousePurpose(String housePurpose) {
		this.housePurpose = housePurpose;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public Long getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(Long roomNum) {
		this.roomNum = roomNum;
	}
	public Long getRoomNum1() {
		return roomNum1;
	}
	public void setRoomNum1(Long roomNum1) {
		this.roomNum1 = roomNum1;
	}
	public Short getTheFloor() {
		return theFloor;
	}
	public void setTheFloor(Short theFloor) {
		this.theFloor = theFloor;
	}
	public Short getTotalFloor() {
		return totalFloor;
	}
	public void setTotalFloor(Short totalFloor) {
		this.totalFloor = totalFloor;
	}
	public String getCohabitRela() {
		return cohabitRela;
	}
	public void setCohabitRela(String cohabitRela) {
		this.cohabitRela = cohabitRela;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getOtherHouseOwnerTypedesc() {
		return otherHouseOwnerTypedesc;
	}
	public void setOtherHouseOwnerTypedesc(String otherHouseOwnerTypedesc) {
		this.otherHouseOwnerTypedesc = otherHouseOwnerTypedesc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getMonthPay() {
		return monthPay;
	}
	public void setMonthPay(BigDecimal monthPay) {
		this.monthPay = monthPay;
	}
	public BigDecimal getHouseArea() {
		return houseArea;
	}
	public void setHouseArea(BigDecimal houseArea) {
		this.houseArea = houseArea;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProvince2() {
		return province2;
	}
	public void setProvince2(String province2) {
		this.province2 = province2;
	}
	public String getCity2() {
		return city2;
	}
	public void setCity2(String city2) {
		this.city2 = city2;
	}
	public String getArea2() {
		return area2;
	}
	public void setArea2(String area2) {
		this.area2 = area2;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getHouseTypeDesc() {
		return houseTypeDesc;
	}
	public void setHouseTypeDesc(String houseTypeDesc) {
		this.houseTypeDesc = houseTypeDesc;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	public String getCompleteYear() {
		return completeYear;
	}
	public void setCompleteYear(String completeYear) {
		this.completeYear = completeYear;
	}
	public String getRighterCertno() {
		return righterCertno;
	}
	public void setRighterCertno(String righterCertno) {
		this.righterCertno = righterCertno;
	}
	public String getRighterCertno2() {
		return righterCertno2;
	}
	public void setRighterCertno2(String righterCertno2) {
		this.righterCertno2 = righterCertno2;
	}
	public String getBankLoanCase() {
		return bankLoanCase;
	}
	public void setBankLoanCase(String bankLoanCase) {
		this.bankLoanCase = bankLoanCase;
	}
	public String getLoanBank() {
		return loanBank;
	}
	public void setLoanBank(String loanBank) {
		this.loanBank = loanBank;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getPledgeCase() {
		return pledgeCase;
	}
	public void setPledgeCase(String pledgeCase) {
		this.pledgeCase = pledgeCase;
	}
	public String getRighter() {
		return righter;
	}
	public void setRighter(String righter) {
		this.righter = righter;
	}
	public Short getRighterNum() {
		return righterNum;
	}
	public void setRighterNum(Short righterNum) {
		this.righterNum = righterNum;
	}
	public Short getValidNum() {
		return validNum;
	}
	public void setValidNum(Short validNum) {
		this.validNum = validNum;
	}
	public String getLiveNature() {
		return liveNature;
	}
	public void setLiveNature(String liveNature) {
		this.liveNature = liveNature;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}