@tests
Feature: Research & Education

  Scenario Outline: Economic Calendar verifications for Web mode
    Given user opens 'Home' page with '<browserSize>'
    Then 'Home' page is displayed and matches to mockup
    When user navigates to "RESEARCH & EDUCATION" page
    Then 'Research & Education' page is displayed and matches to mockup
    When user navigates to 'Economic Calendar' page
    Then user verifies that 'Economic Calendar' page is displayed
    When user clicks on "<calendarFilter>" filter
    Then user verifies that correct date for "<calendarFilter>" filter is applied
    When user opens 'Risk Disclosure' link
    When user verifies that 'Risk Disclosure' page is displayed

    Examples:
      | browserSize | calendarFilter |
      | max         | Yesterday      |
      | max         | Today          |
      | max         | Tomorrow       |
      | max         | This Week      |
      | 1024 x 768  | Yesterday      |
      | 1024 x 768  | Today          |
      | 1024 x 768  | Tomorrow       |
      | 1024 x 768  | This Week      |

  Scenario Outline: Economic Calendar verifications for mobile mode
    Given user opens 'Home' page with '<browserSize>'
    Then 'Home' page is displayed in mobile mode and matches to mockup
    When user opens the left side 'Menu' in mobile view mode
    Then left side menu in mobile mode has the options:
      | Home | Member Login | Deposit Funds | Promotions | Trading | Platforms | Research & Education | About Us | Partnerships |
    When user navigates to "RESEARCH & EDUCATION" page mobile mode
    When user opens "Economic Calendar" page mobile mode
    Then user verifies that 'Economic Calendar' page is displayed
    When user clicks on "<calendarFilter>" filter
    Then user verifies that correct date for "<calendarFilter>" filter is applied
    When user opens 'Risk Disclosure' link
    When user verifies that 'Risk Disclosure' page is displayed

    Examples:
      | browserSize | calendarFilter |
      | 800 x 600   | Yesterday      |
      | 800 x 600   | Today          |
      | 800 x 600   | Tomorrow       |
      | 800 x 600   | This Week      |
