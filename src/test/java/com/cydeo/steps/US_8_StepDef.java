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

import java.util.ArrayList;
import java.util.List;


public class US_8_StepDef {

    DashBoardPage dashBoardPage = new DashBoardPage();
    Actions actions = new Actions(Driver.getDriver());
    BookPage bookPage = new BookPage();
    String UserId ;
    String UserStatus;
    String UserInactiveStatus = "INACTIVE";


    @And("the user navigates to {string} page")
    public void the_user_navigates_to_page(String string) {
        dashBoardPage.navigateModule(string);
        BrowserUtil.waitFor(1);


    }
    @When("the user clicks Edit User button")
    public void the_user_clicks_edit_user_button() {
        UserId = bookPage.firstUserId.getText();
        UserStatus = bookPage.firstUserStatus.getText();
        System.out.println("listOfUsersInfo = " + UserId);
        System.out.println("UserStatus = " + UserStatus);
        actions.moveToElement(bookPage.editUserBtn).pause(1).click().perform();


    }
    @When("the user changes user status {string} to {string}")
    public void the_user_changes_user_status_to(String string, String string2) {
        Select select = new Select(bookPage.statusDropdown);
        select.selectByValue(string2);
        BrowserUtil.waitFor(1);

    }
    @When("the user clicks save changes button")
    public void the_user_clicks_save_changes_button() {
    actions.moveToElement(bookPage.saveChangesBtn).pause(1).click().perform();
    BrowserUtil.waitForVisibility(bookPage.verificationToastMessage,1);

    }
    @Then("{string} message should appear")
    public void message_should_appear(String string) {
        System.out.println("bookPage.verificationToastMessage.getText() = " + bookPage.verificationToastMessage.getText());
        Assert.assertEquals(bookPage.verificationToastMessage.getText(),string);

    }
    @Then("the users should see same status for related user in database")
    public void the_users_should_see_same_status_for_related_user_in_database() {
        String query = "select id,status from users where id='"+UserId+"'";
        DB_Util.runQuery(query);
        List<String> actualStatus = new ArrayList<>(DB_Util.getRowDataAsList(1));
        System.out.println("actualStatus = " + actualStatus);
        System.out.println("UserInactiveStatus = " + UserInactiveStatus);
        Assert.assertEquals(UserInactiveStatus,actualStatus.get(1));

    }
    @Then("the user changes current user status {string} to {string}")
    public void the_user_changes_current_user_status_to(String string, String string2) {
        Select select = new Select(bookPage.generalStatusDropDown);
        select.selectByValue(string);
        BrowserUtil.waitFor(2);
    bookPage.search.sendKeys(UserId);
    BrowserUtil.waitFor(2);
    actions.moveToElement(bookPage.editUserBtn).pause(1).click().perform();
        Select select2 = new Select(bookPage.statusDropdown);
    BrowserUtil.waitFor(2);
    select2.selectByValue(string2);
    actions.moveToElement(bookPage.saveChangesBtn).pause(1).click().perform();

    }
    }
