package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {

    private static final String
        LOGIN_BUTTON = "css:#p-personal li a",//"xpath://body/div/a[text()='Log in']",
        LOGIN_INPUT = "css:input[name='wpName']",
        PASSWORD_INPUT = "css:input[name='wpPassword']",
        SUBMIT_BUTTON = "css:button#wpLoginAttempt",
        MAIN_MENU_BUTTON = "css:#mw-mf-main-menu-button",
        SKIP_BUTTON = "css:div #mw-input-skipReset"; //"#p-navigation li a.mw-ui-icon-minerva-home";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickAuthButton(){
        this.waitForElementPresentBy(
                LOGIN_BUTTON,
                "Cannot find auth button",
                5
        );
        this.waitForElementAndClick(
                LOGIN_BUTTON,
                "Cannot find and click auth button",
                5
        );
    }

    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(
                LOGIN_INPUT,
                login,
                "Cannot find and put a login to the login input",
                5
        );
        this.waitForElementAndSendKeys(
                PASSWORD_INPUT,
                password,
                "Cannot find and put a password to the password input",
                5
        );
    }

    public void submitForm(){
        this.waitForElementAndClick(
                SUBMIT_BUTTON,
                "Cannot find and click submit auth button",
                5
        );
    }

    public void openMainMenu(){
        this.waitForElementAndClick(
                MAIN_MENU_BUTTON,
                "Cannot find and click main menu button",
                5
        );
    }

    public void clickSkipButton(){
        this.waitForElementAndClick(
                SKIP_BUTTON,
                "Cannot find and click skip button",
                5
        );
    }
}
