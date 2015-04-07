package com.creditease.rc.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.ICashStreamDAO;
import com.creditease.rc.domain.AvgIncRate;
import com.creditease.rc.domain.CashStream;
import com.creditease.rc.domain.OperAverageCsIn;
import com.creditease.rc.domain.PurchaceCost;
import com.creditease.rc.domain.Surveybusinessinfo;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.vo.CashStreamVo;

@Repository
public class CashStreamDAO implements ICashStreamDAO {

	@Resource
	private BaseDao baseDao;

	@Override
	public int addCashStream(CashStream cashStream) {
		return (Integer) baseDao.insertObject("CASHSTREAM.insert", cashStream);
	}

	@Override
	public void addCashStreams(List<CashStream> cashStreams) {
		/** 2014年1月22日郝强去掉了这个循环插入 **/
//		for (int i = 0; i < cashStreams.size(); i++) {
//			CashStream cs = cashStreams.get(i);
//			addCashStream(cs);
//		}
		baseDao.batchInsert("CASHSTREAM.insert", cashStreams);
	}

	@Override
	public int getCountByCreditAppId(int creditapplicationId) {
		int count = (Integer) baseDao.queryUnique("CASHSTREAM.selectCountByCreditAppId", creditapplicationId);
		return count;
	}

	@Override
	public List<CashStream> getCashStreamsByCreditAppId(int creditapplicationId) {
		List l = baseDao.queryList("CASHSTREAM.selectByCreditAppId", creditapplicationId);
		return l;
	}

	@Override
	public int updateById(CashStream cashStream) {
// System.out.println(cashStream.getCashStreamId()+"--"+cashStream.getEveryDay());
		baseDao.update("CASHSTREAM.updateCashStreamById", cashStream);
		return 0;
	}

	@Override
	public List<CashStream> getProjsByCreditAppId(int creditapplicationId) {
		List l = baseDao.queryList("CASHSTREAM.selectPorjsByCreditAppId", creditapplicationId);
		return l;
	}

	@Override
	public List<Surveybusinessinfo> getSbiByCreditAppId(int creditAppId) {
		List l = baseDao.queryList("surveybusinessinfo.selectSbiByCreditAppId", creditAppId);
		return l;
	}
	
	//高艳红提交用于获取经营对象前三条  
	@Override
	public List<Surveybusinessinfo> getSbiByCreditAppIdNew(int creditAppId) {
		// TODO Auto-generated method stub
		List l = baseDao.queryList("surveybusinessinfo.selectSbiByCreditAppIdNew", creditAppId);
		return l;
	}

	@Override
	public int updateCsProjNameById(CashStream cashStream) {
		baseDao.update("CASHSTREAM.updateCsProjNameById", cashStream);
		return 0;
	}

	@Override
	public int updateCsProjNameAndTypeById(CashStream cashStream) {
		baseDao.update("CASHSTREAM.updateCsProjNameAndTypeById", cashStream);
		return 0;
	}

	@Override
	public int updateBorrowerName(CashStream cashStream) {
		baseDao.update("CASHSTREAM.updateBorrowerName", cashStream);
		return 0;
	}

	@Override
	public void addEveryDayOperCsIns(List<OperAverageCsIn> everyDayOperCsIns) {
		for (int i = 0; i < everyDayOperCsIns.size(); i++) {
			OperAverageCsIn edoci = everyDayOperCsIns.get(i);
			baseDao.insertObject("EVERYDAYOPERCSIN.insert", edoci);
		}
	}

	@Override
	public void updateEveryDayOperCsIns(List<OperAverageCsIn> everyOperCsIns) {
		for (int i = 0; i < everyOperCsIns.size(); i++) {
			OperAverageCsIn edoci = everyOperCsIns.get(i);
// System.out.println(edoci.getOperAverageCsInId()+"----"+edoci.getTimeCycle());
			baseDao.update("EVERYDAYOPERCSIN.update", edoci);
		}
	}

	@Override
	public int getCountInCashStreamWorkTable(int belongId) {
		int count = (Integer) baseDao.queryUnique("EVERYDAYOPERCSIN.selectCountByBelongId", belongId);
		return count;
	}

	@Override
	public List<OperAverageCsIn> getEveryDayOperCsInsByBelongId(int belongId) {
		List l = baseDao.queryList("EVERYDAYOPERCSIN.selectByBelongId", belongId);
		return l;
	}

	@Override
	public int addAvgIncRates(List<AvgIncRate> avgIncRates) {
		for (int i = 0; i < avgIncRates.size(); i++) {
			AvgIncRate air = avgIncRates.get(i);
			baseDao.insertObject("AVGINCRATE.insert", air);
		}
		return 0;
	}

	@Override
	public List<AvgIncRate> getAvgIncRatesByBelongId(int belongId) {
		List l = baseDao.queryList("AVGINCRATE.selectByBelongId", belongId);
		return l;
	}

	@Override
	public void updateAvgIncRates(List<AvgIncRate> avgIncRates) {
		for (int i = 0; i < avgIncRates.size(); i++) {
			AvgIncRate edoci = avgIncRates.get(i);
// System.out.println(edoci.getAvgIncRateId()+"--"+edoci.getSellProject());
			baseDao.update("AVGINCRATE.update", edoci);
		}
	}

	@Override
	public int addPurchaceCosts(List<PurchaceCost> purchaceCosts) {
		for (int i = 0; i < purchaceCosts.size(); i++) {
			PurchaceCost edoci = purchaceCosts.get(i);
			baseDao.insertObject("PURCHACECOST.insert", edoci);
		}
		return 0;
	}

	@Override
	public List<PurchaceCost> getPurchaceCostsByBelongId(int belongId) {
		List l = baseDao.queryList("PURCHACECOST.selectByBelongId", belongId);
		return l;
	}

	@Override
	public void updatePurchaceCosts(List<PurchaceCost> purchaceCosts) {
		for (int i = 0; i < purchaceCosts.size(); i++) {
			PurchaceCost edoci = purchaceCosts.get(i);
// System.out.println(edoci.getPurchaceCostId()+"--"+edoci.getConsumables());
			baseDao.update("PURCHACECOST.update", edoci);
		}

	}

	@Override
	public int delAllByBelongId(int belongId) {

		return 0;
	}

	@Override
	public int deleteCashStreamByCreditId(int creditId) {
		return baseDao.update("CASHSTREAM.deleteByCreditId", creditId);
	}

	@Override
	public int deleteAvgIncRateByBelongId(int belongId) {
		return baseDao.update("AVGINCRATE.deleteByBelongId", belongId);
	}

	@Override
	public int deleteEveryDayOperCsInByBelongId(int belongId) {
		return baseDao.update("EVERYDAYOPERCSIN.deleteByBelongId", belongId);
	}

	@Override
	public int deletePurchaceCostByBelongId(int belongId) {
		return baseDao.update("PURCHACECOST.deleteByBelongId", belongId);
	}

	@Override
	public List<CashStream> getCalValByCreditId(int creditId) {
		List l = baseDao.queryList("CASHSTREAM.selectMaxLoanSumByCreditId", creditId);
		return l;
	}

	/**
	 * @author hongjieluo
	 * 添加计算现金流方法
	 * @param cashStreamVo
	 * @return
	 */
	@Override
	public BigDecimal updateCashStreamServiceCalcMaxMoney(CashStreamVo cashStreamVo,int creditapplicationId) {
		// TODO Auto-generated method stub
		BigDecimal bigDecimal=new BigDecimal(0);
		return bigDecimal;
	}

	/**
	 * @author hongjieluo
     * 查询现金流计算旧数据    2014-05-09  到2014-06-05（包含当天的）旧数据
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<CashStream> selectOldCashStreamList() {
		List<CashStream> cashStreamVo=(List<CashStream>) baseDao.queryList("CASHSTREAM.selectOldCashStreamList");
		return cashStreamVo;
	}

	/**
	 * @author hongjieluo
     * 查询现金流计算旧数据 
     * 2014-06-25
     */
	@Override
	public CashStream selectCashSteamByCreditAppId(int creditapplicationId) {
		return (CashStream) baseDao.queryUnique("CASHSTREAM.selectCashSteamByCreditAppId",creditapplicationId);
	}

	 /**
	 * @author hongjieluo
     * 查询现金流计算旧数据    2014-05-09之前做的单子，但是在05-09做的现金流计算的旧数据
     */
	@Override
	public List<CashStream> selectOldCashStreamList140509Before() {
		List<CashStream> cashStreamVo=(List<CashStream>) baseDao.queryList("CASHSTREAM.selectOldCashStreamList140509Before");
		return cashStreamVo;
	}

	@Override
	public void updateBorrowName(Long creditapplicationId) {

		baseDao.update("CASHSTREAM.updateBorrowName", creditapplicationId);
	}
}
