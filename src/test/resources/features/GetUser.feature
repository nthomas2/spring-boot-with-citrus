Feature: You can see users that already exist
  Scenario: User's email does not exist
    When we get on "/user/notexisting@mailinator.com"
    Then we get a Not Found response with the message "Could not find user"

  Scenario: User's email exists
    Given the following user exists
      | email                   | first_name | last_name |
      | existing@mailinator.com | existing   | user      |
    When we get on "/user/existing@mailinator.com"
    Then we get an Ok response with the user
      | email                   | first_name | last_name |
      | existing@mailinator.com | existing   | user      |