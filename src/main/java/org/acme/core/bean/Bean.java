package org.acme.core.bean;

/**
 * bean base
 * @author Luiz
 *
 */
public abstract class Bean {

	private String id;
	private String fila;
	private String notifier;

	public String getFila() {
		return fila;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNotifier() {
		return notifier;
	}

	public void setNotifier(String notifier) {
		this.notifier = notifier;
	}
}