package org.acme.core;

import org.acme.core.bean.Bean;
import org.acme.core.sourceService.Service;

/**Obtém um fonte de dados de eventos para serem recuperados. a partir da configuração.
 * 
 * @author Luiz
 *
 */
public interface BuildSourceService {

	public Service<Bean> getSourceService(String source);
}
