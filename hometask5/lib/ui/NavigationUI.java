package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    public static final String
        MY_LISTS_LINK = "//android.widget.FrameLayout[@content-desc=\"My lists\"]/android.widget.ImageView";
        //MY_LIST_LINK = "//android.widget.FrameLayout[@content_desc='My lists']";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickToMyLists() {
        this.waitForElementAndClick(
                By.xpath(MY_LISTS_LINK),
                "Cannot find navigation button to My lists",
                5
        );
    }
}
