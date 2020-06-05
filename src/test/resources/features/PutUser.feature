Feature: You can add a user to the application
  Scenario: User's email does not already exist
    When we put on "/user" with the user
      | email                   | first_name | last_name |
      | create@mailinator.com   | create   | user      |
    Then we get an Ok response with the user
      | email                   | first_name | last_name |
      | create@mailinator.com   | create   | user      |