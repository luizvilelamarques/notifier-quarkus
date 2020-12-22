package org.acme.core;

import org.acme.core.bean.Bean;
import org.acme.core.notify.Notifier;

/**Obtém um notificador pelo nome informado na configuração da fila
 * 
 * @author Luiz
 *
 */
public interface BuildNotifier {

	public Notifier<Bean> getNotifier(String notificador);
}
