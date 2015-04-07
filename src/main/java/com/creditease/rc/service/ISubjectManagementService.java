package com.creditease.rc.service;

import org.apache.poi.ss.usermodel.Workbook;

import com.creditease.rc.domain.Message;

public interface ISubjectManagementService {

	Message insertQandA(Workbook workbook, int sheetIndex) throws Exception;

}
