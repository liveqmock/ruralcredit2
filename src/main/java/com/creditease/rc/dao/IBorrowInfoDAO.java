package com.creditease.rc.dao;

import java.text.ParseException;
import java.util.List;

import com.creditease.rc.domain.BorrowInfo;
/**
 * 借款情况操作
 * @author zhangman
 *
 */
public interface IBorrowInfoDAO {
	/**
	 * 单个添加借款情况
	 * @param borrowInfo 借款请框id
	 * @return 借款情况id
	 */
	public int addBorrowInfo(BorrowInfo borrowInfo);
	/**
	 * 添加借款情况
	 * @param borrowInfos 借款情况列表
	 */
	public void addBorrowInfo(List<BorrowInfo> borrowInfos);
	/**
	 * 修改借款情况
	 * @param borrowInfo 借款情况对象
	 */
	public void updateBorrowInfo(BorrowInfo borrowInfo);
	
	/**
	 * 按 borrowerServiceAppId id 删除
	 * @param borrowerServiceAppId 借款申请id
	 * @return 删除的行数
	 */
	public int deleteBorrowInfo(int borrowerServiceAppId);
	/**
	 * 按 borrowInfoId 删除
	 * @param borrowInfoId 借款情况id
	 * @return  删除的行数
	 */
	public int deleteByborrowInfoId(int borrowInfoId);
	/**
	 * 
	 * @param borrowerServiceAppId 借款服务申请
	 * @return 借款情况列表
	 * @throws ParseException 转换异常
	 */
	public List<BorrowInfo> selectBorrowInfo(int borrowerServiceAppId)throws ParseException ;
}
