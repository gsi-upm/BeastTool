Story - jade platform test
As a developer
I want to generate tests for the agent
so that i save effort


Scenario: Get believes from agent
Given that TesterAgent is started in Jade Platform in Main-Container with Configuration 1
When tester wants to get a belief from a Jade agent
Then the Jade belief is retrieved

Scenario: Messenger Agent receive a response
Given that TesterAgent is started in Jade Platform in Main-Container with Configuration 6
When tester wants to send a message with MessageService to TesterAgent and to receive a response 
Then the message is received by the tester through MessengerAgent

Scenario: Send a message
Given that TesterAgent is started in Jade Platform in Main-Container with Configuration 5
When tester wants to send a message with MessageService to TesterAgent
Then the message is received by TesterAgent

Scenario: Set believes in agent
Given that TesterAgent is started in Jade Platform in Main-Container with Configuration 4
When tester wants to set a belief inside a Jade agent
Then the Jade belief is set

Scenario: Testing mock bridge case one
Given one bridge mock and one listener mock in Jade Platform
When bridge mocks has to send a message to listener
Then listener receives the message

Scenario: Testing mock bridge case two
Given one repository mock and one bridge mock in Jade Platform
When bridge mocks has to send a message to repository
Then bridge receives the answer from repository

Scenario: Testing mock listener
Given one listener mock in Jade Platform
When 10 messages are sent to listener
Then listener receives all messages

Scenario: Testing mock repository
Given one bridge mock and one repository mock in Jade Platform
When bridge sends a message to repository
Then repository answers with the correct message 