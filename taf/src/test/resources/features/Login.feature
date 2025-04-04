Feature: Login functionality with dynamic method invocation
#
#  Scenario Outline: Login with dynamic values
#    Given I navigate to the login page
#    When I login with username "<username>" and password "<password>"
#    Then I should see the "<expected_result>"
#
#    Examples:
#      | username                | password    | expected_result     |
#      | Admin                   | admin123    | $getTestMessage     |
#      | $getDynamicUsername     | invalidPass | login error message |
#      | $getUserWithRole(Admin) | pass123     | $getTestMessage     |


  Scenario Outline: Login with dynamic placeholders
    Given I navigate to the login page
    When I login with username "<username>" and password "<password>"
    Then I should see the "<expected_result>"

    Examples:
      | username | password    | expected_result     |
      | Admin    | admin123    | dashboard page      |
      | invalid  | invalidPass | login error message |
