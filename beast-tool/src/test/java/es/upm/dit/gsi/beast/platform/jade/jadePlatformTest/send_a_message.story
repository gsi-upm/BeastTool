Scenario:  send a message
Given that TesterAgent is started in Jade Platform in Main-Container with Configuration 5
When tester wants to send a message with MessageService to TesterAgent
Then the message is received by TesterAgent
