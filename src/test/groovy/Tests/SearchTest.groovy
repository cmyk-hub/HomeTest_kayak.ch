package Tests

import Pages.MainPage
import Pages.ResultPage
import Utils.Config
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeSuite
import org.testng.annotations.Test

class SearchTest {
    private static final WebDriver driver = new ChromeDriver()


    @BeforeSuite
    public static void setUp() {
        // ChromeDriver location set up in Config class
        System.setProperty(Config.WEB_DRIVER_KEY, Config.WEB_DRIVER_VALUE)
    }

    @Test(testName = "Find flights below price")
    public static void isThereFlightBelowPrice(){

        driver.get(Config.BASE_URL)
        MainPage mainPage = new MainPage(driver)
        mainPage.acceptCookies()
        mainPage.pressFlightsButton()
        mainPage.toInputOrigin()
        mainPage.toInputDestination()
        mainPage.toInputStartDate()
        mainPage.toInputEndDate()
        mainPage.toSearchResults()
        ResultPage resultPage = new ResultPage(driver)
        resultPage.toClosePopup()
        resultPage.toSetMaxPrice()
        resultPage.toVerifySearchResults()
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies()
        driver.close()
    }
}