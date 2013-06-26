Scenario: Create a report
Given a ReporterAgent has access to the database, and a RecorderAgent has received a call that can understand,
When that ReporterAgent receives a FIPA-INFORM message from that RecorderAgent,
Then the message is processed and a new issue report is created.
