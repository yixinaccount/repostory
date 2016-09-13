package cn.shendiao.util;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
/**
 * 利用开源组件POI3.8动态导出EXCEL文档
 */
public class WriteExcel2 {
 
    /**
     * 根据传进来的文件后缀名创建不同版本的Excel Workbook
     * 
     * @param title
     * @param headers
     * @param list
     * @param out
     */
    public static Workbook createWorkbook(String title,
            Map<Object, String> headers, List<Map<String, Object>> list,
            String filename) {
        // 判断是03之前还是03之后的版本创建不同的工作簿
        Workbook workbook = null;
        if (filename.endsWith(".xls")) {
            workbook = new HSSFWorkbook();
        } else if (filename.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook();
        }
 
        // 创建一个sheet页
        Sheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
 
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();
        // 设置标题行样式
        style.setFillBackgroundColor(HSSFColor.SKY_BLUE.index); // 填充的背景颜色
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 填充图案
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 设置边框样式
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 顶边框
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
 
        // 生成标题行字体
        Font font = workbook.createFont();
        // 设置字体属性
        font.setColor(HSSFColor.VIOLET.index);// 字体颜色
        font.setFontHeightInPoints((short) 12);// 字号
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        // 把字体应用到当前样式中
        style.setFont(font);
 
        // 生成并设置数据行样式
        CellStyle style2 = workbook.createCellStyle();
        // 设置这些样式
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
 
        // 生成数据行字体
        Font font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        // 存储键
        List<String> listKey = new ArrayList<>();
        // 获取键
        Set<Map.Entry<String, Object>> entryset = list.get(0).entrySet();
        for (Map.Entry<String, Object> entry : entryset) {
            listKey.add(entry.getKey());
        }
        // 创建标题行
        Row row = sheet.createRow(0);
        for (int i = 0; i < listKey.size(); i++) {
            // 创建单元格把表头写进去
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(headers.get(listKey.get(i)));
        }
        // 填充数据
        for (int i = 0; i < list.size(); i++) {
            // 有多少数据生成多少行
            Row nextRow = sheet.createRow(i + 1);
            // 写入数据
            for (int j = 0; j < listKey.size(); j++) {
                Cell cell = nextRow.createCell(j);
                // 设置数据行样式
                cell.setCellStyle(style2);
                cell.setCellValue(list.get(i).get(listKey.get(j)).toString());
            }
        }
        return workbook;
    }
 
    /**
     * 生成本地文件
     */
    public static void createLocalExcel(String title,
            Map<Object, String> headers, List<Map<String, Object>> list,
            String filename) {
        // 生成文件
        String path = "E://";
        File file = new File(path + filename);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            createWorkbook(title, headers, list, filename).write(fos);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
 
    /**
     * 以流形式下载Excel文件
     * 
     * @param title
     * @param headers
     * @param list
     * @param filename
     * @param response
     * @return
     * @return
     * @throws Exception
     */
    public static void exportExcel(String title, Map<Object, String> headers,
            List<Map<String, Object>> list, String filename,
            HttpServletResponse response) throws Exception {
        // 以流形式下载文件
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // 把创建好的workbook以字节流的形式写进缓冲中
        createWorkbook(title, headers, list, filename).write(os);
        // 读取os字节数组
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        // 设置response弹出下载框
        // 清空response
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + new String(filename.getBytes(), "iso-8859-1"));
        OutputStream toClient = new BufferedOutputStream(
                response.getOutputStream());
        BufferedInputStream bis = new BufferedInputStream(is);
        // 获取缓冲数据
        byte[] buffer = new byte[bis.available()];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buffer, 0, buffer.length))) {
            toClient.write(buffer, 0, bytesRead);
        }
        toClient.flush();
        bis.close();
        toClient.close();
    }
}
