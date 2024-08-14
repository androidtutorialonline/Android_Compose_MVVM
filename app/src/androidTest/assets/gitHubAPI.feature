Feature: GitHub API Response Handling
  As a developer
  I want to verify the GitHub API returns correct status codes
  So that I can handle responses appropriately in the app

  Scenario Outline: Verify response status codes
    Given the GitHub API endpoint is "<endpoint>"
    #When a <method> request is made
    When a "<method>" request is made
    Then the response status code should be <status_code>

    Examples:
      | endpoint                    | method | status_code |
      | /users                      | GET    | 200         |
      | /users                      | POST   | 404         |
