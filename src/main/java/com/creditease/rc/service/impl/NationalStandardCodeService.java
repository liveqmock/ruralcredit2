package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.INationalStandardCodeDAO;
import com.creditease.rc.domain.NationalStandardCode;
import com.creditease.rc.service.INationalStandardCodeService;
import com.creditease.rc.vo.NationStandardProvencelist;

/**
 *
 * NationalStandardCodeService.java
 * @author yifengwang
 * 2012-12-24 下午04:07:56
 * @version V2.0
 */
@Service
public class NationalStandardCodeService implements
		INationalStandardCodeService {
	@Resource
	private INationalStandardCodeDAO nationalStandardCodeDAO;


	@PostConstruct
	public void initNationStandardCodeSheng() {
		List<NationalStandardCode> nationalStandardCodes = nationalStandardCodeDAO.selectNSC("");
		NationStandardProvencelist.nationalStandardCodeslist = nationalStandardCodes;
	}

	@Override
	public List<NationalStandardCode> selectNSC(String cityName) {
		// TODO Auto-generated method stub
		return nationalStandardCodeDAO.selectNSC(cityName);
	}
	@Override
	public List<NationalStandardCode> selectCity(
			String cityName,Integer parentId) {
		// TODO Auto-generated method stub
		return nationalStandardCodeDAO.selectCity(cityName, parentId);
	}
	@Override
	public List<NationalStandardCode> selectPrefecture(String cityName) {
		// TODO Auto-generated method stub
		return nationalStandardCodeDAO.selectPrefecture(cityName);
	}

	@Override
	public NationalStandardCode selectByCode(Integer cityCode) {
		// TODO Auto-generated method stub
		return nationalStandardCodeDAO.selectByCode(cityCode);
	}

	@Override
	public String selectString(Integer cityCode) {
		String addressString = "";
		NationalStandardCode nationalStandardCode = nationalStandardCodeDAO.selectByCode(cityCode);
		do{
			nationalStandardCode = nationalStandardCodeDAO.selectByCode(nationalStandardCode.getParentId());
			addressString =  nationalStandardCode.getCityName()+"-"+addressString;
		}
		while(nationalStandardCode.getParentId() != null);
//		{
//			nationalStandardCode = nationalStandardCodeDAO.selectByCode(cityCode);
//			addressString = nationalStandardCode+nationalStandardCode.getCityName();
//		}
		return addressString;
	}

	@Override
	public Map<Integer, NationalStandardCode> cityCodeNationStandardMap() {

        List<NationalStandardCode> list = nationalStandardCodeDAO.selectCity(null,null);
		Map<Integer, NationalStandardCode> map = new HashMap<Integer, NationalStandardCode>();
		for (NationalStandardCode nationalStandard : list) {
			map.put(nationalStandard.getCityCode(), nationalStandard);
		}

		return map;
	}

    @Override
    public NationalStandardCode selectByCityName(String cityName) {

        return nationalStandardCodeDAO.selectByCityName(cityName).isEmpty() ? null : nationalStandardCodeDAO.selectByCityName(cityName).get(0);
    }
}
