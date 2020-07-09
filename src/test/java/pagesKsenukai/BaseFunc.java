package pagesKsenukai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseFunc {
    private final String HOME_PAGE_URL = "ksenukai.lv";
    private final By COOKIE_WINDOW_BTN = By.id("CybotCookiebotDialogBodyButtonAccept");
    public WebDriver driver;
    public WebDriverWait wait;
    private final Logger LOGGER = LogManager.getLogger(BaseFunc.class);

    public BaseFunc(){
        LOGGER.info("Setting up driver path");
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        LOGGER.info("Opening browser");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);
    }

    public void goToUrl(String url){
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        driver.get(url);
    }

    public void cookieWindow(){
        findElement(COOKIE_WINDOW_BTN).click();
    }

    public void openHomePage(){
        goToUrl(HOME_PAGE_URL);
        LOGGER.info("Opening Ksenukai home page");
    }

    public List<WebElement> findElements(By locator){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return driver.findElements(locator);
    }

    public WebElement findElement(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public void moveToElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);

    }

    public void click(WebElement webElement){
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.click();
    }

    public void waitForElements(By locator) {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public double getDoubleValue(By locator) {
        return Double.parseDouble((driver.findElement(locator).getText()).replace(",", ".")
                .substring(0, (driver.findElement(locator).getText()).replace(",", ".").lastIndexOf(' ')));
    }

    public void itemSelection(By locator) {
        waitForElements(locator);
        moveToElement(locator);
        waitForElements(locator);
        findElement(locator).click();
    }

    public void closeBrowser(){
        driver.quit();
    }
}
