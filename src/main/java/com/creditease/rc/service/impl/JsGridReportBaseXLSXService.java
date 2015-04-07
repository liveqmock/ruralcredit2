package com.creditease.rc.service.impl;

import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.report.excel.*;
import com.creditease.rc.service.ICustomerReturnVisitService;
import com.creditease.rc.service.IDataDictionaryService;
import com.creditease.rc.service.IJsGridReportBaseXLSXService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.DataDictionaryVo;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * 
 * Title:JsGridReportBase.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-5-2下午02:50:58
 * 
 * @author 王毅峰
 * @version v2.0
 */
@Service
public class JsGridReportBaseXLSXService implements IJsGridReportBaseXLSXService {
    @Resource
    private IDataDictionaryService dataDictionaryService;
	public SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static String MODULE_PATH = "/config/excel/module.xls";// 模板路径
    private static String MODULE_PATH1 = "/config/excel/module.xlsx";// xlsx模板路径
	private HttpServletResponse response;

	private HttpSession session;

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    private ServletOutputStream out;

	public JsGridReportBaseXLSXService() {
	}

	public JsGridReportBaseXLSXService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.response = response;
		session = request.getSession();
		init(this.session);
	}

	private void init(HttpSession session) throws Exception {
		out = response.getOutputStream();
	}

	/**
	 * 向浏览器输出JSON数据
	 *
	 * @param
	 * @return void
	 */
	public void outDataToBrowser(TableData tableData) {
		StringBuffer outData = new StringBuffer();

		// 向前台输出数据
		outData.append("{pageInfo: {totalRowNum: " + tableData.getTotalRows() + "},");
		outData.append("data: [");
		boolean isFirst = true;

		TableHeaderMetaData headerMetaData = tableData.getTableHeader();
		List<TableDataRow> dataRows = tableData.getRows();
		try {
			for (TableDataRow dataRow : dataRows) {
				List<TableDataCell> dataCells = dataRow.getCells();
				int size = dataCells.size();
				if (!isFirst) {
					outData.append(",{");
					for (int i = 0; i < size; i++) {
						outData.append(headerMetaData.getColumnAt(i).getId() + ": '" + dataCells.get(i).getValue()
								+ "',");
					}
					int index = outData.lastIndexOf(",");
					outData.deleteCharAt(index);
					outData.append("}");
				} else {
					outData.append("{");
					for (int i = 0; i < size; i++) {
						outData.append(headerMetaData.getColumnAt(i).getId() + ": '" + dataCells.get(i).getValue()
								+ "',");
					}
					int index = outData.lastIndexOf(",");
					outData.deleteCharAt(index);
					outData.append("}");
					isFirst = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		outData.append("]");
		outData.append("}");

		try {
			out.print(outData.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     *
     * @param
     * @return void
     */
    private void stopGrouping(XSSFSheet sheet, HashMap<Integer, String> word, HashMap<Integer, Integer> counter, int i,
                              int size, int rownum, XSSFCellStyle style) {
        String w = word.get(i);
        if (w != null) {
            int len = counter.get(i);
            CellRangeAddress address = new CellRangeAddress(rownum - len, rownum - 1, i, i);
            sheet.addMergedRegion(address);
            fillMergedRegion(sheet, address, style);
            word.remove(i);
            counter.remove(i);
        }
        if (i + 1 < size) {
            stopGrouping(sheet, word, counter, i + 1, size, rownum, style);
        }
    }

    /**
     *
     * @param
     * @return void
     */
    private void generateColumn(XSSFSheet sheet, TableColumn tc, int maxlevel, int rownum, int colnum,
                                XSSFCellStyle headerstyle) {
        XSSFRow row = sheet.getRow(rownum);
        if (row == null)
            row = sheet.createRow(rownum);

        XSSFCell cell = row.createCell(colnum);
        cell.setCellValue(tc.getDisplay());
        XSSFCellStyle xssfCellStyle =  sheet.getWorkbook().createCellStyle();
		if (headerstyle != null)
            xssfCellStyle.cloneStyleFrom(headerstyle);
			cell.setCellStyle(xssfCellStyle);
		if (tc.isComplex()) {
			CellRangeAddress address = new CellRangeAddress(rownum, rownum, colnum, colnum + tc.getLength() - 1);
			sheet.addMergedRegion(address);
			fillMergedRegion(sheet, address, headerstyle);

			int cn = colnum;
			for (int i = 0; i < tc.getChildren().size(); i++) {
				if (i != 0) {
					cn = cn + tc.getChildren().get(i - 1).getLength();
				}
				generateColumn(sheet, tc.getChildren().get(i), maxlevel, rownum + 1, cn, headerstyle);
			}
		} else {
			CellRangeAddress address = new CellRangeAddress(rownum, rownum + maxlevel - tc.level, colnum, colnum);
			sheet.addMergedRegion(address);
			fillMergedRegion(sheet, address, headerstyle);
		}
		sheet.autoSizeColumn(colnum, true);
	}

	/**
	 *
	 * @param
	 * @return void
	 */
	private void fillMergedRegion(XSSFSheet sheet, CellRangeAddress address, XSSFCellStyle style) {
		for (int i = address.getFirstRow(); i <= address.getLastRow(); i++) {
			XSSFRow row = sheet.getRow(i);
			if (row == null)
				row = sheet.createRow(i);
			for (int j = address.getFirstColumn(); j <= address.getLastColumn(); j++) {
				XSSFCell cell = row.getCell(j);
				if (cell == null) {
					cell = row.createCell(j);
                    XSSFCellStyle xssfCellStyle =  sheet.getWorkbook().createCellStyle();
					if (style != null)
                        xssfCellStyle.cloneStyleFrom(style);
						cell.setCellStyle(xssfCellStyle);
				}
			}
		}
	}

	/**
	 *
	 * @author wyf
	 * @return: String
	 */
	private String getFileName(String title) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		String month_ = new String("" + month);
		if (month < 10) {
			month_ = "0" + month;
		}
		int day = c.get(Calendar.DAY_OF_MONTH);
		String day_ = new String("" + day);
		if (day < 10) {
			day_ = "0" + day;
		}
		return title + year + month_ + day_;
	}

	/**
	 * 写入工作表
	 *
	 * @param wb Excel工作簿
	 * @param title Sheet工作表名称
	 * @param styles 表头样式
	 * @param creator 创建人
	 * @param tableData 表格数据
	 * @throws Exception
	 */
	public XSSFWorkbook writeSheet(XSSFWorkbook wb, String title, HashMap<String, XSSFCellStyle> styles,
			String creator, TableData tableData) throws Exception {
        //  表单创建时间
		SimpleDateFormat formater = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
		String create_time = formater.format(new Date());
        //创建表单
		XSSFSheet sheet = wb.createSheet(title);// 在Excel工作簿中建一工作表
		sheet.setDisplayGridlines(false);// 设置表标题是否有表格边框
        XSSFCellStyle style  = wb.createCellStyle();
        TableHeaderMetaData headerMetaData = tableData.getTableHeader();// 获得HTML的表头元素
        int rownum = 0;
        //创建标题行
		XSSFRow row = sheet.createRow(0);// 创建新行
		XSSFCell cell = row.createCell(0);// 创建新列
        //Set Title
		cell.setCellValue(new XSSFRichTextString(title));
        //get Title Style
        style    = styles.get("TITLE");
		if (style != null)
        cell.setCellStyle(style);
        // 合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headerMetaData.getColumnCount() - 1));

        // 创建第二行 设置创建人及创建时间
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue(new XSSFRichTextString("创建人:"));
		style = styles.get("SUB_TITLE");
		if (style != null)
			cell.setCellStyle(style);
        //设置创建人
		cell = row.createCell(1);
		cell.setCellValue(new XSSFRichTextString(creator));
		style = styles.get("SUB_TITLE2");
		if (style != null)
			cell.setCellStyle(style);
       //  设置创建时间
		cell = row.createCell(2);
		cell.setCellValue(new XSSFRichTextString("创建时间:"));
		style = styles.get("SUB_TITLE");
		if (style != null)
			cell.setCellStyle(style);

		cell = row.createCell(3);
		style = styles.get("SUB_TITLE2");
		cell.setCellValue(new XSSFRichTextString(create_time));
		if (style != null) {
			cell.setCellStyle(style);
		}
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, headerMetaData.getColumnCount() - 1));
        //  创建第三行
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, headerMetaData.getColumnCount() - 1));

        //
        rownum = 3;// 如果rownum = 1，则去掉创建人、创建时间等副标题
		XSSFCellStyle headerstyle = styles.get("TABLE_HEADER");

		int colnum = 0;
		for (int i = 0; i < headerMetaData.getOriginColumns().size(); i++) {
			TableColumn tc = headerMetaData.getOriginColumns().get(i);
			if (i != 0) {
				colnum += headerMetaData.getOriginColumns().get(i - 1).getLength();
			}
            //  创建标题行
			generateColumn(sheet, tc, headerMetaData.maxlevel, rownum, colnum, headerstyle);
		}
		rownum += headerMetaData.maxlevel;
		List<TableDataRow> dataRows = tableData.getRows();

		HashMap<Integer, Integer> counter = new HashMap<Integer, Integer>();
		HashMap<Integer, String> word = new HashMap<Integer, String>();
		int index = 0;
        //循环创建数据行 把数据填充到表单
		for (TableDataRow dataRow : dataRows) {
			row = sheet.createRow(rownum);
            // get all data of cells
			List<TableDataCell> dataCells = dataRow.getCells();
            // get the count of cells
			int size = headerMetaData.getColumns().size();
			index = -1;
			for (int i = 0; i < size; i++) {
				TableColumn tc = headerMetaData.getColumns().get(i);
				if (!tc.isVisible())
					continue;
				index++;
                //get value of cell
				String value = dataCells.get(i).getValue();
				if (tc.isGrouped()) {
					String w = word.get(index);
					if (w == null) {
						word.put(index, value);
						counter.put(index, 1);
						createCell(row, tc, dataCells, i, index, styles);
					} else {
						if (w.equals(value)) {
							counter.put(index, counter.get(index) + 1);
						} else {
							stopGrouping(sheet, word, counter, index, size, rownum, styles.get("STRING"));

							word.put(index, value);
							counter.put(index, 1);
							createCell(row, tc, dataCells, i, index, styles);
						}
					}
				} else {
                    //  生成单元格
					createCell(row, tc, dataCells, i, index, styles);
				}
			}
			rownum++;
		}

		stopGrouping(sheet, word, counter, 0, index, rownum, styles.get("STRING"));
		// 设置前两列根据数据自动列宽
// for (int c = 0; c <= headerMetaData.getColumns().size(); c++) {
// sheet.autoSizeColumn((short) c);
// }
		//sheet.setGridsPrinted(true);   */
        sheet.setDisplayGridlines(true);
		return wb;
	}

	/**
	 * 导出Excel(单工作表)
	 *
	 * @param title
	 *            文件名
	 * @param creator
	 *            创建人
	 * @param tableData
	 *            表格数据
	 * @return void <style name="dataset"> case SYSROWNUM%2==0?#row0:#row1;
	 *         fontsize:9px; </style> <style name="row0"> import(parent);
	 *         bgcolor:#FFFFFF; </style> <style name="row1"> import(parent);
	 *         bgcolor:#CAEAFE; </style>
	 */
	public void exportToExcel(String title, String creator, TableData tableData) throws Exception {
       // 从数据库取出样式
        XSSFWorkbook wb = new XSSFWorkbook();
		HashMap<String, XSSFCellStyle> styles = initStyles(wb);// 初始化表头样式
		wb = writeSheet(wb, title, styles, creator, tableData);// 写入工作表
		response.setHeader("Content-Disposition",
				"attachment;filename=\"" + new String(getFileName(title).getBytes("gb2312"), "iso8859-1") + ".xlsx\"");
		response.setHeader("Connection", "close");
		response.setHeader("Content-Type", "application/x-msexcel");
		OutputStream outputStream = response.getOutputStream();
		wb.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}

    @Override
    public void jsGridReportBaseXLSXService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.response = response;
        this.session = request.getSession();
        init(session);
    }

    public void initBackGroud(XSSFWorkbook wb,HashMap<String,XSSFCellStyle> styleHashMap){
        XSSFCellStyle xssfCellStyle = wb.createCellStyle();

    }
	/**
	 * 导出Excel(多工作表)
	 *
	 * @param title
	 *            文件名
	 * @param creator
	 *            创建人
	 * @param tableDataLst
	 *            各工作格数据(注意：每个tableData要设置sheet名称，否则按默认呈现)
	 * @return void <style name="dataset"> case SYSROWNUM%2==0?#row0:#row1;
	 *         fontsize:9px; </style> <style name="row0"> import(parent);
	 *         bgcolor:#FFFFFF; </style> <style name="row1"> import(parent);
	 *         bgcolor:#CAEAFE; </style>
	 */
	public void exportToExcel(String title, String creator, List<TableData> tableDataLst) throws Exception {

        XSSFWorkbook wb = new XSSFWorkbook();
        HashMap<String, XSSFCellStyle> styles = initStyles(wb);// 初始化表头样式
		int i = 1;
		for (TableData tableData : tableDataLst) {
			String sheetTitle = tableData.getSheetTitle();
			sheetTitle = CommonUtil.isEmpty(sheetTitle) ? "sheet" + i : sheetTitle;
			wb = writeSheet(wb, tableData.getSheetTitle(), styles, creator, tableData);// 写入工作表
			i++;
		}
		String sFileName = title + ".xlsx";
		response.setHeader("Content-Disposition",
				"attachment;filename=\"" + new String(getFileName(title).getBytes("gb2312"), "iso8859-1") + ".xlsx\"");
		response.setHeader("Connection", "close");
		response.setHeader("Content-Type", "application/vnd.ms-excel");

		wb.write(response.getOutputStream());
	}

	/**
	 * 创建单元格（带隔行背景色）
	 *
	 * @param
	 * @return void
	 */
	private void createCell(XSSFRow row, TableColumn tc, List<TableDataCell> data, int i, int index,
			HashMap<String, XSSFCellStyle> styles) {

		TableDataCell dc = data.get(i);
        // 创建列单元格
		XSSFCell cell = row.createCell(index);
		switch (tc.getColumnType()) {
            //  根据单元格类型创建设置单元格不同的样式
		case TableColumn.COLUMN_TYPE_INTEGER:
			cell.setCellValue(dc.getIntValue());
			XSSFCellStyle style = styles.get("INT");
			if (row.getRowNum() % 2 != 0)
				style = styles.get("INT_C");
			if (style != null)
				cell.setCellStyle(style);
			break;
		case TableColumn.COLUMN_TYPE_FLOAT_2:
			cell.setCellValue(dc.getDoubleValue());
			style = styles.get("D2");
			if (row.getRowNum() % 2 != 0)
				style = styles.get("D2_C");
			if (style != null)
                cell.setCellStyle(style);
			break;
		case TableColumn.COLUMN_TYPE_FLOAT_3:
			cell.setCellValue(dc.getDoubleValue());
			style = styles.get("D3");
			if (row.getRowNum() % 2 != 0)
				style = styles.get("D3_C");
			if (style != null)
                cell.setCellStyle(style);
			break;
		case TableColumn.COLUMN_TYPE_RED_BG:
			cell.setCellValue(dc.getValue());
			style = styles.get("RED_BG");
			if (style != null)
                cell.setCellStyle(style);
			break;
		case TableColumn.COLUMN_TYPE_YELLOW_BG:
			cell.setCellValue(dc.getValue());
			style = styles.get("YELLOW_BG");
			if (style != null)
                cell.setCellStyle(style);
			break;
		case TableColumn.COLUMN_TYPE_GREEN_BG:
			cell.setCellValue(dc.getValue());
			style = styles.get("GREEN_BG");
			if (style != null)
                cell.setCellStyle(style);
			break;
		default:
			if (dc.getValue().equalsIgnoreCase("&nbsp;"))
				cell.setCellValue("");
			else
				cell.setCellValue(dc.getValue());
			    style = styles.get("STRING");
			if (row.getRowNum() % 2 != 0)
				style = styles.get("STRING_C");
			if (style != null)
                cell.setCellStyle(style);
		}
	}

    /**
     * 从模板里面取出需要的模板样式
     * @param wb
     * @return
     */
    private HashMap<String, XSSFCellStyle> initStyles(XSSFWorkbook wb) {
        HashMap<String, XSSFCellStyle> ret = new HashMap<String, XSSFCellStyle>();
        try {
            // 加载模板
            getClass().getClassLoader();
            XSSFWorkbook src = new XSSFWorkbook(getClass().getClassLoader().getResourceAsStream(MODULE_PATH1));
            XSSFSheet sheet = src.getSheetAt(0);
            // 从模板的每一行取出模板样式 并放入到map中返回
            buildStyle(wb, src, sheet, 0, ret, "TITLE");
            buildStyle(wb, src, sheet, 1, ret, "SUB_TITLE");
            buildStyle(wb, src, sheet, 2, ret, "SUB_TITLE2");

            buildStyle(wb, src, sheet, 4, ret, "TABLE_HEADER");
            buildStyle(wb, src, sheet, 5, ret, "STRING");
            buildStyle(wb, src, sheet, 6, ret, "INT");
            buildStyle(wb, src, sheet, 7, ret, "D2");
            buildStyle(wb, src, sheet, 8, ret, "D3");

            buildStyle(wb, src, sheet, 10, ret, "STRING_C");
            buildStyle(wb, src, sheet, 11, ret, "INT_C");
            buildStyle(wb, src, sheet, 12, ret, "D2_C");
            buildStyle(wb, src, sheet, 13, ret, "D3_C");

            buildStyle(wb, src, sheet, 15, ret, "RED_BG");
            buildStyle(wb, src, sheet, 16, ret, "YELLOW_BG");
            buildStyle(wb, src, sheet, 17, ret, "GREEN_BG");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    private HashMap<String, XSSFCellStyle> initStyles(XSSFWorkbook wb,HashMap<String, XSSFCellStyle> ret){

            XSSFCellStyle xssfCellStyle = wb.createCellStyle();
            xssfCellStyle.setFillForegroundColor(new XSSFColor(new Color(128,128,128)));
            xssfCellStyle.setFillBackgroundColor(new XSSFColor(new Color(128, 128, 128)));
            xssfCellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            ret.put("TABLE_HEADER",xssfCellStyle);
            return ret;
    }
    /**
     * 初始化表格样式
     *
     * @param
     * @return HashMap<String,HSSFCellStyle>
     */

   /**
	 *
	 * @param
	 * @return void
	 */
	private void buildStyle(XSSFWorkbook  wbDes, XSSFWorkbook src, XSSFSheet sheet, int index,
			HashMap<String, XSSFCellStyle> ret, String key) {
        // 获取模板Excel对应表单单元格
        XSSFRow row = sheet.getRow(index);
		XSSFCell cell = row.getCell(1);
         //创建目标Excel样式及字体对象
        XSSFCellStyle dest = wbDes.createCellStyle();
        XSSFFont font = wbDes.createFont();

        // 从数据库中取配置的模板前背景样式颜色值
        String colorValue = null;
        String[] colors = new String[3];
        DataDictionaryVo dataDictionaryVo = new DataDictionaryVo();
        dataDictionaryVo.setSection("excelForegroundColor");
        dataDictionaryVo.setCodeKey(key);
        dataDictionaryVo.setSelectType("E");
        // 根据key取对应配置的颜色值
        List<CodeTable> list = dataDictionaryService.getCodeTableBySection(dataDictionaryVo);
        if(CommonUtil.isNotEmpty(list) &&  list.size()>0){
            colorValue = list.get(0).getCodeVlue();
            if(CommonUtil.isNotEmpty(colorValue)){
                colors = colorValue.split(",");
            } else{
                // 设置前背景色的默认颜色值
                colors[0] = "255";
                colors[1] = "255";
                colors[2] = "255";
            }
        } else{
            // 设置前背景色的默认颜色值
            colors[0] = "255";
            colors[1] = "255";
            colors[2] = "255";
        }
        switch(index){
            case 0 :
                // TITLE
                font.setFontName("黑体");
                font.setFontHeightInPoints((short) 19);
                dest.setVerticalAlignment(CellStyle.ALIGN_CENTER);
                dest.setAlignment(CellStyle.ALIGN_CENTER);
                dest.setFont(font);
                dest.setLeftBorderColor(new XSSFColor(new Color(0,0,0)));
                dest.setRightBorderColor(new XSSFColor(new Color(0,0,0)));
                dest.setTopBorderColor(new XSSFColor(new Color(0,0,0)));
                dest.setBottomBorderColor(new XSSFColor(new Color(0,0,0)));
                dest.setFillForegroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillBackgroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillPattern(CellStyle.VERTICAL_CENTER);
                break;
            case 1:
                //   SUB_TITLE
                font.setFontName("宋体");
                font.setItalic(true);
                font.setFontHeightInPoints((short) 10);
                dest.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
                dest.setAlignment(CellStyle.ALIGN_RIGHT);
                dest.setFont(font);
                dest.setLeftBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setRightBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setTopBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setBottomBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setFillForegroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillBackgroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillPattern(CellStyle.VERTICAL_CENTER);
                break;
            case 2:
                // SUB_TITLE2
                font.setFontName("宋体");
                font.setItalic(true);
                font.setFontHeightInPoints((short) 10);
                dest.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
                dest.setAlignment(CellStyle.ALIGN_LEFT);
                dest.setFont(font);
                dest.setLeftBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setRightBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setTopBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setBottomBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setFillForegroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillBackgroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillPattern(CellStyle.VERTICAL_CENTER);
                break;
             case 4 :
                 //TABLE_HEADER
                 font.setFontName("黑体");
                 font.setFontHeightInPoints((short) 14);
                 dest.setVerticalAlignment(CellStyle.ALIGN_CENTER);
                 dest.setAlignment(CellStyle.ALIGN_CENTER);
                 dest.setFont(font);
                 dest.setDataFormat(cell.getCellStyle().getDataFormat());
                 dest.setLeftBorderColor(new XSSFColor(new Color(0,0,0)));
                 dest.setRightBorderColor(new XSSFColor(new Color(0,0,0)));
                 dest.setTopBorderColor(new XSSFColor(new Color(0,0,0)));
                 dest.setBottomBorderColor(new XSSFColor(new Color(0,0,0)));
                 dest.setFillForegroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                 dest.setFillBackgroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                 dest.setFillPattern(CellStyle.SOLID_FOREGROUND);
               break;
            case 6 :
                //INT
                dest.setFont(src.getFontAt(cell.getCellStyle().getFontIndex()));
                dest.setDataFormat(cell.getCellStyle().getDataFormat());
                dest.setLeftBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setRightBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setTopBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setBottomBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setFillForegroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillBackgroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillPattern(CellStyle.SOLID_FOREGROUND);
               break;
            case 7 :
                // D2
                dest.setFont(src.getFontAt(cell.getCellStyle().getFontIndex()));
                dest.setDataFormat(cell.getCellStyle().getDataFormat());
                dest.setLeftBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setRightBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setTopBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setBottomBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setFillForegroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillBackgroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;
            case 8 :
                //D3
                dest.setFont(src.getFontAt(cell.getCellStyle().getFontIndex()));
                dest.setDataFormat(cell.getCellStyle().getDataFormat());
                dest.setLeftBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setRightBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setTopBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setBottomBorderColor(new XSSFColor(new Color(255,255,255)));
                dest.setFillBackgroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillForegroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;
            case 10 :
                //STRING_C
                dest.setFont(src.getFontAt(cell.getCellStyle().getFontIndex()));
                dest.setDataFormat(cell.getCellStyle().getDataFormat());
                dest.setLeftBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setRightBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));;
                dest.setTopBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setBottomBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillBackgroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillForegroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;
            case 11 :
                //  INT_C
                dest.setFont(src.getFontAt(cell.getCellStyle().getFontIndex()));
                dest.setDataFormat(cell.getCellStyle().getDataFormat());
                dest.setLeftBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setRightBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));;
                dest.setTopBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setBottomBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillBackgroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillForegroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;
            case 12 :
                //D2_C
                dest.setFont(src.getFontAt(cell.getCellStyle().getFontIndex()));
                dest.setDataFormat(cell.getCellStyle().getDataFormat());
                dest.setLeftBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setRightBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));;
                dest.setTopBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setBottomBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillBackgroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillForegroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;
            case 13 :
                //D3_C
                dest.setFont(src.getFontAt(cell.getCellStyle().getFontIndex()));
                dest.setDataFormat(cell.getCellStyle().getDataFormat());
                dest.setLeftBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setRightBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));;
                dest.setTopBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setBottomBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillBackgroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillForegroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;

            case 15 :
                //RED_BG
                dest.setFont(src.getFontAt(cell.getCellStyle().getFontIndex()));
                dest.setDataFormat(cell.getCellStyle().getDataFormat());
                dest.setLeftBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setRightBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));;
                dest.setTopBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setBottomBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillBackgroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillForegroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;
            case 16 :
                // YELLOW_BG
                dest.setFont(src.getFontAt(cell.getCellStyle().getFontIndex()));
                dest.setDataFormat(cell.getCellStyle().getDataFormat());
                dest.setLeftBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setRightBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));;
                dest.setTopBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setBottomBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillBackgroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillForegroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;
            case 17 :
                //GREEN_BG
                dest.setFont(src.getFontAt(cell.getCellStyle().getFontIndex()));
                dest.setDataFormat(cell.getCellStyle().getDataFormat());
                dest.setLeftBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setRightBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));;
                dest.setTopBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setBottomBorderColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillBackgroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillForegroundColor(new XSSFColor(new Color(Integer.valueOf(colors[0]),Integer.valueOf(colors[1]),Integer.valueOf(colors[2]))));
                dest.setFillPattern(CellStyle.SOLID_FOREGROUND);
                break;
            default:
                dest.cloneStyleFrom(cell.getCellStyle());
        }
		ret.put(key, dest);
	}
    private HashMap<String, XSSFCellStyle>  copyStyleToNewWB(HashMap<String, XSSFCellStyle> styles,XSSFWorkbook wb){
        HashMap<String, XSSFCellStyle> xssfCellStyleHashMap = new HashMap<String, XSSFCellStyle>();
        for(Map.Entry<String,XSSFCellStyle> entry:styles.entrySet()){
            String key = entry.getKey();
            XSSFCellStyle src = entry.getValue();
            XSSFCellStyle dest = wb.createCellStyle();

            if(key.equals("TABLE_HEADER")){
                dest.setLeftBorderColor(new XSSFColor(new Color(255,37,37)));
                dest.setRightBorderColor(new XSSFColor(new Color(128,128,128)));
                dest.setTopBorderColor(new XSSFColor(new Color(128,128,128)));
                dest.setFillForegroundColor(new XSSFColor(new Color(255,37,37)));
                dest.setFillBackgroundColor(new XSSFColor(new Color(255,37,37)));
                dest.setBottomBorderColor(new XSSFColor(new Color(128,128,128)));
                dest.setFillPattern(CellStyle.SOLID_FOREGROUND);
                dest.setFillPattern(CellStyle.THICK_FORWARD_DIAG);
            }else
            if(key.equals("STRING_C") || key.equals("INT_C") || key.equals("D2_C") ||key.equals("D3_C")){
                dest.setDataFormat(src.getDataFormat());
                dest.setFont(src.getFont());
                dest.setLeftBorderColor(new XSSFColor(new Color(221,243,255)));
                dest.setRightBorderColor(new XSSFColor(new Color(221,243,255)));
                dest.setTopBorderColor(new XSSFColor(new Color(221,243,255)));
                dest.setFillForegroundColor(new XSSFColor(new Color(221,243,255)));
                dest.setFillBackgroundColor(new XSSFColor(new Color(221,243,255)));
                dest.setBottomBorderColor(new XSSFColor(new Color(221,243,255)));
                dest.setFillPattern(CellStyle.SOLID_FOREGROUND);
            }
            if(key.equals("RED_BG")){
                dest.setDataFormat(src.getDataFormat());
                dest.setFont(src.getFont());
                dest.setLeftBorderColor(new XSSFColor(new Color(255,192,192)));
                dest.setRightBorderColor(new XSSFColor(new Color(255,192,192)));
                dest.setTopBorderColor(new XSSFColor(new Color(255,192,192)));
                dest.setFillForegroundColor(new XSSFColor(new Color(255,192,192)));
                dest.setFillBackgroundColor(new XSSFColor(new Color(255,192,192)));
                dest.setBottomBorderColor(new XSSFColor(new Color(255,192,192)));
                dest.setFillPattern(CellStyle.SOLID_FOREGROUND);
            }else
            if( key.equals("YELLOW_BG")){
                dest.setDataFormat(src.getDataFormat());
                dest.setFont(src.getFont());
                dest.setLeftBorderColor(new XSSFColor(new Color(255,255,0)));
                dest.setRightBorderColor(new XSSFColor(new Color(255,255,0)));
                dest.setTopBorderColor(new XSSFColor(new Color(255,255,0)));
                dest.setFillForegroundColor(new XSSFColor(new Color(255,255,0)));
                dest.setFillBackgroundColor(new XSSFColor(new Color(255,255,0)));
                dest.setBottomBorderColor(new XSSFColor(new Color(255,255,0)));
                dest.setFillPattern(CellStyle.SOLID_FOREGROUND);

            }else
            if(key.equals("GREEN_BG")){
                dest.setDataFormat(src.getDataFormat());
                dest.setFont(src.getFont());
                dest.setLeftBorderColor(new XSSFColor(new Color(146,208,80)));
                dest.setRightBorderColor(new XSSFColor(new Color(146,208,80)));
                dest.setTopBorderColor(new XSSFColor(new Color(146,208,80)));
                dest.setFillForegroundColor(new XSSFColor(new Color(146,208,80)));
                dest.setFillBackgroundColor(new XSSFColor(new Color(146,208,80)));
                dest.setBottomBorderColor(new XSSFColor(new Color(146,208,80)));
                dest.setFillPattern(CellStyle.SOLID_FOREGROUND);
            } else{
                dest = src;
            }
            xssfCellStyleHashMap.put(key,dest);
        }
        XSSFCellStyle dest = wb.createCellStyle();
        dest.setLeftBorderColor(new XSSFColor(new Color(255,37,37)));
        dest.setRightBorderColor(new XSSFColor(new Color(128,128,128)));
        dest.setTopBorderColor(new XSSFColor(new Color(128,128,128)));
        dest.setFillForegroundColor(new XSSFColor(new Color(255,37,37)));
        dest.setFillBackgroundColor(new XSSFColor(new Color(255,37,37)));
        dest.setBottomBorderColor(new XSSFColor(new Color(128,128,128)));
        dest.setFillPattern(CellStyle.SOLID_FOREGROUND);
        dest.setFillPattern(CellStyle.THICK_FORWARD_DIAG);
        xssfCellStyleHashMap.put("TABLE_HEADER2",dest) ;
        return xssfCellStyleHashMap;
    }
	/**
	 * 工具方法，将一个字符串转换为UTF-8编码
	 * 
	 * @param string
	 *            需要转换的字符串
	 * @return String 转换后的UTF-8字符串
	 */
	protected String getUTF8String(String string) {
		if (string == null) {
			return null;
		} else {
			try {
				String str = new String(string.getBytes("ISO8859-1"), "UTF-8");
				return str;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return string;
			}
		}
	}

	/**
	 * 工具方法，将一个字符串转换为GBK编码
	 * 
	 * @param string
	 *            需要转换的字符串
	 * @return String 转换后的GBK字符串
	 */
	protected String getGBKString(String string) {
		if (string == null) {
			return null;
		} else {
			try {
				String str = new String(string.getBytes("ISO8859-1"), "GBK");
				return str;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return string;
			}
		}
	}

	/**
	 * 单元格值为空处理
	 * 
	 * @param value
	 *            单元格值
	 * @return String 如果单元格值为空，则返回"&nbsp;"，否则返回原值
	 */
	public String fieldRender(String value) {
		if (value == null) {
			return "&nbsp;";
		} else {
			return value;
		}
	}

}
