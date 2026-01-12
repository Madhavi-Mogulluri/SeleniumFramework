Feature: Login Page Feature

  @login @smoke @regression
  Scenario: Check login page title
    Given the user is on the login page
    When the user fetches the page title
    Then the page title should be "Account Login"
    Then the page title should not be empty

  @login @smoke @regression
  Scenario: Check login page URL
    Given the user is on the login page
    When the user checks the page URL
    Then the URL should contain "account/login"

  @login @smoke @regression
  Scenario: Check forgot password link existence
    Given the user is on the login page
    When the user checks the forgot password link
    Then the forgot password link should be displayed

  @login @smoke @regression
  Scenario: Login to application
    Given the user is on the login page
    When the user logs in with username "pwtest@nal.com" and password "test123"
    Then the user should be redirected to the accounts page with title "My Account"

#  @wip @login
#  Scenario: reset password
#    Given the user is on the login page