package com.creditease.rc.dao;

import java.math.BigDecimal;
import java.util.List;

import com.creditease.rc.domain.AvgIncRate;
import com.creditease.rc.domain.CashStream;
import com.creditease.rc.domain.OperAverageCsIn;
import com.creditease.rc.domain.PurchaceCost;
import com.creditease.rc.domain.Surveybusinessinfo;
import com.creditease.rc.vo.CashStreamVo;
/**
 * 
 * @author liuli
 *
 */
public interface ICashStreamDAO {
	
	/**
	* checkstyle
	* @author wyf   
	* @param cashStream 
	* @return    
	* int
	 */
	public int addCashStream(CashStream cashStream);
	
	/**
	* checkstyle
	* @author wyf   
	* @param cashStreams     
	* void
	 */
	public void addCashStreams(List<CashStream> cashStreams);
	
	/**
	* checkstyle
	* @author wyf   
	* @param creditapplicationId 
	* @return    
	* int
	 */
	public int getCountByCreditAppId(int creditapplicationId);
	
	/**
	 * 根据信贷申请ID获取对应的现金流对象
	* @author wyf    
	* @param creditapplicationId 
	* @return    
	* List<CashStream>
	 */
	public List<CashStream> getCashStreamsByCreditAppId(int creditapplicationId);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param cashStream 
	* @return    
	* int
	 */
	public int updateById(CashStream cashStream);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param creditapplicationId 
	* @return    
	* List<CashStream>
	 */
	public List<CashStream> getProjsByCreditAppId(int creditapplicationId);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param creditAppId 
	* @return    
	* List<Surveybusinessinfo>
	 */
	public List<Surveybusinessinfo> getSbiByCreditAppId(int creditAppId);
	
	
	//高艳红提交用于获取经营对象前三条  
	public List<Surveybusinessinfo> getSbiByCreditAppIdNew(int creditAppId);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param cashStream 
	* @return    
	* int
	 */
	public int updateCsProjNameById(CashStream cashStream);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param cashStream 
	* @return    
	* int
	 */
	public int updateBorrowerName(CashStream cashStream);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param cashStream 
	* @return    
	* int
	 */
	public int updateCsProjNameAndTypeById(CashStream cashStream);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param everyDayOperCsIns    
	* void
	 */
	public void addEveryDayOperCsIns(List<OperAverageCsIn> everyDayOperCsIns);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param everyDayOperCsIns    
	* void
	 */
	public void updateEveryDayOperCsIns(List<OperAverageCsIn> everyDayOperCsIns);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param belongId 
	* @return    
	* List<OperAverageCsIn>
	 */
	public List<OperAverageCsIn> getEveryDayOperCsInsByBelongId(int belongId);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param belongId 
	* @return    
	* int
	 */
	public int getCountInCashStreamWorkTable(int belongId);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param avgIncRates 
	* @return    
	* int
	 */
	public int addAvgIncRates(List<AvgIncRate> avgIncRates);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param belongId 
	* @return    
	* List<AvgIncRate>
	 */
	public List<AvgIncRate> getAvgIncRatesByBelongId(int belongId);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param avgIncRates    
	* void
	 */
	public void updateAvgIncRates(List<AvgIncRate> avgIncRates);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param purchaceCosts 
	* @return    
	* int
	 */
	public int addPurchaceCosts(List<PurchaceCost> purchaceCosts); 
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param belongId 
	* @return    
	* List<PurchaceCost>
	 */
	public List<PurchaceCost> getPurchaceCostsByBelongId(int belongId);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param purchaceCosts     
	* void
	 */
	public void updatePurchaceCosts(List<PurchaceCost> purchaceCosts);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param belongId 
	* @return    
	* int
	 */
	public int delAllByBelongId(int belongId);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param creditId 
	* @return    
	* int
	 */
	public int deleteCashStreamByCreditId(int creditId);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param belongId 
	* @return    
	* int
	 */
	public int deleteAvgIncRateByBelongId(int belongId);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param belongId 
	* @return    
	* int
	 */
	public int deleteEveryDayOperCsInByBelongId(int belongId);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param belongId 
	* @return    
	* int
	 */
	public int deletePurchaceCostByBelongId(int belongId);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param creditId 
	* @return    
	* List<CashStream>
	 */
	public List<CashStream> getCalValByCreditId(int creditId);

	/**
	 * @author hongjieluo
	 * 添加计算现金流方法
	 * @param cashStreamVo
	 * @return
	 */
	BigDecimal updateCashStreamServiceCalcMaxMoney(CashStreamVo cashStreamVo,int creditapplicationId);

	/**
	 * @author hongjieluo
     * 查询现金流计算旧数据    2014-05-09  到2014-06-05（包含当天的）旧数据
     */
    public List<CashStream> selectOldCashStreamList();
    
    /**
	 * @author hongjieluo
     * 查询现金流计算旧数据    2014-05-09之前做的单子，但是在05-09做的现金流计算的旧数据
     */
    public List<CashStream> selectOldCashStreamList140509Before();
    
    
    /**
     * @author hongjieluo
     * 根据creditapplicationId查询现金流表中一条数据
     */
    public CashStream selectCashSteamByCreditAppId(int creditapplicationId);
    
    /**
     * 根据申请creditapplicationId更新现金流表中的客户姓名（用于解决查看现金流表没有客户姓名）
     * @param creditapplicationId
     */
    public void updateBorrowName(Long creditapplicationId);
	
}
