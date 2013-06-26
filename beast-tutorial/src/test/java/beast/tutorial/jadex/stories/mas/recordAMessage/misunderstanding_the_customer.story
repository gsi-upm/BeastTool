Scenario: Misunderstanding the customer
Given a RecordAgent and a HelpDeskAgent can communicate between them,
When a phone call is received and the RecorderAgent does not understand the customer,
Then the RecorderAgent sends a FIPA-REQUEST message to a HelpDeskAgent and the RecorderAgent pass the incoming call to a HelpDeskAgent.
