package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import page.BaseClass;
import page.EnterItemsInEngine;
import utils.ConfigFileReader;
import utils.ExcelDataProvider;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MySelChromeTest {
    ConfigFileReader configFileReader;
    public static WebDriver myDriver;
    public static ChromeOptions myChromeOption;
    public BaseClass Base;
    public WebDriverWait wait;


    @BeforeTest
    public void SetUp() {
        configFileReader = new ConfigFileReader();
        long implicitwait = configFileReader.getImplicitlyWait();
        long explicitwait = configFileReader.getExplicitlyWait();
        myChromeOption = new ChromeOptions();
        myChromeOption.addArguments("--remote-allow-origins=*");
        myChromeOption.addArguments("--headless");
        WebDriverManager.chromedriver().setup();
        myDriver = new ChromeDriver(myChromeOption);
        myDriver.manage().timeouts().implicitlyWait(implicitwait, TimeUnit.SECONDS);
        wait = new WebDriverWait(myDriver, Duration.ofSeconds(explicitwait));
        Base = new BaseClass(myDriver, wait);
    }


    @Test(dataProvider = "TestData_Chrome", dataProviderClass = ExcelDataProvider.class)
    public void enterSearchAndValidateTextInChrome(String sTextToSearch, String strTextToValidate, String strSearchEngine) {
        Base.driver.get("https://www." + strSearchEngine + ".com/");
        Base.driver.manage().window().maximize();
        Base.getInstance(EnterItemsInEngine.class).setSearchEditBox(sTextToSearch,strSearchEngine);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'About')]")));
        Assert.assertEquals(Base.getInstance(EnterItemsInEngine.class).getText_link(strSearchEngine), strTextToValidate);
        Reporter.log("Text '" + strTextToValidate + "' found on search engine " + strSearchEngine);
        Assert.assertTrue(Base.getInstance(EnterItemsInEngine.class).getURLText().matches(".*www\\." + sTextToSearch + "\\.com.*"));
        Reporter.log("URL '" + sTextToSearch + "' found on search engine " + strSearchEngine);
        Assert.assertTrue(Base.getInstance(EnterItemsInEngine.class).getTitleText(strSearchEngine).matches(".*(" + StringUtils.capitalize(sTextToSearch) + "|" + sTextToSearch + ").*"));
        Reporter.log("First Text '" + sTextToSearch + "' found on search engine " + strSearchEngine);
    }


    @AfterTest
    public void teardown() {
        myDriver.quit();
    }
}
