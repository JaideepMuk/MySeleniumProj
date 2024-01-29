package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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

public class MySelFireFoxTest {
    ConfigFileReader configFileReader;
    public static WebDriver myfDriver;
    public static FirefoxOptions myfOption;
    public WebDriverWait wait;
    public BaseClass fBase;

    @BeforeTest
    void SetUp() {
        configFileReader = new ConfigFileReader();
        long implicitwait = configFileReader.getImplicitlyWait();
        long explicitwait = configFileReader.getExplicitlyWait();
        myfOption = new FirefoxOptions();
        myfOption.addArguments("--headless");
        WebDriverManager.firefoxdriver().setup();
        myfDriver = new FirefoxDriver(myfOption);
        myfDriver.manage().timeouts().implicitlyWait(implicitwait, TimeUnit.SECONDS);
        wait = new WebDriverWait(myfDriver, Duration.ofSeconds(explicitwait));
        fBase = new BaseClass(myfDriver, wait);
    }


    @Test(dataProvider = "TestData_FireFox", dataProviderClass = ExcelDataProvider.class)
    void enterSearchAndValidateTextInFireFox(String sTextToSearch, String strTextToValidate, String strSearchEngine) {
        fBase.driver.get("https://www." + strSearchEngine + ".com/");
        fBase.driver.manage().window().maximize();
        fBase.getInstance(EnterItemsInEngine.class).setSearchEditBox(sTextToSearch);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'About')]")));
        Assert.assertEquals(fBase.getInstance(EnterItemsInEngine.class).getText_link(strSearchEngine), strTextToValidate);
        Reporter.log("Text '" + strTextToValidate + "' found on search engine " + strSearchEngine);
        Assert.assertTrue(fBase.getInstance(EnterItemsInEngine.class).getTitleText(strSearchEngine).matches(".*(" + StringUtils.capitalize(sTextToSearch) + "|" + sTextToSearch + ").*"));
        Reporter.log("First Text '" + sTextToSearch + "' found on search engine " + strSearchEngine);
    }

    @AfterTest
    void teardown() {
        myfDriver.quit();
    }
}
