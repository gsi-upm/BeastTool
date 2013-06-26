Story: Processing message
As a ReporterAgent,
I want to process any FIPA-INFORM message from a RecorderAgent which contains a recorded message,
So that I am able to generate an issue report based on that message content.

Scenario: Create a report
Given a ReporterAgent has access to the database,
And a RecorderAgent has received a call that can understand,
When that ReporterAgent receives a FIPA-INFORM message from that RecorderAgent,
Then the message is processed
And a new issue report is created.
