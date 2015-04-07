package com.creditease.rc.vo;

import java.util.List;

import com.creditease.rc.domain.BorrowInfo;
/**
 * 
 * @author zhangman
 *
 */
public class BorrowInfoVo {
	private List<BorrowInfo> borrowInfos;

	public List<BorrowInfo> getBorrowInfos() {
		return borrowInfos;
	}

	public void setBorrowInfos(List<BorrowInfo> borrowInfos) {
		this.borrowInfos = borrowInfos;
	}

}
