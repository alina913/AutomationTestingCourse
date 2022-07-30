package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class iOSNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "id:Saved";
        MY_LISTS_UNDER_PROMPT = "xpath://XCUIElementTypeButton[@name='Saved']";
        EMPTY_SPACE_UNDER_PROMPT = "xpath://XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]";
        LOGIN_BUTTON_ON_PROMPT = "xpath://XCUIElementTypeButton[@name='Log in to sync your saved articles']";
        CLOSE_BUTTON_FOR_PROMPT = "xpath://XCUIElementTypeButton[@name='Close']";
    }

    public iOSNavigationUI (AppiumDriver driver) {
        super(driver);
    }
}
