package pageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class TitleTests {
    private BaseFunc baseFunc = new BaseFunc(); //sozdali kopiju objekta base func
    private final Logger LOGGER = LogManager.getLogger(TitleTests.class);
    private final By ARTICLES = By.tagName("article");

    @Test
    public void titleCheck(){
        baseFunc.openHomePage();

        HomePage homePage = new HomePage(baseFunc); //budem rabotatj s funkcionalom glavnoj stranici
        String homePageTitle = homePage.getTitleById(2); //berem vtoruju statju
        LOGGER.info("Main page article text: " + homePageTitle);
        homePage.goToArticleById(2);//perehodim na vtoruju statju

        ArticlePage articlePage = new ArticlePage(baseFunc);
        String articlePageTitle = articlePage.getTitle();
        LOGGER.info("Article page: " + articlePageTitle);
        articlePage.goToCommentPage();

        CommentPage commentPage = new CommentPage(baseFunc);
        String commentPageTitle = commentPage.getTitle();
        LOGGER.info("Comment page article title: " + commentPageTitle);

        Assertions.assertEquals(homePageTitle, articlePageTitle,"Wrong title text!");
        Assertions.assertEquals(homePageTitle,commentPageTitle,"Wrong title text!");

        baseFunc.closeBrowser();

    }

    @Test
    public void commentCheck(){
        baseFunc.openHomePage();

        HomePage homePage = new HomePage(baseFunc);
        String homePageTitle = homePage.getTitleById(2);
        int homePageCommentCount = homePage.getCommentCount();
        LOGGER.info("Main page article text: " + homePageTitle);
        LOGGER.info(homePageCommentCount);
        //homePage.goToArticleById(2);

        /*ArticlePage articlePage = new ArticlePage(baseFunc);
        String articlePageTitle = articlePage.getTitle();
        LOGGER.info("Article page: " + articlePageTitle);
        articlePage.goToCommentPage();

        CommentPage commentPage = new CommentPage(baseFunc);
        String anonimCommentCount = commentPage.getAnonimCommentCount();
        LOGGER.info("Anonim comment count: " + anonimCommentCount);

        String regCommentCount = commentPage.getRegCommentCount();
        LOGGER.info("Reg comment count: " + regCommentCount);

        LOGGER.info("Comment page total comment count: " + (regCommentCount + anonimCommentCount)); */

    }
}
