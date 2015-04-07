package com.creditease.rc.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import oracle.net.aso.e;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.Website;
import com.creditease.rc.domain.WxAnswer;
import com.creditease.rc.domain.WxQuestion;
import com.creditease.rc.service.ISubjectManagementService;

@Controller
@RequestMapping("subjectManagementController")
public class SubjectManagementController {

	@Resource
	ISubjectManagementService subjectManagementService;

	@RequestMapping(value = "uploadQuestionExcel")
	public @ResponseBody
	Message uploadQuestionExcel(@RequestParam("UpLoadFile") MultipartFile UpLoadFile) {
		Message message = new Message();

		Map<String, String> map = new HashMap<String, String>();
		/* 测试，调用查询方法 */
		InputStream is = null;
		try {
			is = UpLoadFile.getInputStream();
			Workbook workbook = WorkbookFactory.create(is);
			message = subjectManagementService.insertQandA(workbook, 0);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("导入失败！");
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
		return message;
	}

}
