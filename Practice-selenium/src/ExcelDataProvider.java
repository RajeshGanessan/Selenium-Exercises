
public class ExcelDataProvider {
	
	static String filepath = System.getProperty("user.dir") + "/src/resources/ExcelData.xlsx";
		
	
	public static void main(String[] args) {
		
		testData(filepath, "Data");
	}

	
	public static void testData(String excelPath,String sheetName) {
		
		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);
		
		int rowCount = excel.getRowCount();
		int colCount = excel.getColumnCount(); 
		
		for(int i=1 ;i<rowCount;i++) {
			
			for(int j=0;j<colCount;j++) {
				
				String cellData = excel.getCellData(i, j);
				System.out.print(cellData + " | ");
			}
			System.out.println();
		}
	}
}
