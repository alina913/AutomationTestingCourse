package lib.ui;

import io.appium.java_client.AppiumDriver;

import java.time.Duration;

abstract public class NavigationUI extends MainPageObject {
    protected static String MY_LISTS_LINK,
            MY_LISTS_UNDER_PROMPT,
    EMPTY_SPACE_UNDER_PROMPT,
            CLOSE_BUTTON_FOR_PROMPT,
            DELETE_ARTICLE_BUTTON,
            LOGIN_BUTTON_ON_PROMPT;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickToMyLists() {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find navigation button to My lists",
                Duration.ofSeconds(5)
        );
    }

    public void skipPrompt() {
        this.waitForElementAndClick(
                LOGIN_BUTTON_ON_PROMPT,//MY_LISTS_UNDER_PROMPT, // CLOSE_BUTTON_ON_PROMPT EMPTY_SPACE_UNDER_PROMPT; MY_LISTS_UNDER_PROMPT
                "Cannot skip prompt",
                Duration.ofSeconds(10)
        );
        this.waitForElementAndClick(CLOSE_BUTTON_FOR_PROMPT, "Can't close extra login page", Duration.ofSeconds(15));
        this.waitForElementNotPresent(CLOSE_BUTTON_FOR_PROMPT,"Login page does not disappear", Duration.ofSeconds(15));
    }
}
