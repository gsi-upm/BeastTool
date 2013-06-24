Story: Processing message
As a reporter agent,
I want to process any fipa inform message from a recorder agent which contains a recorded message,
So that I am able to generate an issue report based on that message content.

Scenario: Create a report
Given a reporter agent has access to the database,
When that agent receives a fipa inform message from a recorder agent,
Then the message is processes and a new issue report is created.
