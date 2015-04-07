package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.dao.ICustomerConsultPoolDao;
import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IInquirePoolOfficeService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.CustomerConsultPoolVo;

@Service
public class InquirePoolOfficeService implements IInquirePoolOfficeService {

	@Resource
	private ICustomerConsultPoolDao customerConsultPoolDAO;
	
	@Override
	public Pagination selectInquirePoolOfficeList(Pagination pagination, Map<String, String> pramMap) {
		return customerConsultPoolDAO.selectInquirePoolOfficeList(pagination, pramMap);
	}
	
	//导出数据
	@Override
	public Pagination downloadExcel(Pagination pagination, CustomerConsultPoolVo customerConsultPoolVo,String sort, String order, HttpServletRequest request) {
		
		updateBusinessStatus();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sort", sort);
		map.put("order", order);
		map.put("customerConsultPoolVo", customerConsultPoolVo);
		// 添加权限查询
		List<String> authList = SpringSecurityUtils.getAuthList(request.getSession());
		String sqlsid = "";
		if (authList.size() > 0) {
			for (String e : authList) {
				if (sqlsid != null) {
					sqlsid = sqlsid + "'" + e + "'" + ",";
				} else {
					sqlsid = "'" + e + "'" + ",";
				}
			}
			sqlsid = sqlsid.substring(0, sqlsid.length() - 1);
		}
		map.put("sqlSid", sqlsid);
		return customerConsultPoolDAO.downloadExcel(pagination, map);
	}

	@Override
	public void updateBusinessStatus() {
		List<CustomerConsultPool> customerConsultPoolList = new ArrayList<CustomerConsultPool>();
		customerConsultPoolList = (List<CustomerConsultPool>) customerConsultPoolDAO.queryList("CUSTOMERCONSULTPOOL.selectNeedData");
		List<CustomerConsultPool> ccpList = new ArrayList<CustomerConsultPool>();
		if (CommonUtil.isNotEmpty(customerConsultPoolList)) {
			for (CustomerConsultPool customerConsultPool : customerConsultPoolList) {
				if (customerConsultPool.getConsultPoolId()!=null && CommonUtil.isNotEmpty(customerConsultPool.getApplicationStatus())) {
					CustomerConsultPool ccp = new CustomerConsultPool();
					ccp.setConsultPoolId(customerConsultPool.getConsultPoolId());
					//申请中
					if (customerConsultPool.getApplicationStatus().equals("0")) {
						ccp.setBusinessStatus("0");
					}//风险客户系统拒贷
					else if(customerConsultPool.getApplicationStatus().equals("1")) {
						ccp.setBusinessStatus("1");
					}//等待变更信息
					else if(customerConsultPool.getApplicationStatus().equals("3")) {
						ccp.setBusinessStatus("0");
					}//等待调查
					else if(customerConsultPool.getApplicationStatus().equals("4")) {
						ccp.setBusinessStatus("0");
					}//调查中
					else if(customerConsultPool.getApplicationStatus().equals("5")) {
						ccp.setBusinessStatus("0");
					}//等待重新调查
					else if(customerConsultPool.getApplicationStatus().equals("6")) {
						ccp.setBusinessStatus("0");
					}//等待调查确认
					else if(customerConsultPool.getApplicationStatus().equals("7")) {
						ccp.setBusinessStatus("0");
					}//等待审贷会
					else if(customerConsultPool.getApplicationStatus().equals("8")) {
						ccp.setBusinessStatus("0");
					}//审贷会中
					else if(customerConsultPool.getApplicationStatus().equals("9")) {
						ccp.setBusinessStatus("0");
					}//审贷会协商
					else if(customerConsultPool.getApplicationStatus().equals("10")) {
						ccp.setBusinessStatus("0");
					}//审贷会协商中
					else if(customerConsultPool.getApplicationStatus().equals("11")) {
						ccp.setBusinessStatus("0");
					}//等待高级审批
					else if(customerConsultPool.getApplicationStatus().equals("12")) {
						ccp.setBusinessStatus("0");
					}//等待打印合同
					else if(customerConsultPool.getApplicationStatus().equals("13")) {
						ccp.setBusinessStatus("0");
					}//合同已打印
					else if(customerConsultPool.getApplicationStatus().equals("14")) {
						ccp.setBusinessStatus("0");
					}//等待签订合同
					else if(customerConsultPool.getApplicationStatus().equals("15")) {
						ccp.setBusinessStatus("0");
					}//等待重新签订合同
					else if(customerConsultPool.getApplicationStatus().equals("16")) {
						ccp.setBusinessStatus("0");
					}//合同已签订待确认
					else if(customerConsultPool.getApplicationStatus().equals("17")) {
						ccp.setBusinessStatus("0");
					}//申请拒绝
					else if(customerConsultPool.getApplicationStatus().equals("18")) {
						ccp.setBusinessStatus("18");
					}//调查拒绝
					else if(customerConsultPool.getApplicationStatus().equals("19")) {
						ccp.setBusinessStatus("19");
					}//审贷会拒绝
					else if(customerConsultPool.getApplicationStatus().equals("20")) {
						ccp.setBusinessStatus("20");
					}//客户放弃
					else if(customerConsultPool.getApplicationStatus().equals("21")) {
						ccp.setBusinessStatus("21");
					}//签约失败
					else if(customerConsultPool.getApplicationStatus().equals("22")) {
						ccp.setBusinessStatus("22");
					}//还款中
					else if(customerConsultPool.getApplicationStatus().equals("23")) {
						ccp.setBusinessStatus("23");
					}//已结清
					else if(customerConsultPool.getApplicationStatus().equals("24")) {
						ccp.setBusinessStatus("24");
					}//逾期
					else if(customerConsultPool.getApplicationStatus().equals("25")) {
						ccp.setBusinessStatus("23");
					}//等待修改调查
					else if(customerConsultPool.getApplicationStatus().equals("26")) {
						ccp.setBusinessStatus("0");
					}//修改调查中
					else if(customerConsultPool.getApplicationStatus().equals("27")) {
						ccp.setBusinessStatus("0");
					}//重新调查中
					else if(customerConsultPool.getApplicationStatus().equals("28")) {
						ccp.setBusinessStatus("0");
					}//等待放款
					else if(customerConsultPool.getApplicationStatus().equals("29")) {
						ccp.setBusinessStatus("29");
					}//合同信息确认中
					else if(customerConsultPool.getApplicationStatus().equals("30")) {
						ccp.setBusinessStatus("0");
					}//重新确认合同
					else if(customerConsultPool.getApplicationStatus().equals("32")) {
						ccp.setBusinessStatus("0");
					}//违例拒贷
					else if(customerConsultPool.getApplicationStatus().equals("33")) {
						ccp.setBusinessStatus("33");
					}//等待违例复核
					else if(customerConsultPool.getApplicationStatus().equals("34")) {
						ccp.setBusinessStatus("0");
					}
					ccpList.add(ccp);
				}
			}
		}
		if (CommonUtil.isNotEmpty(ccpList)) {
			customerConsultPoolDAO.batchUpdate(ccpList);
		}
		
		
	}

	/**
	 * 查看详情
	 */
	@Override
	public List<CustomerConsultPoolVo> selectHistory(String telphone) {
		List<CustomerConsultPoolVo> customerConsultPoolVo=customerConsultPoolDAO.selectHistory(telphone);
		return null;
	}

	@Override
	public CustomerConsultPool selectCustomerConsultPool(
			CustomerConsultPool customerConsultPool) {
		customerConsultPool=customerConsultPoolDAO.selectCustomerConsultPool(customerConsultPool);
		return customerConsultPool;
	}
}
