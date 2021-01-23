package Utils;
import JavaBean.ScoreInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
public class WriteExcelFile {
	private static HSSFWorkbook wb;
	public static void writeExcel(ArrayList<ScoreInfo> result, String path) {
		if (result == null) {
			return;
		}
		wb = new HSSFWorkbook();
		//Excel文件sheet1表
		HSSFSheet sheet = wb.createSheet("sheet1");
		//第一行表头
		HSSFRow row0 = sheet.createRow(0);
		row0.createCell(0).setCellValue("id");
		row0.createCell(1).setCellValue("student_name");
		row0.createCell(2).setCellValue("course_number");
		row0.createCell(3).setCellValue("teacher_id");
		row0.createCell(4).setCellValue("teacher_name");
		row0.createCell(5).setCellValue("term");
		row0.createCell(6).setCellValue("score");
		row0.createCell(7).setCellValue("confirm");
		//将ArrayList中数据写到每一行
		for (int i = 0; i < result.size(); i++) {
			HSSFRow row = sheet.createRow(i);
			if (result.get(i) != null) {
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(result.get(i).getId());
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(result.get(i).getStudentName());
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(result.get(i).getCourseNumber());
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(result.get(i).getTeacherId());
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(result.get(i).getTeacherName());
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(result.get(i).getTerm());
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(result.get(i).getScore());
				HSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(result.get(i).getConfirm());
			}
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		// Excel文件生成后存储的位置。
		File file = new File(path);
		OutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(content);
			os.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
