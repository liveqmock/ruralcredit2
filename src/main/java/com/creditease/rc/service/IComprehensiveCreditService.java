package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.vo.OrderInfoList;

public interface IComprehensiveCreditService {

	List<OrderInfoList> getOrderInfoList(Map<String, String> queryMap);

}
