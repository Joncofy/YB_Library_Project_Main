package com.cydeo.pages;

import com.cydeo.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashBoardPage extends BasePage
{


    @FindBy(id = "borrowed_books")
    public WebElement borrowedBooksNumber;

}
