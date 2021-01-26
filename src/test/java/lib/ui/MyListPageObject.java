package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListPageObject extends MainPageObject{

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE,
            REMOVE_FROM_SAVE_BUTTON;

    private static String getFolderXpatchByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSaveArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title){
        return REMOVE_FROM_SAVE_BUTTON.replace("{TITLE}", article_title);
    }

    public MyListPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void openFolderByName(String name_of_folder){
        String folder_name_xpath = getFolderXpatchByName(name_of_folder);
        this.waitForElementAndClick(folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5);
    }

    public void waitForArticleToAppearByTitle(String article_title){
        String article_xpath = getFolderXpatchByName(article_title);
        this.waitForElementPresentBy(article_xpath,
                "Cannot find save article by title " +  article_title,
                15);
    }

    public void waitForArticleToDissapearByTitle(String article_title){
        String article_xpath = getFolderXpatchByName(article_title);
        this.waitForElementNotPresent(article_xpath,
                "Saved article still present with title " +  article_title,
                15);
    }

    public void swipeByArticleToDelete(String article_title)
    {   this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getFolderXpatchByName(article_title);

        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid() ){
            this.swipeElementToLeft(article_xpath,
                    "Cannot find title of article " + article_title);
        }else{
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button",
                    5
            );

        }


        if(Platform.getInstance().isIOS()){
            this.clickElementToThrRightUpperCorner(
                    article_xpath,
                    "Cannot find saved article");
        }

        if(Platform.getInstance().isIMw()){
           driver.navigate().refresh();
        }

        this.waitForArticleToDissapearByTitle(article_title);
    }


}
