package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticlePage {
    private final By TITLE = By.xpath(".//h1[contains(@class, 'd-inline')]");
    private final By ARTICLE_TITLE_COMMENT_ELEMENT = By.xpath("(.//a[contains(@class, 'text-red-ribbon' )])[1]");
    private final By ARTICLE_PAGE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'd-print-none')]");
    private final By ARTICLES = By.tagName("article");
    private BaseFunc baseFunc;

    public ArticlePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getTitle(){
        return baseFunc.findElement(TITLE).getText();
    }

    public void goToCommentPage(){
        WebElement articleTitleComment = baseFunc.findElement(ARTICLE_TITLE_COMMENT_ELEMENT);
        baseFunc.click(articleTitleComment);
    }

    public int getCommentCountArticlePage(){
        List<WebElement> titles = baseFunc.findElements(ARTICLES);
        int articleCommentCount;

        if(baseFunc.findElement(ARTICLE_PAGE_COMMENT_COUNT).isEnabled()){

            WebElement articleComment = baseFunc.findElement(ARTICLE_PAGE_COMMENT_COUNT);
            String articleCommentText = articleComment.getText();
            articleCommentCount = Integer.parseInt(articleCommentText.substring(1,articleCommentText.length()-1));
        } else{
            articleCommentCount = 0;
        }
        return articleCommentCount;
    }
}
