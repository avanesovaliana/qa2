package pageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CommentPage {
    private final By TITLE = By.xpath("(.//h1[contains(@class, 'article-title')])[2]");
    private final By ANONIM_COMMENT_COUNT = By.xpath("(.//span[contains(@class, 'type-cnt')])[1]");
    private final By REG_COMMENT_COUNT = By.xpath("(.//span[contains(@class, 'type-cnt')])[2]");
    private BaseFunc baseFunc;
    private final Logger LOGGER = LogManager.getLogger(CommentPage.class);

    public CommentPage(BaseFunc baseFunc){
        this.baseFunc = baseFunc;
    }

    public String getTitle(){
        return baseFunc.findElement(TITLE).getText();
    }

    public String getAnonimCommentCount(){
        WebElement anonimCommentCount = baseFunc.findElement(ANONIM_COMMENT_COUNT);
        return anonimCommentCount.getText();

    }

    public String getRegCommentCount(){
        WebElement regCommentCount = baseFunc.findElement(REG_COMMENT_COUNT);
        return regCommentCount.getText();
    }


}
