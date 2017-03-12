package trtests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class mavenTests {
	
	private WebDriver driver;
	 String baseUrl = "http://trainingrite.net/";
	 
	 String strXLFilePath="C:\\SeleniumJAVATraining\\TestData\\TRNewMembers.xls";
	 int xlNumOfRows;
	 int xlNumOfCols;
	 
	 String xlDataInLocalArray[][];
	
	@Test
	public void tcXL_NewMember() throws Exception{
		
		ReadDataFromExcel(strXLFilePath);
		
		/*
		String vFirtName="Lisa";
		String vLastName="Selenium";
		String vEmail="lisa.selenium@tr.com";
		String vPassword="Passw0rd";
		String vVerifyPassword="Password";
		String vHomePhone="7148259999";
		String vCellPhone="7148259999";
		String vInstructions="D6 of March 2017 Batch";
		*/
		
for(int i=1; i<xlNumOfRows; i++){
	
	String vFirtName=xlDataInLocalArray[i][1];
	String vLastName=xlDataInLocalArray[i][2];
	String vEmail=xlDataInLocalArray[i][3];
	String vPassword=xlDataInLocalArray[i][4];
	String vVerifyPassword=xlDataInLocalArray[i][5];
	String vHomePhone=xlDataInLocalArray[i][6];
	String vCellPhone=xlDataInLocalArray[i][7];
	String vInstructions=xlDataInLocalArray[i][8];
		
		System.setProperty("webdriver.gecko.driver", "C:\\SeleniumJAVATraining\\Softwares\\geckodriver-v0.14.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get(baseUrl);
	    driver.findElement(By.cssSelector("input.submitbtn")).click();
	    driver.findElement(By.id("ctl00_MainContent_txtFirstName")).clear();
	    driver.findElement(By.id("ctl00_MainContent_txtFirstName")).sendKeys(vFirtName);
	    driver.findElement(By.id("ctl00_MainContent_txtLastName")).clear();
	    driver.findElement(By.id("ctl00_MainContent_txtLastName")).sendKeys(vLastName);
	    driver.findElement(By.id("ctl00_MainContent_txtEmail")).clear();
	    driver.findElement(By.id("ctl00_MainContent_txtEmail")).sendKeys(vEmail);
	    driver.findElement(By.id("ctl00_MainContent_txtPassword")).clear();
	    driver.findElement(By.id("ctl00_MainContent_txtPassword")).sendKeys(vPassword);
	    driver.findElement(By.id("ctl00_MainContent_txtVerifyPassword")).clear();
	    driver.findElement(By.id("ctl00_MainContent_txtVerifyPassword")).sendKeys(vVerifyPassword);
	    driver.findElement(By.id("ctl00_MainContent_txtHomePhone")).clear();
	    driver.findElement(By.id("ctl00_MainContent_txtHomePhone")).sendKeys(vHomePhone);
	    driver.findElement(By.id("ctl00_MainContent_txtCellPhone")).clear();
	    driver.findElement(By.id("ctl00_MainContent_txtCellPhone")).sendKeys(vCellPhone);
	    driver.findElement(By.id("ctl00_MainContent_txtInstructions")).clear();
	    driver.findElement(By.id("ctl00_MainContent_txtInstructions")).sendKeys(vInstructions);
	    driver.findElement(By.id("ctl00_MainContent_btnSubmit")).click();
	    Thread.sleep(2000);
	    
	    assertEquals(driver.findElement(By.id("ctl00_MainContent_lblTransactionResult")).getText(), "Customer information added successfully");
		
       driver.quit();		
}	
		
	}
	

	
	@Test
	public void tcXL_Login() throws Exception{
		
		System.out.println("Code for CSV DDT will get executed here.......");
		
	}
	
	
	@Test
	public void tcXL_AccountSummary() throws Exception{
		
		System.out.println("Code for CSV DDT will get executed here.......");
		
	}
	
	
	@Test
	public void tcXL_Invoicing() throws Exception{
		
		System.out.println("Code for CSV DDT will get executed here.......");
		
	}
	
	
	@Test
	public void tcXL_AccountsPayable() throws Exception{
		
		System.out.println("Code for CSV DDT will get executed here.......");
		
	}
	
	
	@Test
	public void tcXL_AccountsReceivable() throws Exception{
		
		System.out.println("Code for CSV DDT will get executed here.......");
		
	}
	
	
	
	
	public void ReadDataFromExcel(String strXLFilePath) throws Exception{

       File xlFile = new File(strXLFilePath);
       FileInputStream TestDataStream = new FileInputStream(xlFile);

HSSFWorkbook xlWorkBook = new HSSFWorkbook(TestDataStream);


       HSSFSheet xlSheet = xlWorkBook.getSheetAt(0);        // Referring to 1stsheet

       xlNumOfRows = xlSheet.getLastRowNum()+1;
       xlNumOfCols = xlSheet.getRow(0).getLastCellNum();


System.out.println("------------------------------------------------");
       System.out.println("Total Number of Test-Data Rows are " +xlNumOfRows);
       System.out.println("Total Number of Test-Data Cols are " +xlNumOfCols);



       xlDataInLocalArray = new String[xlNumOfRows][xlNumOfCols];


    for (int i = 0; i < xlNumOfRows; i++) {
          HSSFRow row = xlSheet.getRow(i);
           for (int j = 0; j < xlNumOfCols; j++) {
              HSSFCell cell = row.getCell(j); // To read value fromeach col in each row
              String value = cellToString(cell);
              xlDataInLocalArray[i][j] = value;
         //     System.out.print(value);
         //     System.out.print("@@");
              }
           System.out.println();

       }


}



public static String cellToString(HSSFCell cell) {
       // This function will convert an object of type excel cell to a string value
       int type = cell.getCellType();
       Object result;
       switch (type) {
           case HSSFCell.CELL_TYPE_NUMERIC: //0
               result = cell.getNumericCellValue();
               break;
           case HSSFCell.CELL_TYPE_STRING: //1
               result = cell.getStringCellValue();
               break;
           case HSSFCell.CELL_TYPE_FORMULA: //2
               throw new RuntimeException("We can't evaluateformulas in Java");
           case HSSFCell.CELL_TYPE_BLANK: //3
               result = "-";
               break;
           case HSSFCell.CELL_TYPE_BOOLEAN: //4
               result = cell.getBooleanCellValue();
               break;
           case HSSFCell.CELL_TYPE_ERROR: //5
               throw new RuntimeException ("This cell has anerror");
           default:
               throw new RuntimeException("We don't support thiscell type: " + type);
       }
       return result.toString();
   } 
	
	
	
	
	
	
	
	

}
