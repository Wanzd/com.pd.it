package com.pd.common.util;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.pd.common.annotations.Export;
import com.pd.common.calobject.ExportCfgVO;
import com.pd.springboot.SpringUtil;
import com.pd.standard.itf.IExportConfigEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExportUtil {

	public static <T extends IExportConfigEnum> void export(List exportData, Class<T> exportOp) {
		ExportCfgVO cfgVO = calExportCfgVO(exportOp);
		HttpServletResponse res = SpringUtil.getResponse();

		try {
			// 实例化HSSFWorkbook
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建一个Excel表单，参数为sheet的名字
			HSSFSheet sheet = workbook.createSheet(cfgVO.getSheetName());
			// 设置表头
			setTitle(workbook, sheet, cfgVO.getLabels());
			// 设置单元格并赋值
			setData(sheet, cfgVO.calData(exportData));
			// 设置浏览器下载
			res.reset();
			// 设置response的Header
			res.addHeader("Content-Disposition", "attachment;filename=" + cfgVO.getTitleName() + ".xlsx");
			OutputStream os = new BufferedOutputStream(res.getOutputStream());
			res.setContentType("application/vnd.ms-excel;charset=gb2312");
			// 将excel写入到输出流中
			workbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static <T extends IExportConfigEnum> ExportCfgVO calExportCfgVO(Class<T> exportOp) {
		if (!exportOp.isEnum()) {
			return null;
		}
		ExportCfgVO rsVO = new ExportCfgVO();
		Export exportAnnotation = exportOp.getAnnotation(Export.class);
		if (exportAnnotation != null) {
			rsVO.setTitleName(exportAnnotation.titleName());
			rsVO.setSheetName(exportAnnotation.sheetName());
		}
		T[] enumConstants = exportOp.getEnumConstants();
		List<T> asList = ListFactory.asList(enumConstants);
		rsVO.setCfgList(asList);
		return rsVO;
	}

	/**
	 * 方法名：setTitle 功能：设置表头 描述： 创建人：typ 创建时间：2018/10/19 10:20 修改人： 修改描述： 修改时间：
	 */
	private static void setTitle(HSSFWorkbook workbook, HSSFSheet sheet, List<String> titleList) {
		try {
			HSSFRow row = sheet.createRow(0);
			// 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
			for (int i = 0; i <= titleList.size(); i++) {
				sheet.setColumnWidth(i, 15 * 256);
			}
			// 设置为居中加粗,格式化时间格式
			HSSFCellStyle style = workbook.createCellStyle();
			HSSFFont font = workbook.createFont();
			font.setBold(true);
			style.setFont(font);
			style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
			// 创建表头名称
			HSSFCell cell;
			for (int j = 0; j < titleList.size(); j++) {
				cell = row.createCell(j);
				cell.setCellValue(titleList.get(j));
				cell.setCellStyle(style);
			}
		} catch (Exception e) {
			log.info("导出时设置表头失败！");
			e.printStackTrace();
		}
	}

	/**
	 * 方法名：setData 功能：表格赋值 描述： 创建人：typ 创建时间：2018/10/19 16:11 修改人： 修改描述： 修改时间：
	 */
	private static void setData(HSSFSheet sheet, List<String[]> data) {
		try {
			int rowNum = 1;
			for (int i = 0; i < data.size(); i++) {
				HSSFRow row = sheet.createRow(rowNum);
				for (int j = 0; j < data.get(i).length; j++) {
					row.createCell(j).setCellValue(data.get(i)[j]);
				}
				rowNum++;
			}
			log.info("表格赋值成功！");
		} catch (Exception e) {
			log.info("表格赋值失败！");
			e.printStackTrace();
		}
	}
}
