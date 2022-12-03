package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.pages.DashBoardPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import com.cydeo.utility.Driver;
import io.cucumber.java.en.And;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class US_7_StepDef {
    Actions actions = new Actions(Driver.getDriver());
    BookPage bookPage = new BookPage();
    String borrowedBook;
    String actualInfo;


    @And("I search book name called {string}")
    public void i_search_book_name_called(String string) {
        bookPage.search.sendKeys(string);
        BrowserUtil.waitFor(2);
    }
    @When("I click Borrow Book")
    public void i_click_borrow_book() {
        actions.moveToElement(bookPage.sortingByDesc).pause(1).click().perform();
        BrowserUtil.waitFor(2);
        borrowedBook = bookPage.allRows.get(1).getText();
        actions.moveToElement(bookPage.listForBorrowingBooks.get(1)).pause(1).click().perform();
        BrowserUtil.waitFor(1);
        Assert.assertTrue(bookPage.verificationToastMessage.isDisplayed());

        System.out.println("borrowedBook = " + borrowedBook);

    }
    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String string) {
        bookPage.navigateModule(string);
        BrowserUtil.waitFor(2);
        actualInfo = bookPage.allRows.get(bookPage.allRows.size()-1).getText();
        System.out.println("actualInfo = " + actualInfo);
        Assert.assertTrue(borrowedBook.contains("Head First Java"));


    }
    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {
        String query = "select full_name,b.name,bb.borrowed_date from users u\n" +
                "                                                  inner join book_borrow bb on u.id = bb.user_id\n" +
                "                                                  inner join books b on bb.book_id = b.id\n" +
                "where full_name='Test Student 41'\n" +
                "order by 3 desc";
        DB_Util.runQuery(query);

        List<String> expected = DB_Util.getRowDataAsList(1);
        System.out.println("actualInfo = " + actualInfo);
        System.out.println("expected = " + expected);
        Assert.assertTrue(actualInfo.contains(expected.get(2)));

        actions.moveToElement(bookPage.listForReturningBorrowingBooks.get(bookPage.listForReturningBorrowingBooks.size()-1)).pause(1).click().perform();

    }
}
