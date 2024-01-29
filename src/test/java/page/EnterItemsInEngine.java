package page;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnterItemsInEngine extends BaseClass {
    private static String strElement;
    String nameofCurrMethod = "";

    public EnterItemsInEngine(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    //  WebDriver driver = null;
    By textbox_search = By.name("q");
    By linkText_Google = By.xpath("//*[contains(text(),'Credit Card Processing for Small Businesses')]");
    By linkText_Bing = By.xpath("//a[contains(text(),'Clover Dashboard')]");
    By TitleText_Bing = By.xpath("//div[@id = 'b_content']//*[contains(text() ,'" + strElement + "')]");
    By TitleText_Google = By.xpath("//div[@id = 'search']//span[text() = '" + StringUtils.capitalize(strElement) + "']");
    By Url = By.xpath("//*[contains(text(),'https://www." + strElement + ".com')]");
    By Url_FF = By.xpath("//*[contains(text(),'dashboard')]");

    public void setSearchEditBox(String pElement) {
        try {
            strElement = pElement;
            driver.findElement(textbox_search).sendKeys(pElement);
            driver.findElement(textbox_search).submit();
        } catch (Exception e) {
            nameofCurrMethod = new Throwable()
                    .getStackTrace()[0]
                    .getMethodName();
            System.out.println("Check : " + nameofCurrMethod);
            e.printStackTrace();
        }
    }

    public String getTitleText(String streng) {
        try {
            if (streng.equals("Google")) {
                return driver.findElements(TitleText_Google).get(0).getText();
            } else {
                return driver.findElements(TitleText_Bing).get(0).getText();
            }
        } catch (Exception e) {
            nameofCurrMethod = new Throwable()
                    .getStackTrace()[0]
                    .getMethodName();
            System.out.println("Check : " + nameofCurrMethod);
            e.printStackTrace();
        }
        return null;
    }

    public String getURLText() {
        try {
            return driver.findElements(Url).get(0).getText();
        } catch (Exception e) {
            nameofCurrMethod = new Throwable()
                    .getStackTrace()[0]
                    .getMethodName();
            System.out.println("Check : " + nameofCurrMethod);
            e.printStackTrace();
        }
        return null;
    }

    public String getFFURLText(String streng) {
        try {
            if (streng.equals("Google"))
                return driver.findElements(Url).get(0).getText();
            else return driver.findElements(Url_FF).get(0).getText();
        } catch (Exception e) {
            nameofCurrMethod = new Throwable()
                    .getStackTrace()[0]
                    .getMethodName();
            System.out.println("Check : " + nameofCurrMethod);
            e.printStackTrace();
        }
        return null;
    }

    public String getText_link(String streng) {
        try {
            if (streng.equals("Google"))
                return driver.findElements(linkText_Google).get(0).getText();
            else return driver.findElements(linkText_Bing).get(0).getText();
        } catch (Exception e) {
            nameofCurrMethod = new Throwable()
                    .getStackTrace()[0]
                    .getMethodName();
            System.out.println("Check : " + nameofCurrMethod);
            e.printStackTrace();
        }
        return null;
    }
}
