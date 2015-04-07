package com.creditease.rc.app.sia.vo;

/**
 * @author mw
 * 
 * 申请表、共同借款人的联系人
 */
public class Contact extends Entity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1863599040898988641L;

	private Integer id;

	private int type;//联系人类型

	private int applyId;//申请表id

	private int clientId;//客户id

	private String name;//客户名称

	private Integer relation;//与申请人关系 20130327修改类型

	private String company;//所在公司

	private String address;//地址

	private String position;//职务

	private String telephone;//电话

	private int isSupplement;//是否是补充的

	private String remark;//备注

	private int isCommit;//是否已经提交
	
	private String strrelation;//关系备注 20130327新增

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public int getApplyId()
	{
		return applyId;
	}

	public void setApplyId(int applyId)
	{
		this.applyId = applyId;
	}

	public int getClientId()
	{
		return clientId;
	}

	public void setClientId(int clientId)
	{
		this.clientId = clientId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}


	public Integer getRelation() {
		return relation;
	}

	public void setRelation(Integer relation) {
		this.relation = relation;
	}

	public String getStrrelation() {
		return strrelation;
	}

	public void setStrrelation(String strrelation) {
		this.strrelation = strrelation;
	}

	public String getCompany()
	{
		return company;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getPosition()
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	public int getIsSupplement()
	{
		return isSupplement;
	}

	public void setIsSupplement(int isSupplement)
	{
		this.isSupplement = isSupplement;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public int getIsCommit()
	{
		return isCommit;
	}

	public void setIsCommit(int isCommit)
	{
		this.isCommit = isCommit;
	}
}
