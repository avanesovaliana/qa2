package pageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticlePage {
    private final By TITLE = By.xpath(".//h1[contains(@class, 'd-inline')]");
    private final By ARTICLE_TITLE_COMMENT_ELEMENT = By.xpath("(.//a[contains(@class, 'text-red-ribbon' )])[1]");
    private final By ARTICLE_PAGE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'd-print-none')]");
    private BaseFunc baseFunc;
    private final Logger LOGGER = LogManager.getLogger(ArticlePage.class);

    public ArticlePage(BaseFunc baseFunc) { //sozdali konstruktor
        this.baseFunc = baseFunc;
    }

    public String getTitle(){
        return baseFunc.findElement(TITLE).getText();
    }

    public void goToCommentPage(){
        WebElement articleTitleComment = baseFunc.findElement(ARTICLE_TITLE_COMMENT_ELEMENT);
        baseFunc.click(articleTitleComment);
    }
}
