package org.acme.core.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.acme.core.BuildConfig;
import org.acme.core.BuildNotifier;
import org.acme.core.BuildSourceService;
import org.acme.core.SelectorCDI;
import org.acme.core.bean.Bean;
import org.acme.core.bean.Config;
import org.acme.core.notify.Notifier;
import org.acme.core.sourceService.Service;

/**Ponto central onde as configurações estão disponíveis.
 * 
 * @author Luiz
 *
 */
@Singleton
public class CentralConfiguration implements BuildConfig, BuildNotifier, BuildSourceService{

	@Inject
	@Named("json")
	BuildConfig buildConfig;

	@Inject
	@Named("serviceSelector")
	SelectorCDI<Service<Bean>> serviceSelector;
	
	@Inject
	@Named("notifierSelector")
	SelectorCDI<Notifier<Bean>> notifierSelector;
	
	private Config config;
	
	private Service<Bean> sourceService;
	
	private Notifier<Bean> notifier;
	
	@Override
	public Config getConfig() {
		if (config==null) {
			config = buildConfig.getConfig();
		}
		return config;
	}
	
	@Override
	public Notifier<Bean> getNotifier(String notificador) {
		if (notifier==null) {
			notifier =  notifierSelector.selectService(notificador);
		}
		return notifier;
	}
	
	@Override
	public Service<Bean> getSourceService(String source) {
		if (sourceService==null) {
			sourceService  =  serviceSelector.selectService(source);
		}
		return sourceService;
	}
}
