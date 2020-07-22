import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static XSSFCell cell;
	static XSSFRow row;
	static FileOutputStream outFile;
	static FileInputStream file;

	public static void main(String[] args) {

	}

	public ExcelUtils(String excelPath, String sheetName) {
		try {
			file = new FileInputStream(new File(excelPath));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetName);
			System.out.println(sheet.getSheetName());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getRowCount() {

		try {

			int rowCount = sheet.getPhysicalNumberOfRows();
			System.out.println("no of rows :" + rowCount);
			return rowCount;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
			return 0;
		}

	}

	// to get column count
	public int getColumnCount() {
		int colCount = 0;
		try {
			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("no of columns :" + colCount);
			return colCount;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}

	// To get String cell value
	public String getCellData(int rowNum, int celNum) {
		String cellValue = null;
		try {

			cellValue = sheet.getRow(rowNum).getCell(celNum).toString();
			return cellValue;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// to get numeric Value
	public void getCellNumeric(int rowNum, int celNum) {

		try {

			double cellValue = sheet.getRow(rowNum).getCell(celNum).getNumericCellValue();
			System.out.println(cellValue);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// to set Stringvalue
	public void setCellData(int rowNum, int cel, String data) throws IOException {

		try {
			row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);

				row.createCell(cel).setCellValue(data);
			} else {
				row.createCell(cel).setCellValue(data);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			file.close();
			outFile = new FileOutputStream(new File(System.getProperty("user.dir") + "/src/resources/ExcelData.xlsx"));
			workbook.write(outFile);
			outFile.close();
		}
	}
}
