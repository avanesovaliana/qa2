package pagesKsenukai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private BaseFunc baseFunc = new BaseFunc();
    private HomePage homePage = new HomePage(baseFunc);
    private ItemPage itemPage = new ItemPage(baseFunc);
    private CartPage cartPage = new CartPage(baseFunc);

    private final Logger LOGGER = LogManager.getLogger(ProductTest.class);

    private String itemName = "Thrustmaster Race Gear Sparco Mod 4060131";
    private int itemCount = 2;
    private int itemFirst = 0;
    private int itemSecond = 3;

    @Test
    public void firstTask(){
        baseFunc.openHomePage();
        LOGGER.info("Opening k-senukai home page");
        baseFunc.cookieWindow();
        homePage.findInSearchInput(itemName);
        LOGGER.info("Finding item: " + itemName);
        itemPage.itemNumberSelectionFirst(itemCount);
        LOGGER.info("Item quantity selection: " + itemCount);
        double itemPrice = itemPage.getItemPrice();
        String itemName = itemPage.getItemName();
        LOGGER.info("Item info in item page --> Name: '" + itemName + "'. Price: " + itemPrice);
        itemPage.addToCart();
        LOGGER.info("Item added to cart");
        itemPage.goToCart();
        LOGGER.info("Open cart");
        LOGGER.info("Item info in cart page --> Name: '" + cartPage.getCartItemName() + "'. Price: " + cartPage.getCartItemPrice());
        LOGGER.info("Item total sum in item page: " + cartPage.getCartItemSum());
        LOGGER.info("Selected item quantity in item page: " + itemCount + ", in cart: " + cartPage.getCartItemQuantity());
        assertEquals(itemName, cartPage.getCartItemName(), "Wrong item name");
        assertEquals(itemCount, cartPage.getCartItemQuantity(), "Wrong item quantity");
        assertEquals(cartPage.getCartItemSum(),itemCount * itemPrice, "Wrong item total sum");
        baseFunc.closeBrowser();

    }

    @Test
    public void firstFirstTask(){
        baseFunc.openHomePage();
        LOGGER.info("Opening k-senukai home page");
        baseFunc.cookieWindow();
        homePage.findInSearchDropdown(itemName);
        LOGGER.info("Finding item: " + itemName);
        itemPage.itemNumberSelectionFirstFirst(itemCount);
        LOGGER.info("Item quantity selection: " + itemCount);
        double itemPrice = itemPage.getItemPrice();
        String itemName = itemPage.getItemName();
        LOGGER.info("Item info in item page --> Name: '" + itemName + "'. Price: " + itemPrice);
        itemPage.addToCart();
        LOGGER.info("Item added to cart");
        itemPage.goToCart();
        LOGGER.info("Open cart");
        LOGGER.info("Item info in cart page --> Name: '" + cartPage.getCartItemName() + "'. Price: " + cartPage.getCartItemPrice());
        LOGGER.info("Item total sum in item page: " + cartPage.getCartItemSum());
        LOGGER.info("Selected item quantity in item page: " + itemCount + ", in cart: " + cartPage.getCartItemQuantity());
        assertEquals(itemName, cartPage.getCartItemName(), "Wrong item name");
        assertEquals(itemCount, cartPage.getCartItemQuantity(), "Wrong item quantity");
        assertEquals(cartPage.getCartItemSum(),itemCount * itemPrice, "Wrong item total sum");
        baseFunc.closeBrowser();
    }

    @Test
    public void secondTask(){
        baseFunc.openHomePage();
        LOGGER.info("Opening k-senukai home page");
        baseFunc.cookieWindow();
        homePage.findItemFromOffers(itemFirst);
        LOGGER.info("First choice from offers");
        double firstItemPrice = itemPage.getItemPrice();
        LOGGER.info("Saving first item price");
        itemPage.addToCart();
        LOGGER.info("First item added to cart");
        itemPage.continuePurchase();
        LOGGER.info("Click to continue shopping");
        itemPage.goToHomePage();
        LOGGER.info("Click on the main logo to return to the main page");
        homePage.findItemFromOffers(itemSecond);
        LOGGER.info("Second choice from offers");
        double secondItemPrice = itemPage.getItemPrice();
        LOGGER.info("Saving second item price");
        itemPage.addToCart();
        LOGGER.info("Second item added to cart");
        itemPage.goToCart();
        LOGGER.info("Open cart");
        LOGGER.info("First item price " + firstItemPrice + ". In cart price " + cartPage.getItemPriceInList(0));
        LOGGER.info("Second item price " + secondItemPrice + ". In cart price " + cartPage.getItemPriceInList(1));
        LOGGER.info("Total price " + (cartPage.getItemPriceInList(0) + cartPage.getItemPriceInList(1)) + ". In cart total price " + cartPage.getCartItemSum());
        assertEquals(firstItemPrice, cartPage.getItemPriceInList(0), "Wrong first item price");
        assertEquals(secondItemPrice, cartPage.getItemPriceInList(1), "Wrong second item price");
        assertEquals(cartPage.getCartItemSum(), cartPage.getItemPriceInList(0) + cartPage.getItemPriceInList(1), "Wrong item total price");
        baseFunc.closeBrowser();
    }
}
