package Pages

import Utils.Config
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions
import java.util.concurrent.TimeUnit

public class BasePage {
    protected WebDriver driver

    public BasePage(WebDriver driver){
        this.driver = driver
        PageFactory.initElements(driver, this)
    }

    public waitElement(WebElement elementToBeWaited){
        WebDriverWait wait = new WebDriverWait(this.driver, Config.timeoutInSeconds)
        wait.until(ExpectedConditions.elementToBeClickable(elementToBeWaited))
    }

    public waitVisibleElement(String elementToBeVisible){
        WebDriverWait wait = new WebDriverWait(this.driver, Config.timeoutInSeconds)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementToBeVisible)))
    }

    public explicitWait(String element){
        def waitMe = this.waitVisibleElement(element)
        waitMe.click()
    }

    public implicitWait(Integer sec){
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS)
    }

    public static toWait(milisec){
        Thread.sleep(milisec)
    }


}