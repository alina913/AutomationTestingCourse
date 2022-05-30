package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.time.Duration;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_CONTAINER = "xpath://*[@class='android.widget.TextView']",
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]",
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_TEXT_TPL = "xpath://*[contains(@text, '{TEXT}')]",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']",
        // SEARCH_RESULT_ELEMENT = "xpath://*[resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    //Templates methods
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getTextSearchElement(String substring){
        return SEARCH_RESULT_BY_TEXT_TPL.replace("{TEXT}", substring);
    }
    //Templates methods

    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", Duration.ofSeconds(5));
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find Search Cancel button", Duration.ofSeconds(5));
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search Cancel button is still present", Duration.ofSeconds(15));
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click on Cancel Search button", Duration.ofSeconds(15));
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot type into search field", Duration.ofSeconds(15));
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring "+ substring);
    }

    public void waitForSearchResultByText(String substring){
        String search_result_xpath = getTextSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with text "+ substring);
    }

    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring "+ substring, Duration.ofSeconds(10));
    }

    public int getAmoutOfFoundArticles(){
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                Duration.ofSeconds(25)
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", Duration.ofSeconds(15));
    }

    public void assertThereIsNoResultsOfSearch(){
        this.assertElementNotPresent
                (
                        SEARCH_RESULT_ELEMENT,
                        "We've found some results, supposed not to found"
                );
    }

    public void assertElementHasText(String text_expected, String error_message) {
        this.waitForElementPresent(SEARCH_CONTAINER, "Cannot find search input");
        String text_actual = this.waitForElementAndGetAttribute(SEARCH_CONTAINER, "text", "can't get text", Duration.ofSeconds(15));
        this.assertElementHasText(text_actual, text_expected, error_message);
    }
}
