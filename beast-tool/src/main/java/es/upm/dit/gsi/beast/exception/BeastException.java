/**
 * es.upm.dit.gsi.beast.exception.BeastException.java
 */
package es.upm.dit.gsi.beast.exception;

/**
 * Project: beast
 * File: es.upm.dit.gsi.beast.exception.BeastException.java
 * 
 * Generic exception launched by Beast Tool
 * 
 * Grupo de Sistemas Inteligentes
 * Departamento de Ingeniería de Sistemas Telemáticos
 * Universidad Politécnica de Madrid (UPM)
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * @date 21/06/2013
 * @version 0.1
 * 
 */
public class BeastException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -2996699933056365412L;

    /**
     * Constructor
     *
     * @param message
     */
    public BeastException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param cause
     */
    public BeastException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor
     *
     * @param message
     * @param cause
     */
    public BeastException(String message, Throwable cause) {
        super(message, cause);
    }

}
