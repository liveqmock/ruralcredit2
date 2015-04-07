package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.vo.OrderInfoList;

public interface IComprehensiveCreditDao {

	List<OrderInfoList> getOrderInfoList(Map<String, String> queryMap);

}
