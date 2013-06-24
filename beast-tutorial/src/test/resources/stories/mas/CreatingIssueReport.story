Story: Processing message
As a ReporterAgent,
I want to process any FIPA-INFORM message from a RecorderAgent which contains a recorded message,
So that I am able to generate an issue report based on that message content.

Scenario: Create a report
Given a ReporterAgent has access to the database,
When that agent receives a FIPA-INFORM message from a RecorderAgent,
Then the message is processes
And a new issue report is created.
