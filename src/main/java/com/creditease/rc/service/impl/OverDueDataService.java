package com.creditease.rc.service.impl;

import com.creditease.rc.app.icp.OverdueMicroInfoDTO;
import com.creditease.rc.app.icp.OverdueMicroInfoReq;
import com.creditease.rc.app.icp.OverdueMicroInfoRes;
import com.creditease.rc.app.icp.OverdueServiceWS;
import com.creditease.rc.dao.IOverDueDataDao;
import com.creditease.rc.dao.IOverDueSummaryDao;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.OverDueData;
import com.creditease.rc.domain.OverDueSummary;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IOverDueDataService;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.OverDueListVo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OverDueDataService implements IOverDueDataService{
	@Resource
	private IOverDueDataDao overDueDataDao;
    @Resource
    private OverdueServiceWS overdueServiceWS;
    private static Logger logger = Logger.getLogger(OverDueDataService.class);

    @Resource
    private IOverDueSummaryDao overDueSummaryDao;

    @Resource
    private ICreditApplicationService     creditApplicationService;

    @Override
	public boolean dynamicInsert(OverDueData overDueData) {
		// TODO Auto-generated method stub
		return overDueDataDao.dynamicInsert(overDueData);
	}

	@Override
	public boolean dynamicUpdate(OverDueData overDueData) {
		// TODO Auto-generated method stub
		return overDueDataDao.dynamicUpdate(overDueData);
	}

	@Override
	public boolean saveOverDueDatas(List<OverDueData> overDueDatas) {
		// TODO Auto-generated method stub
		return overDueDataDao.saveOverDueDatas(overDueDatas);
	}

	@Override
	public boolean deleteOverDueData() {
		// TODO Auto-generated method stub
		return overDueDataDao.deleteOverDueData();
	}

    /**
     *
     *每隔两个小时跑批计算出逾期列表放入到数据库及页面同步逾期使用
     *
     *定时跑批任务
     *   */
    @Override
    public Message   insertOverDueData() throws Exception{

        //step1 封装webService请求对象 传入 系统id
        //step2 调用webService获取逾期结果
        //step3 循环逾期列表 把逾期记录更新到数据库表
        // 循环逾期列表 计算出逾期天数 金额 逾期数量 逾期开始时间等字段
        logger.info("================逾期跑批任务开始。。。。======================");
        Message msg = new Message();
        msg.setMsg("同步逾期数据成功....");
        msg.setSuccess(true);
        //step1 封装webService请求对象 传入 系统id
            OverdueMicroInfoReq   overDueReq =  new OverdueMicroInfoReq();
            List<String> overDueReqInfoList = overDueReq.getOverdueMicroInfoReqList();
            //get product id from creditApplication table
            List<String> productIdList = new ArrayList<String>();
            productIdList = creditApplicationService.getProductIdList();
            if(productIdList != null && productIdList.size() > 0){
                 for(String productId:productIdList){
                     overDueReqInfoList.add(productId);
                 }
            }   else {
                msg.setMsg("获取产品id列表失败....");
                msg.setSuccess(false);
                throw new Exception("获取产品id列表失败....");
            }

           // 日志记录webService输出参数数据信息
            logger.info("================getOverdueInfo() 入参 ，类型：OverdueMicroInfoReq======================");
            logger.info(JsonUtil.getJackson(overDueReq));
        //step2 调用webService获取逾期结果
        OverdueMicroInfoRes overDueResInfo = new OverdueMicroInfoRes();

        try {
            overDueResInfo =   overdueServiceWS.getOverdueInfo(overDueReq);

        }catch (Exception e) {
            e.printStackTrace();
            logger.info("调用综合信贷获取逾期数据出错"+e.getMessage());
            logger.info("================getOverdueInfo() 出参 ，类型：OverdueMicroInfoRes======================");
            logger.info("=======调用综合信贷获取逾期数据出错======");
            msg.setMsg("调用综合信贷获取逾期数据出错....");
            msg.setSuccess(false);
            throw new Exception("调用综合信贷获取逾期数据出错....");
        }
        //step3 删除整个表
        // 循环逾期列表 把逾期记录更新到数据库表
        // 循环逾期列表 计算出逾期天数 金额 逾期数量 逾期开始时间等字段

        try {
            this.insertOverDueInfoIntoTable(overDueResInfo.getOverduelist());

        }catch (Exception e) {
            e.printStackTrace();
            logger.info("插入逾期数据到本地失败"+e.getMessage());
            logger.info("================insertOverDueInfoIntoTable======================");
            logger.info("=======插入逾期数据到本地失败======");
            msg.setMsg("插入逾期数据到本地失败....");
            msg.setSuccess(false);
            throw new Exception("插入逾期数据到本地失败....");
        }
        logger.info("================逾期跑批任务结束。。。。======================");
        return msg;
    }

    /**
     *
     查询逾期列表信息
     * @param queryObj
     * @param pagination
     * @return
     */
    @Override
    public Pagination queryOverdueGrid(Object queryObj, Pagination pagination) {
        return overDueDataDao.queryOverdueGrid(queryObj,pagination);
    }

    /**
     *
     查询逾期信息供导出使用
     * @param queryObj
     * @return
     */
    @Override
    public List<OverDueListVo> queryOverdueListForExport(Object queryObj) {
        return (List<OverDueListVo>)overDueDataDao.queryOverdueListForExport(queryObj);  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     插入调用综合信贷返回的逾期列表
     * @param overduelist
     * @throws Exception
     */
    public void insertOverDueInfoIntoTable( List<OverdueMicroInfoDTO> overduelist)throws Exception{

      List<OverDueSummary> overDueSummaryList = new ArrayList<OverDueSummary>();
      for(OverdueMicroInfoDTO overdueInfo :overduelist){
           OverDueSummary overDueSummary =  new OverDueSummary();
          //获取逾期金额    逾期期数      逾期数量     逾期开始时间
           OverDueSummary overDueInfo = new OverDueSummary();
          // overDueInfo.setCreditApplicationId(Long.valueOf(overdueInfo.getApplyId()));
           String sysUUID = null;
           sysUUID = overdueInfo.getApplyId();
          // overDueInfo = overDueSummaryDao.getOverDueInfoByCreidtApplicationId(overDueInfo)   ;
           overDueInfo = overDueSummaryDao.getOverDueInfoBySysUUID(sysUUID);
           if(overDueInfo != null ){
               overDueSummary.setCreditApplicationId(Long.valueOf(overDueInfo.getCreditApplicationId()));
               overDueSummary.setOverdueDayCount(overdueInfo.getOverDays());
               overDueSummary.setHistoryMaxOverDays(overdueInfo.getHistoryMaxOverDays());
               overDueSummary.setCreateDate(new Date());
               overDueSummary.setCreateUser("sys");
               overDueSummary.setaOverdueCount(overDueInfo.getaOverdueCount());
               overDueSummary.setaOverdueMoney(overDueInfo.getaOverdueMoney());
               overDueSummary.setaOverdueStart(overDueInfo.getaOverdueStart());
               overDueSummaryList.add(overDueSummary);
           }
      }
      if(overDueSummaryList.size() >0){
           overDueSummaryDao.mergeOverDueSummaryInfoToLocalTable(overDueSummaryList);
      }
    }

    @Override
    public Integer checkOverDueById(Long creditApplicationId) {
        return overDueSummaryDao.checkOverDueById(creditApplicationId);
    }
}
