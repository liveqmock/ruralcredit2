package com.creditease.rc.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.dao.ICustomerReturnVisitDao;
import com.creditease.rc.domain.CustomerReturnVisit;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ReturnPlan;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ICustomerReturnVisitService;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.vo.CustomerReturnVisitVo;

@Service
public class CustomerReturnVisitService implements ICustomerReturnVisitService {

	@Resource
	private ICustomerReturnVisitDao customerReturnVisitDao;
	private static Logger logger = Logger.getLogger(OverDueDataService.class);
	@Override
	public Pagination selectPagination(CustomerReturnVisitVo customerReturnVisitVo, Pagination pagination) {
		// TODO Auto-generated method stub
		return customerReturnVisitDao.selectPagination(customerReturnVisitVo, pagination);
	}

	@Override
	public CustomerReturnVisitVo selectPresent(CustomerReturnVisitVo customerReturnVisitVo) {
		/**
		 * 在贷后做完放款确认 通知农贷已经放款，插入还款计划时插入回访计划 要是12期 插入12条回访记录 9期则插入9条回访记录
		 * 回访记录情况为： (status)为 0 validateStatus 为 null
		 *
		 * 当做完当期回访记录是 更新 validateStatus 为0 status为 1
		 * 如果当期已经做了回访 再次做回访时 根据查询的当期回访记录的status状态 判断是当期第一次做回访 还是当期第n次做回访
		 *   status 为0 说明当期首次做回访
		 *          为1 说明当期已经做过回访 再次做回访 插入新的当期回访
		 */
		// 查询所有的回访 判断是否有回访记录

		CustomerReturnVisitVo customerReturnVisitVo2 = new CustomerReturnVisitVo(); // 查询该申请下回访记录的条件
		customerReturnVisitVo2.setCreditapplicationId(customerReturnVisitVo.getCreditapplicationId());
		customerReturnVisitVo2.setSqlsid(customerReturnVisitVo.getSqlsid());
		logger.info("编辑客户回访，用户权限有："+customerReturnVisitVo2.getSqlsid());
		//查询做过回访的所有记录 status=0
		List<CustomerReturnVisitVo> customerReturnVisitVos = customerReturnVisitDao.selectAllList(customerReturnVisitVo2); // 查询出的列表
		//查询当期做过回访的记录 t.repayment_date >= presentDate
	   //	and  presentDate > add_months(t.repayment_date,-1)
		List<CustomerReturnVisitVo> returnVisitVoList = customerReturnVisitDao.selectPresent(customerReturnVisitVo);
		CustomerReturnVisitVo returnVisitVo = new CustomerReturnVisitVo();

		if (returnVisitVoList.size() == 0) { // 如果当前没有回访数据
			//查询下一期回访记录 t.repayment_date >= presentDate
			//	and  presentDate > add_months(t.repayment_date,-1)
			returnVisitVoList = customerReturnVisitDao.selectPresentAgain(customerReturnVisitVo); // 查询下个月
			if (returnVisitVoList.size() > 0) {
				returnVisitVo = returnVisitVoList.get(0);
			} else if (customerReturnVisitVos.size() > 0) { // 如果当前月份和
															// 下一个月份都没有回访计划，就是已经过了回访计划的所有日期，现在取回访计划的最后一期
				//如果当期没有回访记录 而且也没有下一期回访记录 说明所有回访均已做完 则取最后一次回访记录 做为最新的回访
				Date dateLast = customerReturnVisitVos.get(customerReturnVisitVos.size() - 1).getRepaymentDate();
				Date dateFirst = customerReturnVisitVos.get(0).getRepaymentDate();
				if (dateLast.getTime() > dateFirst.getTime()) { // 判断获取
																// 还款日期最靠后的一期
																// 回访计划
					returnVisitVo = customerReturnVisitVos.get(customerReturnVisitVos.size() - 1);
				} else {
					returnVisitVo = customerReturnVisitVos.get(0);
				}
			}
		} else {
			returnVisitVo = returnVisitVoList.get(0);
		}
		if (true) {

		}
		return returnVisitVo;
	}

	@Override
	public Message insert(CustomerReturnVisit customerReturnVisit) {
		Message message = new Message();
		long id = customerReturnVisitDao.insert(customerReturnVisit);
		if (id > 0) {
			message.setSuccess(true);
		}
		return message;
	}

	@Override
	public Message update(CustomerReturnVisit customerReturnVisit) {
		/**
		 * 在贷后做完放款确认 通知农贷已经放款，插入还款计划时插入回访计划 要是12期 插入12条回访记录 9期则插入9条回访记录
		 * 回访记录情况为： (status)为 0 validateStatus 为null
		 *
		 * 当做完当期回访记录是 更新 validateStatus 为0 status为 1
		 * 如果当期已经做了回访 再次做回访时 根据查询的当期回访记录的status状态 判断是当期第一次做回访 还是当期第n次做回访
		 *   status 为0 说明当期首次做回访
		 *          为1 说明当期已经做过回访 再次做回访 插入新的当期回访
		 */
		Message message = new Message();
		long result = 0l;
		CustomerReturnVisitVo customerReturnVisitOld = new CustomerReturnVisitVo();
		customerReturnVisitOld.setCustomerReturnVisitId(customerReturnVisit.getCustomerReturnVisitId());
		customerReturnVisitOld.setValidateStatus("0");
		 //通过id查询回访记录
		customerReturnVisitOld = customerReturnVisitDao.selectByID(customerReturnVisitOld);
		// 设置成回访过 标记
		customerReturnVisit.setStatus("1");
		customerReturnVisit.setCreditapplicationId(customerReturnVisitOld.getCreditapplicationId());
		customerReturnVisit.setOperatorTime(new Date());
		customerReturnVisit.setOperatorId(SpringSecurityUtils.getCurrentUser().getUserId());
		customerReturnVisit.setOperatorName(SpringSecurityUtils.getCurrentUser().getName_zh());
		customerReturnVisit.setRepaymentDate(customerReturnVisitOld.getRepaymentDate());

		if (customerReturnVisitOld.getStatus() != null && "0".equals(customerReturnVisitOld.getStatus())) {
			//status为 0 说明是当期第一次做回访
			result = customerReturnVisitDao.update(customerReturnVisit);
		} else {
			//当期第n次做回访 或者是已经超过回访时间做的回访
			result = customerReturnVisitDao.insert(customerReturnVisit);
		}
		if (result > 0) {
			message.setSuccess(true);
		}
		return message;
	}

	@Override
	public Message insertBatch(List<CustomerReturnVisit> customerReturnVisitList) {
		Message message = new Message();
		customerReturnVisitDao.insertBatch(customerReturnVisitList);
		message.setSuccess(true);
		return message;
	}

	@Override
	public List<CustomerReturnVisitVo> selectList(CustomerReturnVisitVo customerReturnVisitVo) {
		// TODO Auto-generated method stub
		return customerReturnVisitDao.selectList(customerReturnVisitVo);
	}

	@Override
	public List<Object> selectOverdue() {

		return null;
	}

	@Override
	public List<Map> selectWarn(HttpServletRequest request, int days) {
		Map conditions = new HashMap();
		Date today = new Date();
		Date endDay = DateUtil.getWeekDay(today, days);
		try {
			today = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(today));
			conditions.put("beginDateString", new SimpleDateFormat("yyyy-MM-dd").format(today));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			endDay = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(endDay));
			conditions.put("endDateString", new SimpleDateFormat("yyyy-MM-dd").format(endDay));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String todays = DateUtil.dateConvertString(today);
		String endDays = DateUtil.dateConvertString(endDay);
		System.out.println(todays);
		System.out.println(endDays);

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
		conditions.put("authList", sqlsid);
		conditions.put("beginDate", today);
		conditions.put("endDate", endDay);

		return customerReturnVisitDao.selectWarn(conditions);
	}

	@Override
	public List<Map> selectDontCallBackOfThisMonth(HttpServletRequest request) {
		Map conditions = new HashMap();
		Date today = new Date();
		try {
			today = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(today));
			conditions.put("nowTime", new SimpleDateFormat("yyyy-MM-dd").format(today));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
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
		conditions.put("authList", sqlsid);
		return customerReturnVisitDao.selectDontCallBackOfThisMonth(conditions);
	}

	@Override
	public List<CustomerReturnVisitVo> selectAllList(CustomerReturnVisitVo customerReturnVisitVo) {
		return customerReturnVisitDao.selectAllList(customerReturnVisitVo);
	}

	@Override
	public List<CustomerReturnVisit> selectToday(CustomerReturnVisitVo customerReturnVisitVo) {
		return customerReturnVisitDao.selectToday(customerReturnVisitVo);
	}

	@Override
	public List<ReturnPlan> selectReturnPlanList(Date repaymentDates) {
		return customerReturnVisitDao.selectReturnPlanList(repaymentDates);
	}

	@Override
	public Pagination haveNoVisitPlanList(Pagination pagination, Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return customerReturnVisitDao.haveNoVisitPlanList(pagination, queryMap);
	}

	@Override
	public Message updateCusReViVSByCreIdNDat(Map<String, String> map) {
		Message message = new Message();
		boolean s = customerReturnVisitDao.updateCusReViVSByCreIdNDat(map);
		message.setSuccess(s);
		return message;
	}
}
