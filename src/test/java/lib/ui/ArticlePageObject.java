package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.factories.MyListsPageObjectFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{
    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_TITLE;

    public ArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresentBy(TITLE,
                "Cannot find article title on page",
                5);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getText();
        } else if(Platform.getInstance().isIOS()){
            return  title_element.getAttribute("name");
        }else {
            return title_element.getText();
        }
    }

    public void swipeToFooter(){
        if(Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);
        }else if(Platform.getInstance().isIOS()){
            this.swipeUpTitleElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);
        }else{
            this.scrollWebPageTitleElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);
        }
    }

    public void addArticleToMylist(String nameOfFolder){

        this.waitForElementAndClick(OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5);

        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to open article to reading list",
                5);

        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY,
                "Cannot find button to create new article list ",
                5);

        this.waitForElementAndClear(MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles",
                5);

        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT,
                nameOfFolder,
                "Cannot put text into articles folder  input",
                5);

        this.waitForElementAndClear(MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5);
    }

    public void closeArticle(){
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()){
            this.waitForElementAndClick(CLOSE_ARTICLE_TITLE,
                    "Cannot close article",
                    5);
        }else{
            System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());

        }
    }

    public void assertTitlePage(){
       this.assertElementPresent(TITLE,
               "An element supposed to be present");
    }

    public void addOneArticleMyTolist(String name_of_folder){

        this.waitForElementAndClick(OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5);

        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to open article to reading list",
                5);

        MyListPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);
        MyListPageObject.openFolderByName(name_of_folder);
    }

    public void addArticlesToMySaved(){
        if(Platform.getInstance().isIMw()) {
            this.remoweArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5);
    }

    public  void remoweArticleFromSavedIfItAdded(){

        if (this.isElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON)){
            this.waitForElementAndClick(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot click button to remove",
                    5
            );
            this.waitForElementPresentBy(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot click button to add an article",
                    5
            );
        }

    }

}
