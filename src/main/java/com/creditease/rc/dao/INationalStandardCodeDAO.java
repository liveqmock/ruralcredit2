package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.NationalStandardCode;
/**
 * 
 * @author zhangman
 *
 */
public interface INationalStandardCodeDAO {
/**
 * 
 * @param cityName 城市名称
 * @return 城市列表（国标列表）
 */
	public List<NationalStandardCode> selectNSC(String cityName);
	/**
	 * 
	 * @param cityName 城市名称
	 * @param parentId 父id（市区编码 city-code ， 省城市编码）
	 * @return 城市列表（国标列表）
	 */
	public List<NationalStandardCode> selectCity(String cityName,Integer parentId);
	/**
	 * 
	 * @param cityName 城市名称
	 * @return 城市列表（国标列表）
	 */
	public List<NationalStandardCode> selectPrefecture(String cityName);
	/**
	 * 按code查询
	 * @param cityCode 城市编码
	 * @return 国标对象
	 */
	public NationalStandardCode selectByCode(Integer cityCode);

    /**
     * 按 cityName 查询
     * @param  cityName 城市名称
     * @return
     */
    public List<NationalStandardCode> selectByCityName(String cityName);
    
    public List<NationalStandardCode> selectNSCByName(NationalStandardCode nationalStandardCode);
}
