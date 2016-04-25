
Scenario: User searches for a single step
 
Given user is on Home page
When user opens Login link
Then Login page is shown

When user enters username malinowiec and password malin
Then Login error is shown

When user enters username malinowiec and password dellene
Then Login info is shown

When user clicks Logout link
Then Logout info is shown

