package com.creditease.rc.service;

import java.text.ParseException;
import java.util.List;

import com.creditease.rc.domain.BorrowInfo;
import com.creditease.rc.vo.BorrowInfoVo;
/**
 * 
 * @author zhangman
 *
 */
public interface IBorrowInfoService {
	/**
	 * 添加或修改借款情况
	 * @param borrowInfos 借款信息列表 
	 * @return 借款信息列表
	 */
	public List<BorrowInfo> addOrUpdateBorrowInfo(List<BorrowInfo> borrowInfos);
	
	/**
	 * 查询借款情况
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return  借款信息列表
	 * @throws ParseException 转换异常
	 */
	public List<BorrowInfo> selectBorrowInfo(int borrowerServiceAppId)  throws ParseException;
	/**
	 * 按 id 删除
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 删除了几行
	 */
	public int deleteBorrowInfo(int borrowerServiceAppId);
	/**
	 * 按借款服务申请id查询
	 * @param borrowerServiceAppId 借款给服务申请id
	 * @return 借款情况对象
	 */
	public BorrowInfoVo selectById(int borrowerServiceAppId);
}
