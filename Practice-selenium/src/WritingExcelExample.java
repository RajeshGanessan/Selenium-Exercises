import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.InvalidArgumentException;

import java.io.*;

public class WritingExcelExample {
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static XSSFCell cell;
	private static XSSFRow row;

	public static void main(String[] args) throws InvalidFormatException, IOException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {

		FileInputStream fis = new FileInputStream(new File("/Users/letsventure/RajeshFiles/New Workspace/my-practice/eclipse-workspace/Practice-selenium/src/resources/data.xlsx"));
		workbook = new XSSFWorkbook(fis);
		//Create Sheet named as Data
		sheet = workbook.createSheet("erer");
		//String array
		String[][] data = new String[3][2];
		data[0][0] = "Name";
		data[0][1] = "City";
		data[1][0] = "Shekhar";
		data[1][1] = "Bangalore";
		data[2][0] = "Manan";
		data[2][1] = "Delhi";
		//Row count array

		String[] details = {"hello","world","rajesh"};
		String[] detailsTwo = {"good","morning","world"};
		int rowcount = 3;
			int columnCount = 3;

			for(int i=0;i<rowcount;i++){


				row =  sheet.createRow(i);

				for(int j=0;j<columnCount;j++){

					switch (i){
						case 0:
							row.createCell(j).setCellValue(details[j]);
							break;
						case 1:
							row.createCell(j).setCellValue(detailsTwo[j]);
							break;
					}

				}

				String number = "b";
				try{
					int num = Integer.parseInt(number);
				} catch (NumberFormatException e){

				}
		}

		//Writing data to created excel instance
		FileOutputStream fileout = new FileOutputStream(new File("/Users/letsventure/RajeshFiles/New Workspace/my-practice/eclipse-workspace/Practice-selenium/src/resources/data.xlsx"));
		workbook.write(fileout);
		fileout.close();
	}

	}


