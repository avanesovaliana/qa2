package pagesForKsenukai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage {

    public static BaseFunc baseFunc;
    private final Logger LOGGER = LogManager.getLogger(ProductPage.class);

    private final By ITEM = By.xpath(".//div[contains(@class,'new-product-item')]");
    private static By GO_TO_CART_BTN = By.xpath(".//a[contains(.,'Pārlūkot pirkumu grozu')]");
    private final By ADD_TO_CART_BTN = By.id("add_to_cart_btn");
    private final By GO_TO_HOMEPAGE = By.xpath(".//a[contains(@class,'main-logo')]");
    private final By CONTINUE_PURCHASE_BTN = By.id("continue_shopping");

    private static By ITEM_PRICE = By.xpath(".//div[contains(@class,'product-price-details__block')]/span/span");
    private final By ITEM_NAME = By.xpath(".//div[contains(@class,'product-righter google-rich-snippet')]/h1");
    private final By ITEM_COUNT = By.id("product_quantity");
    private final By NO_ONLINE_PRODUCT_NOTIFICATION = By.xpath(".//div[contains(@class,'no-online-product-notification')]");


    public ProductPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void selectCount(String prodCount) {
        baseFunc.itemSelection(ITEM);
        if (baseFunc.elementAvailability(NO_ONLINE_PRODUCT_NOTIFICATION)) {
            LOGGER.info("Item not available");
        } else {
            WebElement productCount = baseFunc.findElement(ITEM_COUNT);
            productCount.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), prodCount);
        }
    }

    public void selectCounts(String prodCount) {

        if (baseFunc.elementAvailability(NO_ONLINE_PRODUCT_NOTIFICATION)) {
            LOGGER.info("Item not available");
        } else {
            WebElement productCount = baseFunc.findElement(ITEM_COUNT);
            productCount.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), prodCount);
        }
    }

    public void goToHomePage() {
        baseFunc.itemSelection(GO_TO_HOMEPAGE);
    }

    public static void goToCart() {
        baseFunc.wait.until(ExpectedConditions.elementToBeClickable(baseFunc.findElement(GO_TO_CART_BTN))).click();
    }

    public void addToCart() {
        if (baseFunc.elementAvailability(NO_ONLINE_PRODUCT_NOTIFICATION)) {
            LOGGER.info("Item not available");
        } else {
            baseFunc.itemSelection(ADD_TO_CART_BTN);
        }
    }

    public void continuePurchase() {
        baseFunc.itemSelection(CONTINUE_PURCHASE_BTN);
    }

    public String getItemName() {
        return baseFunc.findElement(ITEM_NAME).getText();
    }

    public static double getItemPrice() {
        return Double.parseDouble((baseFunc.findElements(ITEM_PRICE).get(0).getText()).replace(",", "."));
    }

}
