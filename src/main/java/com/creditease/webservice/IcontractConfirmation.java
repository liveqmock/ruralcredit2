package com.creditease.webservice;

import javax.jws.WebService;

import com.creditease.rc.vo.ContractConfirmNoticeReqVO;
import com.creditease.rc.vo.ContractConfirmNoticeResVO;

@WebService
public interface IcontractConfirmation {
	/**
	 * 合同确认
	 * @param contractConfirmNoticeReqVO
	 * @return
	 */
	public ContractConfirmNoticeResVO contractConfirmation(ContractConfirmNoticeReqVO contractConfirmNoticeReqVO);

}
