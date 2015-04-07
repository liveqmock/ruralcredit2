package com.creditease.rc.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import com.creditease.rc.domain.AvgIncRate;
import com.creditease.rc.domain.CashStream;
import com.creditease.rc.domain.OperAverageCsIn;
import com.creditease.rc.domain.PurchaceCost;
import com.creditease.rc.vo.CashStreamVo;
import com.creditease.rc.vo.CashStreamWorkTableVo;
/**
 * 
 *
 */
public interface ICashStreamService {
	
	public int addCashStream(CashStream cashStream);
	
	public int addCashStream(CashStreamVo cashStreamVo);
	/**
	 * 
	* @param creditAppId 
	* @param name 
	* @param type    0-页面输入 1-excel导入 
	* void 
	 */
	public void addCashStreams(int creditAppId,String name,String type);
	
	public int getCountByCreditAppId(int creditapplicationId);
	
	public List<CashStream> getCashStreamsByCreditAppId(int creditapplicationId);
	
	public CashStreamVo getCashStreamVoByCreditAppId(int creditapplicationId,String name);
	
	public CashStreamVo getCashStreamVoByCreditAppId(int creditapplicationId);
	
	public int updateCashStream(CashStreamVo cashStreamVo);
	
	public List<CashStream> initData(int creditAppId,String name,String type);
	
	public int addEveryDayOperCsIns(List<OperAverageCsIn> everyDayOperCsIns);
	
	public int updateCashStreamWorkTableVo(CashStreamWorkTableVo cashStreamWorkTableVo);
	
	public CashStreamWorkTableVo getCashStreamWorkTableVo(int belongId);
	
	public int addCashStreamWorkTable(String param);
	
	public int getCountInCashStreamWorkTable(int belongId);
	
	public int addAvgIncRates(List<AvgIncRate> avgIncRates);
	
	public int addPurchaceCosts(List<PurchaceCost> purchaceCosts);
	
	public int delAllByBelongId(int belongId);
	
	public Double getCalValByCreditId(int creditId);
	
	public CashStreamVo getCashStreamVoByCreditAppIdForView(int creditapplicationId);
	
	/**
	 * 导入现金流excel到数据库中
	 * @param is
	 * @return
	 */
	public String importCashStreamExcel(Sheet sheet);
	/**
	 * 删除现关联申请单金流表的数据
	* @author wyf    
	* @param creditapplicasthionId 
	* @return    
	* int
	 */
	public int deleteCashStream(int creditapplicasthionId);

	/**
	 * @author hongjieluo
	 * 添加计算现金流方法
	 * @param cashStreamVo
	 * @param creditapplicationId 
	 * @return
	 */
	BigDecimal updateCashStreamServiceCalcMaxMoney(CashStreamVo cashStreamVo, int creditapplicationId);

    /**
     * @author hongjieluo
     * 现金流计算旧数据    2014-05-09  到2014-06-05（包含当天的）旧数据
     */
    public void oldCashStreamCalcMaxMoney();
    
    /**
     * @author hongjieluo
     * 现金流计算旧数据   计算现金流旧数据2014-05-09之前做的单子，在05-09计算的现金流的方法
     */
    public void oldCashStreamCalcMaxMoney140509Before();
    
    
    /**
	 * @author hongjieluo
	 * 计算现金流旧数据的计算公式，专用，只用一次
	 * 2014-06-25
	 * begin
	 */
    BigDecimal updateCashStreamServiceOldDataCalcMaxMoney(CashStreamVo cashStreamVo, int creditapplicationId);
    
    /**
     * 根据申请creditapplicationId更新现金流表中的客户姓名（用于解决查看现金流表没有客户姓名）
     * @param creditapplicationId
     */
    public void updateBorrowName(Long creditapplicationId);
}
