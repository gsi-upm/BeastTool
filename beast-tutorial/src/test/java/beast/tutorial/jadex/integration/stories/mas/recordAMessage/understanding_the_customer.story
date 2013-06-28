Scenario: Understanding the customer
Given a RecordAgent and a ReporterAgent can communicate between them,
When a phone call is received and the RecordAgent understands the customer,
Then the RecordAgent records his/her message and the RecordAgent sends that FIPA-INFORM message to a ReporterAgent.
