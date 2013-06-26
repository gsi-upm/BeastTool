Scenario: Pass a call
Given a HelpDeskAgent has connection to the desktop of the operator computer, and a RecorderAgent has received a call that cannot understand,
When that HelpDeskAgent receives a FIPA-REQUEST message from that RecorderAgent,
Then the request is accepted and the incoming call is transfered to the human operator.
