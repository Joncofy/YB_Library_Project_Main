package com.cydeo.pages;

import com.cydeo.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BookPage extends BasePage {

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> allRows;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement search;

    @FindBy(id = "book_categories")
    public WebElement mainCategoryElement;

    @FindBy(name = "name")
    public WebElement bookName;

    @FindBy(xpath = "(//input[@type='text'])[4]")
    public WebElement author;

    @FindBy(name = "year")
    public WebElement year;

    @FindBy(name = "isbn")
    public WebElement isbn;

    @FindBy(id = "status")
    public WebElement statusDropdown;

    @FindBy(id = "user_status")
    public WebElement generalStatusDropDown;

    @FindBy(xpath = "//a[@class='btn btn-primary btn-sm  ']")
    public List<WebElement> listForBorrowingBooks;

    @FindBy(xpath = "//a[@class='btn btn-primary btn-sm ']")
    public List<WebElement> listForReturningBorrowingBooks;

    @FindBy(xpath = "//th[@class='sorting_desc']")
    public WebElement sortingByDesc;

    @FindBy(xpath = "(//a[@class='btn btn-primary btn-sm'])[1]")
    public WebElement editUserBtn;

    public WebElement editBook(String book) {
        String xpath = "//td[3][.='" + book + "']/../td/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    @FindBy(xpath = "//a[@class='btn btn-lg btn-outline btn-primary btn-sm add_book_btn']")
    public WebElement addBookBtn;

    @FindBy(xpath = "//button[.='Save changes']")
    public WebElement saveChangesBtn;

    @FindBy(id = "book_group_id")
    public WebElement dropDownOption;

    @FindBy(xpath = "//div[@class='toast-message']")
    public WebElement verificationToastMessage;

    @FindBy(xpath = "//div[@class='dataTables_info']")
    public WebElement totalNumberOfBook2;

    @FindBy(xpath = "//tr//td[2]")
    public WebElement firstUserId;

    @FindBy(xpath = "//tr//td[6]")
    public WebElement firstUserStatus;



}
