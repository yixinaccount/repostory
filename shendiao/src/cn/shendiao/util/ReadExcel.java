package cn.shendiao.util;



import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class ReadExcel {
	

	public static List<Object[]> readFirstSheet(InputStream inputStream,String fileName,int column) throws IOException {
		
		//得到上传的文件名后缀
		String fileNameSuffix=fileName.substring(fileName.lastIndexOf("."), fileName.length()).trim();
		//表明是2003版的Excel
		if(fileNameSuffix.equals(".xls")){
			// 进行Excel解析
			
			// 1、 工作薄对象
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(
					inputStream);
			// 解析工作薄
			hssfWorkbook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK); // 避免空指针异常

			// 2、 获得Sheet
			HSSFSheet sheet = hssfWorkbook.getSheetAt(0); // 获得第一个sheet
			
			//得到sheet总行数
			int rowNum=sheet.getLastRowNum();
			
			List<Object[]> list = new ArrayList<Object[]>();
			
			// 3、遍历每一行
			for(int j=1;j<=rowNum;j++){
				
				Row row=sheet.getRow(j);
				Cell cell2=row.getCell(0);
				/*cell2.getNumericCellValue();*/
				/*System.out.println(cell2.getNumericCellValue()==null);
				System.out.println(cell2.getNumericCellValue().equals(""));*/
				
				if(cell2.getStringCellValue().equals("")){
					break;
				}
				
				//循环得到每一个行的数据
				Object[] object= new Object[column];
				for(int i=0;i<column;i++){
									
					Cell cell=row.getCell(i);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					String cell1=cell.getStringCellValue().trim();
					object[i]=cell1;
				}
				System.out.println();
				
				list.add(object);
				
			}

			return list;
			
			
		}else{
			//1、工作薄对象
			XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
			
			//解析工作薄
			xssfWorkbook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);//避免空指针异常
			
			//2、获得Sheet
			XSSFSheet sheet=xssfWorkbook.getSheetAt(0);//获取第一个sheet
			
			//得到sheet总行数
			int rowNum=sheet.getLastRowNum();
			
			List<Object[]> list = new ArrayList<Object[]>();
			
			// 3、遍历每一行
			for(int j=1;j<=rowNum;j++){
				
				Row row=sheet.getRow(j);
				if(row==null){
					break;
				}
				Cell cell2=row.getCell(0);
				
				/*cell2.getNumericCellValue();*/
				/*System.out.println(cell2.getNumericCellValue()==null);
				System.out.println(cell2.getNumericCellValue().equals(""));*/
				
				if(cell2.getStringCellValue().equals("")){
					break;
				}
				
				//循环得到每一个行的数据
				Object[] object= new Object[column];
				for(int i=0;i<column;i++){
									
					Cell cell=row.getCell(i);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					String cell1=cell.getStringCellValue().trim();
					object[i]=cell1;
				}
				System.out.println();
				
				list.add(object);
				
			}

			return list;
			
		}
		
		/*// 进行Excel解析
			
		// 1、 工作薄对象
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(
				inputStream);
		// 解析工作薄
		hssfWorkbook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK); // 避免空指针异常

		// 2、 获得Sheet
		HSSFSheet sheet = hssfWorkbook.getSheetAt(0); // 获得第一个sheet
*/		
		/*//1、工作薄对象
		XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
		
		//解析工作薄
		xssfWorkbook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);//避免空指针异常
		
		//2、获得Sheet
		XSSFSheet sheet1=xssfWorkbook.getSheetAt(0);//获取第一个sheet
*/		
		
		
		
		/*//得到sheet总行数
		int rowNum=sheet.getLastRowNum();
		
		List<Object[]> list = new ArrayList<Object[]>();
		
		// 3、遍历每一行
		for(int j=1;j<=rowNum;j++){
			
			Row row=sheet.getRow(j);
			Cell cell2=row.getCell(0);
			cell2.getNumericCellValue();
			System.out.println(cell2.getNumericCellValue()==null);
			System.out.println(cell2.getNumericCellValue().equals(""));
			
			if(cell2.getStringCellValue().equals("")){
				break;
			}
			
			//循环得到每一个行的数据
			Object[] object= new Object[column];
			for(int i=0;i<column;i++){
								
				Cell cell=row.getCell(i);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				String cell1=cell.getStringCellValue().trim();
				object[i]=cell1;
			}
			System.out.println();
			
			list.add(object);
			
		}

		return list;*/
	}

	
	public static List<Object[]> readExcel(InputStream inputStream,String fileName,int column) throws IOException {
		//得到上传的文件名后缀
		String fileNameSuffix=fileName.substring(fileName.lastIndexOf("."), fileName.length()).trim();
		//表明是2003版的Excel
		if(fileNameSuffix.equals(".xls")){
			return read2003Excel(inputStream,column);
		}else{
			return read2007Excel(inputStream,column);
		}
	}
	

	public static List<Object[]> read2003Excel(InputStream file,int column)
			throws IOException {
		List<Object[]> list = new ArrayList<Object[]>();
		HSSFWorkbook hwb = new HSSFWorkbook(file);
		HSSFSheet sheet = hwb.getSheetAt(0);
		HSSFFormulaEvaluator  evaluator = new HSSFFormulaEvaluator(hwb);  
		Object value = null;
		HSSFRow row = null;
		HSSFCell cell = null;
        //HSSFRow firstRow=sheet.getRow(sheet.getFirstRowNum());
		List<CellRangeAddress> listCombineCell  = getCombineCell(sheet);
		for (int i = sheet.getFirstRowNum()+1; i < sheet
				.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			Object[] objects = new Object[column];
			for (int j = 0; j < column; j++) {
				cell = row.getCell(j);
				if(null == cell){
					continue;
				}
				value = getCombineCell(listCombineCell,cell,sheet,evaluator);
				objects[j] = value;

			}
			list.add(objects);
		}

		return list;
	}

	public static List<Object[]> read2007Excel(InputStream file,int column)
			throws IOException {
		List<Object[]> list = new LinkedList<Object[]>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(file);
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		XSSFFormulaEvaluator  evaluator = new XSSFFormulaEvaluator(xwb);  
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		//XSSFRow firstRow = sheet.getRow(sheet.getFirstRowNum());
		List<CellRangeAddress> listCombineCell  = getCombineCell(sheet);
		for (int i = sheet.getFirstRowNum()+1; i < sheet
				.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			Object[] objects = new Object[column];
			for (int j = 0; j < column; j++) {
				cell = row.getCell(j);
				if(null == cell){
					continue;
				}
				value = getCombineCell(listCombineCell,cell,sheet,evaluator);
				objects[j] = value;
			}
			
			for(int j=0;j<objects.length;j++){//验证每行的信息有没有为空的cell
			   if(null != objects[j] && StringUtils.isNotEmpty(objects[j])){
				  list.add(objects);
				  break;
			  }
		   }
			//list.add(objects);
		}
		return list;
	}
	
	public static String getNumber(String value){
		if(".0".equals(value.substring(value.indexOf(".")))){
			return value.substring(0,value.indexOf("."));
		}else{
			return value;
		}
	}
	
	
	/** 
	 * @获取Excel中某个单元格的值
	 * @param cell  	EXCLE单元格对象
	 * @param evaluator EXCLE单元格公式
	 * @return			单元格内容
	 */
	public static String getValue(Cell cell,FormulaEvaluator evaluator) { 
		
		String value = "";
		switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC:						//数值型
				if (HSSFDateUtil.isCellDateFormatted(cell)) {		//如果是时间类型
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					value = format.format(cell.getDateCellValue());
				} else {											//纯数字
				    value =  new BigDecimal(cell.getNumericCellValue()).toPlainString();
				}
				break;
			case HSSFCell.CELL_TYPE_STRING:							//字符串型
				value = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:						//布尔
				value = " " + cell.getBooleanCellValue();
				break;
			case HSSFCell.CELL_TYPE_BLANK:							//空值
				value = "";
				break;
			case HSSFCell.CELL_TYPE_ERROR:							//故障
				value = "";
				break;
			case HSSFCell.CELL_TYPE_FORMULA:						//公式型
				try {
					CellValue cellValue;
					cellValue = evaluator.evaluate(cell);
					switch (cellValue.getCellType()) {				//判断公式类型
					    case Cell.CELL_TYPE_BOOLEAN:
					    	value  = String.valueOf(cellValue.getBooleanValue());
					        break;
					    case Cell.CELL_TYPE_NUMERIC:
					    	// 处理日期  
					        if (DateUtil.isCellDateFormatted(cell)) {  
					    	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
					    	   Date date = cell.getDateCellValue();  
					           value = format.format(date);
					        } else {  
					    	   value  =  new BigDecimal(cell.getNumericCellValue()).toPlainString();;
					    	}
					        break;
					    case Cell.CELL_TYPE_STRING:
					    	value  = cellValue.getStringValue();
					        break;
					    case Cell.CELL_TYPE_BLANK:
					    	value = "";
					        break;
					    case Cell.CELL_TYPE_ERROR:
					    	value = "";
					        break;
					    case Cell.CELL_TYPE_FORMULA:
					    	value = "";
					        break;
					}
				} catch (Exception e) {
					value = cell.getStringCellValue().toString();
					cell.getCellFormula();
				}
				break;
			default:
				value = cell.getStringCellValue().toString();
				break;
		}
		return value;
	}
	
    public static List<CellRangeAddress> getCombineCell(Sheet sheet){
	    List<CellRangeAddress> list = new ArrayList<CellRangeAddress>();
	    //获得一个 sheet 中合并单元格的数量
	    int sheetmergerCount = sheet.getNumMergedRegions();
	    //遍历合并单元格
	    for(int i = 0; i<sheetmergerCount;i++) {
	        //获得合并单元格加入list中
	        CellRangeAddress ca = sheet.getMergedRegion(i);
	        list.add(ca);
	    }
	    return list;
	}
    
    public static String getCombineCell(List<CellRangeAddress> listCombineCell,Cell cell,Sheet sheet,FormulaEvaluator evaluator) {
	    int firstC = 0;
	    int lastC = 0;
	    int firstR = 0;
	    int lastR = 0;
	    String cellValue = "";
	    for(CellRangeAddress ca:listCombineCell){
	        //获得合并单元格的起始行, 结束行, 起始列, 结束列
	        firstC = ca.getFirstColumn();
	        lastC = ca.getLastColumn();
	        firstR = ca.getFirstRow();
	        lastR = ca.getLastRow();
	        if(null != cell && cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR) {
		        if(cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC) {
		            Row fRow = sheet.getRow(firstR);
		            Cell fCell = fRow.getCell(firstC);
		            cellValue = getValue(fCell,evaluator);
		            return cellValue;
		        }
	      }
	    }
	    cellValue = getValue(cell,evaluator);
	    return cellValue;
	  }
    
    
    
    
   /* 
    * protected MultipartFile toMultipartFile(org.springframework.web.multipart.MultipartFile multipartFile){
		if(multipartFile==null){
			return null;
		}
		MultipartFile tmpMultipartFile = new MultipartFile();
		try {
			tmpMultipartFile.setBytes(multipartFile.getBytes());
			tmpMultipartFile.setContentType(multipartFile.getContentType());
			tmpMultipartFile.setName(multipartFile.getName());
			tmpMultipartFile.setOriginalFilename(multipartFile.getOriginalFilename());
			tmpMultipartFile.setInputStream(multipartFile.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmpMultipartFile;
	}
    
    
  //ReadExcel readExcel = new ReadExcel();
  	List<Object[]> list = ReadExcel.readExcel(
  			excelFile.getInputStream(), excelFile.getOriginalFilename(), 9);
  			
*/
    
    
    


}
