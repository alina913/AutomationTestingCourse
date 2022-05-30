package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static org.junit.Assert.*;

public class ArticlePageObject extends MainPageObject{
    private static final String
        TITLE = "id:org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"More options\"]",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
        MY_LIST = "xpath://*[@text='Learning programming']",
        ARTICLE_TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        //CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc=\"Navigate up\"]";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(TITLE, "Cannot find article with title on page", Duration.ofSeconds(15));
    }

    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of the article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                Duration.ofSeconds(25)
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                Duration.ofSeconds(5)
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find Got it tip overlay",
                Duration.ofSeconds(5)
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                Duration.ofSeconds(5)
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into article folder input",
                Duration.ofSeconds(5)
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find OK button when adding article folder",
                Duration.ofSeconds(5)
        );
    }

    public void addSecondArticleToMyList(){
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                Duration.ofSeconds(25)
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                Duration.ofSeconds(5)
        );

        this.waitForElementAndClick(
                MY_LIST,
                "Cannot choose reading list",
                Duration.ofSeconds(5)
        );
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                Duration.ofSeconds(15)
        );
    }

    public void assertArticleTitle(String article_title2){
        String article_title = this.getArticleTitle();
        assertEquals("Wrong article", article_title2, article_title);
    }

    public void assertArticle(){
        this.assertElementPresent(
                ARTICLE_TITLE,
                "(no title)");
    }
}
