package org.acme.core.notify.rocketchat;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import org.acme.core.bean.Bean;
import org.acme.core.impl.CentralConfiguration;
import org.acme.core.notify.Notifier;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Named("rocketchat")
@Singleton
public class RocketChatNotifica implements Notifier<Bean> {

	@ConfigProperty(name = "rocketchat.user")
	String userNameRocketchat;
	
	@Inject
	CentralConfiguration controleSM;
	
	@Override
	public void notifica(Bean t) {
		ClientBuilder.newClient().target(controleSM.getConfig().getCanalNotificacao(t.getFila())).request()
				.accept("application/json")
				.post(Entity.entity(new RocktMessageRequest(userNameRocketchat, "", t.toString()), "application/json"), String.class);
	}

}