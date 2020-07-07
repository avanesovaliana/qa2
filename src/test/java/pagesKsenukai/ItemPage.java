package pagesKsenukai;

import org.openqa.selenium.By;
import org.openqa.selenium.*;

public class ItemPage {
    private BaseFunc baseFunc;
    private final By ITEM = By.xpath(".//div[contains(@class,'new-product-item')]");
    private final By ADD_TO_CART_BTN = By.xpath(".//button[contains(@name,'button')]");
    private final By GO_TO_CART_BTN = By.xpath(".//a[contains(.,'Pārlūkot pirkumu grozu')]");
    private final By GO_TO_HOMEPAGE = By.xpath(".//a[contains(@class,'main-logo')]");
    private final By CONTINUE_PURCHASE_BTN = By.id("continue_shopping");

    private final By ITEM_PRICE = By.xpath(".//div[contains(@class,'product-price-details__block')]/span/span");
    private final By ITEM_NAME = By.xpath(".//div[contains(@class,'product-righter google-rich-snippet')]/h1");
    private final By ITEM_QUANTITY = By.id("product_quantity");
    private final By INPUT_ITEM_QUANTITY = By.id("product_quantity");
    private final By NO_ONLINE_PRODUCT_NOTIFICATION = By.xpath(".//div[contains(@class,'no-online-product-notification')]");



    public ItemPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void itemNumberSelectionFirst(int itemCount) {
        baseFunc.itemSelection(ITEM);
        if(baseFunc.isElementPresent(NO_ONLINE_PRODUCT_NOTIFICATION)){
            System.out.println("Item is not available for purchase");
            baseFunc.closeBrowser();
            System.exit(1);
        }else {
            WebElement itemQuantity = baseFunc.findElement(INPUT_ITEM_QUANTITY);
            itemQuantity.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            itemQuantity.sendKeys(Integer.toString(itemCount));
        }
    }

    public void itemNumberSelectionFirstFirst(int itemCount) {

        if(baseFunc.isElementPresent(NO_ONLINE_PRODUCT_NOTIFICATION)){
            System.out.println("Item is not available");
            baseFunc.closeBrowser();
            System.exit(1);
        }else {
            WebElement itemQuantity = baseFunc.findElement(INPUT_ITEM_QUANTITY);
            itemQuantity.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            itemQuantity.sendKeys(Integer.toString(itemCount));
        }
    }

    public String getItemCount(){
        return baseFunc.findElement(ITEM_QUANTITY).getText();
    }

    public void addToCart() {
        if(baseFunc.isElementPresent(NO_ONLINE_PRODUCT_NOTIFICATION)){
            System.out.println("Selected item is not available");
            baseFunc.closeBrowser();
            System.exit(1);
        }else {
            baseFunc.itemSelection(ADD_TO_CART_BTN);
        }
    }

    public void continuePurchase() {
        baseFunc.itemSelection(CONTINUE_PURCHASE_BTN);
    }

    public void goToHomePage() {
        baseFunc.itemSelection(GO_TO_HOMEPAGE);
    }

    public void goToCart() {
        baseFunc.waitForElements(GO_TO_CART_BTN);
        baseFunc.findElement(GO_TO_CART_BTN).click();
    }

    public String getItemName() {
        return baseFunc.findElement(ITEM_NAME).getText();
    }

    public double getItemPrice() {
        baseFunc.waitForElements(ITEM_PRICE);
        return Double.parseDouble((baseFunc.findElements(ITEM_PRICE).get(0).getText()).replace(",", "."));
    }
    
}
