package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

import java.time.Duration;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
        SEARCH_CONTAINER,
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_CANCEL_BUTTON,
        SEARCH_RESULT_BY_TEXT_TPL,
            SEARCH_RESULT_BY_VALUE_TPL,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_ELEMENT,
            SEARCH_RESULT_ELEMENT_FOR_LIST_OF_ARTICLES,
        SEARCH_EMPTY_RESULT_ELEMENT;

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

    private static String getValueSearchElement(String substring){
        return SEARCH_RESULT_BY_VALUE_TPL.replace("{VALUE}", substring);
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

    public void waitForSearchResultByValue(String substring){
        String search_result_xpath = getValueSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with value "+ substring);
    }

    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring "+ substring, Duration.ofSeconds(10));
    }

    public int getAmoutOfFoundArticles(){
        String search_result_element;
        if (Platform.getInstance().isAndroid()){
            search_result_element = SEARCH_RESULT_ELEMENT;
        }
        else {
            search_result_element = SEARCH_RESULT_ELEMENT_FOR_LIST_OF_ARTICLES;
        }
        this.waitForElementPresent(
                search_result_element,
                "Cannot find anything by the request",
                Duration.ofSeconds(25)
        );
        return this.getAmountOfElements(search_result_element);
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
        String text_actual;
        if (Platform.getInstance().isAndroid()) {
        text_actual = this.waitForElementAndGetAttribute(SEARCH_CONTAINER, "text", "can't get text", Duration.ofSeconds(15));}
        else {
            text_actual = this.waitForElementAndGetAttribute(SEARCH_CONTAINER, "label", "can't get text", Duration.ofSeconds(15));}
        this.assertElementHasText(text_actual, text_expected, error_message);
    }
}
