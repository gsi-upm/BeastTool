Scenario: MessengerAgent receive a response
Given that TesterAgent is started in Jade Platform in Main-Container with Configuration 6
When tester wants to send a message with MessageService to TesterAgent and to receive a response 
Then the message is received by the tester through MessengerAgent
