package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{SUBSTRING}')]";
        SEARCH_LIST = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
        SEARCH_ARTICLE_NAME = "//div[contains(text(), '{ARTICLENAME}')]";//"xpath://h3/strong[contains(text(),'{ARTICLENAME}')]";
        SEARCH_WATCH_STAR = "css:.results li[title='{ARTICLENAME}''] a.watch-this-article";
    }

    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
