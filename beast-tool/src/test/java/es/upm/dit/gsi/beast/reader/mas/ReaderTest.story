Story - Test Story

As a user
I want to do something
So that I have a benefit
  
Scenario: Set believes in agent
Given that TesterAgent is started in Jade Platform in Main-Container with Configuration 4
When tester wants to set a belief inside a Jade agent
Then the Jade belief is set

Scenario: Get believes from agent
Given that TesterAgent is started in Jade Platform in Main-Container with Configuration 1
When tester wants to get a belief from a Jade agent
Then the Jade belief is retrieved

Scenario: This is other Scenario
Given This is other Given
When This is other When
Then This is other Then