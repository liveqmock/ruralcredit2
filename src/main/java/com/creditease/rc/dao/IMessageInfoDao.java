package com.creditease.rc.dao;

import com.creditease.rc.domain.CreditMessage;
import com.creditease.rc.domain.CreditMessageHis;
import com.creditease.rc.vo.MessageInfoVo;

import java.util.List;

/**
 * Created by v-guoxingye on 2014/12/18.
 */
public interface IMessageInfoDao {
    /**
     * 根据还款计划找出需要提前三天发送短信的申请单信息
     * @return
     */
    public List<MessageInfoVo> queryNeedSendMsgListThreeDaysBeforeReturn();
    /**
     * 根据申请单ID查询需要发送短信的电话 账号信息等短信内容
     * @param creditapplicationId
     * @return
     */
    public MessageInfoVo queryOverDueInfoForSendMsgByCreditId(String creditapplicationId);
    /**
     * 插入发送短信数量的统计信息
     * @param creditMessage
     */
    public void insertMessageInfo(CreditMessage creditMessage);
    /**
     * 根据信贷ID查询发送结清短信的信息
     * @param creditapplicationId
     * @return
     */
    public MessageInfoVo queryLoanCloseMsgByCreidtId(String creditapplicationId);

    public List<MessageInfoVo> queryLoanCloseMsgInfoList();

    /**
     * 插入发送短信数量的统计信息
     * @param messageInfoVo
     */
    public void insertMessageInfo(MessageInfoVo messageInfoVo);
    public void inCreaseOverDueCount(CreditMessage creditMessage);

    public void deleteSuccessedLoanCloseList() throws Exception;

    //根据传入的SYSID LIST 查询真正 去除掉已经插入的那部分SYSID ＬＩＳＴ
    public  List<String> queryNeedInsertLoanCloseList(List<String> sysIdLIst);
    //插入需要发送短信到记录表 等待跑批时使用
    public  void insertCreditMessageHisList(List<CreditMessageHis> creditMessageHisList);
    //根据sysID 查询电话号码
    public String getMobilePhoneBySysId(String sysId);
    public void updateCreditMessageHisStatusByMobilePhoneList(List<String> mobilePhoneList);
    public void updateCreditMessageHisStatusToTrue(List<MessageInfoVo> messageInfoVos);
}
