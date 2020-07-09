package pagesForKsenukai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemsTests {

    private BaseFunc baseFunc = new BaseFunc();
    private HomePage homePage = new HomePage(baseFunc);
    private ProductPage productPage = new ProductPage(baseFunc);
    private CartPage cartPage = new CartPage(baseFunc);

    private final Logger LOGGER = LogManager.getLogger(ItemsTests.class);

    private final String HOME_PAGE_URL = "ksenukai.lv";
    private String itemName = "Thrustmaster Race Gear Sparco Mod 4060131";
    private String prodCount = "2";

    @Test
    public void checkItemFirstVariant() {
        baseFunc.goToUrl(HOME_PAGE_URL);
        LOGGER.info("Opening Ksenukai home page");
        baseFunc.cookieWindow();
        LOGGER.info("Click to coockie button");
        homePage.findItem(itemName);
        LOGGER.info("Desired item: " + itemName);
        productPage.selectCount(prodCount);
        LOGGER.info("Select quantity: " + prodCount);
        double itemPrice = ProductPage.getItemPrice();
        LOGGER.info("Item price: " + productPage.getItemPrice());
        String itemName = productPage.getItemName();
        productPage.addToCart();
        LOGGER.info("Add to cart item");
        productPage.goToCart();
        LOGGER.info("Go to cart");
        LOGGER.info("Item total sum: " + cartPage.getCartItemTotalSum());
        assertEquals(itemName, cartPage.getItemName(), "Wrong product name");
        assertEquals(itemPrice, cartPage.getItemCost(), "Wrong product cost");
        assertEquals((itemPrice * Integer.parseInt(prodCount)), cartPage.getCartItemTotalSum(), "Wrong total product cost");
        baseFunc.closeBrowser();
    }

    @Test
    public void checkItemSecondVariant(){
        baseFunc.goToUrl(HOME_PAGE_URL);
        LOGGER.info("Opening Ksenukai home page");
        baseFunc.cookieWindow();
        LOGGER.info("Click to coockie button");
        homePage.findItemInSearchDropdown(itemName);
        LOGGER.info("Desired item: " + itemName);
        LOGGER.info("Item price: " + productPage.getItemPrice());
        productPage.selectCounts(prodCount);
        LOGGER.info("Select quantity: " + prodCount);
        double itemPrice = productPage.getItemPrice();
        String itemName = productPage.getItemName();
        productPage.addToCart();
        LOGGER.info("Add to cart item");
        productPage.goToCart();
        LOGGER.info("Go to cart");
        LOGGER.info("Item total sum: " + cartPage.getCartItemTotalSum());
        assertEquals(itemName, cartPage.getItemName(), "Wrong product name");
        assertEquals(itemName, cartPage.getItemName(), "Wrong product name");
        assertEquals((itemPrice * Integer.parseInt(prodCount)), cartPage.getCartItemTotalSum(), "Wrong total product cost");
        baseFunc.closeBrowser();

    }

    @Test
    public void offerItemsCheck() {
        baseFunc.goToUrl(HOME_PAGE_URL);
        LOGGER.info("Open Ksenukai home page");
        baseFunc.cookieWindow();
        LOGGER.info("Click to coockie button");
        homePage.findOfferedItems(1);
        LOGGER.info("Search offered item");
        double firstProductPrice = ProductPage.getItemPrice();
        LOGGER.info("Get item price");
        LOGGER.info("Item price was " + firstProductPrice);
        productPage.addToCart();
        LOGGER.info("Add to cart item");
        productPage.continuePurchase();
        LOGGER.info("Click to continue shopping");
        baseFunc.goToHomePage();
        LOGGER.info("Go to home page");
        homePage.findOfferedItems(2);
        LOGGER.info("Search offered item");
        double secondProductPrice = ProductPage.getItemPrice();
        LOGGER.info("Get item price");
        LOGGER.info("Item price was " + secondProductPrice);
        productPage.addToCart();
        LOGGER.info("Add to cart item");
        productPage.goToCart();
        LOGGER.info("Go to cart");
        LOGGER.info("Total price: " + (firstProductPrice + secondProductPrice) + ". Total price in cart: " + cartPage.getCartItemTotalSum());
        assertEquals(cartPage.getCartItemTotalSum(), firstProductPrice + secondProductPrice, "Wrong item total price");
        baseFunc.closeBrowser();
    }
}
