import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SecondHomeTask {

    private final By ARTICLE_TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'd-inline')]");
    private final By ARTICLE_TITLE_COMMENT_ELEMENT = By.xpath("(.//a[contains(@class,'text-red-ribbon')])[1]");
    private final By ARTICLE_TITLE_COMMENT_PAGE = By.xpath("(.//h1[contains(@class, 'article-title')])[2]");
    private final Logger LOGGER = LogManager.getLogger(MyTest.class);
    private WebDriver driver;

    private final By MAIN_PAGE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By ARTICLE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'd-print-none')]");
    private final By ANONIM_COMMENT_COUNT = By.xpath("(.//span[contains(@class, 'type-cnt')])[1]");
    private final By REG_COMMENT_COUNT = By.xpath("(.//span[contains(@class, 'type-cnt')])[2]");
    private final By ARTICLES = By.tagName("article");
    private final By COMMENT_BUTTON = By.xpath(".//a[contains(@class, 'btn-comments')]");

    @Test
    public void delfiArticleTest(){

        LOGGER.info("Setting up driver path");
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        LOGGER.info("Opening browser");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://rus.delfi.lv");
        LOGGER.info("Opening Delfi home page");

        List<WebElement> articles = driver.findElements(ARTICLE_TITLE);
        LOGGER.info("Creating article list");

        String mainPageArticle = articles.get(1).getText();
        LOGGER.info("Main page article text: " + mainPageArticle);
        articles.get(1).click();
        WebElement articleTitle = driver.findElement(ARTICLE_PAGE_TITLE);

        String articleTitleText = articleTitle.getText().trim();
        LOGGER.info("Article page: " + articleTitleText);

        WebElement articleTitleComment = driver.findElement(ARTICLE_TITLE_COMMENT_ELEMENT);

        articleTitleComment.click();

        WebElement articleTitleCommentPage = driver.findElement(ARTICLE_TITLE_COMMENT_PAGE);

        String articleTitleCommentPageText = articleTitleCommentPage.getText().trim();
        LOGGER.info("Comment page article title: " + articleTitleCommentPageText);

        Assertions.assertEquals(mainPageArticle, articleTitleText,"Wrong title text!");
        Assertions.assertEquals(mainPageArticle, articleTitleCommentPageText,"Wrong title text!");
    }

    @Test
    public void delfiCommentsTest(){

        LOGGER.info("Setting up driver path");
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        LOGGER.info("Opening browser");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://rus.delfi.lv");
        LOGGER.info("Opening Delfi home page");

        List<WebElement> articles = driver.findElements(ARTICLES);
        LOGGER.info("Creating article list");

        int mainPageCommentCount, articleCommentCount;
        int myArticleNum=1;
        LOGGER.info(articles.get(myArticleNum).getText());
        if(!articles.get(myArticleNum).findElements(MAIN_PAGE_COMMENT_COUNT).isEmpty()){
            mainPageCommentCount = Integer.parseInt(articles.get(myArticleNum).findElement(MAIN_PAGE_COMMENT_COUNT).getText().substring(1,articles.get(myArticleNum).findElement(MAIN_PAGE_COMMENT_COUNT).getText().length()-1));
        } else{
            mainPageCommentCount = 0;
        }

        LOGGER.info(mainPageCommentCount);

        articles.get(myArticleNum).findElement(ARTICLE_TITLE).click();

        if(!driver.findElements(ARTICLE_COMMENT_COUNT).isEmpty()){

            WebElement articleComment = driver.findElement(ARTICLE_COMMENT_COUNT);

            String articleCommentText = articleComment.getText();

            articleCommentCount = Integer.parseInt(articleCommentText.substring(1,articleCommentText.length()-1));

        } else{
            articleCommentCount = 0;
        }

        LOGGER.info("Article comment count: " + articleCommentCount );

        driver.findElement(COMMENT_BUTTON).click();

        WebElement anonimComment = driver.findElement(ANONIM_COMMENT_COUNT);

        String anonimCommentCountText = anonimComment.getText();

        int anonimCommentCount = Integer.parseInt(anonimCommentCountText.substring(1,anonimCommentCountText.length()-1));
        LOGGER.info("Anonim comment count: " + anonimCommentCount );

        WebElement regComment = driver.findElement(REG_COMMENT_COUNT);

        String regCommentCountText = regComment.getText();

        int regCommentCount = Integer.parseInt(regCommentCountText.substring(1,regCommentCountText.length()-1));
        LOGGER.info("Reg comment count: " + regCommentCount );

        LOGGER.info("Comment page total comment count: " + (regCommentCount + anonimCommentCount));

        Assertions.assertEquals(mainPageCommentCount,articleCommentCount, "Wrong comment count on article page!");
        Assertions.assertEquals(mainPageCommentCount,anonimCommentCount+regCommentCount, "Wrong count on comment page!");
    }

    @AfterEach
    public void closeBrowser(){

        driver.quit();
    }
}
