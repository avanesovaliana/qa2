package pageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TitleTests {
    private BaseFunc baseFunc = new BaseFunc();
    private final Logger LOGGER = LogManager.getLogger(TitleTests.class);


    @Test
    public void titleCheck(){
        baseFunc.openHomePage();

        HomePage homePage = new HomePage(baseFunc);
        String homePageTitle = homePage.getTitleById(2);
        LOGGER.info("Main page article text: " + homePageTitle);
        homePage.goToArticleById(2);

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
        homePage.goToArticleById(2);

        ArticlePage articlePage = new ArticlePage(baseFunc);
        int articlePageCommentCount = articlePage.getCommentCountArticlePage();
        LOGGER.info("Article comment count: " + articlePageCommentCount);
        articlePage.goToCommentPage();

        CommentPage commentPage = new CommentPage(baseFunc);
        int anonimCommentCount = commentPage.getAnonimCommentCount();
        LOGGER.info("Anonymous comment count: " + anonimCommentCount);

        int regCommentCount = commentPage.getRegCommentCount();
        LOGGER.info("Registration comment count: " + regCommentCount);

        LOGGER.info("Comment page total comment count: " + (regCommentCount + anonimCommentCount));

        Assertions.assertEquals(homePageCommentCount,articlePageCommentCount, "Wrong comment count on article page!");
        Assertions.assertEquals(homePageCommentCount,anonimCommentCount+regCommentCount, "Wrong count on comment page!");

        baseFunc.closeBrowser();
    }
}
