package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.creditease.rc.domain.CreditChannelConfig;
import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IDiscountConfigurationDao;
import com.creditease.rc.domain.DiscountConfiguration;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IDiscountConfigurationService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.vo.CreditDiscountVo;

@Service
public class DiscountConfigurationService implements IDiscountConfigurationService {

	@Resource
	private IDiscountConfigurationDao discountConfigurationDao;

	@Override
	public Pagination queryDiscountConfigurationList(DiscountConfiguration discountConfiguration, Pagination pagination) {
		// TODO Auto-generated method stub
		return discountConfigurationDao.queryDiscountConfigurationList(discountConfiguration, pagination);
	}

	@Override
	public Message saveDiscountConfiguration(DiscountConfiguration discountConfiguration) {
		// TODO Auto-generated method stub
		Message message = new Message();
		Long getDiscountConfigurationId = discountConfiguration.getDiscountConfigurationId();
		boolean success = false;
		if (getDiscountConfigurationId == null) {
			DiscountConfiguration queryDiscountConfiguration = new DiscountConfiguration();
			queryDiscountConfiguration.setProductCategoryId(discountConfiguration.getProductCategoryId());
			queryDiscountConfiguration.setProductinfoId(discountConfiguration.getProductinfoId());
			queryDiscountConfiguration.setAreaDepartmentId(discountConfiguration.getAreaDepartmentId());
			List<DiscountConfiguration> discountConfigurations = discountConfigurationDao
					.queryDiscountConfiguration(queryDiscountConfiguration);
			if (CommonUtil.isNotEmpty(discountConfigurations)) {
				message.setSuccess(success);
				message.setMsg("该营业部下的该产品已经配过打折费率！");
				return message;
			}
			success = discountConfigurationDao.insertDiscountConfiguration(discountConfiguration);
			message.setSuccess(success);
			message.setMsg("保存成功！");
		} else {
			success = discountConfigurationDao.updateDiscountConfiguration(discountConfiguration);
			message.setSuccess(success);
			message.setMsg("更新成功！");
		}
		return message;
	}

	@Override
	public Message removeDiscountConfigurationByDiscountConfigurationId(Long discountConfigurationId) {
		// TODO Auto-generated method stub
		Message message = new Message();

		boolean success = discountConfigurationDao
				.removeDiscountConfigurationByDiscountConfigurationId(discountConfigurationId);
		if (success) {
			message.setSuccess(true);
			message.setMsg("删除数据成功！");
		} else {
			message.setSuccess(false);
			message.setMsg("删除数据失败！");
		}
		return message;
	}

	@Override
	public Message discountRule(Long creditapplicationId) {
		// TODO Auto-generated method stub
		Message message = new Message();
		boolean success = false;
		// discountConfigurationDate
		String discountConfigurationDate = DicUtil.convertCodeKeyToCodeValue("discountConfigurationDate",
				"discountConfigurationDate");
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("creditapplicationId", creditapplicationId.toString());
		queryMap.put("discountConfigurationDate", discountConfigurationDate);
		List<Long> creditapplicationIds = discountConfigurationDao.discountRule(queryMap);
		if (CommonUtil.isNotEmpty(creditapplicationIds)) {
			// 可以打折
			success = discountConfigurationDao.updateCreditDiscountVo(creditapplicationId, "1", null);
		} else {
			// 不可以打折
			success = discountConfigurationDao.updateCreditDiscountVo(creditapplicationId, "0", null);
		}
		message.setSuccess(success);
		return message;
	}

	@Override
	public Map<String, String> checkDiscountConfiguration(Long creditapplicationId) {
		// TODO Auto-generated method stub
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("success", "true");
		CreditDiscountVo creditDiscountVo = discountConfigurationDao.checkDiscountConfiguration(creditapplicationId);
		String getDiscountFlag = creditDiscountVo.getDiscountFlag();
		BigDecimal getDiscount = creditDiscountVo.getDiscount();
		if ("1".equals(getDiscountFlag) && getDiscount == null) {
			// 需要提示
			returnMap.put("alert", "alert");
			returnMap.put("msg", "该笔业务满足循环贷打折条件，但未配置对应营业部和产品的打折率，请先配置打折率！");
		} else {
			// 不需要提示
			returnMap.put("alert", "unAlert");
		}

		return returnMap;
	}

	@Override
	public boolean updateCreditDiscountVo(Long creditapplicationId, String discountFlag, BigDecimal discount) {
		// TODO Auto-generated method stub
		return discountConfigurationDao.updateCreditDiscountVo(creditapplicationId, discountFlag, discount);
	}

	@Override
	public Pagination queryCreditChannelConfig(String ids, Pagination pagination) {
		CreditChannelConfig config = new CreditChannelConfig();
		config.setIds(ids);
		return discountConfigurationDao.queryCreditChannelConfig(config, pagination);
	}

	@Override
	public Message saveCreditChannelConfig(CreditChannelConfig config) {
		Message message = new Message();
		Long configId = config.getConfigId();
		if (null == configId) {
			message.setSuccess(discountConfigurationDao.insertCreditChannelConfig(config) == null ? false : true);
			message.setMsg(message.isSuccess() ? "保存成功！" : "保存失败！");
		} else {
			message.setSuccess(((Integer)discountConfigurationDao.updateCreditChannelConfig(config)).intValue() > 0 ? true : false);
			message.setMsg(message.isSuccess() ? "更新成功！" : "更新失败！");
		}
		return message;
	}

	@Override
	public Message removeCreditChannelConfigById(Long configId) {
		Message message = new Message();
		if (null != discountConfigurationDao.removeCreditChannelConfigById(configId)) {
			message.setSuccess(true);
			message.setMsg("删除成功！");
		} else {
			message.setSuccess(false);
			message.setMsg("删除失败！");
		}
		return message;
	}

	@Override
	public boolean checkExistOfConfig(String departmentId){
		return ((Integer)discountConfigurationDao.countCreditChannelConfig(departmentId)).intValue() > 0 ? true : false;
	}

	@Override
	public String queryIsZhxindai(Long creditapplicationId) {
		return discountConfigurationDao.queryIsZhxindai(creditapplicationId);
	}
}
