package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testCancelSearchText() { //задание ex3

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitSearchElementAndSendClear();
        SearchPageObject.waitSearchInvisibilityElement();
    }

    @Test
    public void testCheckPageTitle() { //задание ex6

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.assertTitlePage();
    }

    @Test
    public void testCheckTitleAndDescriptionOfArticle(){ // ex9
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Indonesian island");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "High-level programming language");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Object-oriented programming language");
    }


    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitElementList();
        SearchPageObject.clickCancelSearch();
    }

    @Test
    public void testSearchByWord() {

        /*MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot send keys to search input",
                5);

        for (int i = 0; i < 3; i++) {
            MainPageObject.waitElementWithTextBy(By.id("org.wikipedia:id/page_list_item_title"),
                    "Java",
                    "Cannot find text 'Java'",
                    5);
        }*/
    }
}
