package com.creditease.rc.action;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.domain.CustomerConsultPoolLog;
import com.creditease.rc.service.ICustomerConsultPoolLogService;
import com.creditease.rc.service.ICustomerConsultPoolService;
import com.creditease.rc.util.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.creditease.core.security.SpringSecurityUtils;

@Controller
@RequestMapping("customerConsultPoolController")
public class CustomerConsultPoolController {

    @Resource
    private ICustomerConsultPoolService icustomerConsultPoolService;

    @Resource
    private ICustomerConsultPoolLogService customerConsultPoolLogService;

    @SuppressWarnings("deprecation")
    @RequestMapping("/uploadExcel.do")
    public
    @ResponseBody
    Map<String, String> uploadExcel(
            @RequestParam("UpLoadFile") MultipartFile UpLoadFile) {
        Map<String, String> map = new HashMap<String, String>();
        InputStream is = null;
        try {
            is = UpLoadFile.getInputStream();
            Workbook workbook = WorkbookFactory.create(is);
            List<String[]> list = icustomerConsultPoolService.getAllData(
                    workbook, 0);
            map = icustomerConsultPoolService.isRepeat(list);
            if (map.containsKey("成功上传")) {
                //获取相关section数据字典
                Map dataMap = icustomerConsultPoolService.getCodeTableMapBySection(new String[]{"marketConsultBorrowing", "marketConsultInfoSource", "inquirePoolConsultWay"});

                List<CustomerConsultPool> entityList = new ArrayList<CustomerConsultPool>();
                CustomerConsultPool consultPool;

                for (int i = 0; i < list.size(); i++) {
                    String[] value = list.get(i);
                    consultPool = new CustomerConsultPool();
                    consultPool.setRegistDate(new Date(value[0]));
                    consultPool.setCustomerName(value[1].trim());
                    String sex = "";
                    if ("男".equals(value[2])) {
                        sex = "0";
                    } else if ("女".equals(value[2])) {
                        sex = "1";
                    }
                    consultPool.setConSex(sex);
                    // 设置省，直辖市，城市的id

                    if ("北京市".equals(value[3])) {
                        consultPool.setProvince(value[value.length - 3]);
                        consultPool.setCity("110100");
                    } else if ("天津市".equals(value[3])) {
                        consultPool.setProvince(value[value.length - 3]);
                        consultPool.setCity("120100");
                    } else if ("上海市".equals(value[3])) {
                        consultPool.setProvince(value[value.length - 3]);
                        consultPool.setCity("310100");
                    } else if ("重庆市".equals(value[3])) {
                        consultPool.setProvince(value[value.length - 3]);
                        consultPool.setCity("500100");
                    } else {

                        consultPool.setProvince(value[value.length - 3]);
                        consultPool.setCity(value[value.length - 2]);
                    }
                    // 存地区id
                    if (!"".equals(value[5].trim()) && value[5].trim() != null) {
                        consultPool.setArea(value[value.length - 1]);
                    }
                    //户籍地址
                    consultPool.setResidenceAddress(value[3] + "-" + value[4] + "-" + value[5]);
                    //手机号
                    consultPool.setMobilePhone(value[6].trim());
                    //年龄
                    if (value[7] != null && !"".equals(value[7].trim())) {
                        consultPool.setConAge(new Long(value[7]));
                    }
                    //借款用途
                    List<CodeTable> bs = (List<CodeTable>) dataMap.get("marketConsultBorrowing");
                    for (CodeTable c : bs) {
                        if (value[8].equals(c.getCodeVlue())) {
                            consultPool.setBorrowing(c.getCodeKey());
                        }
                    }
                    //咨询金额
                    consultPool.setConsultMoney(value[9].trim());
                    //信息来源
                    List<CodeTable> infos = (List<CodeTable>) dataMap.get("marketConsultInfoSource");
                    for (CodeTable c1 : infos) {
                        if (value[10].equals(c1.getCodeVlue())) {
                            consultPool.setChannel(c1.getCodeKey());
                        }
                    }
                    //咨询方式
                    List<CodeTable> ways = (List<CodeTable>) dataMap.get("inquirePoolConsultWay");
                    for (CodeTable c2 : ways) {
                        if (value[11].equals(c2.getCodeVlue())) {
                            consultPool.setConsultWay(c2.getCodeKey());
                        }
                    }
                    //备注
                    consultPool.setMarketConsultRemark(value[12]);
                   /* if ("不满半年".equals(value[8])) {
                        consultPool.setBusinessPeriod("0");
                    }
                    if ("半年到一年（不含）".equals(value[8])) {
                        consultPool.setBusinessPeriod("1");
                    }
                    if ("一年到两年（不含）".equals(value[8])) {
                        consultPool.setBusinessPeriod("2");
                    }
                    if ("两年到三年（不含）".equals(value[8])) {
                        consultPool.setBusinessPeriod("3");
                    }
                    if ("三年以上".equals(value[8])) {
                        consultPool.setBusinessPeriod("4");
                    }
                    if ("是".equals(value[9])) {
                        consultPool.setIsBusinessLicense("1");
                    }
                    if ("否".equals(value[9])) {
                        consultPool.setIsBusinessLicense("0");
                    }
                    // 借款额度-取消
                    if (value[10] != null && !"".equals(value[10].trim())) {
                        consultPool.setBorrowAmount(new BigDecimal(value[10]));
                    }
                    if ("400客服".equals(value[11])) {
                        consultPool.setChannel("23");
                    }
                    if ("宜人贷客服".equals(value[11])) {
                        consultPool.setChannel("24");
                    }
                    if ("小微官网表单申请".equals(value[11])) {
                        consultPool.setChannel("25");
                    }
                    if ("小微官网在线咨询".equals(value[11])) {
                        consultPool.setChannel("26");
                    }
                    if ("小微微信咨询".equals(value[11])) {
                        consultPool.setChannel("27");
                    }
                    if ("批量渠道".equals(value[11])) {
                        consultPool.setChannel("28");
                    }*/

                    // 创建人
                    String creater = SpringSecurityUtils.getCurrentUser().getName_zh();
                    consultPool.setCreater(creater);

                    // 创建时间
                    consultPool.setCreateDate(new Date());
                    consultPool.setMarketConsultRegistType("1");

                    consultPool.setMarketConsultState("0");
                    entityList.add(consultPool);
                }
                //以咨询日期排序
                Collections.sort(entityList,new Comparator<CustomerConsultPool>() {
                    @Override
                    public int compare(CustomerConsultPool o1, CustomerConsultPool o2) {
                        return o1.getRegistDate().compareTo(o2.getRegistDate());
                    }
                });
                icustomerConsultPoolService.uploadExcel(entityList);
                System.out.println("导入成功");
                this.addBatchImpLog(entityList);
            } else {
                // 导入失败
                System.out.println("导入失败");
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("导入失败");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            is = null;
        }
        return map;
    }

    /**
     * 批量导入-增加日志
     * @param list
     */
    private void addBatchImpLog(List<CustomerConsultPool> list) {
        for (CustomerConsultPool pool : list) {
            //记录操作日志：登记日期、客户姓名、备注
            CustomerConsultPoolLog log = new CustomerConsultPoolLog();
            log.setOptModule("a00030");//操作模块名
            log.setCurrStatus("0");//操作前状态
            log.setNextStatus("0");//操作后状态
            log.setConnectionId(pool.getConsultPoolId());//咨询记录主键ID
            log.setConnectionCetegory("0");//关联ID分类
            log.setCurrConnectionDicSection("marketConsultStatus");//操作前状态section
            log.setNextConnectionDicSection("marketConsultStatus");//操作后状态section
            HashMap<String, String> cm = new LinkedHashMap<String, String>();//记录内容
            cm.put("登记日期", DateUtil.dateConvertString(pool.getRegistDate()));
            cm.put("客户姓名", pool.getCustomerName());
            cm.put("备注", pool.getMarketConsultRemark() == null ? "" : pool.getMarketConsultRemark());
            customerConsultPoolLogService.insertOpt(log, cm);
        }
    }
}
