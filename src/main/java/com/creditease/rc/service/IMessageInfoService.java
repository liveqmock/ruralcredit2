package com.creditease.rc.service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.domain.CreditMessage;
import com.creditease.rc.domain.CreditMessageHis;
import com.creditease.rc.domain.Message;
import com.creditease.rc.vo.MessageInfoVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by v-guoxingye on 2014/12/18.
 */
public interface IMessageInfoService {
    /**
     * 发送需要提前三天还款提醒的短信
     * 定时任务调用
     * @return
     * @throws Exception
     */
    public Message sendMsgsForThreeDaysBeforeReturn() throws Exception;



    /**
     * 根据还款计划找出需要提前三天发送短信的申请单信息
     * @return
     */
    public List<MessageInfoVo> queryNeedSendMsgListThreeDaysBeforeReturn();

    /**
     * 根据信贷申请ID查询需要发送逾期短信的信息
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
     * 发送短信数量 增加1
     * @param creditMessage
     */
    public void inCreaseOverDueCount(CreditMessage creditMessage);

    /**
     * 根据信贷ID查询发送结清短信的信息
     * @param creditapplicationId
     * @return
     */
    public MessageInfoVo queryLoanCloseMsgByCreidtId(String creditapplicationId);



    /**
     * 根据信贷申请ID 发送结清短信
     * @param creditapplicationId
     * @return
     * @throws Exception
     */
    public Message sendMsgsForLoanClosedByCreidtId(String creditapplicationId) ;

    /**
     * 根据信贷申请ID 发送逾期提醒短信
     * @param creditapplicationId
     * @return
     * @throws Exception
     */
    public Message sendOverDueRemindMsgByCreidtId(String creditapplicationId) throws RuntimeException;

    /**
     * 根据List 放结清短信
     * @param sysIdList
     * @return
     * @throws Exception
     */
    public List<String> getNeedSendLoanCloseSysIdList(List<String> sysIdList) throws Exception;
    public List<String> queryNeedInsertLoanCloseList(List<String> sysIdList);
    public  void insertCreditMessageHisList(List<String> sysIdList);
    public void sendMsgsForLoanClosed() throws Exception;
    public void deleteSuccessedLoanCloseList()throws Exception;
    //public  void updateCreditMessageHisMobilePhoneBySysIdList(List<String> sysIdList);
    public void sendMsgForOverDueRemind(MessageInfoVo messageInfoVo)throws RuntimeException;
    /**
     * 根据客户咨询选择的发送短信
     * map中的参数如下：
     *    phone 咨询客户的电话
     *    custName 客户经理的名称
     *    phoneCode 客户经理的电话
     */
    public Message sendMsgForCustomerConsultation(Map msgInfo) throws RuntimeException ;
}
