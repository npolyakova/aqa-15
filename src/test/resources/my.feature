Feature: User auth

  Scenario: User can auth after logout
    Given user is logged out
    When the user log in again
    Then user is authorized successfully