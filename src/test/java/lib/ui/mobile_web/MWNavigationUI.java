package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {

    static {
        MY_LISTS_LINK = "css:a[data-event-name='watchlist']";
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
        WATCH_STAR_LIST = "css:mw-ui-icon-minerva-unStar";
    }

    public MWNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}
