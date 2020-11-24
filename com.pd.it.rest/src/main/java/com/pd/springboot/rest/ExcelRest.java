package com.pd.springboot.rest;

import java.io.BufferedOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.base.exception.BusinessException;

@RestController
@RequestMapping("excelRest")
public class ExcelRest {

	@RequestMapping("/export{dimension}")
	public void exportDimension(HttpServletResponse res, String foStr, @PathVariable String dimension)
			throws BusinessException {
		try {
			// 实例化HSSFWorkbook
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建一个Excel表单，参数为sheet的名字
			HSSFSheet sheet = workbook.createSheet("testSheet");
			// 设置表头
			// setTitle(workbook, sheet, data.getHead());
			// 设置单元格并赋值
			// setData(sheet, data.getData());
			// 设置浏览器下载
			res.reset();
			// 设置response的Header
			res.addHeader("Content-Disposition", "attachment;filename=" + "test.xlsx");
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
}
