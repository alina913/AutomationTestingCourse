package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static org.junit.Assert.*;

abstract public class ArticlePageObject extends MainPageObject{
    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        MY_LIST,
        ARTICLE_TITLE,
        CLOSE_ARTICLE_BUTTON,
        SECOND_ARTICLE;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(TITLE, "Cannot find article with title on page", Duration.ofSeconds(15));
    }

    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("label");
        }
    }

    public void swipeToFooter(){
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    20
            );
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40);
        }
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

    public void addArticlesToMySaved(){
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", Duration.ofSeconds(5));
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

    public void compareImages(String firstImageAttribute, String remainingImageAttribute){
        assertFalse
                (
                        "First article remained",
                        firstImageAttribute.equals(remainingImageAttribute)
                );
    }

    public void assertArticle(){
        this.assertElementPresent(
                ARTICLE_TITLE,
                "(no title)");
    }

    public void checkSecondArticlePresented(){
        this.waitForElementPresent(SECOND_ARTICLE, "second article isn't presented", Duration.ofSeconds(15));
    }
}
