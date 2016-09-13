package cn.shendiao.util;



import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;


public class WriteExcel {
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * @Title:exportExcel
	 * @Description: 导出Excel 
	 * @param list
	 * @param path
	 * @param nameWriteExcel
	 * @return 
	 * @throws IOException 
	 */
	public static HSSFWorkbook excelExport( String [] headStr,String title,List<Object[]> lists) throws IOException{
		// 生成工作簿对象
		HSSFWorkbook hssfwookbook = new HSSFWorkbook();
		// 生成脚本的名称
		HSSFSheet sheet = hssfwookbook.createSheet(!StringUtils.isEmpty(title)?title:null);
		HSSFCellStyle headStyle = hssfwookbook.createCellStyle();       //设置标题样式
		HSSFCellStyle contentStyle = hssfwookbook.createCellStyle();  // 设置内容样式
		
		headStyle.setFillForegroundColor((short) 13);								//设置背景色
		headStyle.setFillForegroundColor(HSSFColor.SEA_GREEN.index); //设置颜色
		headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平布局：居中
		headStyle.setVerticalAlignment(HSSFCellStyle.SOLID_FOREGROUND);
		headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);			//上边框    
		headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);	//下边框    
		headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);			//左边框    
		headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);		//右边框    
		
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);			//上边框    
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);	//下边框    
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);			//左边框    
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);		//右边框    

		// 生成头标题行
		HSSFRow head = sheet.createRow(0);         
		for (int i=0;i<headStr.length;i++) {
			HSSFCell cell = head.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(headStr[i]);
		}
		
		Object[][] data=new Object[lists.size()][headStr.length];
		//生成Excel内容
		for(int i=0; i<lists.size();i++){
			data[i] = lists.get(i);
			HSSFRow dataRow = sheet.createRow(i + 1);
			for(int j=0;j<headStr.length;j++){
				HSSFCell cell = dataRow.createCell(j);
				cell.setCellStyle(contentStyle);
				cell.setCellValue(StringUtils.isNotEmpty(data[i][j]) ? data[i][j].toString() : "");
			}
		}
		
		return hssfwookbook;
	}
	
	
	
	/*String[] headStr={"报名号","学生姓名","联系电话","所在地（省份）","高中","高考年份","选课","其他号码","其他号码归属人","进线","渠道","第一条沟通记录"};
		List<Object[]> CoursesReportLists = reportService.exportCoursesExcel(bigTypeId);
		String tittle = "课程报名情况统计"; //定义导出Excel名称
    HSSFWorkbook hssfWorkbook = WriteExcel.excelExport(headStr, tittle, CoursesReportLists);
    ExcelExportTag.excelExportTag(response, hssfWorkbook, tittle);*/
	
	
	
	
	
    /*public  String writeExcel(List<Object[]> list,String path) {
	   
		try {
			
				String uuid=ToolUtils.getUUID();
				
				String textName=uuid+".xls";
				
				//创建一个工作薄
				Workbook wb=new HSSFWorkbook();
				
				
				//创建一个sheet
				Sheet sheet=wb.createSheet();
				Row r=sheet.createRow(0);
				
				List<String> list11=new ArrayList<String>();
				list11.add("手机");
				list11.add("学生姓名");
				list11.add("a[1]到a[42]的值");
				list11.add("邮箱");
				list11.add("班级");
				list11.add("高中");
				list11.add("QQ");
				list11.add("归属");
				list11.add("课程顾问");
				for(int m=0;m<9;m++){
					Cell cell=r.createCell(m);
					cell.setCellValue(list11.get(m));
				}
			
				//xls文件最大支持65536行
				for(int rownum=1;rownum<list.size()+1;rownum++){
					
					r=sheet.createRow(rownum);
					
					for(int cellnum=0;cellnum<9;cellnum++){
						//在行里创建单元格
						Cell cell=r.createCell(cellnum);
						//向单元格中写入数据
						cell.setCellValue(list.get(rownum-1)[cellnum].toString());
					}
					
				}
			
			
				FileOutputStream out=new FileOutputStream(path+textName);
				wb.write(out);//输出文件内容
				File file=new File(path+textName);
				
				FileInputStream is=new FileInputStream(file);
				AccessResult<String> accessResult=FileAccessUtil.getFileAccess().add(FileUtils.readBytes(is), textName);
				out.close();
				if(accessResult.isSuccess()){
					//得到上传的云服务器的url
					path=accessResult.getResult();
					return path;
				}else{
					throw new Exception("答案上传失败，请重新上传！！");
					
				}

			} catch (Exception ex) {
				try {
					throw new Exception("答案上传失败，请重新上传！！");
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
			
		    return null;
		
	}
*/
}
