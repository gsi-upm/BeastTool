Scenario: Set believes in agent
Given that one agent is started in Jadex Platform
When tester wants to set a belief inside a Jadex agent
Then the Jadex belief is set

Scenario: Get believes from agent
Given that one agent is started in Jadex Platform
When tester wants to get a belief from a Jadex agent
Then the Jadex belief is retrieved

Scenario: Testing mock bridge 1
Given one listener mock and one repository mock in Jadex Platform
When bridge mocks has to send a message to listener
Then listener receives the message

Scenario: Testing mock bridge 2
Given one repository mock and one bridge mock in Jadex Platform
When bridge mocks has to send a message to repository
Then bridge receives the answer from repository

Scenario: Testing mock listener
Given one listener mock in Jadex Platform
When 10 messages are sended to listener
Then listener receives all messages

Scenario: Testing mock repository
Given one bridge mock and one repository mock in Jadex Platform
When bridge sends a message to repository
Then repository answers with the correct message