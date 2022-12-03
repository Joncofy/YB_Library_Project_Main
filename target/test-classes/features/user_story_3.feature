@db@smoke
Feature:As a data consumer, I want UI and DB book categories are match.

	
	@TS4-210
	Scenario: Verify  UI and DB book categories are match.
		Given user login as a "librarian"
		    When I navigate to "Books" page
		    And I take all book categories in UI
		    And I execute query to get book categories
		    Then verify book categories must match book_categories table from db