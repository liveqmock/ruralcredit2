package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.WxAnswer;
import com.creditease.rc.domain.WxQuestion;
import com.creditease.rc.service.ISubjectManagementService;
import com.creditease.rc.service.IWxAnswerService;
import com.creditease.rc.service.IWxQuestionService;
import com.creditease.rc.util.CommonUtil;

@Service
public class SubjectManagementService implements ISubjectManagementService {

	@Resource
	private IWxQuestionService wxQuestionService;
	@Resource
	private IWxAnswerService wxAnswerService;

	@Override
	public Message insertQandA(Workbook workbook, int sheetIndex) throws Exception {
		Message message = new Message();
		int columnNum = 0;
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		if (sheet.getRow(0) != null) {
			columnNum = sheet.getRow(0).getLastCellNum() - sheet.getRow(0).getFirstCellNum();
		}
		Map<String, WxQuestion> questionMap = new HashMap<String, WxQuestion>();
		Map<String, List<WxAnswer>> answerListMap = new HashMap<String, List<WxAnswer>>();
		if (columnNum > 0) {
			for (Row row : sheet) {
				// 跳过第一行
				int n = row.getRowNum();
				if (n == 0 || n == 1) {
					continue;
				}
				Iterator<Cell> iterator = row.cellIterator();
				List<String> tempList = new ArrayList<String>();
				while (iterator.hasNext()) {
					Cell temp = iterator.next();
					temp.setCellType(Cell.CELL_TYPE_STRING);
					String getStringCellValue = temp.getStringCellValue();
					tempList.add(getStringCellValue);
				}
				WxQuestion wxQuestion = new WxQuestion();
				List<WxAnswer> wxAnswerList = new ArrayList<WxAnswer>();
				int trueAnswer = 0;
				int max = tempList.size();
				for (int i = 0; i < max; i++) {
					String cell = tempList.get(i);
					if (i == 0) {
						continue;
					} else if (i == 1) {
						wxQuestion.setQuestion(cell);
					} else if (i == 2) {
						wxQuestion.setType(cell);
					} else if (i == 3) {
						trueAnswer = Integer.parseInt(cell);
					} else {
						if (trueAnswer == 0) {
							throw new Exception("正确答案判断出错！");
						} else {
							if(CommonUtil.isEmpty(cell)){
								continue;
							}
							WxAnswer wxAnswer = new WxAnswer();
							wxAnswer.setAnswer(cell);
							wxAnswer.setTrueFlag("1");
							if (i - 3 == trueAnswer) {
								wxAnswer.setTrueFlag("0");
							}
							wxAnswerList.add(wxAnswer);
						}
					}
				}
				questionMap.put(n + "", wxQuestion);
				answerListMap.put(n + "", wxAnswerList);
			}
			wxQuestionService.inserQuestionMap(questionMap);
			wxAnswerService.inserAnswerListMap(questionMap, answerListMap);
			message.setSuccess(true);
			message.setMsg("导入成功！");
		}
		return message;
	}
}
