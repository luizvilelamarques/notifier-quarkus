package org.acme.jira;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.acme.core.Service;
import org.acme.core.bean.Bean;

@Named("jira")
@Singleton
public class JiraTicketManager implements Service<Bean> {

	@Override
	public List<Bean> getBeans() {
		// conectar na base do hpsm
		return null;
	}

}
