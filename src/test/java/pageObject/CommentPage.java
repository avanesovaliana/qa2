package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CommentPage {
    private final By TITLE = By.xpath("(.//h1[contains(@class, 'article-title')])[2]");
    private final By ANONIM_COMMENT_COUNT = By.xpath("(.//span[contains(@class, 'type-cnt')])[1]");
    private final By REG_COMMENT_COUNT = By.xpath("(.//span[contains(@class, 'type-cnt')])[2]");
    private BaseFunc baseFunc;

    public CommentPage(BaseFunc baseFunc){
        this.baseFunc = baseFunc;
    }

    public String getTitle(){
        return baseFunc.findElement(TITLE).getText();
    }

    public int getAnonimCommentCount(){
        WebElement anonimComment = baseFunc.findElement(ANONIM_COMMENT_COUNT);
        String anonimCommentCountText = anonimComment.getText();
        int anonimCommentCount = Integer.parseInt(anonimCommentCountText.substring(1,anonimCommentCountText.length()-1));

        return anonimCommentCount;

    }

    public int getRegCommentCount(){
        WebElement regComment = baseFunc.findElement(REG_COMMENT_COUNT);
        String regCommentCountText = regComment.getText();
        int regCommentCount = Integer.parseInt(regCommentCountText.substring(1,regCommentCountText.length()-1));

        return regCommentCount;
    }
}
