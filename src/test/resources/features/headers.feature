@test
  Feature: Checking response headers
    Checking of some response headers

    Background:
      Given Open localhost

    Scenario: Checking all response headers
      Given Get all headers of the "/posts"

    Scenario Outline: Checking some existing response headers
      Given Send GET request to the "/posts"
      Then Check some "<headers>" "<value>"

      Examples:
        |   headers     |      value              |
        | Vary          | Origin, Accept-Encoding |
        | Connection    | keep-alive              |
        | Cache-Control | no-cache                |

    Scenario Outline: Checking some nonexistent response headers
      Given Send GET request to the "/posts"
      Then Check some nonexistent "<headers>" value is null

      Examples:
        |   headers        |
        | Server           |
        | Content-Encoding |
