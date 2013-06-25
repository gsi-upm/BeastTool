Scenario: Pass a call
Given a HelpDeskAgent has connection to the desktop of the operator computer,
When that agent receives a FIPA-REQUEST message from a RecorderAgent,
Then the request is accepted and the incoming call is transfered to the human operator.
