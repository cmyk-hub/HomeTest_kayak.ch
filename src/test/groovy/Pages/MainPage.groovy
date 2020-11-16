package Pages

import Utils.SearchParams
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

public class MainPage extends BasePage{

    // Locators
    @FindBy(xpath = "//button[@title='Akzeptieren']")
    WebElement acceptCookieConsent

    @FindBy(partialLinkText = "Flüge")
    private WebElement flightsLink

    @FindBy(xpath = "//div[contains(@id, 'origin-airport-display') and contains(@data-placeholder,'Von?')]")
    WebElement inputOriginWrapper

    @FindBy(xpath = "//input[@name='origin']")
    WebElement inputOrigin

    String inputOriginWait = "//*[contains(@id, 'origin-airport-smarty-wrapper')]"

    @FindBy(xpath = "//div[contains(@id, 'destination-airport-display') and contains(@data-placeholder,'Nach?')]")
    WebElement inputDestinationWrapper

    @FindBy(xpath = "//input[@name='destination']")
    WebElement inputDestination

    @FindBy(xpath = "//div[contains(@id, 'multiAirportCheckbox') and contains(@id,'multi-select-container')]/div[contains(@class, 'code')")
    WebElement codeDestination

    String inputDestinationWait = "//div[contains(@class, 'explore')]"

    @FindBy(xpath = "//div[contains(@id, 'dateRangeInput-display-start-inner')and contains(@class, 'input')]")
    WebElement startDateWrapper

    @FindBy(xpath = "//div[contains(@id, 'depart') and contains(@class, 'input')]")
    WebElement startDate

    String startDateWait = "//div[contains(@class, 'month')]/div[contains(@class, 'weeks')]"

    @FindBy(xpath = "//div[contains(@id, 'dateRangeInput-display-end-inner')and contains(@class, 'input')]")
    WebElement endDateWrapper

    @FindBy(xpath = "//div[contains(@id, 'return') and contains(@class, 'input')]")
    WebElement endDate

    String endDateWait = "//div[contains(@class, 'month')]/div[contains(@class, 'weeks')]"

    @FindBy(xpath = "//button[contains(@id, 'submit') and contains(@class, 'searchButton')]")
    WebElement searchButton
    //END Locators

    // Interactions
    public void acceptCookies(){
        toWait(1000)
        this.waitElement(acceptCookieConsent)
        this.acceptCookieConsent.click()
    }

    public void pressFlightsButton(){
        this.flightsLink.click()
    }

    public void inputOriginCity(){
        this.inputOrigin.sendKeys(SearchParams.PORT_OF_ORIGIN)
    }

    public void toInputOrigin() {
        this.inputOriginWrapper.click()
        this.inputOrigin.click()
        this.inputOrigin.sendKeys(Keys.BACK_SPACE)
        this.inputOrigin.sendKeys(Keys.BACK_SPACE)
        toWait(1000)
        this.inputOriginCity()
        this.inputOrigin.click()
        this.inputOrigin.sendKeys(Keys.RETURN)
    }

    public void inputDestinationCity(){
        this.inputDestination.sendKeys(SearchParams.PORT_OF_ARRIVAL)
    }

    public void toInputDestination() {
        this.inputDestinationWrapper.click()
        toWait(1000)
        this.inputDestinationCity()
        toWait(1000)
        this.inputDestination.sendKeys(Keys.RETURN)
    }

    public void toInputStartDate() {
        this.startDateWrapper.click()
        toWait(1000)
        this.startDate.sendKeys(Keys.BACK_SPACE)
        this.startDate.sendKeys(Keys.BACK_SPACE)
        toWait(1000)
        this.startDate.sendKeys(SearchParams.DEPART_DATA + Keys.RETURN)
    }

    public void toInputEndDate() {
        this.endDateWrapper.click()
        this.endDate.sendKeys(Keys.BACK_SPACE)
        this.endDate.sendKeys(Keys.BACK_SPACE)
        toWait(1000)
        this.endDate.sendKeys(SearchParams.ARRIVAL_DATA + Keys.RETURN)
    }

    public void toSearchResults() {
        this.searchButton.click()
        toWait(4000)
    }
    //END Interactions

    public MainPage(WebDriver driver) {
        super(driver)
    }
}