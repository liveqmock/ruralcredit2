package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.Website;
import com.creditease.rc.vo.WebsiteInfoRequestParam;
import com.creditease.rc.vo.WebsiteVo;

public interface IWebsiteDao {
   
	Long insert(Website record);
	
    int updateByPrimaryKey(Website record);

    int updateByPrimaryKeySelective(Website record);

    Website selectByPrimaryKey(Long websiteId);
    
    List<WebsiteVo> qyWebsiteByMap(WebsiteInfoRequestParam websiteInfoRequestParam);

    int deleteByPrimaryKey(Long websiteId);
    
    // 批量插入
	void batchInsert(List entityList);
	
	/**
	 * 根据营业部id查询信息（综合信贷）
	 * @param companyId
	 * @return
	 */
	public Website selectByCompanyId(String companyId);
}