@ui @kiwiCalculator
Feature: Jira-Feature# Used Cars Details Search
  As a sandbox test user
  I will search for the used cars in trade me sandbox application
  So that I can see the cars list from the search

  @validateFieldInformation
  Scenario Outline: Users wants that while using the KiwiSaver Retirement Calculator all fields in the calculator have got the
  information icon present

    Given user opens Westpac desktop application
    When user navigated to the <headerMenu> and <uberMenu> and then to <subMenuItem> menu option
    And she finds the information icon besides all the calculator field
    Then she is able to get a clear information of what needs to be entered in the field
      | calField    | fieldInformation                                                                                                |
      | Current age | This calculator has an age limit of 18 to 64 years old as you need to be under the age of 65 to join KiwiSaver. |
  #   | Employment status | If you are earning a salary or wage, select ‘Employed’. Your employer contributions will be automatically calculated at a rate of 3% of your before-tax salary or wages. You can also select ‘Self-employed’ or ‘Not employed’ and then enter below (in the Voluntary contributions field), the amount and frequency of any contributions that you wish to make. |
  #   keep adding your calculator field name and field information to validate

    Examples:
      | headerMenu | uberMenu  | subMenuItem           |
      | Personal   | KiwiSaver | KiwiSaver calculators |


  @kiwiRetirementProjection
  Scenario Outline: Users want that the KiwiSaver Retirement Calculator users are able to calculate what their KiwiSaver projected
  balance would be at retirement

    Given user opens Westpac desktop application
    When user navigated to the <headerMenu> and <uberMenu> and then to <subMenuItem> menu option
    Then user is able to calculate his kiwi saver projected balance at retirement
      | currentAge | employment    | salary | kiwiBalance | kiwiSaver | volContribution | frequent    | riskProfile  | savingGoal | projection |
      | 30         | Employed      | 82000  |             | 4         |                 |             | Defensive    |            | $436,365   |
      | 45         | Self-employed |        | 100000      |           | 90              | Fortnightly | Conservative | 290000     | $259,581   |
      | 55         | Not employed  |        | 140000      |           | 10              | Annually    | Balanced     | 200000     | $197,679   |

    #projection data is hardcoded as the calculation logic is not known.
    Examples:
      | headerMenu | uberMenu  | subMenuItem           |
      | Personal   | KiwiSaver | KiwiSaver calculators |