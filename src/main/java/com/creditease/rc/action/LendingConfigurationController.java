package com.creditease.rc.action;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.LendingConfiguration;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.LendingConfigurationService;
@Controller
@RequestMapping("lendingConfigurationController")
public class LendingConfigurationController {

	@Resource
	private LendingConfigurationService configurationService;
	
	//查询所有
	@RequestMapping("selectAll")
	public @ResponseBody Pagination selectAll(LendingConfiguration lendingConfiguration,String page,String rows){
		
		Pagination pagination = new Pagination();
		if(!StringUtils.isBlank(page)){
			pagination.setPage(Integer.valueOf(page));
		}
		if(!StringUtils.isBlank(rows)){
			pagination.setPageSize(Integer.valueOf(rows));
		}
		 pagination = configurationService.selectAll(pagination, lendingConfiguration);
		 return pagination;
	}
	
	//保存和更新新增配置
	@RequestMapping("saveLendingConfiguration")
	public @ResponseBody Message  saveLendingConfiguration(LendingConfiguration configuration){
		Message message = new Message();
		try {
			configurationService.insert(configuration);
			message.setSuccess(true);
			message.setMsg("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			message.setSuccess(false);
			message.setMsg("操作失败");
		}
		return message;
	}
	
	//删除一条配置
	@RequestMapping("removeLendingConfiguration")
	public @ResponseBody Message removeLendingConfiguration(String lendingConfigurationId){
		Message message = new Message();
		try {
			configurationService.removeLendingConfiguration(Long.parseLong(lendingConfigurationId));
			message.setSuccess(true);
			message.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			message.setSuccess(false);
			message.setMsg("删除失败");
		}
		return message;
	}
}
