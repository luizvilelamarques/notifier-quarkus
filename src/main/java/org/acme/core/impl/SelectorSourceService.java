package org.acme.core.impl;

import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.acme.core.SelectorCDI;
import org.acme.core.bean.Bean;
import org.acme.core.sourceService.Service;

@Singleton
@Named("serviceSelector")
public class SelectorSourceService implements SelectorCDI<Service<Bean>> {

	private static final String DEFAULT_SM = "hpsm";

    @Inject
    Instance<Service<Bean>> services;
    
	@Override
	public Service<Bean> selectService(String name) {
		Instance<Service<Bean>> serviceInstance = services.select(new NamedLiteral(name));
        if (!serviceInstance.isUnsatisfied()) {
            return serviceInstance.get();
        } else {
            return services.select(new NamedLiteral(DEFAULT_SM)).get();
        }
	}
	
	private class NamedLiteral extends AnnotationLiteral<Named> implements Named {

        private String value;

        NamedLiteral(String value) {
            this.value = value;
        }

        @Override
        public String value() {
            return value;
        }
    }

}
