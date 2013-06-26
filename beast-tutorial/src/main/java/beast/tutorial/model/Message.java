/**
 * beast.tutorial.tools.Message.java
 */
package beast.tutorial.model;

import java.io.Serializable;

/**
 * Project: beast-tutorial
 * File: beast.tutorial.tools.Message.java
 * 
 * Grupo de Sistemas Inteligentes
 * Departamento de Ingeniería de Sistemas Telemáticos
 * Universidad Politécnica de Madrid (UPM)
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * @date 26/06/2013
 * @version 0.1
 * 
 */
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4137178850404445565L;
	
	private Call call;
	
	public Message(Call call) {
		this.setCall(call);
	}

	public Call getCall() {
		return call;
	}

	public void setCall(Call call) {
		this.call = call;
	}

}
