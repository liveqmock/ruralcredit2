package com.creditease.rc.service;

import com.creditease.rc.domain.BorrowerService;
import com.creditease.rc.vo.HouseServeyVo;
import com.creditease.rc.vo.IDInfoVo;

/**
 * 
 * @author zhangman
 * 
 */
public interface IBorrowerApplicationService {
	/**
     *
	 *
     * @param houseServeyVo
     * @param tempSave 暂存标记 1-全部保存 0-暂存
     * @return 成功失败
	 */
	public boolean addApplication(HouseServeyVo houseServeyVo, String tempSave) throws Exception;

	/**
	 * 
	 * @param borrowerServiceAppId
	 * @return 表单封装对象
	 */
	public HouseServeyVo selectApplication(Long borrowerServiceAppId);

    /**
     * 信贷申请表初始化查询
     * @param borrowerServiceAppId
     * @return 表单封装对象
     */
    public HouseServeyVo queryApplicationVOInfo(Long borrowerServiceAppId);
    /**
     *
     * @param businessNumber
     * @return
     */
    public IDInfoVo queryIDInfoVo(String businessNumber);
    /**
     *
     保存身份证信息
     * @param idInfoVo
     * @return
     */
    public boolean updateIdInfo(IDInfoVo idInfoVo);
	/**
	 * 
	 * @param houseServeyVo 表单封装对象
	 * @return 成功失败
	 */
	public boolean updateApplication(HouseServeyVo houseServeyVo, String firstFlag);

	/**
	 * 按照id 查询
	 * 
	 * @param borrowerServiceAppId
	 * @return
	 */
	public BorrowerService selectByPrimaryKey(Long borrowerServiceAppId);

	/**
	 * 郝强提交
	 * 在保存借款人方法中增加了循环贷和打折判断
	 * 
	 * @param houseServeyVo
	 * @param doSaveStatus
	 * @return
	 */
	public boolean addApplicationIncludeRevolvingAndDiscount(HouseServeyVo houseServeyVo, String doSaveStatus) throws Exception;

    public boolean isSpecDep(Long depId);
}
