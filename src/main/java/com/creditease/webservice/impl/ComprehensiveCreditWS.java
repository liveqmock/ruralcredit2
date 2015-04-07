package com.creditease.webservice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.creditease.rc.service.IComprehensiveCreditService;
import com.creditease.rc.util.DepartmentUtil;
import com.creditease.rc.util.ObjectUtil;
import com.creditease.rc.vo.DepartmentEntityVo;
import com.creditease.rc.vo.OrderInfoList;
import com.creditease.rc.vo.OrderInfoRequestParam;
import com.creditease.rc.vo.OrderInfoResponseParam;
import com.creditease.webservice.IComprehensiveCreditWS;
import org.springframework.stereotype.Service;

public class ComprehensiveCreditWS implements IComprehensiveCreditWS {

	@Resource
	private IComprehensiveCreditService comprehensiveCreditService;

	@SuppressWarnings("finally")
	@Override
	public OrderInfoResponseParam getOrderInfo(OrderInfoRequestParam orderInfoRequestParam) {
		// TODO Auto-generated method stub
		// 校验入参
		OrderInfoResponseParam orderInfoResponseParam = new OrderInfoResponseParam();

		if (orderInfoRequestParam == null) {
			orderInfoResponseParam.setCodeInfo("1");
			orderInfoResponseParam.setMessage("入参orderInfoRequestParam为空！");
			return orderInfoResponseParam;
		}

		if (ObjectUtil.checkObjectIsNull(orderInfoRequestParam)) {
			orderInfoResponseParam.setCodeInfo("2");
			orderInfoResponseParam.setMessage("查询条件不能为空！");
			return orderInfoResponseParam;
		}
		try {
			// 查询数据
			String getBusinessNumber = orderInfoRequestParam.getBusinessNumber();
			String getClientIDNumber = orderInfoRequestParam.getClientIDNumber();
			String getClientName = orderInfoRequestParam.getClientName();
			String getUuid = orderInfoRequestParam.getUuid();
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("businessNumber", getBusinessNumber);
			queryMap.put("clientName", getClientName);
			queryMap.put("clientIDNumber", getClientIDNumber);
			queryMap.put("uuid", getUuid);
			List<OrderInfoList> orderInfoList = comprehensiveCreditService.getOrderInfoList(queryMap);
            //新增城市ID、城市名称
            if (null != orderInfoList && !orderInfoList.isEmpty()) {
                Map<String, DepartmentEntityVo> entityVoMap = DepartmentUtil.departmentMap;
                for (Map.Entry<String, DepartmentEntityVo> voEntry : entityVoMap.entrySet()) {
                    if (null != voEntry.getValue().getDepartmentIds() && voEntry.getValue().getDepartmentIds().contains(orderInfoList.get(0).getSalesDepartmentId().toString())) {
                        orderInfoList.get(0).setCityId(voEntry.getValue().getDepartmentId());
                        orderInfoList.get(0).setCityName(voEntry.getValue().getDepartmentName());
                    }
                }
            }
            orderInfoResponseParam.setCodeInfo("0");
			orderInfoResponseParam.setMessage("查询成功！");
			orderInfoResponseParam.setOrderInfoList(orderInfoList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			orderInfoResponseParam.setCodeInfo("3");
			orderInfoResponseParam.setMessage("农贷系统内部错误！");
		} finally {
			return orderInfoResponseParam;
		}

	}
}
