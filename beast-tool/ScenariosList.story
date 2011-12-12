Scenario: Mars World normal
Given un agente carry, un productor y un sentry
When hay poco tiempo (90 segs)
Then no se recoge todo el oro

Scenario: Mars World superpoblado
Given tres agentes carry, dos productor y un sentry
When hay mucho tiempo (5 min)
Then se recoge todo el oro

Scenario: Mars World modificado
Given un agente carry, un productor y un sentry, todos acelerados
When hay poco tiempo (1 min)
Then se recoge todo el oro

Scenario: Probando mock bridge
Given un agente bridge y un listener
When al agente bridge se le pide reenviar un mensaje al listener
Then el listener recibe el mensaje

Scenario: Probando mock listener
Given un agente listener
When se le mandan 10 mensajes
Then el listener los recibe en orden

Scenario: Probando mock repository
Given un agente bridge y un agente repository
When bridge le manda un mensaje a repository
Then repository le contesta con el mensaje especifico

Scenario: Mars World con mock
Given un agente carry, un agente sentry y un mock que hace del agente productor
When sentry encuentra una mina
Then carry lleva el oro a casa 