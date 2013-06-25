Scenario: Create a report
Given a ReporterAgent has access to the database,
When that agent receives a FIPA-INFORM message from a recorder agent,
Then the message is processed and a new issue report is created.
