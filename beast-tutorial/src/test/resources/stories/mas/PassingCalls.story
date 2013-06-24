Story: Passing an incoming call
As a helpdesk agent,
I want to process a fipa request message from a recorder agent,
So that I am able to pass an incoming call to a human operator.

Scenario: Pass a call
Given a helpdesk agent has connection to the desktop of the operator computer,
When that agent receives a fipa request message from a recorder agent,
Then the request is accepted and the incoming call is transfered to the human operator. 