package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
    public WebDriver driver;
    public WebDriverWait wait;

    // page class constructor:
    public BaseClass(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    public <TPage extends BaseClass> TPage getInstance(Class<TPage> pageClass) {
        try {
            return pageClass.getDeclaredConstructor(WebDriver.class, WebDriverWait.class).newInstance(this.driver, this.wait);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
