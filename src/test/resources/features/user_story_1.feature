@db@smoke
Feature: As a data consumer, I want the user information are stored in mySql DB correctly in users table.

  @TS4-203
  Scenario: verify users has unique IDs
    Given Establish the database connection
    When Execute query to get all IDs from users
    Then verify all users has unique ID

  @TS4-205
  Scenario: Verify user information are stored in mySql DB correctly in users table
    Given Establish the database connection
    When Execute query to get all columns
    Then verify the below columns are listed in result
      | id            |
      | full_name     |
      | email         |
      | password      |
      | user_group_id |
      | image         |
      | extra_data    |
      | status        |
      | is_admin      |
      | start_date    |
      | end_date      |
      | address       |