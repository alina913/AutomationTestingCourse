package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.time.Duration;

public class NavigationUI extends MainPageObject {

    public static final String
        MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc=\"My lists\"]/android.widget.ImageView";
        //MY_LIST_LINK = "xpath://android.widget.FrameLayout[@content_desc='My lists']";

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
}
