package org.acme.core.impl;

import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.acme.core.SelectorCDI;
import org.acme.core.bean.Bean;
import org.acme.core.notify.Notifier;

@Singleton
@Named("notifierSelector")
public class SelectorNotifier implements SelectorCDI<Notifier<Bean>> {

	private static final String DEFAULT_NOTIFIER = "rocketchat";
	
	@Inject
    Instance<Notifier<Bean>> notifiers;
	
	@Override
	public Notifier<Bean> selectService(String name) {
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
