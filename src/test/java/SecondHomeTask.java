import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SecondHomeTask {

    private final By ARTICLE_TITLE = By.xpath("(.//h1[contains(@class, 'headline__title')])[2]");
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'd-inline')]");
    private final By ARTICLE_TITLE_COMMENT_ELEMENT = By.xpath("(.//a[contains(@class,'text-red-ribbon')])[1]");
    private final By ARTICLE_TITLE_COMMENT_PAGE = By.xpath("(.//h1[contains(@class, 'article-title')]//a[starts-with(a,'')])[2]");

    private final By MAIN_PAGE_COMMENT_COUNT = By.xpath("(.//a[contains(@class, 'comment-count')])[2]");
    private final By ARTICLE_COMMENT = By.xpath("(.//h1[contains(@class, 'headline__title')])[2]");
    private final By ARTICLE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'd-print-none')]");
    private final By ANONIM_COMMENT_COUNT = By.xpath("(.//span[contains(@class, 'type-cnt')])[1]");
    private final By REG_COMMENT_COUNT = By.xpath("(.//span[contains(@class, 'type-cnt')])[2]");

    @Test
    public void delfiArticleTest(){

        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://rus.delfi.lv");


        WebElement secondTitle = driver.findElement(ARTICLE_TITLE);

        String secondTitleText = secondTitle.getText().trim();

        secondTitle.click();

        WebElement articleTitle = driver.findElement(ARTICLE_PAGE_TITLE);

        String articleTitleText = articleTitle.getText().trim();

        WebElement articleTitleComment = driver.findElement(ARTICLE_TITLE_COMMENT_ELEMENT);

        articleTitleComment.click();

        WebElement articleTitleCommentPage = driver.findElement(ARTICLE_TITLE_COMMENT_PAGE);

        String articleTitleCommentPageText = articleTitleCommentPage.getText().trim();

        Assertions.assertEquals(secondTitleText, articleTitleText,"Wrong title text!");
        Assertions.assertEquals(secondTitleText, articleTitleCommentPageText,"Wrong title text!");

        driver.quit();
    }

    @Test
    public void delfiCommentsTest(){

        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://rus.delfi.lv");

        WebElement mainPageComment = driver.findElement(MAIN_PAGE_COMMENT_COUNT);

        String mainPageCommentText = mainPageComment.getText();

        int mainPageCommentCount = Integer.parseInt(mainPageCommentText.substring(1,mainPageCommentText.length()-1));

        driver.findElement(ARTICLE_COMMENT).click();

        WebElement articleComment = driver.findElement(ARTICLE_COMMENT_COUNT);

        String articleCommentText = articleComment.getText();

        int articleCommentCount = Integer.parseInt(articleCommentText.substring(1,articleCommentText.length()-1));

        driver.findElement(ARTICLE_COMMENT_COUNT).click();

        WebElement anonimComment = driver.findElement(ANONIM_COMMENT_COUNT);

        String anonimCommentCountText = anonimComment.getText();

        int anonimCommentCount = Integer.parseInt(anonimCommentCountText.substring(1,anonimCommentCountText.length()-1));

        WebElement regComment = driver.findElement(REG_COMMENT_COUNT);

        String regCommentCountText = regComment.getText();

        int regCommentCount = Integer.parseInt(regCommentCountText.substring(1,regCommentCountText.length()-1));

        Assertions.assertEquals(mainPageCommentCount,articleCommentCount, "Wrong comment count on article page!");
        Assertions.assertEquals(mainPageCommentCount,anonimCommentCount+regCommentCount, "Wrong count on comment page!");

        driver.quit();

    }
}
