Feature: GitHub API Response Handling
  As a developer
  I want to verify the GitHub API returns correct status codes
  So that I can handle responses appropriately in the app

  Scenario Outline: Verify response status codes
    Given the GitHub API endpoint is "<endpoint>"
    When a <method> request is made
    Then the response status code should be <status_code>

    Examples:
      | endpoint                    | method | status_code |
      | /repos/octocat/Hello-World   | GET    | 200         |
      | /repos/octocat/Hello-World   | POST   | 400         |
      | /repos/octocat/Unknown-Repo  | GET    | 404         |
      | /repos/octocat/Hello-World   | DELETE | 500         |
