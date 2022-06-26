package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TMP = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
        ARTICLE_BY_TITLE_IN_SAVED = "xpath://XCUIElementTypeStaticText[contains(@name, '{TITLE}')]";
        DELETE_ARTICLE_BUTTON = "xpath://XCUIElementTypeButton[@name='swipe action delete']";
        EDIT_BUTTON = "xpath://XCUIElementTypeButton[@name='Edit']";
        FIRST_ARTICLE_IN_EDIT_MODE = "xpath://XCUIElementTypeStaticText[@name='Java (programming language)']";
        UNSAVE_BUTTON = "xpath://XCUIElementTypeButton[@name='Unsave']";
    }

    public iOSMyListsPageObject(AppiumDriver driver){
        super(driver);
    }
}
