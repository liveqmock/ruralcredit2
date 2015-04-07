package com.creditease.rc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.NationalStandardCode;
import com.creditease.rc.domain.Town;
import com.creditease.rc.service.INationalStandardCodeService;
import com.creditease.rc.service.ITownService;
import com.creditease.rc.vo.NationStandardProvencelist;
/**
 * 
 * @author zhangman
 *
 */
@Controller
@RequestMapping("NSC")
public class NationalStandardCodeController {
	
	@Resource
	private INationalStandardCodeService nationalStandardCodeService;
	
	@Resource
	private  ITownService townService;
	

	/**
	 * 
	 * @param q  城市的名称 easyui-combobox传值
	 * @return 国标码列表
	 */
	@RequestMapping(value="list")
	public @ResponseBody List<NationalStandardCode> showApplicarion(String q){
		return nationalStandardCodeService.selectNSC(q);
	}
	/**
	 * 
	 * @param q 城市的名称 easyui-combobox传值
	 * @param parentId 父id
	 * @return 国标码列表
	 */
	@RequestMapping(value="listCity")
	public @ResponseBody List<NationalStandardCode> showCity(String q,Integer parentId){
		//TODO 根据SMP查找 县信息
		List<NationalStandardCode> codes = new ArrayList<NationalStandardCode>();
		if(parentId != null && !("".equals(parentId))){
			 codes = nationalStandardCodeService.selectCity(q, parentId);	
		}
		return codes;
	}
	/**
	 * 
	 * @param q 城市的名称 easyui-combobox传值
	 * @return 国标码列表
	 */
	@RequestMapping(value="listPrefecture")
	public @ResponseBody List<NationalStandardCode> showPrefecture(String q){
		return nationalStandardCodeService.selectPrefecture(q);
	}
	/**
	 * 按城市编码 查询
	 * @param cityCode 城市编码
	 * @return 国标对象
	 */
	@RequestMapping(value = "selectByCode")
	public @ResponseBody
	NationalStandardCode selectByCode(Integer cityCode){
		return nationalStandardCodeService.selectByCode(cityCode);
	}
	
	/**
	 * 按登陆人的城市编码 查询 
	 * @return 国标对象
	 */
	@RequestMapping(value = "selectByAreaCode")
	public @ResponseBody
	Map selectByAreaCode(HttpServletRequest request){
		HttpSession session = request.getSession();
		Map returnMap = new HashMap();
		String cityCodeString = SpringSecurityUtils.getCurrentUser().getAreaCode();
			if(cityCodeString != null && cityCodeString != ""){
				Integer cityCode = Integer.valueOf(cityCodeString);
				NationalStandardCode nationalStandardCodeQu = nationalStandardCodeService.selectByCode(cityCode);
				NationalStandardCode nationalStandardCodeShi = nationalStandardCodeService.selectByCode(nationalStandardCodeQu.getParentId());
				
				List<NationalStandardCode> nationalStandardCodeQuList = nationalStandardCodeService.selectCity("",nationalStandardCodeQu.getParentId());
				List<NationalStandardCode> nationalStandardCodeShiList = nationalStandardCodeService.selectCity("",nationalStandardCodeShi.getParentId());
				List<Town>  toList = townService.selectTown("", nationalStandardCodeQu.getCityCode());
				
				returnMap.put("nationalStandardCodeQu", nationalStandardCodeQu);
				returnMap.put("nationalStandardCodeShi", nationalStandardCodeShi);
				returnMap.put("nationalStandardCodeSheng", NationStandardProvencelist.nationalStandardCodeslist);
				returnMap.put("nationalStandardCodeQuList", nationalStandardCodeQuList);
				returnMap.put("nationalStandardCodeShiList", nationalStandardCodeShiList);
				returnMap.put("toList", toList);
				return returnMap;
//				if(nationalStandardCodeQu != null){
//					returnMap.put("nationalStandardCode", nationalStandardCodeQu);
//					returnMap.put("cityCode", cityCode);
//					return returnMap;
//				}else{
//					return null;
//				}
			}else{
				return null;
			}
	}
	
	/**
	 * 通过一个 citycode返回他的父级字符串
	 * @param cityCode 城市编码
	 * @return Message 消息
	 * @throws Exception  异常
	 */
	@RequestMapping(value = "selectString")
	public @ResponseBody Message selectString(Integer cityCode) throws Exception{
		Message message = new Message();
		String re = nationalStandardCodeService.selectString(cityCode);
		message.setMsg(re);
//		String s2=new String(re.getBytes("utf-8"),"iso8859-1");
		return message;
	};
	
	
	/**
	 * 按登陆人的城市编码 查询 
	 * @return 国标对象
	 */
	@RequestMapping(value = "selectByVallageId")
	public @ResponseBody
	Map selectByVallageId(String VallageId,HttpServletRequest request){
		HttpSession session = request.getSession();
		Map returnMap = new HashMap();
		 Town townVallage = townService.searchParentId(Long.valueOf(VallageId));
		List<Town> townVallageList = townService.selectTown("", townVallage.getParentId());
		
		 Town townTown = townService.searchParentId(townVallage.getParentId());
		List<Town> townTownList = townService.selectTown("", townTown.getParentId());
		 
		NationalStandardCode nationalStandardCodeQu = nationalStandardCodeService.selectByCode(townTown.getParentId().intValue());
		List<NationalStandardCode> nationalStandardCodeQuList = nationalStandardCodeService.selectCity("",nationalStandardCodeQu.getParentId());
		
		NationalStandardCode nationalStandardCodeShi = nationalStandardCodeService.selectByCode(nationalStandardCodeQu.getParentId());
		List<NationalStandardCode> nationalStandardCodeShiList = nationalStandardCodeService.selectCity("",nationalStandardCodeShi.getParentId());
				
				
				returnMap.put("nationalStandardCodeSheng", NationStandardProvencelist.nationalStandardCodeslist);
				returnMap.put("nationalStandardCodeQu", nationalStandardCodeQu);
				returnMap.put("nationalStandardCodeShi", nationalStandardCodeShi);
				returnMap.put("nationalStandardCodeQuList", nationalStandardCodeQuList);
				returnMap.put("nationalStandardCodeShiList", nationalStandardCodeShiList);
				returnMap.put("townVallage", townVallage);
				returnMap.put("townVallageList", townVallageList);
				returnMap.put("townTownList", townTownList);
				returnMap.put("townTown", townTown);
				return returnMap;
	}
	
}
