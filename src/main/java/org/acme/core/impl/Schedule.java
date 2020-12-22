package org.acme.core.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.core.bean.Bean;

import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class Schedule {
	
	@Inject
	CentralConfiguration controleSM;
	
	@Scheduled(every = "10s")
	void verify() throws Throwable {
		List<Bean> beans = controleSM.getSourceService(controleSM.getConfig().getName()).getBeans();
		beans.stream().forEach(t -> {
			controleSM.getNotifier(t.getNotifier()).notifica(t);
		});
	}
}