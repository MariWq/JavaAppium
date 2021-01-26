package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.*;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lib.Platform;

import javax.swing.plaf.metal.MetalBorders;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver){
        this.driver = driver;
    }

    public Boolean waitForElementNotPresent(String locator, String error_message, long timeOutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementPresentBy(String locator, String error_message, long timeOutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public boolean waitInvisibilityOfElementBy(String locator, String error_message, long timeOutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeOutInSeconds){
        WebElement element = waitForElementPresentBy(locator, error_message, timeOutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator,  String value, String error_message, long timeOutInSeconds){
        WebElement element = waitForElementPresentBy(locator, error_message, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeOutInSeconds){
        WebElement element = waitForElementPresentBy(locator, error_message, timeOutInSeconds);
        element.clear();
        return element;
    }

    public WebElement waitForElementAndSendClear(String locator, String error_message, long timeOutInSeconds){
        WebElement element = waitForElementPresentBy(locator, error_message, timeOutInSeconds);
        element.clear();
        return element;
    }

    public void assertElementHasText(String locator, String value, String error_message){
        WebElement element = waitForElementPresentBy(locator, error_message, 5);
        Assert.assertEquals(error_message, value, element.getAttribute("text"));
    }

    public boolean waitElementWithTextBy(String locator, String value, String error_message, long timeOutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(by, value));
    }

    public void swipeUp(WaitOptions timeOfSwipe){

        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);

            action
                    .press(PointOption.point(x, start_y))
                    .waitAction(timeOfSwipe)
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
        }else{
            System.out.println("Method swipeUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    public void swipeUpQuick(){swipeUp(WaitOptions.waitOptions(Duration.ofSeconds(200)));
    }

    public void scrollWebPageUp(){
        if (Platform.getInstance().isIMw()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        }else{
            System.out.println("Method scrollWebPageUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTitleElementNotVisible(String locator, String error_message, int max_swipes){
        int already_swiped = 0;

        WebElement element = this.waitForElementPresentBy(locator, error_message, 5);

        while (!this.isElementLocatedOnTheScreen(locator)){
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped > max_swipes){
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes){
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while(driver.findElements(by).size() == 0){

            if (already_swiped > max_swipes){
                waitForElementPresentBy(locator, "Cannot find element" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeUpTitleElementAppear(String locator, String error_message, int max_swipes){
        int already_swiped = 0;

        while(!this.isElementLocatedOnTheScreen(locator)){
            if(already_swiped > max_swipes){
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator){
        int element_location_by_y = this.waitForElementPresentBy(locator,
                "Cannot find element locator" ,
                5).getLocation().getY();
        if (Platform.getInstance().isIMw()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }

    public void clickElementToThrRightUpperCorner(String locator, String error_message){

        if (driver instanceof AppiumDriver) {
            WebElement element = this.waitForElementPresentBy(locator + "/..", error_message, 5);
            int right_x = element.getLocation().getX();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            int width = element.getSize().getWidth();

            int point_to_cleck_x = (right_x + width) - 3;
            int point_to_cleck_y = middle_y;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.tap(PointOption.point(point_to_cleck_x, point_to_cleck_y)).perform();
        }else{
        System.out.println("Method clickElementToThrRightUpperCorner() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeElementToLeft(String locator, String error_message){

        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresentBy(
                    locator,
                    error_message,
                    10);

            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();

            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.press(PointOption.point(right_x, middle_y));
            action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(300)));

            if(Platform.getInstance().isAndroid()){
                action.moveTo(PointOption.point(left_x, middle_y));
            }else {
                int offset_x = (-1 * element.getSize().getWidth());
                action.moveTo(PointOption.point(offset_x, 0));
            }
            action.release();
            action.perform();
        }else{
            System.out.println("Method swipeElementToLeft() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void tryClickElementWithFewAttemts(String locator, String error_message, int amout_of_attempts){
        int current_attempts = 0;
        boolean need_more_attempts = true;

        while (need_more_attempts){
            try {
                this.waitForElementAndClick(locator, error_message,1);
                need_more_attempts = false;
            }catch (Exception e){
                if (current_attempts > amout_of_attempts){
                    this.waitForElementAndClick(locator, error_message,1);
                }
            }
            ++ current_attempts;
        }
    }

    public int getAmoumtOfElement(String locator){
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public boolean isElementPresent(String locator){
        return getAmoumtOfElement(locator) > 0;
    }

    public void assertElementPresent(String locator, String error_message) {
        int amout_of_elements = getAmoumtOfElement(locator);
        if (amout_of_elements == 0){
            String default_message = "An element " + locator + " supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private By getLocatorByString(String locator_with_type){
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"),2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if(by_type.equals("xpath")){
            return By.xpath(locator);
        }else if(by_type.equals("id")){
            return By.id(locator);
        }else if(by_type.equals("css")){
            return By.cssSelector(locator);
        }else {
            throw new IllegalArgumentException("Cannot get type of locator: " + locator_with_type);
        }
    }
}
