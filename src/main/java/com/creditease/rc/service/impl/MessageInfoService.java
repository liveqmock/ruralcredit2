package com.creditease.rc.service.impl;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.app.icp.OverdueMicroInfoReq;
import com.creditease.rc.app.icp.OverdueMicroInfoRes;
import com.creditease.rc.dao.IMessageInfoDao;
import com.creditease.rc.domain.*;
import com.creditease.rc.service.IDataDictionaryService;
import com.creditease.rc.service.IMessageInfoService;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.DataDictionaryVo;
import com.creditease.rc.vo.MessageInfoVo;
import com.creditease.service.client.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by v-guoxingye on 2014/12/18.
 */
@Service
public class MessageInfoService implements IMessageInfoService {

    @Resource
    private IMessageInfoDao messageInfoDao;

    @Resource
    private IDataDictionaryService dataDictionaryService;
    @Resource
    private IRural2CreditService rural2CreditService;
    /**
     * 根据还款计划找出需要提前三天发送短信的申请单信息
     * @return
     */
    @Override
    public List<MessageInfoVo> queryNeedSendMsgListThreeDaysBeforeReturn() {
        return messageInfoDao.queryNeedSendMsgListThreeDaysBeforeReturn();
    }
    private static Logger logger = Logger.getLogger(MessageInfoService.class);
    /**
     * 根据信贷申请ID查询需要发送逾期短信的信息
     * @param creditapplicationId
     * @return
     */
    @Override
    public MessageInfoVo queryOverDueInfoForSendMsgByCreditId(String creditapplicationId) {
        MessageInfoVo messageInfoVo = new MessageInfoVo();
        //根据申请单ID查询需要发送短信的电话 账号信息等短信内容
        messageInfoVo = messageInfoDao.queryOverDueInfoForSendMsgByCreditId(creditapplicationId);
        //调用贷后接口查询还款计划
        List<LocalReturnSchemeResponse> returnScheme  = rural2CreditService.returnScheme(Long.valueOf(creditapplicationId));
        //以咨询日期排序
        Collections.sort(returnScheme,new Comparator<LocalReturnSchemeResponse>() {
            @Override
            public int compare(LocalReturnSchemeResponse o1, LocalReturnSchemeResponse o2) {
                return o2.getRepayDate().compareTo(o1.getRepayDate());
            }
        });

        LocalReturnSchemeResponse localReturnSchemeResponse =null ;
        //循环还款计划查找上期的还款计划
        for(LocalReturnSchemeResponse localReturnSchemeResponseTem:returnScheme){
            if(localReturnSchemeResponseTem.getRepayDate().getTime() < new Date().getTime()){
                localReturnSchemeResponse = localReturnSchemeResponseTem;
                break;
            }
        }
        if(localReturnSchemeResponse == null){
            localReturnSchemeResponse = returnScheme.get(returnScheme.size()-1);
        }
        //根据当期还款计划设置还款月 日 当期还款金额 等信息
        String overDueDays = localReturnSchemeResponse.getOverDays();
        Integer overDueDayCount = 0;
        if(CommonUtil.isNotEmpty(overDueDays)){
            overDueDayCount = Integer.valueOf(overDueDays);
        }
        messageInfoVo.setOverdueDayCount(overDueDayCount);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(localReturnSchemeResponse.getRepayDate());
        messageInfoVo.setMonth(calendar.get(Calendar.MONTH) + 1);
        messageInfoVo.setDay(calendar.get(Calendar.DATE));
        messageInfoVo.setYear(calendar.get(Calendar.YEAR));
        messageInfoVo.setCurrAccountTotal(String.valueOf(localReturnSchemeResponse.getReceivableMoney()));
        return messageInfoVo;
    }
    /**
     * 插入发送短信数量的统计信息
     * @param creditMessage
     */
    @Override
    public void insertMessageInfo(CreditMessage creditMessage) {
        messageInfoDao.insertMessageInfo(creditMessage);
    }
    /**
     * 发送短信数量 增加1
     * @param creditMessage
     */
    @Override
    public void inCreaseOverDueCount(CreditMessage creditMessage) {
        messageInfoDao.inCreaseOverDueCount(creditMessage);
    }

    /**
     * 根据信贷ID查询发送结清短信的信息
     * @param creditapplicationId
     * @return
     */
    @Override
    public MessageInfoVo queryLoanCloseMsgByCreidtId(String creditapplicationId) {
        return messageInfoDao.queryLoanCloseMsgByCreidtId(creditapplicationId);
    }
    /**
     * 发送需要提前三天还款提醒的短信
     * 定时任务调用
     * @return
     * @throws Exception
     */
    @Override
    public Message sendMsgsForThreeDaysBeforeReturn() {
        //step1 获取提前三天能还款的的信息 供发送使用
        //step2 封装发送接口对象
        //step3 调用短信接口 发送
        //step4 获取发送结果
        if(!canSendMsg()){
            Message msg = new Message();
            logger.info("配置不允许发送短信（提前三天短信还款提醒）....");
            msg.setMsg("配置不允许发送短信（提前三天短信还款提醒）....");
            msg.setSuccess(false);
            return msg;
        }
        logger.info("================获取提前三天能还款的的信息发送短信,调度任务开始。。。。======================");
        Message msg = new Message();
        msg.setMsg("发送短信成功....");
        msg.setSuccess(true);
        //step1 获取提前三天能还款的的信息
        List<MessageInfoVo> messageInfoVos = messageInfoDao.queryNeedSendMsgListThreeDaysBeforeReturn();
        if(messageInfoVos.size()>0){
            //step2 封装发送接口对象
            MessageReqJaxb reqJaxb =  generateReqObjForThreeDaysBeforeReturn(messageInfoVos);
            MessageResJaxb res = null;
                //step3 调用短信接口 发送
                logger.info("**************调用短信平台发送提前三天提醒还款短信 请求对象为 requestParam******************");
                logger.info(JsonUtil.getJackson(reqJaxb));
                res = getService().batchOrderMsgSendJaxb(reqJaxb);
                logger.info("**************调用短信平台发送提前三天提醒还款短信 返回对象为 responseParam:******************");
                logger.info(JsonUtil.getJackson(reqJaxb));
                if(!"000000".equals(res.getRetCode())){
                    logger.info("调用短信平台，发送提前三天还款提醒短信失败返回状态码："+res.getRetCode());
                    logger.info("调用短信平台，发送提前三天还款提醒短信失败返回手机号："+res.getRetInfo());
                    msg.setMsg("调用短信平台，发送提前三天还款提醒短信失败....");
                    msg.setSuccess(false);
                }else{
                    logger.info("调用短信平台，发送提前三天还款提醒短信成功");
                }
        }else{
            msg.setMsg("获取提前三天能还款的的信息我空....");
            msg.setSuccess(false);
            logger.info("获取提前三天能还款的的信息我空，不需要发送短信....");
        }
        return msg;
    }
    /**
     * 根据信贷申请ID 发送结清短信
     * @param creditapplicationId
     * @return
     * @throws Exception
     */
    @Override
    public Message sendMsgsForLoanClosedByCreidtId(String creditapplicationId) {
        logger.info("================发送借款结清短信开始。。。======================");
        Message msg = new Message();
        msg.setMsg("发送短信成功....");
        msg.setSuccess(true);
        //step1 通过信贷申请ID获取提前贷款结清信息
        MessageInfoVo messageInfoVo = messageInfoDao.queryLoanCloseMsgByCreidtId(creditapplicationId);
        //step2 封装发送接口对象
        if(messageInfoVo != null){
            MessageReqJaxb reqJaxb =  generateReqObjForLoanClosed(messageInfoVo);
            MessageResJaxb res = null;
            // 记Log日志
            logger.info("**************短信平台.orderMsgSendJaxb(reqJaxb) requestParam******************");
            logger.info(JsonUtil.getJackson(reqJaxb));
                //step3 调用短信接口 发送
                res = getService().orderMsgSendJaxb(reqJaxb);
            logger.info("**************调用短信平台发送贷款结清短信 返回对象为 responseParam******************");
            logger.info(JsonUtil.getJackson(res));
        }else{
            msg.setMsg("通过信贷申请ID获取提前贷款结清信息为空....");
            msg.setSuccess(true);
            logger.info("通过信贷申请ID获取提前贷款结清信息为空....");
        }
        return msg;
    }

    /**
     * 定时发送结清短信
     * @throws Exception
     */
    @Override
    public void sendMsgsForLoanClosed()  {
        if(!canSendMsg()){
            logger.info("================配置不允许发送短信(批量发送贷款结清短信)。。。======================");
            return;
        }
        logger.info("================批量发送借款结清短信,调度开始。。。======================");
        //step1 查询需要发送结清短信的消息信息
        List<MessageInfoVo> messageInfoVoList = messageInfoDao.queryLoanCloseMsgInfoList();
        //step2 封装发送接口对象
        if(messageInfoVoList.size()>0){
            //根据需要发送结清短信的信息 生成 短信平台需要的请求对象
            MessageReqJaxb reqJaxb =  generateReqObjForLoanClosed(messageInfoVoList);
            MessageResJaxb res = null;
                //step3 调用短信接口 发送
                // 记Log日志
                logger.info("**************调用短信平台发送贷款结清短信 请求对象为 requestParam******************");
                logger.info(JsonUtil.getJackson(reqJaxb));
                res = getService().batchOrderMsgSendJaxb(reqJaxb);
                logger.info("**************调用短信平台发送贷款结清短信 返回对象为 responseParam******************");
                logger.info(JsonUtil.getJackson(res));
                if("000000".equals(res.getRetCode())){
                    //短信发送成功  更改需要发送结清短信的记录状态为TRUE
                    messageInfoDao.updateCreditMessageHisStatusToTrue(messageInfoVoList);
                    logger.info("调用短信平台发送贷款结清短信成功.....");
                }else{
                    String falseRS = res.getRetInfo();
                    //把所有发送过的结清短信状态置为TRUE
                    messageInfoDao.updateCreditMessageHisStatusToTrue(messageInfoVoList);
                    logger.info("调用短信平台发送贷款结清短信失败，失败状态码为:"+res.getRetCode()+"失败手机号有:"+falseRS+".....");
                    if (falseRS != null && !"".equals(falseRS)) {
                        String[] phoneArrs = falseRS.split("\\|");
                        ArrayList<String> phoneList = new ArrayList<String>(Arrays.asList(phoneArrs));
                        //把发送失败的结清短信记录状态置为FALSE 同时增加发送次数
                        messageInfoDao.updateCreditMessageHisStatusByMobilePhoneList(phoneList);
                    }
                }
        }else{
            logger.info("获取贷款结清信息为空,不用发送短信....");
        }
    }
    //删除已经发送短信成功的记录
    @Override
    public void deleteSuccessedLoanCloseList() throws Exception {
        messageInfoDao.deleteSuccessedLoanCloseList();
    }

    @Override
    public void sendMsgForOverDueRemind(MessageInfoVo messageInfoVo) throws RuntimeException {
        if(messageInfoVo != null && messageInfoVo.getCreditapplicationId() !=null){
            //根据信贷申请ID查询逾期短信
            MessageInfoVo messageInfoVoTem = queryOverDueInfoForSendMsgByCreditId(String.valueOf(messageInfoVo.getCreditapplicationId()));
            CreditMessage creditMessage = new CreditMessage();
            String newDay = messageInfoVo.getNewDay();
            if(messageInfoVo.getRemindOverdueCount()>0){
                //如果已经发送过逾期提醒短信 修改逾期短信提醒记录的提醒次数
                creditMessage.setUpdateDate(new Date());
                creditMessage.setUpdateUser(SpringSecurityUtils.getCurrentUser().getUsername());
                creditMessage.setCreditapplicationId(messageInfoVoTem.getCreditapplicationId());
                if("T".equals(newDay)){
                    creditMessage.setRemindOverdueCount(0);
                }else{
                    creditMessage.setRemindOverdueCount(messageInfoVoTem.getRemindOverdueCount());
                }
                inCreaseOverDueCount(creditMessage);
            }else{
                //首次发送逾期短信提醒 记录发送短信记录
                creditMessage.setCreditapplicationId(messageInfoVoTem.getCreditapplicationId());
                creditMessage.setRemindOverdueCount(1);
                creditMessage.setCreateDate(new Date());
                creditMessage.setCreateUser(SpringSecurityUtils.getCurrentUser().getUsername());
                creditMessage.setUpdateDate(new Date());
                creditMessage.setUpdateUser(SpringSecurityUtils.getCurrentUser().getUsername());
                insertMessageInfo(creditMessage);
            }
            //根据逾期信贷申请ＩＤ发送逾期短信
          Message msg=   sendOverDueRemindMsgByCreidtId(String.valueOf(messageInfoVoTem.getCreditapplicationId()));
            if(!msg.isSuccess()){
                throw new RuntimeException(msg.getMsg());
            }
        }
    }

    @Override
    public Message sendMsgForCustomerConsultation(Map msgInfo) throws RuntimeException {
        logger.info("================发送客户咨询短信开始。。。======================");
        Message msg = new Message();
        msg.setMsg("发送客户咨询短信成功....");
        msg.setSuccess(true);
        //step1 封装发送接口对象
        //根据逾期短信内容 生成 调用短信平台的请求对象
        MessageReqJaxb reqJaxb =  generateReqObjForCustomerConsultation(msgInfo);
        MessageResJaxb res = null;
        //step3 调用短信接口 发送
        // 记Log日志
        logger.info("**************调用短信平台发送客户咨询短信 请求对象为 requestParam******************");
        logger.info(JsonUtil.getJackson(reqJaxb));
        res = getService().orderMsgSendJaxb(reqJaxb);
        logger.info("**************调用短信平台发送客户咨询短信 返回对象为 responseParam******************");
        logger.info(JsonUtil.getJackson(res));
        //step4 获取发送结果
        if(!"000000".equals(res.getRetCode())){
            logger.info("调用短信平台，发送客户咨询短信失败.返回状态码为：" + res.getRetCode() + " 失败信息为：...." + res.getRetInfo());
            msg.setMsg("调用短信平台，发送客户咨询短信失败.返回状态码为："+res.getRetCode()+" 失败信息为：...."+res.getRetInfo());
            msg.setSuccess(false);
        }else{
            logger.info("调用短信平台，发送客户咨询短信返回信息如下："+res.getRetInfo());
            logger.info("调用短信平台发送客户咨询短信成功。。。。");
        }
        return msg;
    }


    /**
     * 根据信贷申请ID 发送逾期提醒短信
     * @param creditapplicationId
     * @return
     * @throws Exception
     */
    @Override
    public Message sendOverDueRemindMsgByCreidtId(String creditapplicationId)  {
        logger.info("================发送逾期提醒短信开始。。。======================");
        Message msg = new Message();
        msg.setMsg("发送逾期提醒短信成功....");
        msg.setSuccess(true);
        //step1 通过信贷申请ID获取提前贷款结清信息
        // MessageInfoVo messageInfoVo = messageInfoDao.queryOverDueInfoForSendMsgByCreditId(creditapplicationId);
        //根据信贷申请ID 查询需要发送逾期短信的内容
        MessageInfoVo messageInfoVo = this.queryOverDueInfoForSendMsgByCreditId(creditapplicationId);
        //step2 封装发送接口对象
        if(messageInfoVo != null){
            //根据逾期短信内容 生成 调用短信平台的请求对象
            MessageReqJaxb reqJaxb =  generateReqObjForOverDueRemind(messageInfoVo);
            MessageResJaxb res = null;
                //step3 调用短信接口 发送
                // 记Log日志
                logger.info("**************调用短信平台发送逾期提醒短信 请求对象为 requestParam******************");
                logger.info(JsonUtil.getJackson(reqJaxb));
                res = getService().orderMsgSendJaxb(reqJaxb);
                logger.info("**************调用短信平台发送逾期提醒短信 返回对象为 responseParam******************");
                logger.info(JsonUtil.getJackson(res));
                 //step4 获取发送结果
                if(!"000000".equals(res.getRetCode())){
                    logger.info("调用短信平台，发送逾期提醒短信失败.返回状态码为：" + res.getRetCode() + " 失败信息为：...." + res.getRetInfo());
                    msg.setMsg("调用短信平台，发送逾期提醒短信失败.返回状态码为："+res.getRetCode()+" 失败信息为：...."+res.getRetInfo());
                    msg.setSuccess(false);
                }else{
                    logger.info("调用短信平台，发送逾期提醒短信返回信息如下："+res.getRetInfo());
                    logger.info("调用短信平台发送逾期提醒短信成功。。。。");
                }
        }else{
            msg.setMsg("通过信贷申请ID获取发送逾期提醒短信为空,不能发送逾期短信....");
            msg.setSuccess(false);
            logger.info("通过信贷申请ID获取发送逾期提醒短信为空,不能发送逾期短信....");
        }
        return msg;
    }

    @Override
    public List<String> getNeedSendLoanCloseSysIdList(List<String> sysIdList) throws Exception {
        return null;
    }

    @Override
    public List<String> queryNeedInsertLoanCloseList(List<String> sysIdList) {
        return messageInfoDao.queryNeedInsertLoanCloseList(sysIdList);
    }
    //把贷款结清的记录 插入到需要发送结清短信的记录表
    @Override
    public void insertCreditMessageHisList(List<String> sysIdList) {
      //根据传入的SYSID LIST 查询真正 去除掉已经插入的那部分SYSID ＬＩＳＴ
      List<String> canInsertSysIdList =   messageInfoDao.queryNeedInsertLoanCloseList(sysIdList);
      List<CreditMessageHis> creditMessageHises = new LinkedList<CreditMessageHis>();
      for(int i=0;i<canInsertSysIdList.size();i++) {
          CreditMessageHis creditMessageHis = new CreditMessageHis();
          //根据sysID 查询电话号码
          String mobilePhone = messageInfoDao.getMobilePhoneBySysId(canInsertSysIdList.get(i));
          creditMessageHis.setMobilephone(mobilePhone);
          creditMessageHis.setSysUUID(canInsertSysIdList.get(i));
          creditMessageHis.setCreateUser("sys");
          creditMessageHis.setCreditTime(new Date());
          creditMessageHis.setSendCount("0");
          creditMessageHises.add(creditMessageHis);
      }
       if(creditMessageHises.size()>0){
           //插入需要发送短信到记录表 等待跑批时使用
           messageInfoDao.insertCreditMessageHisList(creditMessageHises);
       }
    }

    public static MessageService getService() {
        MessageServiceJaxbImplService service = new MessageServiceJaxbImplService();
        return service.getMessageServicePort();
    }
    //把要发送提前三天还款提醒短信封装到发送短信接口对象
    public MessageReqJaxb generateReqObjForThreeDaysBeforeReturn(List<MessageInfoVo> messageInfoVos){
        /** Text Template
         * 【宜信公司】尊敬的客户您好，建议您最迟于XX月XX日16点前在您尾号为XXXX的
         * 账户中存入XXXX.XX元用于本月还款，并留意银行可能会扣除年费、跨行存款手续
         * 费等情况，确保卡内余额充足。如果您需要提前一次性结清、变更还款账户，请至
         * 少提前一个工作日联系工作人员。谢谢您的合作！（农商贷400-818-9199）
         */
        /**
         * 新版本
         * 【宜信公司】尊敬的客户,您通过宜信普惠办理的借款将于XX月XX日对尾号为XXXX的账户进行划扣,
         * 本期应还款XXXX.XX元，请确保卡内余额充足。以免影响您的信用记录，谢谢！（农商贷400-818-9199）
         * 【宜信公司】尊敬的客户,您通过宜信普惠办理的借款将于${month}月${day}日对尾号为${accountNo}的账户进行划扣,
         * 本期应还款${amount}元，请确保卡内余额充足。以免影响您的信用记录，谢谢！（农商贷400-818-9199）
         */
        MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
        DetailsJaxb[] ds = new DetailsJaxb[messageInfoVos.size()]; // 短信内容数组
        for (int i = 0; i < ds.length; i++) {
            DetailsJaxb d =  new DetailsJaxb();
            MessageInfoVo messageInfoVo = messageInfoVos.get(i);
            String[] keywords = new String[4];// 模板关键字
            keywords[0] = "day|"+messageInfoVo.getDay();
            String  accountNo = (!"".equals(messageInfoVo.getAccount()) && messageInfoVo.getAccount()!=null && messageInfoVo.getAccount().length()>4)?
                    messageInfoVo.getAccount().substring(messageInfoVo.getAccount().length()-4,messageInfoVo.getAccount().length()):messageInfoVo.getAccount();
            keywords[1] = "accountNo|"+accountNo;
            keywords[2] = "amount|"+messageInfoVo.getCurrAccountTotal();
            keywords[3] = "month|"+messageInfoVo.getMonth();
            d.setMobile(messageInfoVo.getMobilephone()); // 电话号码
            d.setKeywords(keywords);
            d.setPriority("5"); // 优先级
            ds[i] = d;
        }
        fillOrgTypeNo(req,"beforeThreeDays");
     //   req.setOrgNo("2265");// 组织机构号
        req.setBatchId("000001");// 批次号
      //  req.setTypeNo("5184");// 模板号
        req.setVersion("2.00");// 接口版本
        req.setDetails(ds);
        return req;
    }
    public MessageReqJaxb generateReqObjForLoanClosed(MessageInfoVo messageInfoVo){
        MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
        DetailsJaxb[] ds = new DetailsJaxb[1]; // 短信内容数组
        DetailsJaxb d =  new DetailsJaxb();
        d.setMobile(messageInfoVo.getMobilephone()); // 电话号码
        d.setPriority("5"); // 优先级
        ds[0] = d;
        req.setOrgNo("2265");// 组织机构号
        req.setBatchId("000001");// 批次号
        req.setTypeNo("5184");// 模板号
        req.setVersion("2.00");// 接口版本
        req.setDetails(ds);
        return req;
    }
    //为贷款结清的贷款发送结清短信提醒 封装要发送的信息到短息平台接口对象
    public MessageReqJaxb generateReqObjForLoanClosed(List<MessageInfoVo> messageInfoVoList){
        /**Loan Closed Text Template
         *【宜信公司】尊敬的客户您好！您通过宜信申请的借款已全部还清，感谢您的支持！（农商贷400-818-9199）         *
         */
        /**
         * 新模板
         * 【宜信公司】尊敬的客户您好，您通过宜信普惠申请的借款已全部还清，感谢您一直以来的支持！如有借款需求，请致电我们（农商贷400-818-9199）
         */
        MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
        DetailsJaxb[] ds = new DetailsJaxb[messageInfoVoList.size()]; // 短信内容数组
        for(int i=0;i<ds.length;i++){
            MessageInfoVo messageInfoVo = messageInfoVoList.get(i);
            DetailsJaxb d =  new DetailsJaxb();
            d.setMobile(messageInfoVo.getMobilephone()); // 电话号码
            d.setPriority("5"); // 优先级
            ds[i] = d;
        }
        fillOrgTypeNo(req,"loanClosed");
       // req.setOrgNo("2265");// 组织机构号
        req.setBatchId("000001");// 批次号
       // req.setTypeNo("5184");// 模板号
        req.setVersion("2.00");// 接口版本
        req.setDetails(ds);
        return req;
    }
    //根据逾期短信 生成调用短信平台的请求对象
    public MessageReqJaxb generateReqObjForCustomerConsultation(Map msgInfo){
        MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
        DetailsJaxb[] ds = new DetailsJaxb[1]; // 短信内容数组
        DetailsJaxb d =  new DetailsJaxb();
        d.setMobile((String) msgInfo.get("phone")); // 电话号码
        d.setPriority("5"); // 优先级
        String[] keywords = new String[4];// 模板关键字
        keywords[0] = "custName|"+msgInfo.get("custName");
        keywords[1] = "phoneCode|"+msgInfo.get("phoneCode");
        d.setKeywords(keywords);
        ds[0] = d;
        fillOrgTypeNo(req,"customerConsultation");
        req.setBatchId("000001");// 批次号
        req.setVersion("2.00");// 接口版本
        req.setDetails(ds);
        return req;
    }
    //更加codeKey 填充短信请求对象的
    public void fillOrgTypeNo(MessageReqJaxb req,String codeKey){
        DataDictionaryVo dataDictionaryVo = new DataDictionaryVo();
        dataDictionaryVo.setSection("msg_info");
        dataDictionaryVo.setCodeKey(codeKey);
        dataDictionaryVo.setSelectType("E");
        // 根据key取对应配置的颜色值
        String[] orgTypeNoValues = new String[2];
        //根据codeKey 查询配置的组织机构号 和 模板号
        List<CodeTable> list = dataDictionaryService.getCodeTableBySection(dataDictionaryVo);
        if(CommonUtil.isNotEmpty(list) &&  list.size()>0) {
            String orgTypeValue = list.get(0).getCodeVlue();
            if (CommonUtil.isNotEmpty(orgTypeValue)) {
                orgTypeNoValues = orgTypeValue.split("-");
            }else{
                orgTypeNoValues[0]="000";
                orgTypeNoValues[1]="000";
            }
        }
        req.setOrgNo(orgTypeNoValues[0]);// 组织机构号        ----
        req.setTypeNo(orgTypeNoValues[1]);// 模板号          ------
    }

    public MessageReqJaxb generateReqObjForOverDueRemind(MessageInfoVo messageInfoVo){


/**
 * old:  逾期提醒：尊敬的客户您好！受出借人委托提醒，您本期还款已逾期${year}天，
 *       应还款金额${amount}元，应还日期${month}月${day}日，请您尽快还款。谢谢您的合作（农商贷400-818-9199）

 * new  【宜信公司】逾期提醒：尊敬的客户您好，受出借人委托提醒，您本期还款已逾期${year}天，目前应还款金额${amount}元。
 *      为了维护您的信用及避免支付更多的违约金，请您务必尽快还款。（农商贷400-818-9199）
 */
        MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
        DetailsJaxb[] ds = new DetailsJaxb[1]; // 短信内容数组
        DetailsJaxb d =  new DetailsJaxb();
        d.setMobile(messageInfoVo.getMobilephone()); // 电话号码
        d.setPriority("5"); // 优先级
        String[] keywords = new String[4];// 模板关键字
        keywords[0] = "amount|"+messageInfoVo.getCurrAccountTotal();
        keywords[1] = "month|"+messageInfoVo.getMonth();
        keywords[2] = "day|"+messageInfoVo.getDay();
        keywords[3] = "year|"+messageInfoVo.getOverdueDayCount();
        d.setKeywords(keywords);
        ds[0] = d;
        fillOrgTypeNo(req,"overDueRemind");
        req.setBatchId("000001");// 批次号
        req.setVersion("2.00");// 接口版本
        req.setDetails(ds);
        return req;
    }


   /* public MessageReqJaxb generateReqObjForOverDueRemind(MessageInfoVo messageInfoVo){
        MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
        DetailsJaxb[] ds = new DetailsJaxb[1]; // 短信内容数组
        DetailsJaxb d =  new DetailsJaxb();
        d.setMobile(messageInfoVo.getMobilephone()); // 电话号码
        d.setPriority("5"); // 优先级
        String[] keywords = new String[4];// 模板关键字
        keywords[0] = "amount|"+messageInfoVo.getCurrAccountTotal();
        keywords[1] = "month|"+messageInfoVo.getMonth();
        keywords[2] = "day|"+messageInfoVo.getDay();
        keywords[3] = "year|"+messageInfoVo.getOverdueDayCount();
        d.setKeywords(keywords);
        ds[0] = d;
        fillOrgTypeNo(req,"overDueRemind");
        req.setBatchId("000001");// 批次号
        req.setVersion("2.00");// 接口版本
        req.setDetails(ds);
        return req;
    }*/


    //更加codeKey 填充短信请求对象的
    public boolean canSendMsg(){
        DataDictionaryVo dataDictionaryVo = new DataDictionaryVo();
        dataDictionaryVo.setSection("msg_info");
        dataDictionaryVo.setCodeKey("canSendMsg");
        dataDictionaryVo.setSelectType("E");
        boolean canSendMsg = false;
        //根据codeKey 查询配置的组织机构号 和 模板号
        List<CodeTable> list = dataDictionaryService.getCodeTableBySection(dataDictionaryVo);
        if(CommonUtil.isNotEmpty(list) &&  list.size()>0) {
            String canSendFlag = list.get(0).getCodeVlue();
            if("T".equals(canSendFlag)){
                canSendMsg = true;
            }
        }
        return  canSendMsg;
    }
}
