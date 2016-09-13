package cn.shendiao.util;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @Description: 对通用EXCEL导出设置输出流
 * @Date:2014年11月18日
 */
public class ExcelExportTag {
	
	/**
	 * @Date:2014年11月18日
	 * @Title:excelExportTag
	 * @Description: 对通用EXCEL导出设置输出流
	 * @param response
	 * @param hssfwookbook
	 * @param tittle
	 * @throws IOExceptionExcelExportTag
	 */
	public static void excelExportTag(HttpServletResponse response,HSSFWorkbook hssfWorkbook,String title) throws IOException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMDDHHSS");
		String textName=simpleDateFormat.format(new Date());
        String fileName=title+textName+".xls";
		response.setContentType("application/x-excel");
		response.setCharacterEncoding("UTF-8"); 
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);
	    ServletOutputStream out = response.getOutputStream();
	    hssfWorkbook.write(out);
		out.close();
	}
}
