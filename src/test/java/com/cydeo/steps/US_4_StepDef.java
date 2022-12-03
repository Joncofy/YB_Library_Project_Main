package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US_4_StepDef {
    BookPage bookPage = new BookPage();
    String actualBookName;
    String actualAuthor;
    String actualYear;
    List<String> dbBookInfo;

    @When("I open book {string}")
    public void i_open_book(String string) {
       bookPage.search.sendKeys(string);
        BrowserUtil.waitFor(2);
        bookPage.editBook(string).click();
        BrowserUtil.waitForVisibility(bookPage.bookName,5);
        actualBookName = bookPage.bookName.getAttribute("value");
        actualAuthor = bookPage.author.getAttribute("value");
        actualYear = bookPage.year.getAttribute("value");
        System.out.println("actualBookName = " + actualBookName);
        System.out.println("actualAuthor = " + actualAuthor);
        System.out.println("actualYear = " + actualYear);


    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
        DB_Util.runQuery("select name, author,year from books where name='Chordeiles minor'");
        dbBookInfo = DB_Util.getRowDataAsList(1);
        System.out.println("dbBookInfo = " + dbBookInfo);
        Assert.assertEquals(actualBookName,dbBookInfo.get(0));
        Assert.assertEquals(actualAuthor,dbBookInfo.get(1));
        Assert.assertEquals(actualYear,dbBookInfo.get(2));


    }

}
