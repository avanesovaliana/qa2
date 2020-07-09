package pagesForKsenukai;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;

public class CartPage {

    public static BaseFunc baseFunc;

    private static By CART_PRODUCT_TOTAL_SUM = By.id("cart-full-total-price");
    private static By PRODUCT_COST = By.xpath(".//p[contains(@class,'detailed-cart-item__price')]");
    private static By PRODUCT_NAME = By.xpath(".//a[contains(@class,'detailed-cart-item__name-link')]");
    private final By CART_PRODUCT_COUNT = By.xpath(".//div[contains(@class, 'detailed-cart-item__form-item')]/input");


    public CartPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public static String getItemName() {
        return baseFunc.findElement(PRODUCT_NAME).getText();
    }

    public static double getItemCost() {
        baseFunc.waitForElements(PRODUCT_COST);
        return baseFunc.getValue(PRODUCT_COST);
    }

    public int getCartItemCount() {
        return Integer.parseInt(baseFunc.findElement(CART_PRODUCT_COUNT).getAttribute("data-original-quantity"));
    }

    public static double getCartItemTotalSum() {
        baseFunc.wait.until(ExpectedConditions.visibilityOfElementLocated(CART_PRODUCT_TOTAL_SUM));
        return baseFunc.getValue(CART_PRODUCT_TOTAL_SUM);
    }
}
