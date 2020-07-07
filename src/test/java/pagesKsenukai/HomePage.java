package pagesKsenukai;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage {
    private final By MAIN_SEARCH_INPUT = By.id("q");
    private final By SEARCH_BTN = By.xpath(".//button[contains(@class,'main-search-submit')]");
    private final By YOUR_CHOICE = By.xpath(".//div[contains(@class,'glide__slide product-slide products-list clearfix glide__slide--active')]/a");
    private final By SUGGEST_BTN = By.xpath(".//div[contains(@class, 'sn-suggest-doc sn-suggest-item senukai-v2')]");
    private BaseFunc baseFunc;


    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void findInSearchInput(String name){
        WebElement searchInput = baseFunc.findElement(MAIN_SEARCH_INPUT);
        searchInput.clear();
        searchInput.sendKeys(name);
        baseFunc.findElement(SEARCH_BTN).click();

    }

    public void findInSearchDropdown(String name){
        WebElement searchInput = baseFunc.findElement(MAIN_SEARCH_INPUT);
        searchInput.clear();
        searchInput.sendKeys(name);
        baseFunc.findElement(SUGGEST_BTN).click();
    }

    public void findItemFromOffers(int number) {
        baseFunc.waitForElements(YOUR_CHOICE);
        List<WebElement> offers = baseFunc.findElements(YOUR_CHOICE);

        if (number <= offers.size() && number >= 0) {
            baseFunc.moveToElement(YOUR_CHOICE);
            baseFunc.wait.until(ExpectedConditions.elementToBeClickable(offers.get(number)));
            baseFunc.click(offers.get(number));
        } else {
            System.out.println("Not available");
            baseFunc.closeBrowser();
            System.exit(1);
        }
    }
}
