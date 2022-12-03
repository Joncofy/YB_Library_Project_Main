@db@smoke
Feature: As a data consumer, I want UI and DB book information are match.

	
	@TS4-212
	Scenario: As a data consumer, I want UI and DB book information are match.
			Given user login as a "librarian"
		    And I navigate to "Books" page
		    When I open book "Chordeiles minor"
		    Then book information must match the Database