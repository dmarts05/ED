package ule.ed.plane;

/**
 * Al heredar de {@link Exception} se trata de una excepción de tipo "checked",
 * y como tal debe declararse mediante <code>throws</code> en los métodos que
 * pueden lanzarla.
 * 
 * @author profesor
 *
 */
public class NoneChildrenInAvionException extends Exception {


	/**
	 * Es interesante proporcionar un mensaje que explique la causa de la excepción
	 * 
	 * @param hint información sobre la causa de la excepción
	 */
	public NoneChildrenInAvionException() {
		super("El avión no tiene pasajeros niños.");
	}
}
