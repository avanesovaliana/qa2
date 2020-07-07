package pagesKsenukai;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage {


    private final BaseFunc baseFunc;
    private final By ITEM_PRICE = By.xpath(".//p[contains(@class,'detailed-cart-item__price')]");
    private final By CART_ITEM_NAME = By.xpath(".//a[contains(@class,'detailed-cart-item__name-link')]");
    private final By CART_ITEM_SUM = By.id("cart-full-total-price");
    private final By CART_ITEM_QUANTITY = By.xpath(".//div[contains(@class, 'detailed-cart-item__form-item')]/input");
    private final By CART_ITEM_LIST = By.xpath(".//div[contains(@class,'detailed-cart-item')][@data-price]");

    public CartPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getCartItemName() {
        return baseFunc.findElement(CART_ITEM_NAME).getText();
    }

    public int getCartItemQuantity() {
        return Integer.parseInt(baseFunc.findElement(CART_ITEM_QUANTITY).getAttribute("data-original-quantity"));
    }

    public double getCartItemPrice() {
        baseFunc.waitForElements(ITEM_PRICE);
        return baseFunc.getDoubleValue(ITEM_PRICE);
    }

    public double getCartItemSum() {
        baseFunc.waitForElements(CART_ITEM_SUM);
        return baseFunc.getDoubleValue(CART_ITEM_SUM);
    }

    public double getItemPriceInList(int itemIndex) {
        baseFunc.moveToElement(CART_ITEM_LIST);
        List<WebElement> prices = baseFunc.findElements(CART_ITEM_LIST);
        return Double.parseDouble(prices.get(itemIndex).findElement(ITEM_PRICE).getText().replace(",", ".")
                .substring(0, (baseFunc.findElement(CART_ITEM_SUM).getText()).replace(",", ".").lastIndexOf(' ')));
    }
}
