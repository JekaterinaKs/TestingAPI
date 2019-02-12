@test
Feature: Check status codes
  Check positive and negative status codes with GET request

  Background:
    Given Open localhost

  Scenario Outline: Check that status code is positive
    Given Send GET request to the "<uri>"
    Then Check that status code is positive 200
    Then Check the status line "HTTP/1.1 200 OK"

    Examples:
    |   uri       |
    | /posts      |
    | /profile    |
    | /comments   |
    | /posts/1    |
    | /comments/2 |

  Scenario Outline: Check that status code is negative
    Given Send GET request to the "<incorrect uri>"
    Then Check that status code is negative 404
    Then Check the status line "HTTP/1.1 404 Not Found"

    Examples:
    | incorrect uri |
    | /mistake      |
    | /comment      |
    | /posts/10     |