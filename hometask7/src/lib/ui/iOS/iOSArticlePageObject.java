package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        MY_LIST = "xpath://*[@text='Learning programming']";
        ARTICLE_TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']";
        //CLOSE_ARTICLE_BUTTON = "id:Back";
        CLOSE_ARTICLE_BUTTON = "xpath://XCUIElementTypeButton[@name='Search']";
        SECOND_ARTICLE = "xpath://XCUIElementTypeImage[@elementId='49010000-0000-0000-A50B-000000000000']";
    }

    public iOSArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
