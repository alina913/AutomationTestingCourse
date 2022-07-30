package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

import java.time.Duration;

abstract public class MyListsPageObject extends MainPageObject {
    protected static String
        FOLDER_BY_NAME_TMP,
            ARTICLE_BY_TITLE_TMP,
            ARTICLE_BY_TITLE_IN_SAVED,
            DELETE_ARTICLE_BUTTON,
    EDIT_BUTTON,
    FIRST_ARTICLE_IN_EDIT_MODE,
    UNSAVE_BUTTON;

    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TMP.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TMP.replace("{TITLE}", article_title);
    }

    private static String getSavedArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_IN_SAVED.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder){
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                Duration.ofSeconds(15)
        );
    }

    public void openArticleFromListByTitle(String article_title){
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(
                article_xpath,
                "Cannot open article from list",
                Duration.ofSeconds(15)
        );
    }

    public void waitForArticleToAppearByTitle(String article_title){
        String article_xpath;
        if (Platform.getInstance().isAndroid()) {
            article_xpath = getFolderXpathByName(article_title);
        } else {
            article_xpath = getSavedArticleXpathByTitle(article_title);//"//XCUIElementTypeStaticText[@name='Java (programming language)']";
        }
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article with title " + article_title,
                Duration.ofSeconds(15)
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title){
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title,
                Duration.ofSeconds(15)
        );
    }

    public void swipeByArticleToDelete(String article_title){
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath;
        if (Platform.getInstance().isAndroid()) {
            article_xpath = getFolderXpathByName(article_title);
        } else
        {
            article_xpath = getSavedArticleXpathByTitle(article_title);
        }
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );
        if (Platform.getInstance().isIOS()){
            this.waitForElementPresent(DELETE_ARTICLE_BUTTON, "No delete button", Duration.ofSeconds(10));
            this.waitForElementAndClick(DELETE_ARTICLE_BUTTON, "Can't tap on delete button", Duration.ofSeconds(15));
           // this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find Save article button");
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void deleteFirstArticleFromEditMode(String article_title1){
        this.waitForElementAndClick(EDIT_BUTTON, "Can't click Edit button", Duration.ofSeconds(15));
        this.waitForElementPresent(FIRST_ARTICLE_IN_EDIT_MODE, "Don't see the first article", Duration.ofSeconds(15));
        this.waitForElementAndClick(FIRST_ARTICLE_IN_EDIT_MODE, "Can't choose first article", Duration.ofSeconds(15));
        this.waitForElementPresent(UNSAVE_BUTTON, "Don't see Unsave button", Duration.ofSeconds(15));
        this.waitForElementAndClick(UNSAVE_BUTTON, "Can't click Unsave button", Duration.ofSeconds(15));
    }
}
