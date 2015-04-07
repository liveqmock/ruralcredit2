package com.creditease.rc.service;

import com.creditease.rc.domain.LendingConfiguration;
import com.creditease.rc.framework.pager.Pagination;

public interface LendingConfigurationService {

    
    /**
     * 查询所有配置
     */
    public Pagination selectAll(Pagination pagination,LendingConfiguration lendingConfiguration);
    
    
    
    //新增和更新一条放款配置
    public Long insert(LendingConfiguration record);
    
    //根据id删除一条配置
    public int removeLendingConfiguration(Long lendingConfigurationId);
}
