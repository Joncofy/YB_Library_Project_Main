package com.cydeo.steps;

import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US_2_StepDef {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBorrowedBookNumberUI;

    @Given("user login as a {string}")
    public void user_login_as_a(String string) {
    loginPage.login(string);
        BrowserUtil.waitFor(2);
    }

    @When("user take borrowed books number")
    public void user_take_borrowed_books_number() {
        actualBorrowedBookNumberUI = dashBoardPage.borrowedBooksNumber.getText();


    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        DB_Util.runQuery("select count(*) as borrowedBooks from users u inner join book_borrow b on u.id = b.user_id where is_returned = 0");
        String expectedBorrowedBookNumber = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedBorrowedBookNumber,actualBorrowedBookNumberUI);
        System.out.println("expectedBorrowedBookNumber = " + expectedBorrowedBookNumber);
        System.out.println("actualBorrowedBookNumberUI = " + actualBorrowedBookNumberUI);


    }
}
