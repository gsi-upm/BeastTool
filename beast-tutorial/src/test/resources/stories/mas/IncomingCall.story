Story: Record a message
As a recorder agent,
I want to record incoming calls,
So that I can pass the message to a reporter agent.

Scenario: Misunderstanding the customer
Given a phone call is received,
When I do not understand the customer,
Then I send a fipa request message to a helpdesk agents 
And I pass the incoming call to a helpdesk agent.

Scenario: Understanding the customer
Given a phone call is received,
When I understand the customer,
Then I record his/her message and I send that fipa inform message to a reporter agent.