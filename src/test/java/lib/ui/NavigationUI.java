package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static String
        MY_LISTS_LINK,
        OPEN_NAVIGATION,
        WATCH_STAR_LIST;

    public NavigationUI(RemoteWebDriver driver){
        super(driver);
    }

    public void openNavigation(){
        if (Platform.getInstance().isIMw()){
            this.waitForElementAndClick(OPEN_NAVIGATION,
                    "Cannot find and click open navigation button",
                    5);
        }else{
            System.out.println("Method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());

        }
    }

    public void clickMyList(){
        if (Platform.getInstance().isIMw()) {
            this.tryClickElementWithFewAttemts(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        }
    }

    public void clickWatchList(){
        this.waitForElementAndClick(
                WATCH_STAR_LIST,
                "Cannot find navigation button WatchList",
                5
        );
    }
}
