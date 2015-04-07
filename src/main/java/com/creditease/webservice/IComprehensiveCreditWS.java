package com.creditease.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.creditease.rc.vo.OrderInfoRequestParam;
import com.creditease.rc.vo.OrderInfoResponseParam;

@WebService
public interface IComprehensiveCreditWS {

	public OrderInfoResponseParam getOrderInfo(
			@WebParam(name = "orderInfoRequestParam") OrderInfoRequestParam orderInfoRequestParam);
}
