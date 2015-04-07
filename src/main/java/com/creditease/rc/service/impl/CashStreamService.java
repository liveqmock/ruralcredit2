package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.creditease.rc.domain.*;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.*;
import com.creditease.rc.vo.CreditApplicationVo;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import com.creditease.rc.dao.ICashStreamDAO;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.CurrencyUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.vo.CashStreamVo;
import com.creditease.rc.vo.CashStreamWorkTableVo;

@Service
public class CashStreamService implements ICashStreamService {

	@Resource
	private ICashStreamDAO cashStreamDAO;
	@Resource
	private ICreditApplicationService creditApplicationService;

	@Resource
	private IMsgLogService msgLogService;

    @Resource
    private IDataDictionaryService dataDictionaryService;
    @Resource
    private IDiscountConfigurationService discountConfigurationService;
	@Override
	public int addCashStream(CashStream cashStream) {
		cashStreamDAO.addCashStream(cashStream);
		return 0;
	}

	@Override
	public int addCashStream(CashStreamVo cashStreamVo) {
		CashStream cs = cashStreamVo.getCsNetEveryWeekEq();
		// System.out.println("CashStreamService--addCashStream--userId="
		// + cs.getCreditapplicationId());
		cashStreamDAO.addCashStream(cs);
		return 0;
	}

	@Override
	public synchronized void addCashStreams(int creditAppId, String name, String type) {
		/** 2014年1月22日郝强去掉了这个判断 **/
// int count = this.getCountByCreditAppId(creditAppId);
// if (count == 0) {
		// 初始化表数据
		List<CashStream> css = this.initData(creditAppId, name, type);
		cashStreamDAO.addCashStreams(css);
		// }
	}

	@Override
	public int getCountByCreditAppId(int creditapplicationId) {
		int count = cashStreamDAO.getCountByCreditAppId(creditapplicationId);
		// System.out.println("CashStreamService--getCountByUserId--count="
		// + count);
		return count;
	}

	/*
	 * 根据业务单号获取相应数据
	 */
	@Override
	public List<CashStream> getCashStreamsByCreditAppId(int creditapplicationId) {
		List<CashStream> cashStreams = new ArrayList<CashStream>();
		cashStreams = cashStreamDAO.getCashStreamsByCreditAppId(creditapplicationId);
		return cashStreams;
	}

	/*
	 * 根据业务单id获取相应的数据并拼装成VO对象
	 */
	@Override
	public CashStreamVo getCashStreamVoByCreditAppId(int creditapplicationId, String name) {

		// 获取现金表里所有项目对象
		List<CashStream> cashStreamProjs = cashStreamDAO.getProjsByCreditAppId(creditapplicationId);
		// 获取经营对象
		/*List<Surveybusinessinfo> sbis = cashStreamDAO.getSbiByCreditAppId(creditapplicationId);*/
		
		//高艳红提交用于获取经营对象前三条   
		List<Surveybusinessinfo> sbis = cashStreamDAO.getSbiByCreditAppIdNew(creditapplicationId);
		
		// 更新现金流表里的项目对象名称
		updateProjName(cashStreamProjs, sbis);
		// 更新借款人姓名
		updateBorrowerName(cashStreamProjs, name);

		List<CashStream> cashStreams = new ArrayList<CashStream>();
		cashStreams = cashStreamDAO.getCashStreamsByCreditAppId(creditapplicationId);
		
		return this.assemblyObj(cashStreams);
	}

	/*
	 * 根据业务单id获取相应的数据并拼装成VO对象
	 */
	@Override
	public CashStreamVo getCashStreamVoByCreditAppIdForView(int creditapplicationId) {
		List<CashStream> cashStreams = new ArrayList<CashStream>();
		cashStreams = cashStreamDAO.getCashStreamsByCreditAppId(creditapplicationId);

		if (cashStreams != null && cashStreams.size() > 0) {
			return this.assemblyObj(cashStreams);
		} else {
			return null;
		}

	}

	/*
	 * 根据业务单id获取相应的数据并拼装成VO对象
	 */
	@Override
	public CashStreamVo getCashStreamVoByCreditAppId(int creditapplicationId) {

		// 获取现金表里所有项目对象
		List<CashStream> cashStreamProjs = cashStreamDAO.getProjsByCreditAppId(creditapplicationId);
		// 获取借款人的经营项目
		List<Surveybusinessinfo> sbis = cashStreamDAO.getSbiByCreditAppId(creditapplicationId);
		// 更新现金流表里的项目对象名称
		updateProjName(cashStreamProjs, sbis);

		List<CashStream> cashStreams = new ArrayList<CashStream>();
		cashStreams = cashStreamDAO.getCashStreamsByCreditAppId(creditapplicationId);

		return this.assemblyObj(cashStreams);
	}

	/**
	 * 更新现金流表里的项目对象名称
	 * 
	 * @param cashStreamProjs
	 *            所有项目对象
	 * @param sbis
	 *            经营对象
	 */
	public void updateProjName(List<CashStream> cashStreamProjs, List<Surveybusinessinfo> sbis) {

		// 经营流入项目
		List<CashStream> operIn = new ArrayList<CashStream>();
		// 经营流出项目
		List<CashStream> operOut = new ArrayList<CashStream>();
		int cspSize = cashStreamProjs.size();
		for (int j = 0; j < cspSize; j++) {
			CashStream cs = cashStreamProjs.get(j);
			// 经营流入
			if (cs.getProjectType().startsWith("operIn")) {
				operIn.add(cs);
			} else if (cs.getProjectType().startsWith("operOut")) {// 经营流出
				operOut.add(cs);
			}
		}
		// 遍历借款人的经营项目
		int sbiSize = sbis.size();
		for (int i = sbiSize - 1; i > -1; i--) {
			Surveybusinessinfo sbi = sbis.get(i);
			String id = sbi.getSurveyBusinessInfoId().toString();
			// 如果列表中已经存有该id的条目则更新
			if (null != operIn && operIn.size() > 0) {
				CashStream cs1 = isInList(id, operIn);
				if (cs1.getProjectType().split("_").length != 2) {
					// 名字不同才需要更新
					// if (cs1.getProjectName() != sbi.getOperatingItemsDetail()) {
					cs1.setProjectName(sbi.getOperatingItemsDetail());
					cs1.setProjectCodeKey(sbi.getOperatingItems());
					cashStreamDAO.updateCsProjNameById(cs1);
					// }
				} else {
					cs1.setProjectType(cs1.getProjectType() + "_" + id);
					cs1.setProjectName(sbi.getOperatingItemsDetail());
					cs1.setProjectCodeKey(sbi.getOperatingItems());
					cashStreamDAO.updateCsProjNameAndTypeById(cs1);
				}
				removeInList(cs1, operIn);
			}

			if (null != operOut && operOut.size() > 0) {
				CashStream cs2 = isInList(id, operOut);
				if (cs2.getProjectType().split("_").length == 2) {
					cs2.setProjectType(cs2.getProjectType() + "_" + id);
					cs2.setProjectName(sbi.getOperatingItemsDetail());
					cs2.setProjectCodeKey(sbi.getOperatingItems());
					cashStreamDAO.updateCsProjNameAndTypeById(cs2);
				} else {
					cs2.setProjectName(sbi.getOperatingItemsDetail());
					cs2.setProjectCodeKey(sbi.getOperatingItems());
					cashStreamDAO.updateCsProjNameById(cs2);
				}
				removeInList(cs2, operOut);
			}
		}
	}

	/**
	 * 更新借款人姓名
	 */
	public void updateBorrowerName(List<CashStream> css, String name) {
		CashStream c1 = new CashStream();
		if (css != null && css.size() > 1) {
			c1 = css.get(0);
			if (c1.getBorrowerName() != name) {
				c1.setBorrowerName(name);
				cashStreamDAO.updateBorrowerName(c1);
			}
		}
	}

	/**
	 * 判断该id是否在列表中,如果在，则返回该对象，如果不在，则返回一个没有包含任何id的对象
	 * 
	 * @param id
	 * @param l
	 * @return
	 */
	public CashStream isInList(String id, List<CashStream> l) {
		CashStream cs = null;
		for (int i = 0; i < l.size(); i++) {
			cs = l.get(i);
			if (cs.getProjectType().contains(id)) {
				return cs;
			}
		}
		if (cs == null) {
			for (int i = 0; i < l.size(); i++) {
				CashStream csT = l.get(i);
				String projType = csT.getProjectType();
				String[] types = projType.split("_");
				if (types.length == 2) {
					cs = csT;
					break;
				}
			}
		}
		return cs;
	}

	public void removeInList(CashStream cs, List<CashStream> l) {
		int position = 0;
		for (int i = 0; i < l.size(); i++) {
			CashStream csT = l.get(i);
			if (csT.getCashStreamId().equals(cs.getCashStreamId())) {
				position = i;
			}
		}
		l.remove(position);
	}

	/*
	 * 更新现金流入流出表
	 */
	@Override
	public int updateCashStream(CashStreamVo cashStreamVo) {
		List<CashStream> l = new ArrayList<CashStream>();
		// 分类进行相应的计算
		// this.calculate(cashStreamVo);

		//调用计算方法
		// int bb=cashStreamServiceCalcMaxMoney(cashStreamVo);
		// System.out.println(bb);
		// 将计算过的数据添加到数组
		l.addAll(cashStreamVo.getCsOperInList());
		l.add(cashStreamVo.getCsOperInTotal());
		l.addAll(cashStreamVo.getCsOperOutListChild());
		l.addAll(cashStreamVo.getCsOperOutList());
		l.add(cashStreamVo.getCsOperOutTotal());
		l.addAll(cashStreamVo.getCsFamilyInList());
		l.add(cashStreamVo.getCsFamilyInTotal());
		l.addAll(cashStreamVo.getCsFamilyOutList());
		l.add(cashStreamVo.getCsFamilyOutTotal());
		l.add(cashStreamVo.getCsFamilyNet());
		l.add(cashStreamVo.getCsOperNet());
		l.add(cashStreamVo.getCsNetTotal());
		l.add(cashStreamVo.getCsNetEveryDayEq());
		l.add(cashStreamVo.getCsNetEveryWeekEq());
		l.add(cashStreamVo.getCsNetEveryMonthEq());
		l.add(cashStreamVo.getCsNetEveryQuarterEq());
		l.add(cashStreamVo.getCsNetEveryHalfYearEq());
		//l.add(cashStreamVo.getCsNetEverynineMonthEq());
		l.add(cashStreamVo.getCsNetEveryYearEq());
		l.add(cashStreamVo.getCanDominateMoney());
		l.add(cashStreamVo.getApplyLoanSum());
		CashStream yearRate = cashStreamVo.getYearInterestRate();
		yearRate.setCalculateValue(CurrencyUtil.div(yearRate.getCalculateValue(), Double.valueOf("100")));
		l.add(yearRate);
		l.add(cashStreamVo.getMaxLoanSum());

		for (int i = 0; i < l.size(); i++) {
			CashStream cs = l.get(i);
			cashStreamDAO.updateById(cs);
		}
		return 0;
	}
	
	/**
	 * @author hongjieluo
	 * 计算现金流旧数据方法
	 * 2014-06-12    2014-05-09  到2014-06-05（包含当天的）旧数据
	 * begin
	 */
	@Override
	public void oldCashStreamCalcMaxMoney(){
		List<CashStream> cashStreamVoList=cashStreamDAO.selectOldCashStreamList();
		for(CashStream cashStream:cashStreamVoList){
			CashStreamVo cashStreamVo=this.getCashStreamVoByCreditAppIdForView(cashStream.getCreditapplicationId());
			//System.out.println("***************************");
//			System.out.println(cashStreamVo);
//			System.out.println(cashStreamVo.getMaxLoanSum().getCalculateValue());
//			System.out.println(cashStream.getCreditapplicationId());
//			System.out.println("***************************");
			if(cashStreamVo.getMaxLoanSum().getCalculateValue()!=null){
			this.updateCashStreamServiceOldDataCalcMaxMoney(cashStreamVo,cashStream.getCreditapplicationId());
			}
		}
		//System.out.println(cashStreamVoList.size());
	}
	
	
	/**
	 * @author hongjieluo
	 * 计算现金流旧数据方法
	 * 2014-06-12
	 * end
	 */
	
	
	/**
	 * @author hongjieluo
	 * 计算现金流旧数据方法
	 * 2014-06-30    2014-05-09之前做的单子但是在05-09那天做的现金流计算的旧数据
	 * begin
	 */
	@Override
	public void oldCashStreamCalcMaxMoney140509Before(){
		List<CashStream> cashStreamVoList=cashStreamDAO.selectOldCashStreamList140509Before();
		for(CashStream cashStream:cashStreamVoList){
			CashStreamVo cashStreamVo=this.getCashStreamVoByCreditAppIdForView(cashStream.getCreditapplicationId());
			//System.out.println("***************************");
			//System.out.println(cashStreamVo);
			//System.out.println(cashStreamVo.getMaxLoanSum().getCalculateValue());
			//System.out.println(cashStream.getCreditapplicationId());
			//System.out.println("***************************");
			if(cashStreamVo!=null&&cashStreamVo.getMaxLoanSum().getCalculateValue()!=null){
			this.updateCashStreamServiceOldDataCalcMaxMoney(cashStreamVo,cashStream.getCreditapplicationId());
			}
		}
		//System.out.println(cashStreamVoList.size());
	}
	
	
	/**
	 * @author hongjieluo
	 * 计算现金流旧数据方法
	 * 2014-06-12
	 * end
	 */
	
	
	
	
	
	/**
	 * @author hongjieluo
	 * 计算现金流旧数据的计算公式，专用，只用一次
	 * 2014-06-25
	 * begin
	 */
	@Override
	public BigDecimal updateCashStreamServiceOldDataCalcMaxMoney(CashStreamVo cashStreamVo,int creditapplicationId) {
		/**
		 * 以下为罗红杰计算
		 * 现金流入流出项目      每天的净现金流合计   
		 * begin
		 */
		//经营现金流入项目
		List<CashStream> csoperInList=cashStreamVo.getCsOperInList();
		//经营现金流出项目子项目
		List<CashStream> csOperOutListChild=cashStreamVo.getCsOperOutListChild();
		//经营现金流流出项目
		List<CashStream> csoperOutList=cashStreamVo.getCsOperOutList();
		//家庭现金流入项目
		List<CashStream> csFamilyInList=cashStreamVo.getCsFamilyInList();
		//家庭现金流出项目
		List<CashStream> csFamilyOutList=cashStreamVo.getCsFamilyOutList();
		
		
		//BigDecimal csoperInEveryMouthSum=new BigDecimal(0);	//经营现金流入每月和
		//BigDecimal算数
		BigDecimal csoperInEveryDaySum = new BigDecimal(0);  //经营现金流入每天合计和
		for(int i=0;i<csoperInList.size();i++){
			if(csoperInList.get(i).getEveryDay()!=null){
			BigDecimal inEveryDay = new BigDecimal(csoperInList.get(i).getEveryDay());
			csoperInEveryDaySum=csoperInEveryDaySum.add(inEveryDay);
			}
		}
		BigDecimal csoperOutEveryDayChildSum=new BigDecimal(0); //经营现金流出项目子项目每天和
		
		for(int iChild=0;iChild<csOperOutListChild.size();iChild++){
			if(csOperOutListChild.get(iChild).getEveryDay()!=null){
			BigDecimal csOperOutChildEveryDay=new BigDecimal(csOperOutListChild.get(iChild).getEveryDay());
			csoperOutEveryDayChildSum=csoperOutEveryDayChildSum.add(csOperOutChildEveryDay);
			}
		}
		
		BigDecimal csoperOutEveryDaySum=new BigDecimal(0);	 //经营现金流出每天合计和
		//因为采购成本不被计入，所以i从1开始
		for(int j=1;j<csoperOutList.size();j++){
			if(csoperOutList.get(j).getEveryDay()!=null){
				BigDecimal csoutEveryDay = new BigDecimal(csoperOutList.get(j).getEveryDay());
				csoperOutEveryDaySum=csoperOutEveryDaySum.add(csoutEveryDay);
				continue;
			}
		}
		BigDecimal csFamilyInEveryDaySum=new BigDecimal(0);	//家庭现金流入每天和
		for(int fi=0;fi<csFamilyInList.size();fi++){
			if(csFamilyInList.get(fi).getEveryDay()!=null){
			BigDecimal familyInEveryDay=new BigDecimal(csFamilyInList.get(fi).getEveryDay());
			csFamilyInEveryDaySum=csFamilyInEveryDaySum.add(familyInEveryDay);
			}
		}
		BigDecimal csFamilyOutEveryDayListSum=new BigDecimal(0); //家庭现金流出每天和
		for(int fo=0;fo<csFamilyOutList.size();fo++){
			if(csFamilyOutList.get(fo).getEveryDay()!=null){
				BigDecimal familyOutEveryDay=new BigDecimal(csFamilyOutList.get(fo).getEveryDay());
				csFamilyOutEveryDayListSum=csFamilyOutEveryDayListSum.add(familyOutEveryDay);
			}
		}
		//净现金流合计  每天      SUM(C6:C8)-SUM(C12:C29)+SUM(C33:C36)-SUM(C38:C55)
		BigDecimal StreamEveryDaySum=new BigDecimal(0);
		StreamEveryDaySum=csoperInEveryDaySum.subtract(csoperOutEveryDaySum.add(csoperOutEveryDayChildSum))
				.add(csFamilyInEveryDaySum).subtract(csFamilyOutEveryDayListSum);	
				//System.out.println("净现金流合计  每天:  "+StreamEveryDaySum);
		/**
		 * 每天净现金流合计
		 * end
		 */
		
		/**
		 * 现金流入流出项目    每周的净现金流合计
		 * begin
		 */
		BigDecimal csoperInEveryWeekSum=new BigDecimal(0);	//经营现金流入每周和
		for(int cIw=0;cIw<csoperInList.size();cIw++){
			if(csoperInList.get(cIw).getEveryWeek()!=null){
			BigDecimal csInEveryWeek=new BigDecimal(csoperInList.get(cIw).getEveryWeek());
			csoperInEveryWeekSum=csoperInEveryWeekSum.add(csInEveryWeek);
			}
		}
		BigDecimal csoperOutEveryWeekChildSum=new BigDecimal(0); //经营现金流出项目子项目每周和
		for(int iChildW=0;iChildW<csOperOutListChild.size();iChildW++){
			if(csOperOutListChild.get(iChildW).getEveryWeek()!=null){
			BigDecimal csOperOutChildEveryWeek=new BigDecimal(csOperOutListChild.get(iChildW).getEveryWeek());
			csoperOutEveryWeekChildSum=csoperOutEveryWeekChildSum.add(csOperOutChildEveryWeek);
			}
		}
		
		BigDecimal csoperOutEveryWeekSum=new BigDecimal(0);	//经营现金流出每周和
		//因为采购成本不被计入，所以i从1开始
		for(int cOw=1;cOw<csoperOutList.size();cOw++){
			if(csoperOutList.get(cOw).getEveryWeek()!=null){
				BigDecimal csOutEveryWeek=new BigDecimal(csoperOutList.get(cOw).getEveryWeek());
				csoperOutEveryWeekSum=csoperOutEveryWeekSum.add(csOutEveryWeek);
			}
		}
		BigDecimal csFamilyInEveryWeekSum=new BigDecimal(0);	//家庭现金流入每周和
		for(int cFw=0;cFw<csFamilyInList.size();cFw++){
			if(csFamilyInList.get(cFw).getEveryWeek()!=null){
				BigDecimal familyInEveryWeek=new BigDecimal(csFamilyInList.get(cFw).getEveryWeek());
				csFamilyInEveryWeekSum=csFamilyInEveryWeekSum.add(familyInEveryWeek);
			}
		}
		BigDecimal csFamilyOutEveryWeekSum=new BigDecimal(0);	//家庭现金流入每周和
		for(int cFw=0;cFw<csFamilyOutList.size();cFw++){
			if(csFamilyOutList.get(cFw).getEveryWeek()!=null){
				BigDecimal familyInEveryWeek=new BigDecimal(csFamilyOutList.get(cFw).getEveryWeek());
				csFamilyOutEveryWeekSum=csFamilyOutEveryWeekSum.add(familyInEveryWeek);
			}
		}
		//净现金流合计  每周   SUM(D6:D8)-SUM(D12:D29)+SUM(D33:D36)-SUM(D38:D55)
		BigDecimal StreamEveryWeekSum=new BigDecimal(0);
		StreamEveryWeekSum=csoperInEveryWeekSum.subtract(csoperOutEveryWeekSum.add(csoperOutEveryWeekChildSum))
				.add(csFamilyInEveryWeekSum).subtract(csFamilyOutEveryWeekSum);	
		//System.out.println("净现金流合计  每周："+StreamEveryWeekSum);
		
		/**
		 * 每周净现金流合计
		 * end
		 */
		
		/**
		 * 现金流入流出项目    每月的净现金流合计
		 * begin
		 */
		BigDecimal csoperInEveryMonthSum=new BigDecimal(0);	//经营现金流入每月和
		for(int cOm=0;cOm<csoperInList.size();cOm++){
			if(csoperInList.get(cOm).getEveryMonth()!=null){
				BigDecimal csInEveryMonth=new BigDecimal(csoperInList.get(cOm).getEveryMonth());
				csoperInEveryMonthSum=csoperInEveryMonthSum.add(csInEveryMonth);
				}
		}
		BigDecimal csoperOutEveryMonthChildSum=new BigDecimal(0); //经营现金流出项目子项目每月和
		for(int iChildW=0;iChildW<csOperOutListChild.size();iChildW++){
			if(csOperOutListChild.get(iChildW).getEveryMonth()!=null){
			BigDecimal csOperOutChildEveryMonth=new BigDecimal(csOperOutListChild.get(iChildW).getEveryMonth());
			csoperOutEveryMonthChildSum=csoperOutEveryMonthChildSum.add(csOperOutChildEveryMonth);
			}
		}
		BigDecimal csoperOutEveryMonthSum=new BigDecimal(0);	//经营现金流出每月和
		//因为采购成本不被计入，所以i从1开始
		for(int cOw=1;cOw<csoperOutList.size();cOw++){
			if(csoperOutList.get(cOw).getEveryMonth()!=null){
				BigDecimal csOutEveryMonth=new BigDecimal(csoperOutList.get(cOw).getEveryMonth());
				csoperOutEveryMonthSum=csoperOutEveryMonthSum.add(csOutEveryMonth);
			}
		}
		BigDecimal csFamilyInEveryMonthSum=new BigDecimal(0);	//家庭现金流入每月和
		for(int cFw=0;cFw<csFamilyInList.size();cFw++){
			if(csFamilyInList.get(cFw).getEveryMonth()!=null){
				BigDecimal familyInEveryMonth=new BigDecimal(csFamilyInList.get(cFw).getEveryMonth());
				csFamilyInEveryMonthSum=csFamilyInEveryMonthSum.add(familyInEveryMonth);
			}
		}
		BigDecimal csFamilyOutEveryMonthSum=new BigDecimal(0);	//家庭现金流入每周和
		for(int cFw=0;cFw<csFamilyOutList.size();cFw++){
			if(csFamilyOutList.get(cFw).getEveryMonth()!=null){
				BigDecimal familyInEveryMonth=new BigDecimal(csFamilyOutList.get(cFw).getEveryMonth());
				csFamilyOutEveryMonthSum=csFamilyOutEveryMonthSum.add(familyInEveryMonth);
			}
		}
		
		//净现金流合计  每月		SUM(E6:E8)-SUM(E12:E29)+SUM(E33:E36)-SUM(E38:E55)
		BigDecimal StreamEveryMonthSum=new BigDecimal(0);
		StreamEveryMonthSum=csoperInEveryMonthSum.subtract(csoperOutEveryMonthSum.add(csoperOutEveryMonthChildSum))
		.add(csFamilyInEveryMonthSum).subtract(csFamilyOutEveryMonthSum);
		
		//System.out.println("净现金流合计  每月"+StreamEveryMonthSum);
		/**
		 * 每月净现金流合计
		 * end
		 */
		
		
		
		/**
		 * 现金流入流出项目    每季度的净现金流合计everyQuarter
		 * begin
		 */
		BigDecimal csoperInEveryQuarterSum=new BigDecimal(0);	//经营现金流入每季度和
		for(int cOm=0;cOm<csoperInList.size();cOm++){
			if(csoperInList.get(cOm).getEveryQuarter()!=null){
				BigDecimal csInEveryQuarter=new BigDecimal(csoperInList.get(cOm).getEveryQuarter());
				csoperInEveryQuarterSum=csoperInEveryQuarterSum.add(csInEveryQuarter);
				}
		}
		BigDecimal csoperOutEveryQuarterChildSum=new BigDecimal(0); //经营现金流出项目子项目每季度和
		for(int iChildW=0;iChildW<csOperOutListChild.size();iChildW++){
			if(csOperOutListChild.get(iChildW).getEveryQuarter()!=null){
			BigDecimal csOperOutChildEveryQuarter=new BigDecimal(csOperOutListChild.get(iChildW).getEveryQuarter());
			csoperOutEveryQuarterChildSum=csoperOutEveryQuarterChildSum.add(csOperOutChildEveryQuarter);
			}
		}
		BigDecimal csoperOutEveryQuarterSum=new BigDecimal(0);	//经营现金流出每季度和
		//因为采购成本不被计入，所以i从1开始
		for(int cOw=1;cOw<csoperOutList.size();cOw++){
			if(csoperOutList.get(cOw).getEveryQuarter()!=null){
				BigDecimal csOutEveryQuarter=new BigDecimal(csoperOutList.get(cOw).getEveryQuarter());
				csoperOutEveryQuarterSum=csoperOutEveryQuarterSum.add(csOutEveryQuarter);
			}
		}
		BigDecimal csFamilyInEveryQuarterSum=new BigDecimal(0);	//家庭现金流入每季度和
		for(int cFw=0;cFw<csFamilyInList.size();cFw++){
			if(csFamilyInList.get(cFw).getEveryQuarter()!=null){
				BigDecimal familyInEveryQuarter=new BigDecimal(csFamilyInList.get(cFw).getEveryQuarter());
				csFamilyInEveryQuarterSum=csFamilyInEveryQuarterSum.add(familyInEveryQuarter);
			}
		}
		BigDecimal csFamilyOutEveryQuarterSum=new BigDecimal(0);	//家庭现金流入每季度和
		for(int cFw=0;cFw<csFamilyOutList.size();cFw++){
			if(csFamilyOutList.get(cFw).getEveryQuarter()!=null){
				BigDecimal familyInEveryQuarter=new BigDecimal(csFamilyOutList.get(cFw).getEveryQuarter());
				csFamilyOutEveryQuarterSum=csFamilyOutEveryQuarterSum.add(familyInEveryQuarter);
			}
		}
		
		//净现金流合计  每季度   SUM(F6:F8)-SUM(F12:F29)+SUM(F33:F36)-SUM(F38:F55)
		BigDecimal StreamEveryQuarterSum=new BigDecimal(0);
		StreamEveryQuarterSum=csoperInEveryQuarterSum.subtract(csoperOutEveryQuarterSum.add(csoperOutEveryQuarterChildSum))
		.add(csFamilyInEveryQuarterSum).subtract(csFamilyOutEveryQuarterSum);
		
		//System.out.println("净现金流合计  每季度"+StreamEveryQuarterSum);
		
		/**
		 * 每季度 净现金流合计
		 * end
		 */
		
		
		
		/**
		 * 现金流入流出项目    每半年的净现金流合计everyHalfYear
		 * begin
		 */
		BigDecimal csoperInEveryHalfYearSum=new BigDecimal(0);	//经营现金流入每半年和
		for(int cOm=0;cOm<csoperInList.size();cOm++){
			if(csoperInList.get(cOm).getEveryHalfYear()!=null){
				BigDecimal csInEveryHalfYear=new BigDecimal(csoperInList.get(cOm).getEveryHalfYear());
				csoperInEveryHalfYearSum=csoperInEveryHalfYearSum.add(csInEveryHalfYear);
				}
		}
		BigDecimal csoperOutEveryHalfYearChildSum=new BigDecimal(0); //经营现金流出项目子项目每半年和
		for(int iChildW=0;iChildW<csOperOutListChild.size();iChildW++){
			if(csOperOutListChild.get(iChildW).getEveryHalfYear()!=null){
			BigDecimal csOperOutChildEveryHalfYear=new BigDecimal(csOperOutListChild.get(iChildW).getEveryHalfYear());
			csoperOutEveryHalfYearChildSum=csoperOutEveryHalfYearChildSum.add(csOperOutChildEveryHalfYear);
			}
		}
		BigDecimal csoperOutEveryHalfYearSum=new BigDecimal(0);	//经营现金流出每半年和
		//因为采购成本不被计入，所以i从1开始
		for(int cOw=1;cOw<csoperOutList.size();cOw++){
			if(csoperOutList.get(cOw).getEveryHalfYear()!=null){
				BigDecimal csOutEveryHalfYear=new BigDecimal(csoperOutList.get(cOw).getEveryHalfYear());
				csoperOutEveryHalfYearSum=csoperOutEveryHalfYearSum.add(csOutEveryHalfYear);
			}
		}
		BigDecimal csFamilyInEveryHalfYearSum=new BigDecimal(0);	//家庭现金流入每半年和
		for(int cFw=0;cFw<csFamilyInList.size();cFw++){
			if(csFamilyInList.get(cFw).getEveryHalfYear()!=null){
				BigDecimal familyInEveryHalfYear=new BigDecimal(csFamilyInList.get(cFw).getEveryHalfYear());
				csFamilyInEveryHalfYearSum=csFamilyInEveryHalfYearSum.add(familyInEveryHalfYear);
			}
		}
		BigDecimal csFamilyOutEveryHalfYearSum=new BigDecimal(0);	//家庭现金流入每半年和
		for(int cFw=0;cFw<csFamilyOutList.size();cFw++){
			if(csFamilyOutList.get(cFw).getEveryHalfYear()!=null){
				BigDecimal familyInEveryHalfYear=new BigDecimal(csFamilyOutList.get(cFw).getEveryHalfYear());
				csFamilyOutEveryHalfYearSum=csFamilyOutEveryHalfYearSum.add(familyInEveryHalfYear);
			}
		}
		
		//净现金流合计  每半年  SUM(G6:G8)-SUM(G12:G29)+SUM(G33:G36)-SUM(G38:G55)
		BigDecimal StreamEveryHalfYearSum=new BigDecimal(0);
		StreamEveryHalfYearSum=csoperInEveryHalfYearSum.subtract(csoperOutEveryHalfYearSum.add(csoperOutEveryHalfYearChildSum))
		.add(csFamilyInEveryHalfYearSum).subtract(csFamilyOutEveryHalfYearSum);
		
		//System.out.println("净现金流合计  每半年"+StreamEveryHalfYearSum);
		
		/**
		 * 每半年 净现金流合计
		 * end
		 */
		
		
		
		/**
		 * 现金流入流出项目    每9个月的净现金流合计NineMonth
		 * begin
		 */
		BigDecimal csoperInNineMonthSum=new BigDecimal(0);	//经营现金流入每9个月和
		for(int cOm=0;cOm<csoperInList.size();cOm++){
			if(csoperInList.get(cOm).getNineMonth()!=null){
				BigDecimal csInNineMonth=new BigDecimal(csoperInList.get(cOm).getNineMonth());
				csoperInNineMonthSum=csoperInNineMonthSum.add(csInNineMonth);
				}
		}
		BigDecimal csoperOutNineMonthChildSum=new BigDecimal(0); //经营现金流出项目子项目每9个月和
		for(int iChildW=0;iChildW<csOperOutListChild.size();iChildW++){
			if(csOperOutListChild.get(iChildW).getNineMonth()!=null){
			BigDecimal csOperOutChildNineMonth=new BigDecimal(csOperOutListChild.get(iChildW).getNineMonth());
			csoperOutNineMonthChildSum=csoperOutNineMonthChildSum.add(csOperOutChildNineMonth);
			}
		}
		BigDecimal csoperOutNineMonthSum=new BigDecimal(0);	//经营现金流出每9个月和
		//因为采购成本不被计入，所以i从1开始
		for(int cOw=1;cOw<csoperOutList.size();cOw++){
			if(csoperOutList.get(cOw).getNineMonth()!=null){
				BigDecimal csOutNineMonth=new BigDecimal(csoperOutList.get(cOw).getNineMonth());
				csoperOutNineMonthSum=csoperOutNineMonthSum.add(csOutNineMonth);
			}
		}
		BigDecimal csFamilyInNineMonthrSum=new BigDecimal(0);	//家庭现金流入每9个月和
		for(int cFw=0;cFw<csFamilyInList.size();cFw++){
			if(csFamilyInList.get(cFw).getNineMonth()!=null){
				BigDecimal familyInNineMonth=new BigDecimal(csFamilyInList.get(cFw).getNineMonth());
				csFamilyInNineMonthrSum=csFamilyInNineMonthrSum.add(familyInNineMonth);
			}
		}
		BigDecimal csFamilyOutNineMonthSum=new BigDecimal(0);	//家庭现金流入每9个月和
		for(int cFw=0;cFw<csFamilyOutList.size();cFw++){
			if(csFamilyOutList.get(cFw).getNineMonth()!=null){
				BigDecimal familyInNineMonth=new BigDecimal(csFamilyOutList.get(cFw).getNineMonth());
				csFamilyOutNineMonthSum=csFamilyOutNineMonthSum.add(familyInNineMonth);
			}
		}
		
		//净现金流合计  每9个月
		BigDecimal StreamNineMonthSum=new BigDecimal(0);
		StreamNineMonthSum=csoperInNineMonthSum.subtract(csoperOutNineMonthSum.add(csoperOutNineMonthChildSum))
		.add(csFamilyInNineMonthrSum).subtract(csFamilyOutNineMonthSum);
		
		
		/**
		 * 每9个月 净现金流合计
		 * end
		 */
		
		
		/**
		 * 现金流入流出项目    每年的净现金流合计EveryYear
		 * begin
		 */
		BigDecimal csoperInEveryYearSum=new BigDecimal(0);	//经营现金流入每年和
		for(int cOm=0;cOm<csoperInList.size();cOm++){
			if(csoperInList.get(cOm).getEveryYear()!=null){
				BigDecimal csInNineMonth=new BigDecimal(csoperInList.get(cOm).getEveryYear());
				csoperInEveryYearSum=csoperInEveryYearSum.add(csInNineMonth);
				}
		}
		BigDecimal csoperOutEveryYearChildSum=new BigDecimal(0); //经营现金流出项目子项目每年和
		for(int iChildW=0;iChildW<csOperOutListChild.size();iChildW++){
			if(csOperOutListChild.get(iChildW).getEveryYear()!=null){
			BigDecimal csOperOutChildEveryYear=new BigDecimal(csOperOutListChild.get(iChildW).getEveryYear());
			csoperOutEveryYearChildSum=csoperOutEveryYearChildSum.add(csOperOutChildEveryYear);
			}
		}
		BigDecimal csoperOutEveryYearSum=new BigDecimal(0);	//经营现金流出每年和
		//因为采购成本不被计入，所以i从1开始
		for(int cOw=1;cOw<csoperOutList.size();cOw++){
			if(csoperOutList.get(cOw).getEveryYear()!=null){
				BigDecimal csOutEveryYear=new BigDecimal(csoperOutList.get(cOw).getEveryYear());
				csoperOutEveryYearSum=csoperOutEveryYearSum.add(csOutEveryYear);
			}
		}
		BigDecimal csFamilyInEveryYearSum=new BigDecimal(0);	//家庭现金流入每年和
		for(int cFw=0;cFw<csFamilyInList.size();cFw++){
			if(csFamilyInList.get(cFw).getEveryYear()!=null){
				BigDecimal familyInEveryYear=new BigDecimal(csFamilyInList.get(cFw).getEveryYear());
				csFamilyInEveryYearSum=csFamilyInEveryYearSum.add(familyInEveryYear);
			}
		}
		BigDecimal csFamilyOutEveryYearSum=new BigDecimal(0);	//家庭现金流入每年和
		for(int cFw=0;cFw<csFamilyOutList.size();cFw++){
			if(csFamilyOutList.get(cFw).getEveryYear()!=null){
				BigDecimal familyInEveryYear=new BigDecimal(csFamilyOutList.get(cFw).getEveryYear());
				csFamilyOutEveryYearSum=csFamilyOutEveryYearSum.add(familyInEveryYear);
			}
		}
		
		//净现金流合计  每年  SUM(H6:H8)-SUM(H12:H29)+SUM(H33:H36)-SUM(H38:H55)
		BigDecimal StreamEveryYearSum=new BigDecimal(0);
		StreamEveryYearSum=csoperInEveryYearSum.subtract(csoperOutEveryYearSum.add(csoperOutEveryYearChildSum))
		.add(csFamilyInEveryYearSum).subtract(csFamilyOutEveryYearSum);
		
		//System.out.println("净现金流合计  每年"+StreamEveryYearSum);
		/**
		 * 每年 净现金流合计
		 * end
		 */
		
		/*
		 *  每日等效现金流=C56*30
		 */
		BigDecimal csNetEveryDayEq = new BigDecimal(0);
		BigDecimal csNetEveryDayEq30=new BigDecimal(30);
		csNetEveryDayEq=StreamEveryDaySum.multiply(csNetEveryDayEq30);
		
		/*
		 * 每周等效D56/7*30
		 */
		BigDecimal csNetEveryWeekEq = new BigDecimal(0);
		BigDecimal csNetEveryWeekEq7=new BigDecimal(7);
		BigDecimal csNetEveryWeekEq30=new BigDecimal(30);
		csNetEveryWeekEq=StreamEveryWeekSum.divide(csNetEveryWeekEq7,2,RoundingMode.HALF_UP).multiply(csNetEveryWeekEq30);
		
		/*
		 * 每月等效    E56==E59
		 */
		
		/*
		 * 每季度等效F56/3
		 */
		BigDecimal csNetEveryQuarterEq = new BigDecimal(0);
		BigDecimal csNetEveryQuarterEq3=new BigDecimal(3);
		csNetEveryQuarterEq=StreamEveryQuarterSum.divide(csNetEveryQuarterEq3,2,RoundingMode.HALF_UP);
		
		/*
		 * 每半年等效G56/6
		 */
		BigDecimal csNetEveryHalfYearEq = new BigDecimal(0);
		BigDecimal csNetEveryHalfYearEq6=new BigDecimal(6);
		csNetEveryHalfYearEq=StreamEveryHalfYearSum.divide(csNetEveryHalfYearEq6,2,RoundingMode.HALF_UP);
		
		/*
		 * 年度等效H56/12
		 */
		BigDecimal csNetEveryYearEq = new BigDecimal(0);
		BigDecimal csNetEveryYearEq12=new BigDecimal(12);
		csNetEveryYearEq=StreamEveryYearSum.divide(csNetEveryYearEq12,2,RoundingMode.HALF_UP);
		
		/*
		 * 可支配现金SUM(E56:E62)StreamEveryMonthSum
		 */
		BigDecimal canDominateMoney= new BigDecimal(0);
		canDominateMoney=StreamEveryMonthSum.add(csNetEveryDayEq).add(csNetEveryWeekEq).add(csNetEveryQuarterEq)
						.add(csNetEveryHalfYearEq).add(csNetEveryYearEq);
		
		
		//System.out.println("可支配现金："+canDominateMoney);
		/*
		 * 最大借款额度E63*E64*E67/(1+E65/12*E66)   E66=借款期限（月）
		 * 允许错误范围上下10% 例如：0.667×(1-10%)～0.667×(1+10%) =0.6003～0.7337 =60.03%～73.37%

		 */
		BigDecimal maxLoanSum = new BigDecimal(0);
		BigDecimal a1 = new BigDecimal(cashStreamVo.getCsAdjCoefficient().getCalculateValue());
		BigDecimal a2 = new BigDecimal(100);
		//现金流调整系数
		BigDecimal csAdjCoefficient=a1.divide(a2);
		//得出对应creditapplicationId的年化利率
		Double  yearInterestRate= creditApplicationService.getAnnualizedRateById(creditapplicationId);
		// 借款期限,从申请单取
		CreditApplication creditApplication = creditApplicationService.selectById(creditapplicationId);
		//借款期限
		BigDecimal loanDeadLineNum=new BigDecimal(0);
		if (creditApplication != null && creditApplication.getInstalments() != null
				&& !creditApplication.getInstalments().equals("")) {
			loanDeadLineNum = new BigDecimal(creditApplication.getInstalments());
		}
		// 等额还款期限
		BigDecimal numOne=new BigDecimal(1);
		BigDecimal numTow=new BigDecimal(2);
		BigDecimal num100=new BigDecimal(100);
		BigDecimal  eqRepaymentPeriod=(loanDeadLineNum.subtract(numTow));
		BigDecimal b1=canDominateMoney.multiply(csAdjCoefficient).multiply(eqRepaymentPeriod);
		//把年化利率转换为 BigDecimal
		BigDecimal yearInterest=new BigDecimal(yearInterestRate.toString());
		BigDecimal num12=new BigDecimal(12);
		BigDecimal b2= numOne.add(yearInterest.divide(num12,10,RoundingMode.HALF_UP).multiply(loanDeadLineNum));
		//最终算出的最大借款额度
		maxLoanSum=b1.divide(b2,1,RoundingMode.HALF_UP);
		//System.out.println("最大借款额度："+maxLoanSum);
		maxLoanSum=maxLoanSum.setScale(0,BigDecimal.ROUND_HALF_UP);
		//System.out.println("最大借款额度："+maxLoanSum);
		//把获取到页面的最大借款额度转换为 BigDecimal
		BigDecimal maxLoanSum2=new BigDecimal(cashStreamVo.getMaxLoanSum().getCalculateValue());
		//误差率
		BigDecimal errorNum=new BigDecimal(0);
		//当计算出的最大额度为0时，返回结果为0
		if(maxLoanSum.compareTo(errorNum)==0){
			BigDecimal num0=new BigDecimal(0);
			return num0;
		}
		errorNum=(maxLoanSum2.subtract(maxLoanSum)).divide(maxLoanSum,2,RoundingMode.HALF_UP);
		//判断误差率  返回的结果是int类型，-1表示小于，0是等于，1是大于。
		BigDecimal decimal1=new BigDecimal(0.1);
		BigDecimal decimal2=new BigDecimal(-0.1);
		//System.out.println(errorNum.compareTo(decimal1));
		//System.out.println(errorNum.compareTo(decimal2));
		if(errorNum.compareTo(decimal1)==-1&&errorNum.compareTo(decimal2)==1){
			//正确      没有添加值   删除公式为加号的时候存入msg表中的数据
			//1.判断msg表中是否存在该creditapplicationId数据  若存在：删除该条数据
			List<MsgLog> msgLogList1=msgLogService.selectMsgLogList(creditapplicationId);
			if(msgLogList1.size()>0){
			//删除该条数据方法
			int rows=msgLogService.deleteBycreditapllicationId((long)creditapplicationId);
			//System.out.println(rows);
			}
			//2.若不存在  不做任何操作
			
		}else{
			//错误   
			MsgLog msgLog=new MsgLog();
			//获取1.操作人ID 2.操作人姓名
			msgLog.setOperatorId(Long.parseLong(creditApplication.getLoanOfficerId()));
			msgLog.setOperatorName(creditApplication.getLoanOfficerName());
			//调用查询现金流表中创建时间
			CashStream cashStreamold=cashStreamDAO.selectCashSteamByCreditAppId(creditapplicationId);
			//判断cashStreamold是否为空
			if(null!=cashStreamold){
			msgLog.setOperateDate(cashStreamold.getCreateDate());
			}
			Long creditApplicationId=(long) creditapplicationId;
			//System.out.println(msgLog.getOperateDate());
			msgLog.setCreditapllicationId(creditApplicationId);
			msgLog.setMaxloanlimitError(cashStreamVo.getMaxLoanSum().getCalculateValue());
			Double maxLoanRight=maxLoanSum.doubleValue();
			msgLog.setMaxloanlimitRight(maxLoanRight);
			Double errorRate=errorNum.doubleValue();
			msgLog.setDeviationRate(errorRate);
			//1.判断msg表中是否存在该creditapplicationId数据  若存在：更新该条数据
			List<MsgLog> msgLogList2=msgLogService.selectMsgLogList(creditapplicationId);
			if(msgLogList2.size()>0){
				for(MsgLog msg:msgLogList2){
					msgLog.setMsgLogId(msg.getMsgLogId());
					msgLog.setOperatorId(msg.getOperatorId());
					msgLog.setOperatorName(msg.getOperatorName());
					msgLog.setOperateDate(msg.getOperateDate());
					//System.out.println(msgLog);
					int rows=msgLogService.updateMsgLogDataByCreditapplicationId(msgLog);
					//System.out.println(rows);
				}
			//更新该条数据
			}else{
			//2.若不存在就新插入一条数据
			msgLogService.insertOldData(msgLog);
			}
		}
		//正确运行时，返回1
		BigDecimal num0=new BigDecimal(1);
			return num0;
	}
	
	
	/**
	 * @author hongjieluo
	 * 计算现金流旧数据的计算公式，专用，只用一次
	 * 2014-06-25
	 * end
	 */
	
	
	/**
	 * @author hongjieluo
	 * 现金流入流出表公式计算
	 */
	@Override
	public BigDecimal updateCashStreamServiceCalcMaxMoney(CashStreamVo cashStreamVo,int creditapplicationId) {
		
		
		
		/**
		 * 以下为罗红杰计算
		 * 现金流入流出项目      每天的净现金流合计   
		 * begin
		 */
		//经营现金流入项目
		List<CashStream> csoperInList=cashStreamVo.getCsOperInList();
		//经营现金流出项目子项目
		List<CashStream> csOperOutListChild=cashStreamVo.getCsOperOutListChild();
		//经营现金流流出项目
		List<CashStream> csoperOutList=cashStreamVo.getCsOperOutList();
		//家庭现金流入项目
		List<CashStream> csFamilyInList=cashStreamVo.getCsFamilyInList();
		//家庭现金流出项目
		List<CashStream> csFamilyOutList=cashStreamVo.getCsFamilyOutList();
		
		
		//BigDecimal csoperInEveryMouthSum=new BigDecimal(0);	//经营现金流入每月和
		//BigDecimal算数
		BigDecimal csoperInEveryDaySum = new BigDecimal(0);  //经营现金流入每天合计和
		for(int i=0;i<csoperInList.size();i++){
			if(csoperInList.get(i).getEveryDay()!=null){
			BigDecimal inEveryDay = new BigDecimal(csoperInList.get(i).getEveryDay());
			csoperInEveryDaySum=csoperInEveryDaySum.add(inEveryDay);
			}
		}
		BigDecimal csoperOutEveryDayChildSum=new BigDecimal(0); //经营现金流出项目子项目每天和
		
		for(int iChild=0;iChild<csOperOutListChild.size();iChild++){
			if(csOperOutListChild.get(iChild).getEveryDay()!=null){
			BigDecimal csOperOutChildEveryDay=new BigDecimal(csOperOutListChild.get(iChild).getEveryDay());
			csoperOutEveryDayChildSum=csoperOutEveryDayChildSum.add(csOperOutChildEveryDay);
			}
		}
		
		BigDecimal csoperOutEveryDaySum=new BigDecimal(0);	 //经营现金流出每天合计和
		//因为采购成本不被计入，所以i从1开始
		for(int j=1;j<csoperOutList.size();j++){
			if(csoperOutList.get(j).getEveryDay()!=null){
				BigDecimal csoutEveryDay = new BigDecimal(csoperOutList.get(j).getEveryDay());
				csoperOutEveryDaySum=csoperOutEveryDaySum.add(csoutEveryDay);
				continue;
			}
		}
		BigDecimal csFamilyInEveryDaySum=new BigDecimal(0);	//家庭现金流入每天和
		for(int fi=0;fi<csFamilyInList.size();fi++){
			if(csFamilyInList.get(fi).getEveryDay()!=null){
			BigDecimal familyInEveryDay=new BigDecimal(csFamilyInList.get(fi).getEveryDay());
			csFamilyInEveryDaySum=csFamilyInEveryDaySum.add(familyInEveryDay);
			}
		}
		BigDecimal csFamilyOutEveryDayListSum=new BigDecimal(0); //家庭现金流出每天和
		for(int fo=0;fo<csFamilyOutList.size();fo++){
			if(csFamilyOutList.get(fo).getEveryDay()!=null){
				BigDecimal familyOutEveryDay=new BigDecimal(csFamilyOutList.get(fo).getEveryDay());
				csFamilyOutEveryDayListSum=csFamilyOutEveryDayListSum.add(familyOutEveryDay);
			}
		}
		//净现金流合计  每天      SUM(C6:C8)-SUM(C12:C29)+SUM(C33:C36)-SUM(C38:C55)
		BigDecimal StreamEveryDaySum=new BigDecimal(0);
		StreamEveryDaySum=csoperInEveryDaySum.subtract(csoperOutEveryDaySum.add(csoperOutEveryDayChildSum))
				.add(csFamilyInEveryDaySum).subtract(csFamilyOutEveryDayListSum);	
				//System.out.println("净现金流合计  每天:  "+StreamEveryDaySum);
		/**
		 * 每天净现金流合计
		 * end
		 */
		
		/**
		 * 现金流入流出项目    每周的净现金流合计
		 * begin
		 */
		BigDecimal csoperInEveryWeekSum=new BigDecimal(0);	//经营现金流入每周和
		for(int cIw=0;cIw<csoperInList.size();cIw++){
			if(csoperInList.get(cIw).getEveryWeek()!=null){
			BigDecimal csInEveryWeek=new BigDecimal(csoperInList.get(cIw).getEveryWeek());
			csoperInEveryWeekSum=csoperInEveryWeekSum.add(csInEveryWeek);
			}
		}
		BigDecimal csoperOutEveryWeekChildSum=new BigDecimal(0); //经营现金流出项目子项目每周和
		for(int iChildW=0;iChildW<csOperOutListChild.size();iChildW++){
			if(csOperOutListChild.get(iChildW).getEveryWeek()!=null){
			BigDecimal csOperOutChildEveryWeek=new BigDecimal(csOperOutListChild.get(iChildW).getEveryWeek());
			csoperOutEveryWeekChildSum=csoperOutEveryWeekChildSum.add(csOperOutChildEveryWeek);
			}
		}
		
		BigDecimal csoperOutEveryWeekSum=new BigDecimal(0);	//经营现金流出每周和
		//因为采购成本不被计入，所以i从1开始
		for(int cOw=1;cOw<csoperOutList.size();cOw++){
			if(csoperOutList.get(cOw).getEveryWeek()!=null){
				BigDecimal csOutEveryWeek=new BigDecimal(csoperOutList.get(cOw).getEveryWeek());
				csoperOutEveryWeekSum=csoperOutEveryWeekSum.add(csOutEveryWeek);
			}
		}
		BigDecimal csFamilyInEveryWeekSum=new BigDecimal(0);	//家庭现金流入每周和
		for(int cFw=0;cFw<csFamilyInList.size();cFw++){
			if(csFamilyInList.get(cFw).getEveryWeek()!=null){
				BigDecimal familyInEveryWeek=new BigDecimal(csFamilyInList.get(cFw).getEveryWeek());
				csFamilyInEveryWeekSum=csFamilyInEveryWeekSum.add(familyInEveryWeek);
			}
		}
		BigDecimal csFamilyOutEveryWeekSum=new BigDecimal(0);	//家庭现金流入每周和
		for(int cFw=0;cFw<csFamilyOutList.size();cFw++){
			if(csFamilyOutList.get(cFw).getEveryWeek()!=null){
				BigDecimal familyInEveryWeek=new BigDecimal(csFamilyOutList.get(cFw).getEveryWeek());
				csFamilyOutEveryWeekSum=csFamilyOutEveryWeekSum.add(familyInEveryWeek);
			}
		}
		//净现金流合计  每周   SUM(D6:D8)-SUM(D12:D29)+SUM(D33:D36)-SUM(D38:D55)
		BigDecimal StreamEveryWeekSum=new BigDecimal(0);
		StreamEveryWeekSum=csoperInEveryWeekSum.subtract(csoperOutEveryWeekSum.add(csoperOutEveryWeekChildSum))
				.add(csFamilyInEveryWeekSum).subtract(csFamilyOutEveryWeekSum);	
		//System.out.println("净现金流合计  每周："+StreamEveryWeekSum);
		
		/**
		 * 每周净现金流合计
		 * end
		 */
		
		/**
		 * 现金流入流出项目    每月的净现金流合计
		 * begin
		 */
		BigDecimal csoperInEveryMonthSum=new BigDecimal(0);	//经营现金流入每月和
		for(int cOm=0;cOm<csoperInList.size();cOm++){
			if(csoperInList.get(cOm).getEveryMonth()!=null){
				BigDecimal csInEveryMonth=new BigDecimal(csoperInList.get(cOm).getEveryMonth());
				csoperInEveryMonthSum=csoperInEveryMonthSum.add(csInEveryMonth);
				}
		}
		BigDecimal csoperOutEveryMonthChildSum=new BigDecimal(0); //经营现金流出项目子项目每月和
		for(int iChildW=0;iChildW<csOperOutListChild.size();iChildW++){
			if(csOperOutListChild.get(iChildW).getEveryMonth()!=null){
			BigDecimal csOperOutChildEveryMonth=new BigDecimal(csOperOutListChild.get(iChildW).getEveryMonth());
			csoperOutEveryMonthChildSum=csoperOutEveryMonthChildSum.add(csOperOutChildEveryMonth);
			}
		}
		BigDecimal csoperOutEveryMonthSum=new BigDecimal(0);	//经营现金流出每月和
		//因为采购成本不被计入，所以i从1开始
		for(int cOw=1;cOw<csoperOutList.size();cOw++){
			if(csoperOutList.get(cOw).getEveryMonth()!=null){
				BigDecimal csOutEveryMonth=new BigDecimal(csoperOutList.get(cOw).getEveryMonth());
				csoperOutEveryMonthSum=csoperOutEveryMonthSum.add(csOutEveryMonth);
			}
		}
		BigDecimal csFamilyInEveryMonthSum=new BigDecimal(0);	//家庭现金流入每月和
		for(int cFw=0;cFw<csFamilyInList.size();cFw++){
			if(csFamilyInList.get(cFw).getEveryMonth()!=null){
				BigDecimal familyInEveryMonth=new BigDecimal(csFamilyInList.get(cFw).getEveryMonth());
				csFamilyInEveryMonthSum=csFamilyInEveryMonthSum.add(familyInEveryMonth);
			}
		}
		BigDecimal csFamilyOutEveryMonthSum=new BigDecimal(0);	//家庭现金流入每周和
		for(int cFw=0;cFw<csFamilyOutList.size();cFw++){
			if(csFamilyOutList.get(cFw).getEveryMonth()!=null){
				BigDecimal familyInEveryMonth=new BigDecimal(csFamilyOutList.get(cFw).getEveryMonth());
				csFamilyOutEveryMonthSum=csFamilyOutEveryMonthSum.add(familyInEveryMonth);
			}
		}
		
		//净现金流合计  每月		SUM(E6:E8)-SUM(E12:E29)+SUM(E33:E36)-SUM(E38:E55)
		BigDecimal StreamEveryMonthSum=new BigDecimal(0);
		StreamEveryMonthSum=csoperInEveryMonthSum.subtract(csoperOutEveryMonthSum.add(csoperOutEveryMonthChildSum))
		.add(csFamilyInEveryMonthSum).subtract(csFamilyOutEveryMonthSum);
		
		//System.out.println("净现金流合计  每月"+StreamEveryMonthSum);
		/**
		 * 每月净现金流合计
		 * end
		 */
		
		
		
		/**
		 * 现金流入流出项目    每季度的净现金流合计everyQuarter
		 * begin
		 */
		BigDecimal csoperInEveryQuarterSum=new BigDecimal(0);	//经营现金流入每季度和
		for(int cOm=0;cOm<csoperInList.size();cOm++){
			if(csoperInList.get(cOm).getEveryQuarter()!=null){
				BigDecimal csInEveryQuarter=new BigDecimal(csoperInList.get(cOm).getEveryQuarter());
				csoperInEveryQuarterSum=csoperInEveryQuarterSum.add(csInEveryQuarter);
				}
		}
		BigDecimal csoperOutEveryQuarterChildSum=new BigDecimal(0); //经营现金流出项目子项目每季度和
		for(int iChildW=0;iChildW<csOperOutListChild.size();iChildW++){
			if(csOperOutListChild.get(iChildW).getEveryQuarter()!=null){
			BigDecimal csOperOutChildEveryQuarter=new BigDecimal(csOperOutListChild.get(iChildW).getEveryQuarter());
			csoperOutEveryQuarterChildSum=csoperOutEveryQuarterChildSum.add(csOperOutChildEveryQuarter);
			}
		}
		BigDecimal csoperOutEveryQuarterSum=new BigDecimal(0);	//经营现金流出每季度和
		//因为采购成本不被计入，所以i从1开始
		for(int cOw=1;cOw<csoperOutList.size();cOw++){
			if(csoperOutList.get(cOw).getEveryQuarter()!=null){
				BigDecimal csOutEveryQuarter=new BigDecimal(csoperOutList.get(cOw).getEveryQuarter());
				csoperOutEveryQuarterSum=csoperOutEveryQuarterSum.add(csOutEveryQuarter);
			}
		}
		BigDecimal csFamilyInEveryQuarterSum=new BigDecimal(0);	//家庭现金流入每季度和
		for(int cFw=0;cFw<csFamilyInList.size();cFw++){
			if(csFamilyInList.get(cFw).getEveryQuarter()!=null){
				BigDecimal familyInEveryQuarter=new BigDecimal(csFamilyInList.get(cFw).getEveryQuarter());
				csFamilyInEveryQuarterSum=csFamilyInEveryQuarterSum.add(familyInEveryQuarter);
			}
		}
		BigDecimal csFamilyOutEveryQuarterSum=new BigDecimal(0);	//家庭现金流入每季度和
		for(int cFw=0;cFw<csFamilyOutList.size();cFw++){
			if(csFamilyOutList.get(cFw).getEveryQuarter()!=null){
				BigDecimal familyInEveryQuarter=new BigDecimal(csFamilyOutList.get(cFw).getEveryQuarter());
				csFamilyOutEveryQuarterSum=csFamilyOutEveryQuarterSum.add(familyInEveryQuarter);
			}
		}
		
		//净现金流合计  每季度   SUM(F6:F8)-SUM(F12:F29)+SUM(F33:F36)-SUM(F38:F55)
		BigDecimal StreamEveryQuarterSum=new BigDecimal(0);
		StreamEveryQuarterSum=csoperInEveryQuarterSum.subtract(csoperOutEveryQuarterSum.add(csoperOutEveryQuarterChildSum))
		.add(csFamilyInEveryQuarterSum).subtract(csFamilyOutEveryQuarterSum);
		
		//System.out.println("净现金流合计  每季度"+StreamEveryQuarterSum);
		
		/**
		 * 每季度 净现金流合计
		 * end
		 */
		
		
		
		/**
		 * 现金流入流出项目    每半年的净现金流合计everyHalfYear
		 * begin
		 */
		BigDecimal csoperInEveryHalfYearSum=new BigDecimal(0);	//经营现金流入每半年和
		for(int cOm=0;cOm<csoperInList.size();cOm++){
			if(csoperInList.get(cOm).getEveryHalfYear()!=null){
				BigDecimal csInEveryHalfYear=new BigDecimal(csoperInList.get(cOm).getEveryHalfYear());
				csoperInEveryHalfYearSum=csoperInEveryHalfYearSum.add(csInEveryHalfYear);
				}
		}
		BigDecimal csoperOutEveryHalfYearChildSum=new BigDecimal(0); //经营现金流出项目子项目每半年和
		for(int iChildW=0;iChildW<csOperOutListChild.size();iChildW++){
			if(csOperOutListChild.get(iChildW).getEveryHalfYear()!=null){
			BigDecimal csOperOutChildEveryHalfYear=new BigDecimal(csOperOutListChild.get(iChildW).getEveryHalfYear());
			csoperOutEveryHalfYearChildSum=csoperOutEveryHalfYearChildSum.add(csOperOutChildEveryHalfYear);
			}
		}
		BigDecimal csoperOutEveryHalfYearSum=new BigDecimal(0);	//经营现金流出每半年和
		//因为采购成本不被计入，所以i从1开始
		for(int cOw=1;cOw<csoperOutList.size();cOw++){
			if(csoperOutList.get(cOw).getEveryHalfYear()!=null){
				BigDecimal csOutEveryHalfYear=new BigDecimal(csoperOutList.get(cOw).getEveryHalfYear());
				csoperOutEveryHalfYearSum=csoperOutEveryHalfYearSum.add(csOutEveryHalfYear);
			}
		}
		BigDecimal csFamilyInEveryHalfYearSum=new BigDecimal(0);	//家庭现金流入每半年和
		for(int cFw=0;cFw<csFamilyInList.size();cFw++){
			if(csFamilyInList.get(cFw).getEveryHalfYear()!=null){
				BigDecimal familyInEveryHalfYear=new BigDecimal(csFamilyInList.get(cFw).getEveryHalfYear());
				csFamilyInEveryHalfYearSum=csFamilyInEveryHalfYearSum.add(familyInEveryHalfYear);
			}
		}
		BigDecimal csFamilyOutEveryHalfYearSum=new BigDecimal(0);	//家庭现金流入每半年和
		for(int cFw=0;cFw<csFamilyOutList.size();cFw++){
			if(csFamilyOutList.get(cFw).getEveryHalfYear()!=null){
				BigDecimal familyInEveryHalfYear=new BigDecimal(csFamilyOutList.get(cFw).getEveryHalfYear());
				csFamilyOutEveryHalfYearSum=csFamilyOutEveryHalfYearSum.add(familyInEveryHalfYear);
			}
		}
		
		//净现金流合计  每半年  SUM(G6:G8)-SUM(G12:G29)+SUM(G33:G36)-SUM(G38:G55)
		BigDecimal StreamEveryHalfYearSum=new BigDecimal(0);
		StreamEveryHalfYearSum=csoperInEveryHalfYearSum.subtract(csoperOutEveryHalfYearSum.add(csoperOutEveryHalfYearChildSum))
		.add(csFamilyInEveryHalfYearSum).subtract(csFamilyOutEveryHalfYearSum);
		
		//System.out.println("净现金流合计  每半年"+StreamEveryHalfYearSum);
		
		/**
		 * 每半年 净现金流合计
		 * end
		 */
		
		
		
		/**
		 * 现金流入流出项目    每9个月的净现金流合计NineMonth
		 * begin
		 */
		BigDecimal csoperInNineMonthSum=new BigDecimal(0);	//经营现金流入每9个月和
		for(int cOm=0;cOm<csoperInList.size();cOm++){
			if(csoperInList.get(cOm).getNineMonth()!=null){
				BigDecimal csInNineMonth=new BigDecimal(csoperInList.get(cOm).getNineMonth());
				csoperInNineMonthSum=csoperInNineMonthSum.add(csInNineMonth);
				}
		}
		BigDecimal csoperOutNineMonthChildSum=new BigDecimal(0); //经营现金流出项目子项目每9个月和
		for(int iChildW=0;iChildW<csOperOutListChild.size();iChildW++){
			if(csOperOutListChild.get(iChildW).getNineMonth()!=null){
			BigDecimal csOperOutChildNineMonth=new BigDecimal(csOperOutListChild.get(iChildW).getNineMonth());
			csoperOutNineMonthChildSum=csoperOutNineMonthChildSum.add(csOperOutChildNineMonth);
			}
		}
		BigDecimal csoperOutNineMonthSum=new BigDecimal(0);	//经营现金流出每9个月和
		//因为采购成本不被计入，所以i从1开始
		for(int cOw=1;cOw<csoperOutList.size();cOw++){
			if(csoperOutList.get(cOw).getNineMonth()!=null){
				BigDecimal csOutNineMonth=new BigDecimal(csoperOutList.get(cOw).getNineMonth());
				csoperOutNineMonthSum=csoperOutNineMonthSum.add(csOutNineMonth);
			}
		}
		BigDecimal csFamilyInNineMonthrSum=new BigDecimal(0);	//家庭现金流入每9个月和
		for(int cFw=0;cFw<csFamilyInList.size();cFw++){
			if(csFamilyInList.get(cFw).getNineMonth()!=null){
				BigDecimal familyInNineMonth=new BigDecimal(csFamilyInList.get(cFw).getNineMonth());
				csFamilyInNineMonthrSum=csFamilyInNineMonthrSum.add(familyInNineMonth);
			}
		}
		BigDecimal csFamilyOutNineMonthSum=new BigDecimal(0);	//家庭现金流入每9个月和
		for(int cFw=0;cFw<csFamilyOutList.size();cFw++){
			if(csFamilyOutList.get(cFw).getNineMonth()!=null){
				BigDecimal familyInNineMonth=new BigDecimal(csFamilyOutList.get(cFw).getNineMonth());
				csFamilyOutNineMonthSum=csFamilyOutNineMonthSum.add(familyInNineMonth);
			}
		}
		
		//净现金流合计  每9个月
		BigDecimal StreamNineMonthSum=new BigDecimal(0);
		StreamNineMonthSum=csoperInNineMonthSum.subtract(csoperOutNineMonthSum.add(csoperOutNineMonthChildSum))
		.add(csFamilyInNineMonthrSum).subtract(csFamilyOutNineMonthSum);
		
		
		/**
		 * 每9个月 净现金流合计
		 * end
		 */
		
		
		/**
		 * 现金流入流出项目    每年的净现金流合计EveryYear
		 * begin
		 */
		BigDecimal csoperInEveryYearSum=new BigDecimal(0);	//经营现金流入每年和
		for(int cOm=0;cOm<csoperInList.size();cOm++){
			if(csoperInList.get(cOm).getEveryYear()!=null){
				BigDecimal csInNineMonth=new BigDecimal(csoperInList.get(cOm).getEveryYear());
				csoperInEveryYearSum=csoperInEveryYearSum.add(csInNineMonth);
				}
		}
		BigDecimal csoperOutEveryYearChildSum=new BigDecimal(0); //经营现金流出项目子项目每年和
		for(int iChildW=0;iChildW<csOperOutListChild.size();iChildW++){
			if(csOperOutListChild.get(iChildW).getEveryYear()!=null){
			BigDecimal csOperOutChildEveryYear=new BigDecimal(csOperOutListChild.get(iChildW).getEveryYear());
			csoperOutEveryYearChildSum=csoperOutEveryYearChildSum.add(csOperOutChildEveryYear);
			}
		}
		BigDecimal csoperOutEveryYearSum=new BigDecimal(0);	//经营现金流出每年和
		//因为采购成本不被计入，所以i从1开始
		for(int cOw=1;cOw<csoperOutList.size();cOw++){
			if(csoperOutList.get(cOw).getEveryYear()!=null){
				BigDecimal csOutEveryYear=new BigDecimal(csoperOutList.get(cOw).getEveryYear());
				csoperOutEveryYearSum=csoperOutEveryYearSum.add(csOutEveryYear);
			}
		}
		BigDecimal csFamilyInEveryYearSum=new BigDecimal(0);	//家庭现金流入每年和
		for(int cFw=0;cFw<csFamilyInList.size();cFw++){
			if(csFamilyInList.get(cFw).getEveryYear()!=null){
				BigDecimal familyInEveryYear=new BigDecimal(csFamilyInList.get(cFw).getEveryYear());
				csFamilyInEveryYearSum=csFamilyInEveryYearSum.add(familyInEveryYear);
			}
		}
		BigDecimal csFamilyOutEveryYearSum=new BigDecimal(0);	//家庭现金流入每年和
		for(int cFw=0;cFw<csFamilyOutList.size();cFw++){
			if(csFamilyOutList.get(cFw).getEveryYear()!=null){
				BigDecimal familyInEveryYear=new BigDecimal(csFamilyOutList.get(cFw).getEveryYear());
				csFamilyOutEveryYearSum=csFamilyOutEveryYearSum.add(familyInEveryYear);
			}
		}
		
		//净现金流合计  每年  SUM(H6:H8)-SUM(H12:H29)+SUM(H33:H36)-SUM(H38:H55)
		BigDecimal StreamEveryYearSum=new BigDecimal(0);
		StreamEveryYearSum=csoperInEveryYearSum.subtract(csoperOutEveryYearSum.add(csoperOutEveryYearChildSum))
		.add(csFamilyInEveryYearSum).subtract(csFamilyOutEveryYearSum);
		
		//System.out.println("净现金流合计  每年"+StreamEveryYearSum);
		/**
		 * 每年 净现金流合计
		 * end
		 */
		
		/*
		 *  每日等效现金流=C56*30
		 */
		BigDecimal csNetEveryDayEq = new BigDecimal(0);
		BigDecimal csNetEveryDayEq30=new BigDecimal(30);
		csNetEveryDayEq=StreamEveryDaySum.multiply(csNetEveryDayEq30);
		
		/*
		 * 每周等效D56/7*30
		 */
		BigDecimal csNetEveryWeekEq = new BigDecimal(0);
		BigDecimal csNetEveryWeekEq7=new BigDecimal(7);
		BigDecimal csNetEveryWeekEq30=new BigDecimal(30);
		csNetEveryWeekEq=StreamEveryWeekSum.divide(csNetEveryWeekEq7,2,RoundingMode.HALF_UP).multiply(csNetEveryWeekEq30);
		
		/*
		 * 每月等效    E56==E59
		 */
		
		/*
		 * 每季度等效F56/3
		 */
		BigDecimal csNetEveryQuarterEq = new BigDecimal(0);
		BigDecimal csNetEveryQuarterEq3=new BigDecimal(3);
		csNetEveryQuarterEq=StreamEveryQuarterSum.divide(csNetEveryQuarterEq3,2,RoundingMode.HALF_UP);
		
		/*
		 * 每半年等效G56/6
		 */
		BigDecimal csNetEveryHalfYearEq = new BigDecimal(0);
		BigDecimal csNetEveryHalfYearEq6=new BigDecimal(6);
		csNetEveryHalfYearEq=StreamEveryHalfYearSum.divide(csNetEveryHalfYearEq6,2,RoundingMode.HALF_UP);
		
		/*
		 * 年度等效H56/12
		 */
		BigDecimal csNetEveryYearEq = new BigDecimal(0);
		BigDecimal csNetEveryYearEq12=new BigDecimal(12);
		csNetEveryYearEq=StreamEveryYearSum.divide(csNetEveryYearEq12,2,RoundingMode.HALF_UP);
		
		/*
		 * 可支配现金SUM(E56:E62)StreamEveryMonthSum
		 */
		BigDecimal canDominateMoney= new BigDecimal(0);
		canDominateMoney=StreamEveryMonthSum.add(csNetEveryDayEq).add(csNetEveryWeekEq).add(csNetEveryQuarterEq)
						.add(csNetEveryHalfYearEq).add(csNetEveryYearEq);
		
		
		//System.out.println("可支配现金："+canDominateMoney);
		/*
		 * 最大借款额度E63*E64*E67/(1+E65/12*E66)   E66=借款期限（月）
		 * 允许错误范围上下10% 例如：0.667×(1-10%)～0.667×(1+10%) =0.6003～0.7337 =60.03%～73.37%

		 */
		BigDecimal maxLoanSum = new BigDecimal(0);
		BigDecimal a1 = new BigDecimal(cashStreamVo.getCsAdjCoefficient().getCalculateValue());
		BigDecimal a2 = new BigDecimal(100);
		//现金流调整系数
		BigDecimal csAdjCoefficient=a1.divide(a2);
		//得出对应creditapplicationId的年化利率
		Double  yearInterestRate= creditApplicationService.getAnnualizedRateById(creditapplicationId);
		// 借款期限,从申请单取
		CreditApplication creditApplication = creditApplicationService.selectById(creditapplicationId);
		//借款期限
		BigDecimal loanDeadLineNum=new BigDecimal(0);
		if (creditApplication != null && creditApplication.getInstalments() != null
				&& !creditApplication.getInstalments().equals("")) {
			loanDeadLineNum = new BigDecimal(creditApplication.getInstalments());
		}
		// 等额还款期限
		BigDecimal numOne=new BigDecimal(1);
		BigDecimal numTow=new BigDecimal(2);
		BigDecimal num100=new BigDecimal(100);
		BigDecimal  eqRepaymentPeriod=(loanDeadLineNum.subtract(numTow));
		BigDecimal b1=canDominateMoney.multiply(csAdjCoefficient).multiply(eqRepaymentPeriod);
		//把年化利率转换为 BigDecimal
		BigDecimal yearInterest=new BigDecimal(yearInterestRate.toString());
		BigDecimal num12=new BigDecimal(12);
		BigDecimal b2= numOne.add(yearInterest.divide(num12,10,RoundingMode.HALF_UP).multiply(loanDeadLineNum));
		//最终算出的最大借款额度
		maxLoanSum=b1.divide(b2,1,RoundingMode.HALF_UP);
		//System.out.println("最大借款额度："+maxLoanSum);
		maxLoanSum=maxLoanSum.setScale(0,BigDecimal.ROUND_HALF_UP);
		//System.out.println("最大借款额度："+maxLoanSum);
		//把获取到页面的最大借款额度转换为 BigDecimal
		BigDecimal maxLoanSum2=new BigDecimal(cashStreamVo.getMaxLoanSum().getCalculateValue());
		//误差率
		BigDecimal errorNum=new BigDecimal(0);
		//当计算出的最大额度为0时，返回结果为0
		if(maxLoanSum.compareTo(errorNum)==0){
			BigDecimal num0=new BigDecimal(0);
			return num0;
		}
		errorNum=(maxLoanSum2.subtract(maxLoanSum)).divide(maxLoanSum,2,RoundingMode.HALF_UP);
		//判断误差率  返回的结果是int类型，-1表示小于，0是等于，1是大于。
		BigDecimal decimal1=new BigDecimal(0.1);
		BigDecimal decimal2=new BigDecimal(-0.1);
		//System.out.println(errorNum.compareTo(decimal1));
		//System.out.println(errorNum.compareTo(decimal2));
		if(errorNum.compareTo(decimal1)==-1&&errorNum.compareTo(decimal2)==1){
			//正确      没有添加值
		}else{
			//调用保存错误信息的方法
			MsgLog msgLog=new MsgLog();
			Long creditApplicationId=(long) creditapplicationId;
			msgLog.setCreditapllicationId(creditApplicationId);
			msgLog.setMaxloanlimitError(cashStreamVo.getMaxLoanSum().getCalculateValue());
			Double maxLoanRight=maxLoanSum.doubleValue();
			msgLog.setMaxloanlimitRight(maxLoanRight);
			Double errorRate=errorNum.doubleValue();
			msgLog.setDeviationRate(errorRate);
			msgLogService.insert(msgLog);
		}
		
		/**
		 * 更新现金流入流出表方法
		 */
		List<CashStream> l = new ArrayList<CashStream>();
		// 将计算过的数据添加到数组
		l.addAll(cashStreamVo.getCsOperInList());
		l.add(cashStreamVo.getCsOperInTotal());
		l.addAll(cashStreamVo.getCsOperOutListChild());
		l.addAll(cashStreamVo.getCsOperOutList());
		l.add(cashStreamVo.getCsOperOutTotal());
		l.addAll(cashStreamVo.getCsFamilyInList());
//		l.add(cashStreamVo.getCsFamilyInTotal());	2014-12-24取消显示
		l.addAll(cashStreamVo.getCsFamilyOutList());
//		l.add(cashStreamVo.getCsFamilyOutTotal());	2014-12-24取消显示
//		l.add(cashStreamVo.getCsFamilyNet());		2014-12-24取消显示
		l.add(cashStreamVo.getCsOperNet());
		l.add(cashStreamVo.getCsNetTotal());
		l.add(cashStreamVo.getCsNetEveryDayEq());
		l.add(cashStreamVo.getCsNetEveryWeekEq());
		l.add(cashStreamVo.getCsNetEveryMonthEq());
		l.add(cashStreamVo.getCsNetEveryQuarterEq());
		l.add(cashStreamVo.getCsNetEveryHalfYearEq());
		/*l.add(cashStreamVo.getCsNetEverynineMonthEq());*/
		l.add(cashStreamVo.getCsNetEveryYearEq());
		l.add(cashStreamVo.getCanDominateMoney());
		l.add(cashStreamVo.getApplyLoanSum());
		CashStream yearRate = cashStreamVo.getYearInterestRate();
		yearRate.setCalculateValue(CurrencyUtil.div(yearRate.getCalculateValue(), Double.valueOf("100")));
		l.add(yearRate);
		l.add(cashStreamVo.getMaxLoanSum());

		for (int i = 0; i < l.size(); i++) {
			CashStream cs = l.get(i);
			cashStreamDAO.updateById(cs);
		}
		//正确运行时，返回1
		BigDecimal num0=new BigDecimal(1);
			return num0;
	}

	/**
	 * 初始化表单
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<CashStream> initData(int creditapplicationId, String name, String type) {

		List<CashStream> cashStreams = new ArrayList<CashStream>();

		// 经营现金流入项目1
		CashStream csOperIn1 = new CashStream();
		csOperIn1.setProjectName("");
		csOperIn1.setProjectType("operIn_proj1");
		initEvery(csOperIn1, creditapplicationId, name, type);

		// 经营现金流入项目2
		CashStream csOperIn2 = new CashStream();
		csOperIn2.setProjectName("");
		csOperIn2.setProjectType("operIn_proj2");
		initEvery(csOperIn2, creditapplicationId, name, type);

		// 经营现金流入项目3
		CashStream csOperIn3 = new CashStream();
		csOperIn3.setProjectName("");
		csOperIn3.setProjectType("operIn_proj3");
		initEvery(csOperIn3, creditapplicationId, name, type);

		// 经营现金流入合计
		CashStream csOperInTotal = new CashStream();
		csOperInTotal.setProjectName("经营现金流入合计");
		csOperInTotal.setProjectType("total_operIn");
		initEvery(csOperInTotal, creditapplicationId, name, type);

		// 经营现金流出项目

		// 采购成本
		CashStream csPurchaseCost = new CashStream();
		csPurchaseCost.setProjectName("采购成本");
		csPurchaseCost.setProjectType("operOut_purchaseCost");
		initEvery(csPurchaseCost, creditapplicationId, name, type);

		// 经营现金流出项目1
		CashStream csOperOut1 = new CashStream();
		csOperOut1.setProjectName("");
		csOperOut1.setProjectType("operOut_proj1");
		initEvery(csOperOut1, creditapplicationId, name, type);

		// 经营现金流出项目2
		CashStream csOperOut2 = new CashStream();
		csOperOut2.setProjectName("");
		csOperOut2.setProjectType("operOut_proj2");
		initEvery(csOperOut2, creditapplicationId, name, type);

		// 经营现金流出项目3
		CashStream csOperOut3 = new CashStream();
		csOperOut3.setProjectName("");
		csOperOut3.setProjectType("operOut_proj3");
		initEvery(csOperOut3, creditapplicationId, name, type);

		// 经营现金流出-员工工资
		CashStream csOperOutSalary = new CashStream();
		csOperOutSalary.setProjectName("员工工资");
		csOperOutSalary.setProjectType("operOut_salary");
		initEvery(csOperOutSalary, creditapplicationId, name, type);

		// 经营现金流出-房租
		CashStream csOperOutRent = new CashStream();
		csOperOutRent.setProjectName("房租");
		csOperOutRent.setProjectType("operOut_rent");
		initEvery(csOperOutRent, creditapplicationId, name, type);

		// 经营现金流出-电费
		CashStream csOperOutElectCharges = new CashStream();
		csOperOutElectCharges.setProjectName("电费");
		csOperOutElectCharges.setProjectType("operOut_electCharges");
		initEvery(csOperOutElectCharges, creditapplicationId, name, type);

		// 经营现金流出-水费
		CashStream csOperOutWaterCost = new CashStream();
		csOperOutWaterCost.setProjectName("水费");
		csOperOutWaterCost.setProjectType("operOut_waterCost");
		initEvery(csOperOutWaterCost, creditapplicationId, name, type);

		// 经营现金流出-交通费
		CashStream csOperOutTrafficCost = new CashStream();
		csOperOutTrafficCost.setProjectName("交通费");
		csOperOutTrafficCost.setProjectType("operOut_trafficCost");
		initEvery(csOperOutTrafficCost, creditapplicationId, name, type);

		// 经营现金流出-通讯费
		CashStream csOperOutCommuniCost = new CashStream();
		csOperOutCommuniCost.setProjectName("通讯费");
		csOperOutCommuniCost.setProjectType("operOut_communiCost");
		initEvery(csOperOutCommuniCost, creditapplicationId, name, type);

		// 经营现金流出-供暖费
		CashStream csOperOutWarmCost = new CashStream();
		csOperOutWarmCost.setProjectName("供暖费");
		csOperOutWarmCost.setProjectType("operOut_warmCost");
		initEvery(csOperOutWarmCost, creditapplicationId, name, type);

		// 经营现金流出-税费及证照费
		CashStream csOperOutTaxCost = new CashStream();
		csOperOutTaxCost.setProjectName("税费及证照费");
		csOperOutTaxCost.setProjectType("operOut_taxCost");
		initEvery(csOperOutTaxCost, creditapplicationId, name, type);

		// 经营现金流出-维护及保养
		CashStream csOperOutMaintainCost = new CashStream();
		csOperOutMaintainCost.setProjectName("维护及保养");
		csOperOutMaintainCost.setProjectType("operOut_maintainCost");
		initEvery(csOperOutMaintainCost, creditapplicationId, name, type);

		// 经营现金流出-其他1
		CashStream csOperOutOther1 = new CashStream();
		csOperOutOther1.setProjectName("");
		csOperOutOther1.setProjectType("operOut_other1");
		initEvery(csOperOutOther1, creditapplicationId, name, type);

		// 经营现金流出-其他1
		CashStream csOperOutOther2 = new CashStream();
		csOperOutOther2.setProjectName("");
		csOperOutOther2.setProjectType("operOut_other2");
		initEvery(csOperOutOther2, creditapplicationId, name, type);

		// 经营现金流出-其他1
		CashStream csOperOutOther3 = new CashStream();
		csOperOutOther3.setProjectName("");
		csOperOutOther3.setProjectType("operOut_other3");
		initEvery(csOperOutOther3, creditapplicationId, name, type);

		// 经营现金流出-其他1
		CashStream csOperOutOther4 = new CashStream();
		csOperOutOther4.setProjectName("");
		csOperOutOther4.setProjectType("operOut_other4");
		initEvery(csOperOutOther4, creditapplicationId, name, type);

		// 经营现金流出-其他1
		CashStream csOperOutOther5 = new CashStream();
		csOperOutOther5.setProjectName("");
		csOperOutOther5.setProjectType("operOut_other5");
		initEvery(csOperOutOther5, creditapplicationId, name, type);

		// 经营现金流出-其他1
		CashStream csOperOutOther6 = new CashStream();
		csOperOutOther6.setProjectName("");
		csOperOutOther6.setProjectType("operOut_other6");
		initEvery(csOperOutOther6, creditapplicationId, name, type);

		// 经营现金流出合计
		CashStream csOperOutTotal = new CashStream();
		csOperOutTotal.setProjectName("经营现金流出合计");
		csOperOutTotal.setProjectType("total_operOut");
		initEvery(csOperOutTotal, creditapplicationId, name, type);

		// 经营净现金流
		CashStream csOperNet = new CashStream();
		csOperNet.setProjectName("经营净现金流");
		csOperNet.setProjectType("operNet");
		initEvery(csOperNet, creditapplicationId, name, type);

		// 家庭现金流入项目-工资
		CashStream csFamilyInSalary = new CashStream();
		csFamilyInSalary.setProjectName("工资");
		csFamilyInSalary.setProjectType("familyIn_salary");
		initEvery(csFamilyInSalary, creditapplicationId, name, type);

		// 家庭现金流入项目-津贴
		CashStream csFamilyInAllowance = new CashStream();
		csFamilyInAllowance.setProjectName("津贴");
		csFamilyInAllowance.setProjectType("familyIn_allowance");
		initEvery(csFamilyInAllowance, creditapplicationId, name, type);

		// 家庭现金流入项目-其他1
		CashStream csFamilyInOther1 = new CashStream();
		csFamilyInOther1.setProjectName("");
		csFamilyInOther1.setProjectType("familyIn_other1");
		initEvery(csFamilyInOther1, creditapplicationId, name, type);

		// 家庭现金流入项目-其他2
		CashStream csFamilyInOther2 = new CashStream();
		csFamilyInOther2.setProjectName("");
		csFamilyInOther2.setProjectType("familyIn_other2");
		initEvery(csFamilyInOther2, creditapplicationId, name, type);

		// 家庭现金流入合计
		CashStream csFamilyInTotal = new CashStream();
		csFamilyInTotal.setProjectName("家庭现金流入合计");
		csFamilyInTotal.setProjectType("total_familyIn");
		initEvery(csFamilyInTotal, creditapplicationId, name, type);

		// 家庭现金流出项目-食品
		CashStream csFamilyOutFood = new CashStream();
		csFamilyOutFood.setProjectName("食品");
		csFamilyOutFood.setProjectType("familyOut_food");
		initEvery(csFamilyOutFood, creditapplicationId, name, type);

		// 家庭现金流出项目-教育
		CashStream csFamilyOutEducation = new CashStream();
		csFamilyOutEducation.setProjectName("教育");
		csFamilyOutEducation.setProjectType("familyOut_education");
		initEvery(csFamilyOutEducation, creditapplicationId, name, type);

		// 家庭现金流出项目-电费
		CashStream csFamilyOutElectCost = new CashStream();
		csFamilyOutElectCost.setProjectName("电费");
		csFamilyOutElectCost.setProjectType("familyOut_electCost");
		initEvery(csFamilyOutElectCost, creditapplicationId, name, type);

		// 家庭现金流出项目-水费
		CashStream csFamilyOutWaterCost = new CashStream();
		csFamilyOutWaterCost.setProjectName("水费");
		csFamilyOutWaterCost.setProjectType("familyOut_waterCost");
		initEvery(csFamilyOutWaterCost, creditapplicationId, name, type);

		// 家庭现金流出项目-取暖费
		CashStream csFamilyOutWarmCost = new CashStream();
		csFamilyOutWarmCost.setProjectName("取暖费");
		csFamilyOutWarmCost.setProjectType("familyOut_warmCost");
		initEvery(csFamilyOutWarmCost, creditapplicationId, name, type);

		// 家庭现金流出项目-房租
		CashStream csFamilyOutRent = new CashStream();
		csFamilyOutRent.setProjectName("房租");
		csFamilyOutRent.setProjectType("familyOut_rent");
		initEvery(csFamilyOutRent, creditapplicationId, name, type);

		// 家庭现金流出项目-交通费
		CashStream csFamilyOutTraffiCost = new CashStream();
		csFamilyOutTraffiCost.setProjectName("交通费");
		csFamilyOutTraffiCost.setProjectType("familyOut_traffiCost");
		initEvery(csFamilyOutTraffiCost, creditapplicationId, name, type);

		// 家庭现金流出项目-通讯费
		CashStream csFamilyOutCommunication = new CashStream();
		csFamilyOutCommunication.setProjectName("通讯费");
		csFamilyOutCommunication.setProjectType("familyOut_communication");
		initEvery(csFamilyOutCommunication, creditapplicationId, name, type);

		// 家庭现金流出项目-医疗费
		CashStream csFamilyOutMedicalCost = new CashStream();
		csFamilyOutMedicalCost.setProjectName("医疗费");
		csFamilyOutMedicalCost.setProjectType("familyOut_medicalCost");
		initEvery(csFamilyOutMedicalCost, creditapplicationId, name, type);

		// 家庭现金流出项目-保险费
		CashStream csFamilyOutInsurance = new CashStream();
		csFamilyOutInsurance.setProjectName("保险费");
		csFamilyOutInsurance.setProjectType("familyOut_insurance");
		initEvery(csFamilyOutInsurance, creditapplicationId, name, type);

		// 家庭现金流出项目-服装开销
		CashStream csFamilyOutClothingCost = new CashStream();
		csFamilyOutClothingCost.setProjectName("服装开销");
		csFamilyOutClothingCost.setProjectType("familyOut_clothingCost");
		initEvery(csFamilyOutClothingCost, creditapplicationId, name, type);

		// 家庭现金流出项目-贷款还款
		CashStream csFamilyOutCreditRepayment = new CashStream();
		csFamilyOutCreditRepayment.setProjectName("贷款还款");
		csFamilyOutCreditRepayment.setProjectType("familyOut_creditRepayment");
		initEvery(csFamilyOutCreditRepayment, creditapplicationId, name, type);

		// 家庭现金流出项目-随礼/送礼
		CashStream csFamilyOutGifts = new CashStream();
		csFamilyOutGifts.setProjectName("随礼/送礼");
		csFamilyOutGifts.setProjectType("familyOut_gifts");
		initEvery(csFamilyOutGifts, creditapplicationId, name, type);

		// 家庭现金流出项目-娱乐
		CashStream csFamilyOutAmusement = new CashStream();
		csFamilyOutAmusement.setProjectName("娱乐");
		csFamilyOutAmusement.setProjectType("familyOut_amusement");
		initEvery(csFamilyOutAmusement, creditapplicationId, name, type);

		// 家庭现金流出项目-其他1
		CashStream csFamilyOutOther1 = new CashStream();
		csFamilyOutOther1.setProjectName("");
		csFamilyOutOther1.setProjectType("familyOut_other1");
		initEvery(csFamilyOutOther1, creditapplicationId, name, type);

		// 家庭现金流出项目-其他1
		CashStream csFamilyOutOther2 = new CashStream();
		csFamilyOutOther2.setProjectName("");
		csFamilyOutOther2.setProjectType("familyOut_other2");
		initEvery(csFamilyOutOther2, creditapplicationId, name, type);

		// 家庭现金流出项目-其他1
		CashStream csFamilyOutOther3 = new CashStream();
		csFamilyOutOther3.setProjectName("");
		csFamilyOutOther3.setProjectType("familyOut_other3");
		initEvery(csFamilyOutOther3, creditapplicationId, name, type);

		// 家庭现金流出项目-其他1
		CashStream csFamilyOutOther4 = new CashStream();
		csFamilyOutOther4.setProjectName("");
		csFamilyOutOther4.setProjectType("familyOut_other4");
		initEvery(csFamilyOutOther4, creditapplicationId, name, type);

		// 家庭现金流出合计
		CashStream csFamilyOutTotal = new CashStream();
		csFamilyOutTotal.setProjectName("家庭现金流出合计");
		csFamilyOutTotal.setProjectType("total_familyOut");
		initEvery(csFamilyOutTotal, creditapplicationId, name, type);

		// 家庭净现金流
		CashStream csFamilyNet = new CashStream();
		csFamilyNet.setProjectName("家庭净现金流");
		csFamilyNet.setProjectType("familyNet");
		initEvery(csFamilyNet, creditapplicationId, name, type);

		// 净现金流合计
		CashStream csNetTotal = new CashStream();
		csNetTotal.setProjectName("净现金流合计");
		csNetTotal.setProjectType("netTotal");
		initEvery(csNetTotal, creditapplicationId, name, type);

		// 每日等效净现金流
		CashStream csNetEveryDayEq = new CashStream();
		csNetEveryDayEq.setProjectName("每日等效净现金流");
		csNetEveryDayEq.setProjectType("netEveryDayEq");
		initEvery(csNetEveryDayEq, creditapplicationId, name, type);

		// 每周等效
		CashStream csNetEveryWeekEq = new CashStream();
		csNetEveryWeekEq.setProjectName("每周等效");
		csNetEveryWeekEq.setProjectType("netEveryWeekEq");
		initEvery(csNetEveryWeekEq, creditapplicationId, name, type);

		// 每月等效
		CashStream csNetEveryMonthEq = new CashStream();
		csNetEveryMonthEq.setProjectName("每月等效");
		csNetEveryMonthEq.setProjectType("netEveryMonthEq");
		initEvery(csNetEveryMonthEq, creditapplicationId, name, type);

		// 每季度等效
		CashStream csNetEveryQuarterEq = new CashStream();
		csNetEveryQuarterEq.setProjectName("每季度等效");
		csNetEveryQuarterEq.setProjectType("netEveryQuarterEq");
		initEvery(csNetEveryQuarterEq, creditapplicationId, name, type);

		// 每季度等效
		CashStream csNetEveryHalfYearEq = new CashStream();
		csNetEveryHalfYearEq.setProjectName("每半年等效");
		csNetEveryHalfYearEq.setProjectType("netEveryHalfYearEq");
		initEvery(csNetEveryHalfYearEq, creditapplicationId, name, type);

		// 每九个月等效
//		CashStream csNetEverynineMonthEq = new CashStream();
//		csNetEverynineMonthEq.setProjectName("每九个月等效");
//		csNetEverynineMonthEq.setProjectType("netEverynineMonthEq");
//		initEvery(csNetEverynineMonthEq, creditapplicationId, name, type);

		// 年度等效
		CashStream csNetEveryYearEq = new CashStream();
		csNetEveryYearEq.setProjectName("年度等效");
		csNetEveryYearEq.setProjectType("netEveryYearEq");
		initEvery(csNetEveryYearEq, creditapplicationId, name, type);

		// 可支配现金
		CashStream canDominateMoney = new CashStream();
		canDominateMoney.setProjectName("可支配现金");
		canDominateMoney.setProjectType("canDominateMoney");
		initEvery(canDominateMoney, creditapplicationId, name, type);

		// 年化利率
		CashStream yearInterestRate = new CashStream();
		yearInterestRate.setProjectName("年化利率");
		yearInterestRate.setProjectType("yearInterestRate");
		initEvery(yearInterestRate, creditapplicationId, name, type);
		//得出对应creditapplicationId的年化利率 luohongjie
		Double  yearInterestRateXin= creditApplicationService.getAnnualizedRateById(creditapplicationId);
		if(yearInterestRateXin!=null){
		yearInterestRate.setCalculateValue(Double.valueOf(yearInterestRateXin));
		}
		// 借款期限,从申请单取
		CreditApplication creditApplication = creditApplicationService.selectById(creditapplicationId);
		Double loanDeadLineNum = Double.valueOf("0");
		if (creditApplication != null && creditApplication.getInstalments() != null
				&& !creditApplication.getInstalments().equals("")) {
			loanDeadLineNum = Double.valueOf(creditApplication.getInstalments());
		}
		CashStream loanDeadline = new CashStream();
		loanDeadline.setProjectName("借款期限");
		loanDeadline.setProjectType("loanDeadline");
		initEvery(loanDeadline, creditapplicationId, name, type);
		loanDeadline.setCalculateValue(loanDeadLineNum);

		// 等额还款期数
		CashStream eqRepaymentPeriod = new CashStream();
		eqRepaymentPeriod.setProjectName("等额还款期数");
		eqRepaymentPeriod.setProjectType("eqRepaymentPeriod");
		initEvery(eqRepaymentPeriod, creditapplicationId, name, type);
		if (loanDeadLineNum != 0 && loanDeadLineNum > 2) {
			eqRepaymentPeriod.setCalculateValue(loanDeadLineNum - 2);
		} else {
			eqRepaymentPeriod.setCalculateValue(Double.valueOf("0"));
		}

		// 申请借款金额
		CashStream applyLoanSum = new CashStream();
		applyLoanSum.setProjectName("申请借款金额");
		applyLoanSum.setProjectType("applyLoanSum");
		initEvery(applyLoanSum, creditapplicationId, name, type);

		// 最大借款额度
		CashStream maxLoanSum = new CashStream();
		maxLoanSum.setProjectName("最大借款额度");
		maxLoanSum.setProjectType("maxLoanSum");
		// maxLoanSum.setCalculateValue(Double.valueOf("0"));
		initEvery(maxLoanSum, creditapplicationId, name, type);

		// 经营现金流入项目
		cashStreams.add(csOperIn1);
		cashStreams.add(csOperIn2);
		cashStreams.add(csOperIn3);
		cashStreams.add(csOperInTotal);
		// 经营现金流出项目
		cashStreams.add(csPurchaseCost);
		cashStreams.add(csOperOut1);
		cashStreams.add(csOperOut2);
		cashStreams.add(csOperOut3);
		cashStreams.add(csOperOutSalary);
		cashStreams.add(csOperOutRent);
		cashStreams.add(csOperOutElectCharges);
		cashStreams.add(csOperOutWaterCost);
		cashStreams.add(csOperOutTrafficCost);
		cashStreams.add(csOperOutCommuniCost);
		cashStreams.add(csOperOutWarmCost);
		cashStreams.add(csOperOutTaxCost);
		cashStreams.add(csOperOutMaintainCost);
		cashStreams.add(csOperOutOther1);
		cashStreams.add(csOperOutOther2);
		cashStreams.add(csOperOutOther3);
		cashStreams.add(csOperOutOther4);
		cashStreams.add(csOperOutOther5);
		cashStreams.add(csOperOutOther6);

		cashStreams.add(csOperOutTotal);

		cashStreams.add(csOperNet);
		// 家庭现金流入项目
		cashStreams.add(csFamilyInSalary);
		cashStreams.add(csFamilyInAllowance);
		cashStreams.add(csFamilyInOther1);
		cashStreams.add(csFamilyInOther2);

		// 家庭现金流入合计
		cashStreams.add(csFamilyInTotal);

		// 家庭现金流出项目
		cashStreams.add(csFamilyOutFood);
		cashStreams.add(csFamilyOutEducation);
		cashStreams.add(csFamilyOutElectCost);
		cashStreams.add(csFamilyOutWaterCost);
		cashStreams.add(csFamilyOutWarmCost);
		cashStreams.add(csFamilyOutRent);
		cashStreams.add(csFamilyOutTraffiCost);
		cashStreams.add(csFamilyOutCommunication);
		cashStreams.add(csFamilyOutMedicalCost);
		cashStreams.add(csFamilyOutInsurance);
		cashStreams.add(csFamilyOutClothingCost);
		cashStreams.add(csFamilyOutCreditRepayment);

		cashStreams.add(csFamilyOutGifts);
		cashStreams.add(csFamilyOutAmusement);
		cashStreams.add(csFamilyOutOther1);
		cashStreams.add(csFamilyOutOther2);
		cashStreams.add(csFamilyOutOther3);
		cashStreams.add(csFamilyOutOther4);

		// 家庭现金流出合计
		cashStreams.add(csFamilyOutTotal);

		// 家庭净现金流
		cashStreams.add(csFamilyNet);

		cashStreams.add(csNetTotal);
		cashStreams.add(csNetEveryDayEq);
		cashStreams.add(csNetEveryWeekEq);
		cashStreams.add(csNetEveryMonthEq);
		cashStreams.add(csNetEveryQuarterEq);
		cashStreams.add(csNetEveryHalfYearEq);
		//cashStreams.add(csNetEverynineMonthEq);
		cashStreams.add(csNetEveryYearEq);
		cashStreams.add(canDominateMoney);
		cashStreams.add(yearInterestRate);
		cashStreams.add(loanDeadline);
		cashStreams.add(eqRepaymentPeriod);
		cashStreams.add(applyLoanSum);
		cashStreams.add(maxLoanSum);

		return cashStreams;
	}

	/**
	 * 初始化公共部分
	 * 
	 * @param cashStream
	 * @param creditapplicationId
	 */
	public void initEvery(CashStream cashStream, Integer creditapplicationId, String name, String type) {
		cashStream.setCreditapplicationId(creditapplicationId);
		cashStream.setBorrowerName(name);
		// cashStream.setEveryDay(Double.valueOf("0"));
		// cashStream.setEveryWeek(Double.valueOf("0"));
		// cashStream.setEveryMonth(Double.valueOf("0"));
		// cashStream.setEveryQuarter(Double.valueOf("0"));
		// cashStream.setEveryHalfYear(Double.valueOf("0"));
		// cashStream.setNineMonth(Double.valueOf("0"));
		// cashStream.setEveryYear(Double.valueOf("0"));
		// cashStream.setCalculateValue(Double.valueOf("0"));
		if (CommonUtil.isNotEmpty(type) && "0".equals(type)) {
			cashStream.setCreateDate(new Timestamp( new Date().getTime()) );
		} else {
			cashStream.setCreateDate(null);
		}
	}

	/**
	 * 将数据列封装成一个对象
	 * 
	 * @return
	 */
	public CashStreamVo assemblyObj(List<CashStream> cashStreams) {

		CashStreamVo csv = new CashStreamVo();
		// 经营现金流入项目
		List<CashStream> csOperInList = new ArrayList<CashStream>();
		// 经营现金流入合计
		CashStream csOperInTotal = new CashStream();
		// 经营现金流出项目
		List<CashStream> csOperOutList = new ArrayList<CashStream>();
		// 经营现金流出项目子项目
		List<CashStream> csOperOutListChild = new ArrayList<CashStream>();
		// 经营现金流出合计
		CashStream csOperOutTotal = new CashStream();
		// 经营净现金流
		CashStream csOperNet = new CashStream();
		// 家庭现金流入项目
		List<CashStream> csFamilyInList = new ArrayList<CashStream>();
		// 家庭现金流入合计
		CashStream csFamilyInTotal = new CashStream();
		// 家庭现金流出项目
		List<CashStream> csFamilyOutList = new ArrayList<CashStream>();
		// 家庭现金流出合计
		CashStream csFamilyOutTotal = new CashStream();
		// 家庭净现金流
		CashStream csFamilyNet = new CashStream();
		// 净现金流合计
		CashStream csNetTotal = new CashStream();
		// 每日等效净现金流
		CashStream csNetEveryDayEq = new CashStream();
		// 每周等效净现金流
		CashStream csNetEveryWeekEq = new CashStream();
		// 每月等效净现金流
		CashStream csNetEveryMonthEq = new CashStream();
		// 每季度等效净现金流
		CashStream csNetEveryQuarterEq = new CashStream();
		// 每半年等效净现金流
		CashStream csNetEveryHalfYearEq = new CashStream();
		// 九个月等效净现金流
		//CashStream csNetEverynineMonthEq = new CashStream();
		// 年度等效净现金流
		CashStream csNetEveryYearEq = new CashStream();
		// 可支配现金
		CashStream canDominateMoney = new CashStream();
		// 现金流调整系数
		CashStream csAdjCoefficient = new CashStream();
		// 默认75
		String adjvalue = "75";
		// 如果字典表配置了则从字典表取值
		if (DicUtil.sectionMap.get("cashStream") != null) {
			adjvalue = DicUtil.sectionMap.get("cashStream").get("adjCoeffi");
		}
		csAdjCoefficient.setCalculateValue(Double.valueOf(adjvalue));
		// 年化利率
		CashStream yearInterestRate = new CashStream();
		// 借款期限
		CashStream loanDeadline = new CashStream();
		// 等额还款期数
		CashStream eqRepaymentPeriod = new CashStream();
		// 申请借款金额
		CashStream applyLoanSum = new CashStream();
		// 最大借款额度
		CashStream maxLoanSum = new CashStream();

		// 对从数据库取到的数据库条目进行遍历，并将对象分类
		for (int i = 0; i < cashStreams.size(); i++) {
			CashStream cs = cashStreams.get(i);
			String projType = cs.getProjectType();
			// 根据projType分类
			if (projType.startsWith("operIn")) {
				csOperInList.add(cs);
			} else if (projType.endsWith("operIn")) {
				csOperInTotal = cs;
			} else if (projType.startsWith("operOut") && !projType.contains("proj")) {// 排除子项目
				csOperOutList.add(cs);
			} else if (projType.startsWith("operOut_proj")) {// 添加子项目
				csOperOutListChild.add(cs);
			} else if (projType.endsWith("operOut")) {
				csOperOutTotal = cs;
			} else if (projType.equals("operNet")) {
				csOperNet = cs;
			} else if (projType.startsWith("familyIn")) {
				csFamilyInList.add(cs);
			} else if (projType.endsWith("familyIn")) {
				csFamilyInTotal = cs;
			} else if (projType.startsWith("familyOut")) {
				csFamilyOutList.add(cs);
			} else if (projType.endsWith("familyOut")) {
				csFamilyOutTotal = cs;
			} else if (projType.equals("familyNet")) {
				csFamilyNet = cs;
			} else if (projType.equals("netTotal")) {
				csNetTotal = cs;
			} else if (projType.equals("netEveryDayEq")) {
				csNetEveryDayEq = cs;
			} else if (projType.equals("netEveryWeekEq")) {
				csNetEveryWeekEq = cs;
			} else if (projType.equals("netEveryMonthEq")) {
				csNetEveryMonthEq = cs;
			} else if (projType.equals("netEveryQuarterEq")) {
				csNetEveryQuarterEq = cs;
			} else if (projType.equals("netEveryHalfYearEq")) {
				csNetEveryHalfYearEq = cs;
			/*} else if (projType.equals("netEverynineMonthEq")) {
				csNetEverynineMonthEq = cs;*/
			} else if (projType.equals("netEveryYearEq")) {
				csNetEveryYearEq = cs;
			} else if (projType.equals("canDominateMoney")) {
				canDominateMoney = cs;
			} else if (projType.equals("yearInterestRate")) {
				yearInterestRate = cs;
//                if (null != yearInterestRate.getCalculateValue() && StringUtils.isNotEmpty(yearInterestRate.getCalculateValue().toString())) {
                    /*增加打折判断*/
                    CreditApplication application = new CreditApplication();
                    application.setCreditapplicationId(yearInterestRate.getCreditapplicationId());
                    application = creditApplicationService.selectCreditApplication(application);
                        /*获取打折、非打折年化利率*/
                    String discount = dataDictionaryService.getCodeValueByKey("yearInterestRate", application.getRepaymentPlanName().concat("1"));
                    String non_discount = dataDictionaryService.getCodeValueByKey("yearInterestRate", application.getRepaymentPlanName().concat("0"));
                        /*根据折扣率判断是否打折*/
                    yearInterestRate.setCalculateValue(CurrencyUtil.mul(Double.valueOf(non_discount == null ? "0" : non_discount), Double.valueOf("100")) == 0 ? null : CurrencyUtil.mul(Double.valueOf(non_discount), Double.valueOf("100")));// 显示的时候乘以100
                    String discountFlag = application.getDiscountFlag();
                    if (StringUtils.isNotEmpty(discountFlag) && discountFlag.equals("1")) {
                            /*获取折扣率*/
                        DiscountConfiguration configuration = new DiscountConfiguration();
                        configuration.setAreaDepartmentId(Long.valueOf(application.getCompanyId()));
                        configuration.setProductCategoryId(Long.valueOf(application.getProducttypeid()));
                        List<DiscountConfiguration> configurations = discountConfigurationService.queryDiscountConfigurationList(configuration, new Pagination()).getRows();
                        if (CommonUtil.isNotEmpty(configurations) && !configurations.get(0).getDiscountRate().toString().equals("1")) {
                            yearInterestRate.setCalculateValue(CurrencyUtil.mul(Double.valueOf(discount == null ? "0" : discount), Double.valueOf("100")) == 0 ? null : CurrencyUtil.mul(Double.valueOf(discount), Double.valueOf("100")));// 显示的时候乘以100
                        }
                    }
                    /*之前版本：仅判断新老客户
                    yearInterestRate.setCalculateValue(CurrencyUtil.mul(yearInterestRate.getCalculateValue(), Double.valueOf("100")));// 显示的时候乘以100*/
//                }
			} else if (projType.equals("loanDeadline")) {
				loanDeadline = cs;
			} else if (projType.equals("eqRepaymentPeriod")) {
				eqRepaymentPeriod = cs;
			} else if (projType.equals("applyLoanSum")) {
				applyLoanSum = cs;
			} else if (projType.equals("maxLoanSum")) {
				maxLoanSum = cs;
			}
		}
		Collections.sort(csOperInList, new Comparator<CashStream>() {

			@Override
			public int compare(CashStream o1, CashStream o2) {
				if (CommonUtil.isEmpty(o1.getProjectName()) && CommonUtil.isNotEmpty(o2.getProjectName())) {
					return 1;
				} else if (CommonUtil.isNotEmpty(o1.getProjectName()) && CommonUtil.isEmpty(o2.getProjectName())) {
					return -1;
				} else if (CommonUtil.isEmpty(o1.getProjectName()) && CommonUtil.isEmpty(o2.getProjectName())) {
					return 0;
				} else {
					return o1.getProjectName().compareTo(o2.getProjectName());
				}
			}
		});
		Collections.sort(csOperOutListChild, new Comparator<CashStream>() {

			@Override
			public int compare(CashStream o1, CashStream o2) {
				if (CommonUtil.isEmpty(o1.getProjectName()) && CommonUtil.isNotEmpty(o2.getProjectName())) {
					return 1;
				} else if (CommonUtil.isNotEmpty(o1.getProjectName()) && CommonUtil.isEmpty(o2.getProjectName())) {
					return -1;
				} else if (CommonUtil.isEmpty(o1.getProjectName()) && CommonUtil.isEmpty(o2.getProjectName())) {
					return 0;
				} else {
					return o1.getProjectName().compareTo(o2.getProjectName());
				}
			}
		});
		csv.setCsOperInList(csOperInList);
		csv.setCsOperInTotal(csOperInTotal);
		csv.setCsOperOutList(csOperOutList);
		csv.setCsOperOutListChild(csOperOutListChild);
		csv.setCsOperOutTotal(csOperOutTotal);
		csv.setCsOperNet(csOperNet);
		csv.setCsFamilyInList(csFamilyInList);
		csv.setCsFamilyInTotal(csFamilyInTotal);
		csv.setCsFamilyOutList(csFamilyOutList);
		csv.setCsFamilyOutTotal(csFamilyOutTotal);
		csv.setCsFamilyNet(csFamilyNet);
		csv.setCsNetTotal(csNetTotal);
		csv.setCsNetEveryDayEq(csNetEveryDayEq);
		csv.setCsNetEveryWeekEq(csNetEveryWeekEq);
		csv.setCsNetEveryMonthEq(csNetEveryMonthEq);
		csv.setCsNetEveryQuarterEq(csNetEveryQuarterEq);
		csv.setCsNetEveryHalfYearEq(csNetEveryHalfYearEq);
		//csv.setCsNetEverynineMonthEq(csNetEverynineMonthEq);
		csv.setCsNetEveryYearEq(csNetEveryYearEq);
		csv.setCsAdjCoefficient(csAdjCoefficient);
		csv.setCanDominateMoney(canDominateMoney);
		csv.setYearInterestRate(yearInterestRate);
		// 借款期限
		CreditApplication creditApplication = creditApplicationService
				.selectById(loanDeadline.getCreditapplicationId());
		Double loanDeadLineNum = Double.valueOf(creditApplication.getInstalments());
		loanDeadline.setCalculateValue(loanDeadLineNum);
		csv.setLoanDeadline(loanDeadline);
		// 等额还款期限
		eqRepaymentPeriod.setCalculateValue(loanDeadLineNum - 2);
		csv.setEqRepaymentPeriod(eqRepaymentPeriod);
		csv.setApplyLoanSum(applyLoanSum);
		csv.setMaxLoanSum(maxLoanSum);

		return csv;
	}

	/**
	 * 统计函数
	 * 
	 * @param cashStreamVo
	 * @return
	 */
	public CashStreamVo calculate(CashStreamVo cashStreamVo) {

		// 经营现金流入项目
		List<CashStream> csOperInList = cashStreamVo.getCsOperInList();

		// 计算每行值(等效月度)
		for (int i = 0; i < csOperInList.size(); i++) {
			calculateEqMonth(csOperInList.get(i));
		}
		// 经营现金流入合计
		CashStream csOperInTotal = cashStreamVo.getCsOperInTotal();
		this.calculateTotalDown(csOperInList, csOperInTotal);

		// 流出项目
		List<CashStream> csOperOutList = cashStreamVo.getCsOperOutList();
		for (int i = 0; i < csOperOutList.size(); i++) {
			calculateEqMonth(csOperOutList.get(i));
		}
		// 流出项目子项目
		List<CashStream> csOperOutListChild = cashStreamVo.getCsOperOutListChild();
		for (int i = 0; i < csOperOutListChild.size(); i++) {
			calculateEqMonth(csOperOutListChild.get(i));
		}
		csOperOutList.addAll(csOperOutListChild);
		// 经营现金流出合计
		CashStream csOperOutTotal = cashStreamVo.getCsOperOutTotal();
		this.calculateTotalDown(csOperOutList, csOperOutTotal);

		// 经营净现金流
		CashStream csOperNet = cashStreamVo.getCsOperNet();
		calculateNetTotalSub(csOperInTotal, csOperOutTotal, csOperNet);

		// 家庭现金流入项目
		List<CashStream> csFamilyInList = cashStreamVo.getCsFamilyInList();
		for (int i = 0; i < csFamilyInList.size(); i++) {
			calculateEqMonth(csFamilyInList.get(i));
		}

		// 家庭现金流入合计
		CashStream csFamilyInTotal = cashStreamVo.getCsFamilyInTotal();
		this.calculateTotalDown(csFamilyInList, csFamilyInTotal);

		// 家庭现金流出项目
		List<CashStream> csFamilyOutList = cashStreamVo.getCsFamilyOutList();
		for (int i = 0; i < csFamilyOutList.size(); i++) {
			calculateEqMonth(csFamilyOutList.get(i));
		}

		// 家庭现金流出合计
		CashStream csFamilyOutTotal = cashStreamVo.getCsFamilyOutTotal();
		this.calculateTotalDown(csFamilyOutList, csFamilyOutTotal);

		// 家庭净现金流
		CashStream csFamilyNet = cashStreamVo.getCsFamilyNet();
		calculateNetTotalSub(csFamilyInTotal, csFamilyOutTotal, csFamilyNet);

		// 净现金流合计
		CashStream csNetTotal = cashStreamVo.getCsNetTotal();
		calculateNetTotalAdd(csOperNet, csFamilyNet, csNetTotal);

		// 每日等效现金流=C56*30
		CashStream csNetEveryDayEq = cashStreamVo.getCsNetEveryDayEq();
		csNetEveryDayEq.setCalculateValue(CurrencyUtil.mul(csNetTotal.getEveryDay(), Double.valueOf("30")));

		// 每周等效现金流=D56/7*30
		CashStream csNetEveryWeekEq = cashStreamVo.getCsNetEveryWeekEq();
		csNetEveryWeekEq.setCalculateValue(CurrencyUtil.mul(
				CurrencyUtil.div(csNetTotal.getEveryWeek(), Double.valueOf("7")), Double.valueOf("30")));

		// 每月等效现金流=E56
		CashStream csNetEveryMonthEq = cashStreamVo.getCsNetEveryMonthEq();
		csNetEveryMonthEq.setCalculateValue(csNetTotal.getEveryMonth());

		// 每季度等效现金流=F56/3
		CashStream csNetEveryQuarterEq = cashStreamVo.getCsNetEveryQuarterEq();
		csNetEveryQuarterEq.setCalculateValue(CurrencyUtil.div(csNetTotal.getEveryQuarter(), Double.valueOf("3")));

		// 每半年等效现金流=G56/6
		CashStream csNetEveryHalfYearEq = cashStreamVo.getCsNetEveryHalfYearEq();
		csNetEveryHalfYearEq.setCalculateValue(CurrencyUtil.div(csNetTotal.getEveryHalfYear(), Double.valueOf("6")));

		// 九个月等效现金流=H56/9
		/*CashStream csNetEverynineMonthEq = cashStreamVo.getCsNetEverynineMonthEq();
		csNetEverynineMonthEq.setCalculateValue(CurrencyUtil.div(csNetTotal.getNineMonth(), Double.valueOf("9")));
*/
		// 年度等效现金流=I56/9
		CashStream csNetEveryYearEq = cashStreamVo.getCsNetEveryYearEq();
		csNetEveryYearEq.setCalculateValue(CurrencyUtil.div(csNetTotal.getEveryYear(), Double.valueOf("12")));

		// 年化利率
		CashStream yearInterestRate = cashStreamVo.getYearInterestRate();
		yearInterestRate
				.setCalculateValue(CurrencyUtil.div(yearInterestRate.getCalculateValue(), Double.valueOf("100")));

		// 可支配现金=E56~E63求和
		CashStream canDominateMoney = cashStreamVo.getCanDominateMoney();
		canDominateMoney.setCalculateValue(CurrencyUtil.add(csNetTotal.getEveryMonth(),
				csNetEveryDayEq.getCalculateValue(), csNetEveryWeekEq.getCalculateValue(),
				csNetEveryMonthEq.getCalculateValue(), csNetEveryQuarterEq.getCalculateValue(),
				//csNetEverynineMonthEq.getCalculateValue(), csNetEveryHalfYearEq.getCalculateValue(),
				csNetEveryYearEq.getCalculateValue()));

		// 最大借款额度=（E64*E65*E67）/(1+（E66/12*12))
		CashStream maxLoanSum = cashStreamVo.getMaxLoanSum();
		Double d1 = CurrencyUtil.mul(CurrencyUtil.mul(canDominateMoney.getCalculateValue(), Double.valueOf("0.75")),
				cashStreamVo.getLoanDeadline().getCalculateValue());
		Double d2 = CurrencyUtil.add(
				Double.valueOf("1"),
				CurrencyUtil.div(cashStreamVo.getYearInterestRate().getCalculateValue(),
						CurrencyUtil.mul(Double.valueOf("12"), Double.valueOf("12"))));
		maxLoanSum.setCalculateValue(CurrencyUtil.div(d1, d2));

		return cashStreamVo;
	}

	/**
	 * 计算等效月度(行) 公式==C6*30+D6/7*30+E6+F6/3+G6/6+H6/9+I6/12
	 * 
	 * @param cashStream
	 */
	public void calculateEqMonth(CashStream cashStream) {
		Double calculateValue = CurrencyUtil
				.add(CurrencyUtil.mul(cashStream.getEveryDay(), Double.valueOf("30")),
						CurrencyUtil.mul(CurrencyUtil.div(cashStream.getEveryWeek(), Double.valueOf("7")),
								Double.valueOf("30")), cashStream.getEveryMonth(),
						CurrencyUtil.div(cashStream.getEveryQuarter(), Double.valueOf("3")),
						CurrencyUtil.div(cashStream.getEveryHalfYear(), Double.valueOf("6")),
						CurrencyUtil.div(cashStream.getNineMonth(), Double.valueOf("9")),
						CurrencyUtil.div(cashStream.getEveryYear(), Double.valueOf("12")));
		cashStream.setCalculateValue(calculateValue);
	}

	/**
	 * 计算流入流出合计(列)
	 * 
	 * @param cashStreams
	 * @param cashStream
	 */
	public void calculateTotalDown(List<CashStream> cashStreams, CashStream cashStream) {

		Double everyDay = 0d;
		Double everyWeek = 0d;
		Double everyMonth = 0d;
		Double everyQuarter = 0d;
		Double everyHalfYear = 0d;
		Double nineMonth = 0d;
		Double everyYear = 0d;
		Double calculateValue = 0d;
		// 按列叠加计算
		for (int i = 0; i < cashStreams.size(); i++) {
			CashStream cs = cashStreams.get(i);
			everyDay = CurrencyUtil.add(everyDay, cs.getEveryDay());
			everyWeek = CurrencyUtil.add(everyWeek, cs.getEveryWeek());
			everyMonth = CurrencyUtil.add(everyMonth, cs.getEveryMonth());
			everyQuarter = CurrencyUtil.add(everyQuarter, cs.getEveryQuarter());
			everyHalfYear = CurrencyUtil.add(everyHalfYear, cs.getEveryHalfYear());
			nineMonth = CurrencyUtil.add(nineMonth, cs.getNineMonth());
			everyYear = CurrencyUtil.add(everyYear, cs.getEveryYear());
			calculateValue = CurrencyUtil.add(calculateValue, cs.getCalculateValue());
		}

		cashStream.setEveryDay(everyDay);
		cashStream.setEveryWeek(everyWeek);
		cashStream.setEveryMonth(everyMonth);
		cashStream.setEveryQuarter(everyQuarter);
		cashStream.setEveryHalfYear(everyHalfYear);
		cashStream.setNineMonth(nineMonth);
		cashStream.setEveryYear(everyYear);
		cashStream.setCalculateValue(calculateValue);

	}

	/**
	 * 计算净现金流
	 * 
	 * @param cashStream
	 *            (净现金流对象)
	 */
	public void calculateNetTotal(List<CashStream> ins, List<CashStream> outs, CashStream cashStream) {

		CashStream csInTotal = new CashStream();
		this.calculateTotalDown(ins, csInTotal);

		CashStream csOutTotal = new CashStream();
		this.calculateTotalDown(outs, csOutTotal);

		cashStream.setEveryDay(CurrencyUtil.sub(csInTotal.getEveryDay(), csOutTotal.getEveryDay()));
		cashStream.setEveryWeek(CurrencyUtil.sub(csInTotal.getEveryWeek(), csOutTotal.getEveryWeek()));
		cashStream.setEveryMonth(CurrencyUtil.sub(csInTotal.getEveryMonth(), csOutTotal.getEveryMonth()));
		cashStream.setEveryQuarter(CurrencyUtil.sub(csInTotal.getEveryQuarter(), csOutTotal.getEveryQuarter()));
		cashStream.setEveryHalfYear(CurrencyUtil.sub(csInTotal.getEveryHalfYear(), csOutTotal.getEveryHalfYear()));
		cashStream.setNineMonth(CurrencyUtil.sub(csInTotal.getNineMonth(), csOutTotal.getNineMonth()));
		cashStream.setEveryYear(CurrencyUtil.sub(csInTotal.getEveryYear(), csOutTotal.getEveryYear()));

	}

	/**
	 * 计算净现金流(减)
	 * 
	 * @param cashStream
	 *            (净现金流对象)
	 */
	public void calculateNetTotalSub(CashStream in, CashStream out, CashStream cashStream) {

		cashStream.setEveryDay(CurrencyUtil.sub(in.getEveryDay(), out.getEveryDay()));
		cashStream.setEveryWeek(CurrencyUtil.sub(in.getEveryWeek(), out.getEveryWeek()));
		cashStream.setEveryMonth(CurrencyUtil.sub(in.getEveryMonth(), out.getEveryMonth()));
		cashStream.setEveryQuarter(CurrencyUtil.sub(in.getEveryQuarter(), out.getEveryQuarter()));
		cashStream.setEveryHalfYear(CurrencyUtil.sub(in.getEveryHalfYear(), out.getEveryHalfYear()));
		cashStream.setNineMonth(CurrencyUtil.sub(in.getNineMonth(), out.getNineMonth()));
		cashStream.setEveryYear(CurrencyUtil.sub(in.getEveryYear(), out.getEveryYear()));

	}

	/**
	 * 计算净现金流(加)
	 * 
	 * @param cashStream
	 *            (净现金流对象)
	 */
	public void calculateNetTotalAdd(CashStream cs1, CashStream cs2, CashStream cashStream) {

		cashStream.setEveryDay(CurrencyUtil.add(cs1.getEveryDay(), cs2.getEveryDay()));
		cashStream.setEveryWeek(CurrencyUtil.add(cs1.getEveryWeek(), cs2.getEveryWeek()));
		cashStream.setEveryMonth(CurrencyUtil.add(cs1.getEveryMonth(), cs2.getEveryMonth()));
		cashStream.setEveryQuarter(CurrencyUtil.add(cs1.getEveryQuarter(), cs2.getEveryQuarter()));
		cashStream.setEveryHalfYear(CurrencyUtil.add(cs1.getEveryHalfYear(), cs2.getEveryHalfYear()));
		cashStream.setNineMonth(CurrencyUtil.add(cs1.getNineMonth(), cs2.getNineMonth()));
		cashStream.setEveryYear(CurrencyUtil.add(cs1.getEveryYear(), cs2.getEveryYear()));

	}

	@Override
	public int addEveryDayOperCsIns(List<OperAverageCsIn> everyDayOperCsIns) {
		cashStreamDAO.addEveryDayOperCsIns(everyDayOperCsIns);
		return 0;
	}

	@Override
	public int updateCashStreamWorkTableVo(CashStreamWorkTableVo cashStreamWorkTableVo) {

		List<OperAverageCsIn> everyDayOperCsIns = cashStreamWorkTableVo.getEveryDayOperCsIn();
		List<OperAverageCsIn> everyWeekOperCsIns = cashStreamWorkTableVo.getEveryWeekOperCsIn();
		List<OperAverageCsIn> everyMonthOperCsIns = cashStreamWorkTableVo.getEveryMonthOperCsIn();
		List<OperAverageCsIn> everyCostOperCsIns = cashStreamWorkTableVo.getEveryCostOperCsIn();
		List<AvgIncRate> avgIncRates = cashStreamWorkTableVo.getAvgIncRates();
		List<PurchaceCost> purchaceCosts = cashStreamWorkTableVo.getPurchaceCosts();

		if (everyDayOperCsIns != null) {
			cashStreamDAO.updateEveryDayOperCsIns(everyDayOperCsIns);
		} else if (everyWeekOperCsIns != null) {
			cashStreamDAO.updateEveryDayOperCsIns(everyWeekOperCsIns);
		} else if (everyMonthOperCsIns != null) {
			cashStreamDAO.updateEveryDayOperCsIns(everyMonthOperCsIns);
		} else if (everyCostOperCsIns != null) {
			cashStreamDAO.updateEveryDayOperCsIns(everyCostOperCsIns);
		} else if (avgIncRates != null) {
			cashStreamDAO.updateAvgIncRates(avgIncRates);
		} else if (purchaceCosts != null) {
			cashStreamDAO.updatePurchaceCosts(purchaceCosts);
		}
		return 0;
	}

	@Override
	public synchronized int addCashStreamWorkTable(String param) {

		int count = this.getCountInCashStreamWorkTable(Integer.parseInt(param));

		if (count > 0) {
			return -1;
		}

		List<OperAverageCsIn> everyDayOperCsIns = new ArrayList<OperAverageCsIn>();
		List<AvgIncRate> avgIncRates = new ArrayList<AvgIncRate>();
		List<PurchaceCost> purchaceCosts = new ArrayList<PurchaceCost>();

		// 初始化 平均每天经营现金流入表
		for (int i = 0; i < 9; i++) {
			OperAverageCsIn edoci = new OperAverageCsIn();
			edoci.setBelongId(Integer.parseInt(param));
			edoci.setTimeCycle("day");
			edoci.setItemSort(i);
			everyDayOperCsIns.add(edoci);
		}
		// 初始化 平均每周经营现金流入表
		for (int i = 0; i < 9; i++) {
			OperAverageCsIn edoci = new OperAverageCsIn();
			edoci.setBelongId(Integer.parseInt(param));
			edoci.setTimeCycle("week");
			edoci.setItemSort(i);
			everyDayOperCsIns.add(edoci);
		}
		// 初始化 平均每月经营现金流入表
		for (int i = 0; i < 14; i++) {
			OperAverageCsIn edoci = new OperAverageCsIn();
			edoci.setBelongId(Integer.parseInt(param));
			edoci.setTimeCycle("month");
			edoci.setItemSort(i);
			everyDayOperCsIns.add(edoci);
		}

		// 初始化 采购或进货成本表
		for (int i = 0; i < 4; i++) {
			OperAverageCsIn edoci = new OperAverageCsIn();
			edoci.setBelongId(Integer.parseInt(param));
			edoci.setTimeCycle("cost");
			edoci.setItemSort(i);
			everyDayOperCsIns.add(edoci);
		}

		// 初始化 平均加价率
		for (int i = 0; i < 24; i++) {
			AvgIncRate edoci = new AvgIncRate();
			edoci.setBelongId(Integer.parseInt(param));
			edoci.setItemSort(i);
			avgIncRates.add(edoci);
		}

		// 初始化 表 4:采购或进货成本
		for (int i = 0; i < 12; i++) {
			PurchaceCost edoci = new PurchaceCost();
			edoci.setBelongId(Integer.parseInt(param));
			purchaceCosts.add(edoci);
		}

		this.addEveryDayOperCsIns(everyDayOperCsIns);
		this.addAvgIncRates(avgIncRates);
		this.addPurchaceCosts(purchaceCosts);

		return 0;
	}

	@Override
	public int getCountInCashStreamWorkTable(int belongId) {
		int count = cashStreamDAO.getCountInCashStreamWorkTable(belongId);
		return count;
	}

	@Override
	public CashStreamWorkTableVo getCashStreamWorkTableVo(int belongId) {

		CashStreamWorkTableVo cashStreamWorkTableVo = new CashStreamWorkTableVo();
		List<OperAverageCsIn> everyDayOperCsIns = new ArrayList<OperAverageCsIn>();
		List<OperAverageCsIn> everyWeekOperCsIns = new ArrayList<OperAverageCsIn>();
		List<OperAverageCsIn> everyMonthOperCsIns = new ArrayList<OperAverageCsIn>();
		List<OperAverageCsIn> everyCostOperCsIns = new ArrayList<OperAverageCsIn>();
		List<OperAverageCsIn> all = cashStreamDAO.getEveryDayOperCsInsByBelongId(belongId);

		List<AvgIncRate> avgIncRates = new ArrayList<AvgIncRate>();
		avgIncRates = cashStreamDAO.getAvgIncRatesByBelongId(belongId);

		List<PurchaceCost> purchaceCosts = new ArrayList<PurchaceCost>();
		purchaceCosts = cashStreamDAO.getPurchaceCostsByBelongId(belongId);

		for (int i = 0; i < all.size(); i++) {
			OperAverageCsIn oaci = all.get(i);
			if (oaci.getTimeCycle().equals("day")) {
				everyDayOperCsIns.add(oaci);
			} else if (oaci.getTimeCycle().equals("week")) {
				everyWeekOperCsIns.add(oaci);
			} else if (oaci.getTimeCycle().equals("month")) {
				everyMonthOperCsIns.add(oaci);
			} else if (oaci.getTimeCycle().equals("cost")) {
				everyCostOperCsIns.add(oaci);
			}
		}
		cashStreamWorkTableVo.setEveryDayOperCsIn(everyDayOperCsIns);
		cashStreamWorkTableVo.setEveryWeekOperCsIn(everyWeekOperCsIns);
		cashStreamWorkTableVo.setEveryMonthOperCsIn(everyMonthOperCsIns);
		cashStreamWorkTableVo.setEveryCostOperCsIn(everyCostOperCsIns);
		cashStreamWorkTableVo.setAvgIncRates(avgIncRates);
		cashStreamWorkTableVo.setPurchaceCosts(purchaceCosts);

		return cashStreamWorkTableVo;
	}

	@Override
	public int addAvgIncRates(List<AvgIncRate> avgIncRates) {
		cashStreamDAO.addAvgIncRates(avgIncRates);
		return 0;
	}

	@Override
	public int addPurchaceCosts(List<PurchaceCost> purchaceCosts) {
		cashStreamDAO.addPurchaceCosts(purchaceCosts);
		return 0;
	}

	@Override
	public int delAllByBelongId(int belongId) {
		cashStreamDAO.deleteAvgIncRateByBelongId(belongId);
		cashStreamDAO.deleteCashStreamByCreditId(belongId);
		cashStreamDAO.deleteEveryDayOperCsInByBelongId(belongId);
		cashStreamDAO.deletePurchaceCostByBelongId(belongId);
		return 0;
	}

	@Override
	public Double getCalValByCreditId(int creditId) {
		List<CashStream> l = cashStreamDAO.getCalValByCreditId(creditId);
		CashStream cs = null;
		Double returnValue = 0d;
		if (l != null && l.size() > 0) {
			cs = l.get(0);
			returnValue = cs.getCalculateValue();
		}
		if (returnValue == null) {
			returnValue = 0d;
		}
		return returnValue;
	}

	/*
	 * 将excel数据导入到现金流表中 liuli 2013-05-24
	 */
	@Override
	public String importCashStreamExcel(Sheet sheet) {

		Map excelMap = new HashMap();
		String bNumTmp = "";
		List cseVoList = new ArrayList();
		// 从第二行开始取数据，第一行是表头
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			// 获取行数据
			Row row = sheet.getRow(i);
			// 获取业务编号
			String bNum = killNull(row.getCell(0)).toString().trim();
			String borrowName = killNull(row.getCell(1)).toString().trim();
			String cashStreamType = killNull(row.getCell(2)).toString().trim();// 现金流类型
			String level1Name = killNull(row.getCell(3)).toString().trim();// 一级项目名
			String level2Name = killNull(row.getCell(4)).toString().trim();// 二级项目名
			String frequency = killNull(row.getCell(5)).toString().trim();// 频率
// System.out.println("level2Name==="+level2Name+"--bNum="+bNum);
			Double amount = 0d;
			if (row.getCell(6) != null) {
				amount = row.getCell(6).getNumericCellValue();
			}

			// 将业务单号赋值到变量中
			if (!bNum.equals(bNumTmp)) {
// System.out.println("将业务单号赋值到变量中--bNum="+bNum+"--bNumTmp="+bNumTmp);
				cseVoList = new ArrayList();
				bNumTmp = bNum;
				CashStreamExcelVo cseVo = new CashStreamExcelVo();
				cseVo.setBusinessNum(bNum);
				cseVo.setBorrowName(borrowName);
				cseVo.setCashStreamType(cashStreamType);
				cseVo.setLevel1Name(level1Name);
				cseVo.setLevel2Name(level2Name);
				cseVo.setFrequency(frequency);
				cseVo.setAmount(amount);
				// 将对象放到列表中
				cseVoList.add(cseVo);
				excelMap.put(bNum, cseVoList);
			} else {
// cseVoList = (List)excelMap.get(bNum);
// System.out.println("等于--");
				CashStreamExcelVo cseVo = new CashStreamExcelVo();
				cseVo.setBusinessNum(bNum);
				cseVo.setBorrowName(borrowName);
				cseVo.setCashStreamType(cashStreamType);
				cseVo.setLevel1Name(level1Name);
				cseVo.setLevel2Name(level2Name);
				cseVo.setFrequency(frequency);
				cseVo.setAmount(amount);
				// 将对象放到列表中
				cseVoList.add(cseVo);
// excelMap.put(bNum, cseVoList);
			}

		}
		Set<String> key = excelMap.keySet();
		// 根据业务单号遍历map对象，相当于取每个人的列表
		for (Iterator it = key.iterator(); it.hasNext();) {
			String bNum = (String) it.next();
// System.out.println("----------");
// System.out.println("----------");
			List l = (List) excelMap.get(bNum);
			if (l != null && l.size() > 0) {
				// 对列表进行处理
				this.insertIntoDataBase(l);
// for(int i = 0;i<l.size();i++){
// CashStreamExcelVo cseVo = (CashStreamExcelVo)l.get(i);
// System.out.println("二级项目-----"+cseVo.getLevel2Name()+"--业务单号--"+cseVo.getBusinessNum());
// }
			}
		}
		// 用于保存需要更新的对象
		// Set<CashStream> hashSet = new HashSet<CashStream>();
		// List<CashStream> l = new ArrayList<CashStream>();
		// for (int i = 1; i <= sheet.getLastRowNum(); i++) {
		// Row row = sheet.getRow(i);
		// // String num = killNull(row.getCell(0)).toString();//
		// String cashStreamType = killNull(row.getCell(2)).toString();// 现金流类型
		// String level1Name = killNull(row.getCell(3)).toString();// 一级项目名
		// String level2Name = killNull(row.getCell(4)).toString();// 二级项目名
		// String frequency = killNull(row.getCell(5)).toString();// 频率
		// Double money = 0d;
		// CashStream cs = null;
		// if (row.getCell(6) != null) {
		// money = row.getCell(6).getNumericCellValue();
		// }
		// //
		// System.out.println("cashStreamType--"+cashStreamType+"--level1Name-----"+level1Name+"--"+(level1Name.equals("项目1")));
		// cs = this.getCsByTypeAndName(cashStreamType, level1Name,
		// cashStreams);
		//
		// // 设置相应的值
		// if (cs != null) {
		// this.setValueForDay(cs, frequency, money);
		// // 设置其他的项目名
		// if (level1Name.contains("其他")) {
		// if (!level2Name.equals("")) {
		// cs.setProjectName(level2Name);
		// }
		// }
		// //
		// System.out.println("cs--"+cs.getProjectType()+"-getEveryYear-"+cs.getEveryYear());
		// hashSet.add(cs);
		// // l.add(cs);
		// }
		// }
		// // 使用set是为了集合中的对象不重复，比如食品有每日，每周的都是一条对象里的。
		// Iterator<CashStream> iterator = hashSet.iterator();
		// while (iterator.hasNext()) {
		// CashStream cs = iterator.next();
		// cashStreamDAO.updateById(cs);
		// }
		// for(int i=0;i<l.size();i++){
		// CashStream cs = l.get(i);
		// cashStreamDAO.updateById(cs);
		// }
		return "";
	}

	/**
	 * @param cashStreamExcelVos
	 */
	public void insertIntoDataBase(List<CashStreamExcelVo> cashStreamExcelVos) {
		// 用于保存需要更新的对象
		Set<CashStream> hashSet = new HashSet<CashStream>();
		List<CashStream> l = new ArrayList<CashStream>();
		List<CashStream> cashStreams = new ArrayList<CashStream>();
		int line = cashStreamExcelVos.size();
		String creditAppNum = cashStreamExcelVos.get(0).getBusinessNum();
		String borrowName = cashStreamExcelVos.get(0).getBorrowName();
		CreditApplication creditApplication = creditApplicationService.selectByGroupNumber(creditAppNum);
// System.out.println("--creditApplication="+creditApplication.getCreditapplicationId());
		if (creditApplication != null) {

			int creditapplicasthionId = creditApplication.getCreditapplicationId();
			// TODO 删除原来的数据
			this.deleteCashStream(creditapplicasthionId);
			// 跟页面点击一样，如果数据库没有该id的数据则初始化数据
			this.addCashStreams(creditapplicasthionId, borrowName, "1");
			// 从数据库取数据
			cashStreams = this.getCashStreamsByCreditAppId(creditapplicasthionId);
		}
		for (int i = 0; i < line; i++) {
// Row row = sheet.getRow(i);
			CashStreamExcelVo excelVo = (CashStreamExcelVo) cashStreamExcelVos.get(i);
			// String num = killNull(row.getCell(0)).toString();//
			String cashStreamType = killNull(excelVo.getCashStreamType()).toString();// 现金流类型
			String level1Name = killNull(excelVo.getLevel1Name()).toString();// 一级项目名
			String level2Name = killNull(excelVo.getLevel2Name()).toString();// 二级项目名
			String frequency = killNull(excelVo.getFrequency()).toString();// 频率
			Double money = 0d;
			CashStream cs = null;
			if (excelVo.getAmount() != null) {
				money = excelVo.getAmount();
			}
			//
// System.out.println("cashStreamType--" + cashStreamType
// + "--level1Name-----" + level1Name + "--"+excelVo.getBusinessNum());
			cs = this.getCsByTypeAndName(cashStreamType, level1Name, cashStreams);

			// 设置相应的值
			if (cs != null) {
				cs.setBorrowerName(borrowName);
				this.setValueForDay(cs, frequency, money);
				// 设置其他的项目名
				if (level1Name.contains("项目")) {
					if (!level2Name.equals("")) {
						cs.setProjectName(level2Name);
					}
				}
				// 设置其他的项目名
				if (level1Name.contains("其他")) {
					if (!level2Name.equals("")) {
						cs.setProjectName(level2Name);
					}
				}
				//
// System.out.println("cs--" + cs.getProjectType()
// + "-getEveryYear-" + cs.getEveryYear());
				hashSet.add(cs);
				// l.add(cs);
			}
		}
		// 使用set是为了集合中的对象不重复，比如食品有每日，每周的都是一条对象里的。
		Iterator<CashStream> iterator = hashSet.iterator();
		while (iterator.hasNext()) {
			CashStream cs = iterator.next();
			cashStreamDAO.updateById(cs);
		}
		for (int i = 0; i < l.size(); i++) {
			CashStream cs = l.get(i);
			cashStreamDAO.updateById(cs);
		}
	}

	/**
	 * 将Null值置为空字符串
	 * 
	 * @param o
	 * @return
	 */
	public Object killNull(Object o) {
		if (o == null) {
			return "";
		} else {
			return o;
		}
	}

	/**
	 * 根据项目名称取现金流对象
	 * 
	 * @param s
	 * @param list
	 * @return
	 */
	public CashStream getCashStreamByProjName(String s, List<CashStream> list) {
		for (int i = 0; i < list.size(); i++) {
			CashStream cs = list.get(i);
			// System.out.println(cs.getCreditapplicationId());
			// System.out.println(cs.getProjectType()+"---"+s+"--"+(cs.getProjectType().contains(s)));
			if (cs.getProjectType().contains(s)) {
				return cs;
			}
		}
		return null;
	}

	/**
	 * 根据每天，每周等设置值
	 */
	public void setValueForDay(CashStream cs, String frequency, Double value) {
		if (frequency.equals("每日")) {
			cs.setEveryDay(value);
		} else if (frequency.equals("每周")) {
			cs.setEveryWeek(value);
		} else if (frequency.equals("每月")) {
			cs.setEveryMonth(value);
		} else if (frequency.equals("每季度")) {
			cs.setEveryQuarter(value);
		} else if (frequency.equals("每半年")) {
			cs.setEveryHalfYear(value);
		} else if (frequency.equals("9个月")) {
			cs.setNineMonth(value);
		} else if (frequency.equals("每年")) {
			cs.setEveryYear(value);
		}
	}

	public CashStream getCsByTypeAndName(String cashStreamType, String level1Name, List<CashStream> cashStreams) {
		CashStream cs = null;
		// 经营现金流入
		if (cashStreamType.equals("经营现金流入")) {
			if (level1Name.equals("项目1")) {
				cs = this.getCashStreamByProjName("operIn_proj1", cashStreams);
			} else if (level1Name.equals("项目2")) {
				cs = this.getCashStreamByProjName("operIn_proj2", cashStreams);
			} else if (level1Name.equals("项目3")) {
				cs = this.getCashStreamByProjName("operIn_proj3", cashStreams);
			}
		} else if (cashStreamType.equals("经营现金流出")) {
			if (level1Name.equals("项目1")) {
				cs = this.getCashStreamByProjName("operOut_proj1", cashStreams);
			} else if (level1Name.equals("项目2")) {
				cs = this.getCashStreamByProjName("operOut_proj2", cashStreams);
			} else if (level1Name.equals("项目3")) {
				cs = this.getCashStreamByProjName("operOut_proj3", cashStreams);
			} else if (level1Name.equals("员工工资")) {
				cs = this.getCashStreamByProjName("operOut_salary", cashStreams);
			} else if (level1Name.equals("房租")) {
				cs = this.getCashStreamByProjName("operOut_rent", cashStreams);
			} else if (level1Name.equals("电费")) {
				cs = this.getCashStreamByProjName("operOut_electCharges", cashStreams);
			} else if (level1Name.equals("水费")) {
				cs = this.getCashStreamByProjName("operOut_waterCost", cashStreams);
			} else if (level1Name.equals("水费")) {
				cs = this.getCashStreamByProjName("operOut_waterCost", cashStreams);
			} else if (level1Name.equals("交通费")) {
				cs = this.getCashStreamByProjName("operOut_trafficCost", cashStreams);
			} else if (level1Name.equals("通讯费")) {
				cs = this.getCashStreamByProjName("operOut_communiCost", cashStreams);
			} else if (level1Name.equals("供暖费")) {
				cs = this.getCashStreamByProjName("operOut_warmCost", cashStreams);
			} else if (level1Name.equals("税费及证照费")) {
				cs = this.getCashStreamByProjName("operOut_taxCost", cashStreams);
			} else if (level1Name.equals("维护及保养")) {
				cs = this.getCashStreamByProjName("operOut_maintainCost", cashStreams);
			} else if (level1Name.equals("其他1")) {
				cs = this.getCashStreamByProjName("operOut_other1", cashStreams);
			} else if (level1Name.equals("其他2")) {
				cs = this.getCashStreamByProjName("operOut_other2", cashStreams);
			} else if (level1Name.equals("其他3")) {
				cs = this.getCashStreamByProjName("operOut_other3", cashStreams);
			} else if (level1Name.equals("其他4")) {
				cs = this.getCashStreamByProjName("operOut_other4", cashStreams);
			}
		} else if (cashStreamType.equals("家庭现金流入")) {
			if (level1Name.equals("工资")) {
				cs = this.getCashStreamByProjName("familyIn_salary", cashStreams);
			} else if (level1Name.equals("津贴")) {
				cs = this.getCashStreamByProjName("familyIn_allowance", cashStreams);
			} else if (level1Name.equals("其他1")) {
				cs = this.getCashStreamByProjName("familyIn_other1", cashStreams);
			} else if (level1Name.equals("其他2")) {
				cs = this.getCashStreamByProjName("familyIn_other2", cashStreams);
			}
		} else if (cashStreamType.equals("家庭现金流出")) {
			if (level1Name.equals("食品")) {
				cs = this.getCashStreamByProjName("familyOut_food", cashStreams);
			} else if (level1Name.equals("教育")) {
				cs = this.getCashStreamByProjName("familyOut_education", cashStreams);
			} else if (level1Name.equals("电费")) {
				cs = this.getCashStreamByProjName("familyOut_electCost", cashStreams);
			} else if (level1Name.equals("水费")) {
				cs = this.getCashStreamByProjName("familyOut_waterCost", cashStreams);
			} else if (level1Name.equals("取暖费")) {
				cs = this.getCashStreamByProjName("familyOut_warmCost", cashStreams);
			} else if (level1Name.equals("房租")) {
				cs = this.getCashStreamByProjName("familyOut_rent", cashStreams);
			} else if (level1Name.equals("交通费")) {
				cs = this.getCashStreamByProjName("familyOut_traffiCost", cashStreams);
			} else if (level1Name.equals("通讯费")) {
				cs = this.getCashStreamByProjName("familyOut_communication", cashStreams);
			} else if (level1Name.equals("医疗费")) {
				cs = this.getCashStreamByProjName("familyOut_medicalCost", cashStreams);
			} else if (level1Name.equals("保险费")) {
				cs = this.getCashStreamByProjName("familyOut_insurance", cashStreams);
			} else if (level1Name.equals("服装开销")) {
				cs = this.getCashStreamByProjName("familyOut_clothingCost", cashStreams);
			} else if (level1Name.equals("贷款还款")) {
				cs = this.getCashStreamByProjName("familyOut_creditRepayment", cashStreams);
			} else if (level1Name.equals("随礼/送礼")) {
				cs = this.getCashStreamByProjName("familyOut_gifts", cashStreams);
			} else if (level1Name.equals("娱乐")) {
				cs = this.getCashStreamByProjName("familyOut_amusement", cashStreams);
			} else if (level1Name.equals("其他1")) {
				cs = this.getCashStreamByProjName("familyOut_other1", cashStreams);
			} else if (level1Name.equals("其他2")) {
				cs = this.getCashStreamByProjName("familyOut_other2", cashStreams);
			} else if (level1Name.equals("其他3")) {
				cs = this.getCashStreamByProjName("familyOut_other3", cashStreams);
			} else if (level1Name.equals("其他4")) {
				cs = this.getCashStreamByProjName("familyOut_other4", cashStreams);
			}
		}
		return cs;
	}

	@Override
	public int deleteCashStream(int creditapplicasthionId) {
		return cashStreamDAO.deleteCashStreamByCreditId(creditapplicasthionId);
	}

	@Override
	public void updateBorrowName(Long creditapplicationId) {

		cashStreamDAO.updateBorrowName(creditapplicationId);
	}

}
