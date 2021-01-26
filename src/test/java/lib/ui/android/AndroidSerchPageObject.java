package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSerchPageObject extends SearchPageObject {
   static {
               SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
               SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]";
               SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
               SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[contains(@text, {SUBSTRING}')]"; //"xpath://*[@text='{SUBSTRING}']"
               SEARCH_LIST = "id:org.wikipedia:id/page_list_item_title";
               SEARCH_ARTICLE_NAME = "xpath://*[@text='{ARTICLENAME}']";
   }

    public AndroidSerchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
