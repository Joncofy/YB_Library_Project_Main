@db@smoke
Feature: As a student, I should be able to borrow a book

	
	@TS4-234
	Scenario: As a student, I should be able to borrow a book
		Given user login as a "student"
		  And I navigate to "Books" page
		  And I search book name called "Head First Java"
		  When I click Borrow Book
		  Then  verify that book is shown in "Borrowing Books" page 
		  And  verify logged student has same book in database