package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IWebsiteDao;
import com.creditease.rc.domain.Website;
import com.creditease.rc.service.IWebsiteService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.WebsiteCity;
import com.creditease.rc.vo.WebsiteData;
import com.creditease.rc.vo.WebsiteInfoRequestParam;
import com.creditease.rc.vo.WebsiteProvince;
import com.creditease.rc.vo.WebsiteVo;

@Service
public class WebsiteService implements IWebsiteService {

	@Resource
	private IWebsiteDao websiteDao;

	@Override
	public long insert(Website record) {

		long num = 0;
		num = websiteDao.insert(record);
		return num;
	}

	@Override
	public int updateByPrimaryKey(Website record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(Website record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(Long websiteId) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 批量导入方法
	@Override
	public void uploadExcel(List<Website> entityList) {
		websiteDao.batchInsert(entityList);
	}

	@Override
	public Website selectByPrimaryKey(Long websiteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebsiteData qyWebsiteByMap(WebsiteInfoRequestParam websiteInfoRequestParam) {
		List<WebsiteVo> websiteVoList = websiteDao.qyWebsiteByMap(websiteInfoRequestParam);
		WebsiteData websiteData = new WebsiteData();
		List<WebsiteProvince> websiteProvinces = new ArrayList<WebsiteProvince>();
		Map<String, Set<String>> provinceCityListMap = new HashMap<String, Set<String>>();
		Map<String, List<WebsiteVo>> cityWebsiteVoMap = new HashMap<String, List<WebsiteVo>>();
		Map<String, String> codeNameMap = new HashMap<String, String>();
		for (WebsiteVo websiteVo : websiteVoList) {
			websiteVo.getBranchofficeId();
			websiteVo.getBranchofficeName();
			String getProvincehome = websiteVo.getProvincehome();
			String getProvinceName = websiteVo.getProvinceName();
			String getCityhome = websiteVo.getCityhome();
			String getCityName = websiteVo.getCityName();
			websiteVo.getCountyhome();
			websiteVo.getCountyName();

			codeNameMap.put(getProvincehome, getProvinceName);
			codeNameMap.put(getCityhome, getCityName);

			Set<String> cityCodes = provinceCityListMap.get(getProvincehome);
			if (CommonUtil.isEmpty(cityCodes)) {
				cityCodes = new HashSet<String>();
			}
			List<WebsiteVo> websiteVos = cityWebsiteVoMap.get(getCityhome);
			if (CommonUtil.isEmpty(websiteVos)) {
				websiteVos = new ArrayList<WebsiteVo>();
			}
			websiteVos.add(websiteVo);
			cityWebsiteVoMap.put(getCityhome, websiteVos);
			cityCodes.add(getCityhome);
			provinceCityListMap.put(getProvincehome, cityCodes);
		}
		Set<Entry<String, Set<String>>> provinceCityentries = provinceCityListMap.entrySet();
		for (Entry<String, Set<String>> provinceCityentrie : provinceCityentries) {
			WebsiteProvince websiteProvince = new WebsiteProvince();
			String getProvincehome = provinceCityentrie.getKey();
			Set<String> websiteCitieStrings = provinceCityentrie.getValue();
			String provinceName = codeNameMap.get(getProvincehome);
			websiteProvince.setProvinceCode(getProvincehome);
			websiteProvince.setProvinceName(provinceName);
			List<WebsiteCity> websiteCities = new ArrayList<WebsiteCity>();
			for (String websiteCityString : websiteCitieStrings) {
				List<WebsiteVo> websiteVos = cityWebsiteVoMap.get(websiteCityString);
				String cityName = codeNameMap.get(websiteCityString);
				WebsiteCity websiteCity = new WebsiteCity();
				websiteCity.setCityCode(websiteCityString);
				websiteCity.setCityName(cityName);
				websiteCity.setProvinceName(provinceName);
				websiteCity.setWebsiteVo(websiteVos);
				websiteCities.add(websiteCity);
			}
			websiteProvince.setWebsiteCity(websiteCities);
			websiteProvinces.add(websiteProvince);
		}
		websiteData.setWebsiteList(websiteProvinces);
		return websiteData;
	}
}
