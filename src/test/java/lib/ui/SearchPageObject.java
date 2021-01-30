package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_CANCEL_BUTTON,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_LIST,
        SEARCH_ARTICLE_NAME,
        SEARCH_EMPTY_RESULT_ELEMENT,
        SEARCH_WATCH_STAR;

    public SearchPageObject(RemoteWebDriver driver){
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getTitleName(String article_name){
        return SEARCH_ARTICLE_NAME.replace("{ARTICLENAME}", article_name);
    }

    private static String getArticleWatchStar(String article_name){
        return SEARCH_WATCH_STAR.replace("{ARTICLENAME}", article_name);
    }
    /*TEMPLATES METHODS*/

    @Step("Init search input")
    public void initSearchInput(){
        screenshot(this.takeScreenshot("search_init"));
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element",
                10);
        this.waitForElementPresentBy(SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking search init element",
                10);
    }

    @Step("Wait for cancel button to appear")
    public void waitForCancelButtonToAppear(){
        this.waitForElementPresentBy(SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button",
                5);
    }

    @Step("Wait for element by title and description")
    public void waitForElementByTitleAndDescription(String title, String description){
        this.waitForElementPresentBy(getTitleName(title),
                "Cannot find title article " + title,
                5);
        this.waitForElementPresentBy(getResultSearchElement(description),
                "Cannot find title description " + description,
                5);
    }

    @Step("Set watch star for aticle")
    public void setWatchStarForArticle(String article_name){
        this.waitForElementPresentBy(getTitleName(article_name),
                "Cannot find title article " + article_name,
                5);
    }

    @Step("Wait element list")
    public void waitElementList(){
        this.waitForElementPresentBy(SEARCH_LIST,
                "Cannot find list item",
                5);
    }

    @Step("Wait for cancel button to disappear")
    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present",
                5);
    }

    @Step("Click cancel search")
    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present",
                5);
    }

    @Step("Typing '{search_line}' text to search line")
    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(SEARCH_INPUT,
                search_line,
                "Cannot find and click search chancel button",
                5);
    }

    @Step("Wait for search result")
    public void waitForSearchResult(String substring){
        String search_result_xpatch = getResultSearchElement(substring);
        this.waitForElementPresentBy(search_result_xpatch,
                "Cannot find search result with substring " + substring,
                5);
    }

    @Step("Click by article with substring")
    public void clickByArticleWithSubstring(String substring){
        String search_result_xpatch = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpatch,
                "Cannot find and click search result with substring " + substring,
                5);
    }

    @Step("Wait search element and send clear")
    public void waitSearchElementAndSendClear(){
        this.waitForElementAndSendClear(SEARCH_INIT_ELEMENT,
                "Cannot find search input",
                5);
    }

    @Step("Wait search invisibility element")
    public void waitSearchInvisibilityElement(){
        this.waitInvisibilityOfElementBy(SEARCH_LIST,
                "Element find",
                5);
    }
}
