package org.acme.core;

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
		List<Bean> beans = controleSM.getManager().getBeans();
		notifica(beans);
	}
	
	private void notifica(List<Bean> beans) {
		beans.stream().forEach(t -> {
			controleSM.getNotifier(t.getNotifier()).notifica(t);
		});
	}
}