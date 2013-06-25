Story: Record a message
As a RecorderAgent,
I want to record incoming calls,
So that I can pass the message to a ReporterAgent.

Scenario: Misunderstanding the customer
Given a phone call is received,
When I do not understand the customer,
Then I send a FIPA-REQUEST message to a HelpDeskAgent
And I pass the incoming call to a HelpDeskAgent.

Scenario: Understanding the customer
Given a phone call is received,
When I understand the customer,
Then I record his/her message
And I send that FIPA-INFORM message to a ReporterAgent.