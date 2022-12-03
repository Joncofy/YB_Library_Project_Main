package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US_3_StepDef {
    BookPage bookPage = new BookPage();
    List<String> uiCategories;
    List<String> dbCategories;

    @When("I navigate to {string} page")
    public void i_navigate_to_page(String string) {
        bookPage.navigateModule(string);

    }
    @When("I take all book categories in UI")
    public void i_take_all_book_categories_in_ui() {
        uiCategories = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        uiCategories.remove(0);
        System.out.println("uiCategories = " + uiCategories);

    }
    @When("I execute query to get book categories")
    public void i_execute_query_to_get_book_categories() {
        DB_Util.runQuery("select name from book_categories");
        dbCategories = DB_Util.getColumnDataAsList(1);
        System.out.println("dbCategories = " + dbCategories);

    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        Assert.assertEquals(uiCategories,dbCategories);


    }
}
