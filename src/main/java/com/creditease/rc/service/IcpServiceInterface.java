package com.creditease.rc.service;

import com.creditease.rc.app.icp.rollback.ContractReturnReq;
import com.creditease.rc.app.icp.rollback.ContractReturnRes;

public interface IcpServiceInterface {

	/**
	 * 判断调用综合信贷回退
	 * @param contractReturnReq
	 * @return
	 */
	public ContractReturnRes clicContractReturnRuralReq(ContractReturnReq contractReturnReq);
	/**
	 * 封装回退参数
	 * @param creditapplicationId
	 * @return
	 */
	public ContractReturnReq getContractReturnReq(Long creditapplicationId);
}
