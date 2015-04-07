package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.NationalStandardCode;
/**
 * 
 * @author zhangman
 *
 */
public interface INationalStandardCodeService {
	
	/**
	 * 获取所有的省
	 */
	public void initNationStandardCodeSheng();
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
	 * 通过一个 citycode返回他的父级字符串
	 * @param cityCode
	 * @return
	 */
	public String selectString(Integer cityCode);

	public Map<Integer, NationalStandardCode> cityCodeNationStandardMap();

    /**
     * 按 cityName 查询
     * @param  cityName 城市名称
     * @return
     */
    public NationalStandardCode selectByCityName(String cityName);
}
