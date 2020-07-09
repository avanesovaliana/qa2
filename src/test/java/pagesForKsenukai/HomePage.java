package pagesForKsenukai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage {

    public BaseFunc baseFunc;
    private static Logger LOGGER = LogManager.getLogger(HomePage.class);

    private final By SEARCH_BTN = By.id("q");
    private final By MAIN_SEARCH_BTN = By.xpath(".//button[contains(@class,'main-search-submit')]");
    private static By OFFERS = By.xpath(".//div[contains(@class,'glide_slide product-slide products-list clearfix glide_slide--active')]/a");
    private final By SUGGEST_BTN = By.xpath(".//div[contains(@class, 'sn-suggest-doc sn-suggest-item senukai-v2')]");



    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void findItem(String productName){
        WebElement searchInput = baseFunc.findElement(SEARCH_BTN);
        searchInput.clear();
        searchInput.sendKeys(productName);
        baseFunc.findElement(MAIN_SEARCH_BTN).click();

    }

    public void findItemInSearchDropdown(String productName){
        WebElement searchInput = baseFunc.findElement(SEARCH_BTN);
        searchInput.clear();
        searchInput.sendKeys(productName);
        baseFunc.findElement(SUGGEST_BTN).click();
    }

    public void findOfferedItems(int itemNum) {
        baseFunc.waitForElements(OFFERS);
        List<WebElement> offers = baseFunc.findElements(OFFERS);

        if (itemNum <= offers.size() && itemNum >= 0) {
            baseFunc.goToElement(OFFERS);
            baseFunc.wait.until(ExpectedConditions.visibilityOfElementLocated(OFFERS));
            baseFunc.wait.until(ExpectedConditions.visibilityOfAllElements(offers.get(itemNum)));
            baseFunc.wait.until(ExpectedConditions.elementToBeClickable(offers.get(itemNum))).click();
        } else {
            LOGGER.info("Invalid item number");
        }
    }
}
