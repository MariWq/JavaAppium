package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListPageObject extends MyListPageObject {

    static {
      //  FOLDER_BY_NAME_TPL = "xpath://android.widget.TextView[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE = "xpath://*[@text='{TITLE}']";}

    public IOSMyListPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
