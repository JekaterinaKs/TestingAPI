@test
Feature: Checking response body
  Checking of some response bodies

  Background:
    Given Open localhost

  Scenario: Checking response body
    Given Get full body of the "/comments/2"

  Scenario Outline: Checking some response body values
    Given Send GET request to the "/comments/1"
    And Get one "<value>" of the "<body>"
    Then Validate the "<value>" of "<response>"

    Examples:
    |   value       |  body       |   response   |
    |   author      | Author is:  |     Alex     |
    |    body       | Body is:    | some comment |

  Scenario: Checking some incorrect response body values
    Given Send GET request to the "/posts/1"
    Then Get one incorrect "name" of the body and result is null

