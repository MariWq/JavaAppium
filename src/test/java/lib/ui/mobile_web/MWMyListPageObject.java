package lib.ui.mobile_web;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject {

    static {
        ARTICLE_BY_TITLE = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]";
        REMOVE_FROM_SAVE_BUTTON = "xpath://h3[contains(text(), '{TITLE}')]//../../div//../../a[contains(@class, 'mw-ui-icon-element')]";//"xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]//../../div[contains(@class, 'watched')]";
    }

    public MWMyListPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
