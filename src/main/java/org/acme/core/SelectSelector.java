package org.acme.core;

import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.acme.core.bean.Bean;
import org.acme.core.notify.Notifier;

@Singleton
public class SelectSelector {

	private static final String DEFAULT_SM       = "hpsm";
	private static final String DEFAULT_NOTIFIER = "rocketchat";

    @Inject
    Instance<Service<Bean>> services;
    
    @Inject
    Instance<Notifier<Bean>> notifiers;

    public Service<Bean> selectService(String name) {
        Instance<Service<Bean>> serviceInstance = services.select(new NamedLiteral(name));
        if (!serviceInstance.isUnsatisfied()) {
            return serviceInstance.get();
        } else {
            return services.select(new NamedLiteral(DEFAULT_SM)).get();
        }
    }
    
    public Notifier<Bean> selectNotifier(String name) {
        Instance<Notifier<Bean>> notifierInstance = notifiers.select(new NamedLiteral(name));
        if (!notifierInstance.isUnsatisfied()) {
            return notifierInstance.get();
        } else {
            return notifiers.select(new NamedLiteral(DEFAULT_NOTIFIER)).get();
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
