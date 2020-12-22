package org.acme.hpsm;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.acme.core.bean.Bean;
import org.acme.core.sourceService.Service;

@Named("hpsm")
@Singleton
public class HpsmTicketManager implements Service<Bean> {

	@Override
	public List<Bean> getBeans() {
		// TODO Auto-generated method stub
		return null;
	}

}
