package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private final By TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By HOME_PAGE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By ARTICLES = By.tagName("article");
    private BaseFunc baseFunc;

    public HomePage(BaseFunc baseFunc){
        this.baseFunc = baseFunc;
    }
    public String getTitleById(int id) {
        List<WebElement> titles = baseFunc.findElements(TITLE);
        return titles.get(id - 1).getText();
    }

    public void goToArticleById(int id){
        List<WebElement> titles = baseFunc.findElements(TITLE);
        baseFunc.click(titles.get(id - 1));
    }

    public int getCommentCount(){
        List<WebElement> titles = baseFunc.findElements(ARTICLES);
        int homePageCommentCount;
        int myArticleNum = 1;
        if (!titles.get(myArticleNum).findElements(HOME_PAGE_COMMENT_COUNT).isEmpty()){
            homePageCommentCount = Integer.parseInt(titles.get(myArticleNum).findElement(HOME_PAGE_COMMENT_COUNT).getText().substring(1,titles.get(myArticleNum).findElement(HOME_PAGE_COMMENT_COUNT).getText().length()-1));
        } else{
            homePageCommentCount = 0;
        }
        return homePageCommentCount;
    }
    }

