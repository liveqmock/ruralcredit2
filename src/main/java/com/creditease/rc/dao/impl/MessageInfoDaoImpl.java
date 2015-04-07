package com.creditease.rc.dao.impl;

import com.creditease.rc.dao.IMessageInfoDao;
import com.creditease.rc.domain.CreditMessage;
import com.creditease.rc.domain.CreditMessageHis;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.vo.MessageInfoVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by v-guoxingye on 2014/12/18.
 */
@Repository
public class MessageInfoDaoImpl extends BaseDao implements IMessageInfoDao {

    @Resource
    private IBaseDao baseDao;
    /**
     * 根据还款计划找出需要提前三天发送短信的申请单信息
     * @return
     */
    @Override
    public List<MessageInfoVo> queryNeedSendMsgListThreeDaysBeforeReturn() {

        return (List<MessageInfoVo>)baseDao.queryList("messageInfo.queryNeedSendMsgListThreeDaysBeforeReturn");
    }
    //根据申请单ID查询需要发送短信的电话 账号信息等短信内容
    @Override
    public MessageInfoVo queryOverDueInfoForSendMsgByCreditId(String creditapplicationId) {
        return (MessageInfoVo)baseDao.queryUnique("messageInfo.queryOverDueInfoForSendMsgByCreditId",Integer.valueOf(creditapplicationId));
    }

    @Override
    public void insertMessageInfo(CreditMessage creditMessage) {
        baseDao.insert("messageInfo.insertMessageInfo",creditMessage);
    }


    @Override
    public MessageInfoVo queryLoanCloseMsgByCreidtId(String creditapplicationId) {
        return (MessageInfoVo)baseDao.queryUnique("messageInfo.queryLoanCloseMsgByCreidtId",creditapplicationId);
    }

    @Override
    public List<MessageInfoVo> queryLoanCloseMsgInfoList() {
        return (List<MessageInfoVo>)baseDao.queryList("messageInfo.queryLoanCloseMsgInfoList");
    }


    @Override
    public void insertMessageInfo(MessageInfoVo messageInfoVo) {
        baseDao.insert("messageInfo.insertMessageInfo", messageInfoVo);
    }

    @Override
    public void inCreaseOverDueCount(CreditMessage creditMessage) {
        baseDao.update("messageInfo.inCreaseOverDueCount", creditMessage);
    }

    @Override
    public void deleteSuccessedLoanCloseList() throws Exception {
        CreditMessageHis creditMessageHis = new CreditMessageHis();
        baseDao.delete("messageInfo.deleteSuccessedLoanCloseList",creditMessageHis);
    }
    //根据传入的SYSID LIST 查询真正 去除掉已经插入的那部分SYSID ＬＩＳＴ
    @Override
    public List<String> queryNeedInsertLoanCloseList(List<String> sysIdList) {
        return (List<String>)baseDao.queryList("messageInfo.queryNeedInsertLoanCloseList", sysIdList);
    }
    //插入需要发送短信到记录表 等待跑批时使用
    @Override
    public void insertCreditMessageHisList(List<CreditMessageHis> creditMessageHisList) {
        baseDao.batchInsert("messageInfo.insertCreditMessageHis",creditMessageHisList);
    }
    //根据sysID 查询电话号码
    @Override
    public String getMobilePhoneBySysId(String sysId) {
       return  (String)baseDao.queryUnique("messageInfo.getMobilePhoneBySysId",sysId);
    }
    //把发送失败的结清短信记录状态置为FALSE 同时增加发送次数
    @Override
    public void updateCreditMessageHisStatusByMobilePhoneList(List<String> mobilePhoneList) {
        baseDao.update("messageInfo.updateCreditMessageHisStatusByMobilePhoneList",mobilePhoneList);
    }
    //把所有发送过的结清短信状态置为TRUE
    @Override
    public void updateCreditMessageHisStatusToTrue(List<MessageInfoVo> messageInfoVos) {
       baseDao.batchUpdate("messageInfo.updateCreditMessageHisStatus",messageInfoVos);
    }
}
