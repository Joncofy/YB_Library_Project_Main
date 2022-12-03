package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import com.cydeo.utility.Driver;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class US_6_StepDef {
    Actions actions = new Actions(Driver.getDriver());
    BookPage bookPage = new BookPage();
    List<String> bookInfoCollection = new ArrayList<>();
    String bookNameCollection;
    String isbnCollection;
    String yearCollection;
    String authorCollection;
    String categoryCollection;
    String totalNumberOfBooksString;

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        actions.moveToElement(bookPage.addBookBtn).pause(1).click().pause(1).perform();
    }
    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String string) {
        BrowserUtil.waitFor(1);
        bookPage.bookName.sendKeys(string);
       bookNameCollection = string;
        BrowserUtil.waitFor(1);

    }
    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String string) {
        BrowserUtil.waitFor(1);
        bookPage.isbn.sendKeys(string);
        isbnCollection = string;
        BrowserUtil.waitFor(1);

    }
    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String string) {
        BrowserUtil.waitFor(1);
        bookPage.year.sendKeys(string);
       yearCollection = string;
        BrowserUtil.waitFor(1);

    }
    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String string) {
        BrowserUtil.waitFor(1);
        bookPage.author.sendKeys(string);
       authorCollection = string;
        BrowserUtil.waitFor(1);

    }
    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String string) {
        BrowserUtil.waitFor(1);
        Select select = new Select(bookPage.dropDownOption);
        select.selectByVisibleText(string);
        categoryCollection = string;
        BrowserUtil.waitFor(1);

    }
    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        BrowserUtil.waitFor(1);
        actions.moveToElement(bookPage.saveChangesBtn).pause(1).click().perform();
        BrowserUtil.waitFor(1);
        bookInfoCollection.add(bookNameCollection);
        bookInfoCollection.add(isbnCollection);
        bookInfoCollection.add(yearCollection);
        bookInfoCollection.add(authorCollection);
        bookInfoCollection.add(categoryCollection);

    }
    @Then("the librarian verify new book by {string}")
    public void the_librarian_verify_new_book_by(String string) {
        BrowserUtil.waitForVisibility(bookPage.verificationToastMessage,2);
        Assert.assertTrue(bookPage.verificationToastMessage.isDisplayed());
        bookPage.search.sendKeys(string);
        BrowserUtil.waitFor(2);
        totalNumberOfBooksString = bookPage.totalNumberOfBook2.getText();
        BrowserUtil.waitFor(2);
        for (WebElement each : bookPage.allRows) {
           if(each.getText().contains(bookInfoCollection.toString()));
           break;
        }


    }
    @Then("the librarian verify new book from database by {string}")
    public void the_librarian_verify_new_book_from_database_by(String string) {

        String query = "select name,isbn,author,description,year from books where name='"+string+"'";
        DB_Util.runQuery(query);
        String totalNumberOfBooksFromDb = String.valueOf(DB_Util.getRowCount());

        Assert.assertTrue(totalNumberOfBooksString.contains(totalNumberOfBooksFromDb));
        System.out.println("totalNumberOfBooksFromDb = " + totalNumberOfBooksFromDb);
        System.out.println("totalNumberOfBooksString = " + totalNumberOfBooksString);


    }
}
