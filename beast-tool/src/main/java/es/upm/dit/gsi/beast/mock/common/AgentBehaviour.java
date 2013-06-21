package es.upm.dit.gsi.beast.mock.common;

/**
 * Project: beast
 * File: es.upm.dit.gsi.beast.mock.common.AgentBehaviour.java
 * 
 * Empty skeleton to be copied by mocks, it will provide the actions to perform
 * by mock agents. Each time processMessage is called, the mock will retrieve an
 * String, which will be used to know the receiver, type or content of answer
 * message
 * 
 * Grupo de Sistemas Inteligentes
 * Departamento de Ingeniería de Sistemas Telemáticos
 * Universidad Politécnica de Madrid (UPM)
 * 
 * @author Jorge Solitario
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * @date 21/06/2013
 * @version 0.1
 * 
 */
public class AgentBehaviour {

    public Object processMessage(String message_type, String sender_id,
            Object content) {
        return null;
    }

    public Object processMessage(String message_type, Object content) {
        return null;
    }
}