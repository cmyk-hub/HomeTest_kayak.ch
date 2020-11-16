package Pages

import Utils.SearchParams
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.testng.Assert

public class ResultPage extends BasePage {

    // Locators
    @FindBy(xpath = "//*[contains(@id, 'dialog-content')]/button[contains(@id, 'dialog-close')]")
    WebElement popUpBody

    @FindBy(xpath = "//*[contains(@id, 'form')]/button[contains(@id, 'submit')]")
    WebElement popUpClose

    @FindBy(xpath = "//div[contains(@id, 'price-title')]/*[contains(@id, 'price-title-text')]")
    WebElement priceTitle

    String priceTitleWait = "//div[contains(@id, 'price-title')]/*[contains(@id, 'price-title-text')]"

    @FindBy(xpath = "//div[contains(@id, 'price-price-slider-sliderWidget-handle-0')]")
    WebElement priceSliderHead

    String priceSliderHeadWait = "//div[contains(@id, 'price-price-slider-sliderWidget-handle-0')]"

    @FindBy(xpath = "//div[contains(@id, 'price-price-slider-rangeLabel')]/span[contains(@class, 'max')]")
    WebElement priceMaxValue

    @FindBy(xpath = "//div[contains(@id, 'price-bookingSection')]//span[contains(@id, 'price-text')]")
    WebElement flightPrice

    String flightResulsDivs = "//div[contains(@class, 'Flights-Results-FlightResultItem')]"
    //END Locators

    // Interactions
    public void toClosePopup() {
        toWait(2000)
        this.popUpClose.click()
        toWait(500)
        this.popUpClose.sendKeys(Keys.ESCAPE)
    }

    public void toSetMaxPrice() {
        toWait(1000)
        waitVisibleElement(priceTitleWait)
        Actions action = new Actions(driver)
        action.moveToElement(this.priceTitle).release().build().perform()
        this.priceTitle.click()
        toWait(1000)
        waitVisibleElement(priceSliderHeadWait)
        this.priceSliderHead.click()
        toWait(2000)
        def maxPrice = this.priceMaxValue.getText().replaceAll("[^\\d.]", "").toInteger()
        while (SearchParams.PRICE < maxPrice) {
            action.clickAndHold(this.priceSliderHead).moveToElement(this.priceSliderHead, -4, 0).release().build().perform()
            maxPrice = this.priceMaxValue.getText().replaceAll("[^\\d.]", "").toInteger()
        }
        toWait(3000)
    }

    public void toVerifySearchResults() {
        waitVisibleElement(flightResulsDivs)
        //verify if all flight prices are lower than the max price
        def optionsPrices = driver.findElements(By.xpath("//div[contains(@id, 'price-bookingSection')]//span[contains(@id, 'price-text')]"))
        if (optionsPrices.isEmpty()) {
            Assert.fail("No flight options found under required price")
        }
        for (def optionPrice : optionsPrices) {
            def parsedOptionPrice = optionPrice.getText().replaceAll("[^\\d.]", "").toInteger()
            Assert.assertTrue(SearchParams.PRICE > parsedOptionPrice)
        }
    }
    //END Interactions

    public ResultPage(WebDriver driver) {
        super(driver)
    }
}
