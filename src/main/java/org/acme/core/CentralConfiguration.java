package org.acme.core;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.acme.core.bean.Bean;
import org.acme.core.bean.Config;
import org.acme.core.notify.Notifier;

/**Ponto central onde as configurações estão disponíveis
 * 
 * @author Luiz
 *
 */
@Singleton
public class CentralConfiguration {

	@Inject
	JsonConvert json;

	@Inject
	SelectSelector serviceSelector;
	
	private Config config;
	
	private Service<Bean> manager;
	
	private Notifier<Bean> notifier;
	
	public Config getConfig() {
		if (config==null) {
			build();
		}
		return config;
	}
	
	public Notifier<Bean> getNotifier(String notificador) {
		if (notifier==null) {
			buildNotifier(notificador);
		}
		return notifier;
	}
	
	public Service<Bean> getManager() {
		if (manager==null) {
			build();
		}
		return manager;
	}
	
	private void build() {
		config = json.getConfig();
		manager  =  serviceSelector.selectService(config.getName());
	}
	
	private void buildNotifier(String notificador) {
		notifier =  serviceSelector.selectNotifier(notificador);
	}
	
}
