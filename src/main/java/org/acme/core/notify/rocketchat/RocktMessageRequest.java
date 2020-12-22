package org.acme.core.notify.rocketchat;

public class RocktMessageRequest {

	String alias;
	String avatar;
	String text;

	public RocktMessageRequest() {

	}

	public RocktMessageRequest(String alias, String avatar, String text) {
		super();
		this.alias = alias;
		this.avatar = avatar;
		this.text = text;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}