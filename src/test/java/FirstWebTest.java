import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class FirstWebTest {
    private final By ARTICLE_TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]"); //eto konstanta,lokator ne menjaetsja
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'd-inline')]");

    @Test
    public void delfiTest(){
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe"); //dajem ponjatj nashemu kodu,gde hranitsja driver
        WebDriver driver = new ChromeDriver(); //sozdali kopiju i otkrivajet brauzer
        driver.manage().window().maximize();
        driver.get("http://rus.delfi.lv"); //perehodit na nuzhnij adres


        List<WebElement> articles = driver.findElements(ARTICLE_TITLE);

        for(int i = 0; i < articles.size(); i++){

            if(articles.get(i).getText().length() != 0) { //esli u statji kolichesto simvolov ne ravno 0
                System.out.println(i + ": " + articles.get(i).getText()); //to pechataem
            }
        }

        //Find 1st title element
        WebElement firstTitle = driver.findElement(ARTICLE_TITLE);

        //Get and save this element text
      //  String firstTitleText = firstTitle.getText();

        //Click on this element
      //  firstTitle.click();

        //Find article's title element
        WebElement articleTitle = driver.findElement(ARTICLE_PAGE_TITLE);

        //Get and save element's text
      //  String articleTitleText = articleTitle.getText();

        //Check
      //  Assertions.assertEquals(firstTitleText, articleTitleText, "Wrong article title!");

    }
}
