
Feature: Smoke Test Cases
  Background:
      Given  Application is launched
    @Regression
    Scenario Outline: Verify Search functioanlity in Web Application

      When  Product is searched with "<productName>"
      When   Format searched string
      Then  Verify searched result "<productName>"
      Examples:
        | productName |
        | iphone      |
        | macbook     |


      Scenario Outline: Verify user Register by entering invalid details
        When Select My Account option "<option>"
        When Add Registration Details "<FirstName>" "<LastName>" "<Telephone>" "<Password>"
        Then verify the error message for invalid details
        Examples:
          | option   |  | FirstName |  | LastName |  | Telephone |  | Password     |
          | Register |  |           |  | Bansal   |  | 123456789 |  | DivyanshTest |

