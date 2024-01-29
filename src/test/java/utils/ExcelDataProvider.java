package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelDataProvider {

	public Object[][] getData(String excelPath, String sheetName) {

		ReadExcel excel = new ReadExcel(excelPath, sheetName);

		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();

		Object[][] data = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {

			for (int j = 0; j < colCount; j++) {

				Object testData = excel.getCellValue(i, j);
				data[i - 1][j] = testData;
				System.out.print(data[i - 1][j] + " | ");
			}
			System.out.println();
		}

		return data;
	}


	@DataProvider(name = "TestData_Chrome")
	public Object[][] getTestChromeData() {

		String excelPath = "src/data/test_data.xlsx";
		String sheetName = "ChromeScenarios";
		Object data[][] = getData(excelPath, sheetName);
		return data;
	}

	@DataProvider(name = "TestData_FireFox")
	public Object[][] getFireFoxTestData() {

		String excelPath = "src/data/test_data.xlsx";
		String sheetName = "FireFoxScenarios";
		Object data[][] = getData(excelPath, sheetName);
		return data;
	}

	@DataProvider(name = "MySearchdata")
	public Object[][] getLoginData() {

		String excelPath = "src/data/test_data.xlsx";
		String sheetName = "MySearchdata";
		Object data[][] = getData(excelPath, sheetName);
		return data;
	}
}






