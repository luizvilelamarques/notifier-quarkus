package org.acme.core.bean;

import java.util.List;

/**configuração das filas para notificação.
 * 
 * @author Luiz
 *
 */
public class Config {

	private String name;
	private List<Fila> filas;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Fila> getFilas() {
		return filas;
	}

	public void setFilas(List<Fila> filas) {
		this.filas = filas;
	}
	
	
	public String getCanalNotifier(String sala) {
		for (Fila fila : filas) {
			if (fila.getName().equals(sala)) {
				return fila.getNotifier();
			}
		}
		return null;
	}
	
	
	public String getCanalNotificacao(String sala) {
		for (Fila fila : filas) {
			if (fila.getName().equals(sala)) {
				return fila.getCanal();
			}
		}
		return null;
	}

}

class Fila {
	private String name;
	private String notifier;
	private String canal;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getNotifier() {
		return notifier;
	}

	public void setNotifier(String notifier) {
		this.notifier = notifier;
	}

}
