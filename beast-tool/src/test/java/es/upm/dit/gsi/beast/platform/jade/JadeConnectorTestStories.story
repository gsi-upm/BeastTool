Scenario: Set believes in agent
Given that one agent is started in Jade Platform in Main-Container
When tester wants to set a belief inside a Jade agent
Then the Jade belief is set

Scenario: Get believes from agent
Given that one agent is started in Jade Platform in Main-Container
When tester wants to get a belief from a Jade agent
Then the Jade belief is retrieved