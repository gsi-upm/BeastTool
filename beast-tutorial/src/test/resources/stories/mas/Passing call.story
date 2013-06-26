Story: Passing an incoming call
As a HelpDeskAgent,
I want to process a FIPA-REQUEST message from a RecorderAgent,
So that I am able to pass an incoming call to a human operator.

Scenario: Pass a call
Given a HelpDeskAgent has connection to the desktop of the operator computer,
And a RecorderAgent has received a call that cannot understand,
When that HelpDeskAgent receives a FIPA-REQUEST message from that RecorderAgent,
Then the request is accepted and the incoming call is transfered to the human operator. 