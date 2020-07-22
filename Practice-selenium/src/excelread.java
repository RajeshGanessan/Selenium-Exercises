import java.io.IOException;

public class excelread {

	
	
	public static void main(String[] args) throws IOException {
		
		String filepath = System.getProperty("user.dir") + "/src/resources/ExcelData.xlsx";
		
		ExcelUtils utils = new ExcelUtils(filepath, "Employees");
		String str = "sanjay";
		
		utils.getRowCount();
		
		utils.getCellData(0, 1);
		utils.setCellData(1, 0, str);
		utils.setCellData(1, 1, "startupEquity");
		utils.setCellData(2, 0, "NewWorld ");
		System.out.println(utils.getCellData(9, 5));
	}
}
