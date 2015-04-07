package com.creditease.rc.action;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.creditease.rc.domain.CashStream;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.OperAverageCsIn;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.ICashStreamService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.vo.CashStreamVo;
import com.creditease.rc.vo.CashStreamWorkTableVo;

/**
 * 
 *
 */
@Controller
@RequestMapping(value = "cashStream")
public class CashStreamController {

	@Resource
	private ICashStreamService cashStreamService;

	@Resource
	private ICreditApplicationService creditApplicationService;

	/**
	 * 
	 * @param binder
	 *            WebDataBinder对象
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 获取现金流入流出表
	 */
	@RequestMapping(value = "getCashStream")
	public @ResponseBody
	CashStreamVo getCashStream(String param) {

		String[] ss = param.split("_");
		String name = ss[1];
		int creditAppId = Integer.parseInt(ss[0]);
		// 根据id获取数据条数，目的是为了判断数据库中是否已经有该id的数据
		int count = cashStreamService.getCountByCreditAppId(creditAppId);
		// 现金流入流出表对象
		CashStreamVo csVo = null;
		// 如果数据条目为空，则对该id建表
		if (count == 0) {
			// 将所有条目插入到数据库中
			cashStreamService.addCashStreams(creditAppId, name, "0");
			// 从数据库中从新获取表数据并拼装成vo对象
			csVo = cashStreamService.getCashStreamVoByCreditAppId(creditAppId, name);
		} else {
			csVo = cashStreamService.getCashStreamVoByCreditAppId(creditAppId, name);
			// Double d = cashStreamService.getCalValByCreditId(creditAppId);
			// System.out.println(((CashStream)cashStreams.get(0)).getCreateDate().toLocaleString());
		}
		return csVo;
	}

	/**
	 * 查看现金流入流出表
	 */
	@RequestMapping(value = "toViewCashStream")
	public @ResponseBody
	CashStreamVo toViewCashStream(String param) {

		int creditAppId = Integer.parseInt(param);
		// 现金流入流出表对象
		CashStreamVo csVo = cashStreamService.getCashStreamVoByCreditAppIdForView(creditAppId);
		return csVo;
	}

	/**
	 * 获取现金工作表 2013-04-22
	 */
	@RequestMapping(value = "getCashStreamWorkTable")
	public @ResponseBody
	CashStreamWorkTableVo getCashStreamWorkTable(String param) {

		int belongId = Integer.parseInt(param);
		CashStreamWorkTableVo cswtVo = new CashStreamWorkTableVo();
		int count = cashStreamService.getCountInCashStreamWorkTable(belongId);
		if (count > 0) {
			cswtVo = cashStreamService.getCashStreamWorkTableVo(belongId);
		} else {
			cashStreamService.addCashStreamWorkTable(param);
			cswtVo = cashStreamService.getCashStreamWorkTableVo(belongId);
		}
		return cswtVo;
	}

	/**
	 * 获取现金工作表 2013-04-22
	 */
	@RequestMapping(value = "viewCashStreamWorkTable")
	public @ResponseBody
	CashStreamWorkTableVo viewCashStreamWorkTable(String param) {

		int belongId = Integer.parseInt(param);
		CashStreamWorkTableVo cswtVo = null;
		int count = cashStreamService.getCountInCashStreamWorkTable(belongId);
		if (count > 0) {
			cswtVo = cashStreamService.getCashStreamWorkTableVo(belongId);
		}
		return cswtVo;
	}

	/**
	 * 更新现金工作表 param: 2013-04-22
	 */
	@RequestMapping(value = "updateCashStreamWorkTable")
	public @ResponseBody
	Message updateCashStreamWorkTable(CashStreamWorkTableVo cswtVo) {
		// cashStreamService
		cashStreamService.updateCashStreamWorkTableVo(cswtVo);
		Message message = new Message();
		message.setSuccess(true);
		return message;
	}

	/**
	 * 更新现金流
	 */
	@RequestMapping(value = "update")
	public @ResponseBody
	Message update(CashStreamVo cashStreamVo,int creditapplicationId) {
		Message message=new Message();
		//原来定义的变量为int
		//int updateResult = -1;
		//调罗红杰写的方法时候定义一个BigDecimal
		BigDecimal updateResult=new BigDecimal(-1);
		BigDecimal num=new BigDecimal(0);
		try {
			//现在调用罗红杰写的后台验证借款最大额度计算方法
			updateResult = cashStreamService.updateCashStreamServiceCalcMaxMoney(cashStreamVo,creditapplicationId);
			//原来调用的方法
			//updateResult = cashStreamService.updateCashStream(cashStreamVo);
		} catch (Exception e) {
			System.out.println(e);
		}
		//根据不同的返回结果提示不同的Message提示
		if (updateResult.compareTo(num) == 0) {
			message.setCode("0");
			message.setSuccess(true);
			message.setMsg("请将信息填写完整,并输入有效的数值！");
		} else if(updateResult.compareTo(num) == 1) {
			message.setCode("1");
			message.setSuccess(true);
			message.setMsg("保存成功！");
		}else{
			message.setSuccess(false);
			message.setMsg("操作失败,请检查是否有空值或联系管理员！");
		}
		return message;
	}

	/**
	 * 删除所有跟现金流表有关的数据
	 */
	@RequestMapping(value = "deleteAllCs")
	public @ResponseBody
	Message delAllByBelongId(int belongId) {
		cashStreamService.delAllByBelongId(belongId);
		Message message = new Message();
		message.setSuccess(true);
		return message;
	}

	/**
	 * 查看现金流流入流出表
	 * 
	 * @author zxb
	 * @param creditapplicationId
	 *            信贷申请单ID
	 * @return CashStreamVo
	 */
	@RequestMapping(value = "getCashStreamVoByCreditAppId")
	public @ResponseBody
	CashStreamVo getCashStreamVoByCreditAppId(int creditapplicationId) {
		CashStreamVo cashStreamVo = cashStreamService.getCashStreamVoByCreditAppId(creditapplicationId);
		return cashStreamVo;
	}

	/**
	 * 查看现金流流入流出表
	 * 
	 * @author zxb
	 * @param creditapplicationId
	 *            信贷申请单ID
	 * @return CashStreamVo
	 */
	@RequestMapping(value = "getCashStreamWorkTableVo")
	public @ResponseBody
	CashStreamWorkTableVo getCashStreamWorkTableVo(int creditapplicationId) {
		CashStreamWorkTableVo cashStreamVo = cashStreamService.getCashStreamWorkTableVo(creditapplicationId);
		return cashStreamVo;
	}

	/**
	 * 将现金流excel导入到数据库中 liuli2013-05-24
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "uploadCashStreamExcel", method = RequestMethod.POST)
	public @ResponseBody
	Message uploadCashStream(@RequestParam("file") MultipartFile file, String parentId, HttpServletResponse response)
			throws Exception {
		Message message = new Message();
		String name = file.getOriginalFilename();
		InputStream is = file.getInputStream();
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(is);
		} catch (Exception e) {
			System.out.println(e);
		}
		// System.out.println("name----"+name+"--parentId="+parentId+"--workbook="+workbook);
		if (workbook == null) {
			message.setMsg("您选择的不是excel文件，请重新选择！");
			message.setSuccess(false);
		} else {
			Sheet sheet = workbook.getSheetAt(0);
			// 取标题判断是不是对应的excel
			Row row1 = sheet.getRow(0);

			String title1 = "";
			String title2 = "";
			String title3 = "";
			String title4 = "";
			String title5 = "";
			String title6 = "";

			try {
				title1 = row1.getCell(0).toString();
				title2 = row1.getCell(1).toString();
				title3 = row1.getCell(2).toString();
				title4 = row1.getCell(3).toString();
				title5 = row1.getCell(4).toString();
				title6 = row1.getCell(5).toString();
			} catch (Exception e) {
				message.setMsg("提取数据错误,请重新上传!");
				message.setSuccess(false);
			}

			// 取第二列值，对于每一列，业务编号和客户姓名是一样的，所以取第二行数据的值就行
			// Row row1 = sheet.getRow(1);
			// //业务编号
			// String groupNumber = row1.getCell(0).toString();
			// CreditApplication creditApplication =
			// creditApplicationService.selectByGroupNumber(groupNumber);
			// //
			// System.out.println("Id--"+creditApplication.getCreditapplicationId());
			// //客户姓名
			// String customerName = row1.getCell(1).toString();
			// //跟页面点击一样，如果数据库没有该id的数据则初始化数据
			// int creditapplicasthionId =
			// creditApplication.getCreditapplicationId();
			// cashStreamService.addCashStreams(creditapplicasthionId,
			// customerName);
			//
			// List<CashStream> cashStreams =
			// cashStreamService.getCashStreamsByCreditAppId(creditapplicasthionId);
			// System.out.println(cashStreams.size());
			// 根据excel数据更新对应的数据库数据
			if (title1.equals("业务编号") && title2.equals("客户姓名") && title3.equals("现金流种类") && title4.equals("一级项目")
					&& title5.equals("二级项目") && title6.equals("频率")) {
				cashStreamService.importCashStreamExcel(sheet);
				message.setMsg("上传成功");
			} else {
				message.setMsg("表格模板头部标题不匹配,请检查上传文件模板!");
				message.setSuccess(false);
			}

		}
		return message;
	}

	/**
	 * 
	 * @param sheet
	 *            excel页面
	 * @return true：不存在空数据，false：存在空数据
	 */
	private Map validateNull(Sheet sheet) {

		Map result = new HashMap();
		boolean flag = true;
		Message message = new Message();
		StringBuffer sb = new StringBuffer();

		// 判断空数据 以及具体位置
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if ((row.getCell(0) == null || "".equals(row.getCell(0).toString().trim()))
					&& (row.getCell(1) == null || "".equals(row.getCell(1).toString().trim()))
					&& (row.getCell(2) == null || "".equals(row.getCell(2).toString().trim()))
					&& (row.getCell(3) == null || "".equals(row.getCell(3).toString().trim()))
					&& (row.getCell(4) == null || "".equals(row.getCell(4).toString().trim()))) {
				continue;
			}
			for (int j = 0; j < 5; j++) {
				Cell cell = row.getCell(j);
				if (cell == null || "".equals(cell.toString().trim())) {
					sb.append("第" + (i + 1) + "行,第" + (j + 1) + "列,");
					flag = false;
				}
			}
		}
		if (!("").equals(sb.toString())) {
			sb.append("数据为空，请填写完整！");
			message.setMsg(sb.toString());
			message.setSuccess(false);
		}
		if (sb.length() > 30) {
			sb.append("数据为空，请填写完整！");
			message.setMsg(sb.toString().substring(0, 30) + "......" + "数据为空，请填写完整！(可能excel不符合格式要求)");
			message.setSuccess(false);
		}

		result.put("flag", flag);
		result.put("message", message.getMsg());
		return result;
	}

	@RequestMapping(value = "getProductInfo", method = RequestMethod.POST)
	public @ResponseBody
	CreditApplication getProductInfo(Integer creditapplicationId) {
		CreditApplication application = creditApplicationService.selectById(creditapplicationId);
		return application;
	}
	/**
	 * 现金流流入流出表计算旧的数据
	 * 
	 * @author hongjieluo
	 * @param creditapplicationId
	 *            信贷申请单ID
	 * @return CashStreamVo
	 */
	@RequestMapping(value = "oldCashStreamCalcMaxMoney")
	public @ResponseBody
	Message oldCashStreamCalcMaxMoney() {
		Message message=new Message();
		cashStreamService.oldCashStreamCalcMaxMoney();
		message.setSuccess(true);
		return message;
	}
	
	/**
	 * 现金流流入流出表计算旧的数据
	 * 计算现金流旧数据2014-05-09之前做的单子，在05-09计算的现金流的方法
	 * @author hongjieluo
	 * @param creditapplicationId
	 *            信贷申请单ID
	 * @return CashStreamVo
	 */
	@RequestMapping(value = "oldCashStreamCalcMaxMoney0509Before")
	public @ResponseBody
	Message oldCashStreamCalcMaxMoney140509Before() {
		Message message=new Message();
		cashStreamService.oldCashStreamCalcMaxMoney140509Before();
		message.setSuccess(true);
		return message;
	}
}
