Story: Record a message
As a RecorderAgent,
I want to record incoming calls,
So that I can pass the message to a ReporterAgent.

Scenario: Understanding the customer
Given a phone call is received,
When I understand the customer,
Then I record his/her message
And I send that message to a ReporterAgent.

Scenario: Misunderstanding the customer
Given a phone call is received,
When I do not understand the customer,
Then I pass the incoming call to a HelpDeskAgent.