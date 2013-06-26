Story: Record a message
As a RecorderAgent,
I want to record incoming calls,
So that I can pass the message to a ReporterAgent.

Scenario: Misunderstanding the customer
Given a RecordAgent and a HelpDeskAgent can communicate between them, 
When a phone call is received
And the RecorderAgent does not understand the customer,
Then the RecorderAgent sends a FIPA-REQUEST message to a HelpDeskAgent
And the RecorderAgent pass the incoming call to a HelpDeskAgent.

Scenario: Understanding the customer
Given a RecordAgent and a ReporterAgent can communicate between them,
When a phone call is received
And the RecordAgent understands the customer,
Then the RecordAgent records his/her message
And the RecordAgent sends that FIPA-INFORM message to a ReporterAgent.