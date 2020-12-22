package org.acme.core;

/**Retorna a implementação baseado no rotulo de cada implementação. @namned
 * 
 * @author Luiz
 *
 * @param <T>
 */
public interface SelectorCDI<T> {

	public T selectService(String name);
}
