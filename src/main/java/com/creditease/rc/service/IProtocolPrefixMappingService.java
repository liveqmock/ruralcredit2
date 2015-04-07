/**
 * Title:IProtocolPrefixMappingService.java
 * Description:
 */
package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.ProtocolPrefixMapping;
import com.creditease.rc.framework.pager.Pagination;

/**
 * Title:IProtocolPrefixMappingService.java
 * Description:
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 
 * @author 韩大年
 * @version v1.0
 *          2013-3-18
 */
public interface IProtocolPrefixMappingService {

	/**
	 * 
	 * @author 韩大年
	 * @Description: 插入
	 * @param record
	 * @version v1.0
	 *          2013-3-18
	 */
	public void insert(ProtocolPrefixMapping record);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 全部更新
	 * @param record
	 * @return
	 * @version v1.0
	 *          2013-3-18
	 */
	public int updateByPrimaryKey(ProtocolPrefixMapping record);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 动态更新
	 * @param record
	 * @return
	 * @version v1.0
	 *          2013-3-18
	 */
	public int updateByPrimaryKeySelective(ProtocolPrefixMapping record);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询
	 * @param prefixId
	 * @return
	 * @version v1.0
	 *          2013-3-18
	 */
	public ProtocolPrefixMapping selectByPrimaryKey(Long prefixId);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 删除
	 * @param prefixId
	 * @return
	 * @version v1.0
	 *          2013-3-18
	 */
	public int deleteByPrimaryKey(Long prefixId);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 分页查询
	 * @param protocolPrefixMapping
	 * @param pagination
	 * @return
	 * @version v1.0
	 *          2013-3-18
	 */
	public Pagination selectProtocolPrefixMappingByPagination(ProtocolPrefixMapping protocolPrefixMapping,
			Pagination pagination);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询ProtocolPrefixMappingList
	 * @param protocolPrefixMapping
	 * @return
	 * @version v1.0
	 *          2013-3-25
	 */
	public List<ProtocolPrefixMapping> selectProtocolPrefixMappingListSelective(
			ProtocolPrefixMapping protocolPrefixMapping);

	/**
	 * 分组查询营业部和区/县对应关系
	 * 
	 * @author 郝强
	 * @return
	 */
	public List<ProtocolPrefixMapping> queryDistrictAndDepartment();
}
