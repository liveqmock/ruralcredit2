package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.Website;
import com.creditease.rc.vo.WebsiteData;
import com.creditease.rc.vo.WebsiteInfoRequestParam;

public interface IWebsiteService {
	long insert(Website record);

	int updateByPrimaryKey(Website record);

	int updateByPrimaryKeySelective(Website record);

	Website selectByPrimaryKey(Long websiteId);

	int deleteByPrimaryKey(Long websiteId);

	WebsiteData qyWebsiteByMap(WebsiteInfoRequestParam websiteInfoRequestParam);

	/**
	 * 上传excel文件
	 * 
	 * @param entityList
	 */
	void uploadExcel(List<Website> entityList);
}
