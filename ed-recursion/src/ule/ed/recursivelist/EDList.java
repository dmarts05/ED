package ule.ed.recursivelist;


public interface EDList<T> {
	/**
	 * TAD 'EDList'
	 * 
	 * Almacena una colección de objetos de tipo T, permitiendo
	 * elementos repetidos.
	 * 
	 * Ejemplo: (A B C A B D )
	 *
	 * 
	 * Excepciones
	 * 
	 * No se permiten elementos null. Si a cualquier método que recibe
	 * un elemento se le pasa el valor null, lanzará una excepción
	 * NullPointerException.
	 * 
	 * Los valores de parámetros position deben ser mayores que cero y
	 * nunca negativos. Si se recibe un valor negativo o cero se lanzará
	 * IllegalArgumentException.
	 * 
	 * 
	 * Constructores
	 * 
	 * Se definirá un constructor por defecto que inicialice la instancia como lista
	 * vacía.
	 * 
	 * 
	 * Método  Object#toString()
	 * 
	 * El formato será mostrar el toString de los elementos separados por espacios
	 * (A B C D D D B ) el toString
	 *
	 * 
	 * @author profesor
	 *
	 * @param <T> tipo de elementos en la lista
	 */


	/**
	 * TODO RECURSIVO: Devuelve el número total de elementos en esta lista. <br>
	 * 
	 * Ejemplo:<br>
	 * Si una lista l contiene (A B C B D A B ): <br>
	 * l.size() -> 7
	 * 
	 * @return número total de elementos en esta lista
	 */
	public int size();

	
	/**
	 * Indica si esta lista está vacía
	 * 
	 * @return <code>true</code> si no contiene elementos
	 */
	public boolean isEmpty();

	
	/**
	 * TODO RECURSIVO: Añade un elemento como último elemento de la lista
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.addLast("C") la lista quedará (A
	 * B C C )
	 * 
	 * @param elem el elemento a añadir
	 * 
	 * @throws NullPointerException si elem es <code>null</code>
	 */
	public void addLast(T elem);

	
	/**
	 * TODO RECURSIVO: Añade un elemento como antepenúltimo elemento de la lista
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.addAntePenult("D") la lista quedará
	 * (A D B C )
	 * 
	 * Si la lista está vacía o tiene solamente un elemento lo inserta como primer elemento
	 * 
	 * @param elem el elemento a añadir
	 * 
	 * @throws NullPointerException si elem es <code>null</code>
	 */
	public void addAntePenult(T elem);

	
	/**
	 * TODO RECURSIVO: Añade un elemento en la posición pasada como parámetro desplazando los
	 * elementos que estén a partir de esa posición.
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.addPos("Z", 2) la lista quedará
	 * (A Z B C ).
	 * <p>
	 * Si position>size() se insertará como último elemento.
	 * 
	 * @param elem     el elemento a añadir
	 * @param position la posición en la que añadirá el elemento
	 * 
	 * @throws NullPointerException     si elem es <code>null</code>
	 * @throws IllegalArgumentException si position <= 0
	 * 
	 */
	public void addPos(T elem, int position);

	
		
	/**
	 * TODO RECURSIVO: Devuelve el elemento que está en position.
	 * <p>
	 * Si una lista l contiene (A B C D E ): <br>
	 * l.getElemPos(1) -> A <br>
	 * l.getElemPos(3) -> C <br>
	 * l.getElemPos(10) -> IllegalArgumentException
	 * 
	 * 
	 * @param position posición a comprobar para devolver el elemento
	 * 
	 * @throws IllegalArgumentException si position no está entre 1 y size()
	 * 
	 */
	public T getElemPos(int position);

	
	/**
	 * TODO RECURSIVO: Devuelve la posición de la primera aparición del elemento.
	 * <p>
	 * Si una lista l contiene (A B C B D A ): <br>
	 * l.getPosFirst("A") -> 1 <br>
	 * l.getPosFirst("B") -> 2 <br>
	 * l.getPosFirst("Z") -> NoSuchElementException
	 * 
	 * @param elem elemento a encontrar.
	 *
	 * @throws NullPointerException   si elem es <code>null</code>
	 * @throws NoSuchElementException si elem no está en la lista.
	 * 
	 */
	public int getPosFirst(T elem);

	
	/**
	 * TODO RECURSIVO: Devuelve la posición de la última aparición del elemento.
	 * <p>
	 * Si una lista l contiene (A B C B D A ): <br>
	 * l.getPosLast("A") -> 6 <br>
	 * l.getPosLast("B") -> 4 <br>
	 * l.getPosLast("Z") -> NoSuchElementException
	 * 
	 * @param elem elemento a encontrar.
	 * 
	 * @throws NullPointerException   si elem es <code>null</code>
	 * @throws NoSuchElementException si elem no está en la lista.
	 * 
	 */
	public int getPosLast(T elem);


	/**
	 * TODO RECURSIVO:  Elimina y devuelve el último elemento de la lista.
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.removeLast() la lista quedará (A
	 * B ) y devolverá C
	 * 
	 * @throws EmptyCollectionException si la lista es vacía
	 * 
	 */
	public T removelast() throws EmptyCollectionException;;

	
	/**
	 * TODO RECURSIVO:  Elimina y devuelve el penúltimo elemento de la lista.
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.removePenult() la lista quedará
	 * (A C ) y devolverá B
	 * 
	 * @throws EmptyCollectionException si la lista es vacía
	 * @throws NoSuchElementException   si la lista solo tiene un elemento
	 * 
	 */
	public T removePenult() throws EmptyCollectionException;;

	/**
	 * TODO RECURSIVO: Elimina y devuelve la primera aparición del elemento.
	 * <p>
	 * Si una lista l contiene (A B C B ) y hacemos l.removeFirstElem("B") la lista
	 * quedará (A C B ) y devolverá B
	 * 
	 * @param elem el elemento a eliminar
	 * 
	 * @throws EmptyCollectionException si la lista es vacía
	 * @throws NoSuchElementException   si la lista no contiene el elemento
	 * 
	 */
	public T removeFirstElem(T elem) throws EmptyCollectionException;;

	
	/**
	 * TODO RECURSIVO: Elimina y devuelve la última aparición del elemento.
	 * <p>
	 * Si una lista l contiene (A B C B ) y hacemos l.removeLastElem("B") la lista
	 * quedará (A B C ) y devolverá B
	 * 
	 * @param elem el elemento a eliminar
	 * 
	 * @throws EmptyCollectionException si la lista es vacía
	 * @throws NoSuchElementException   si la lista no contiene el elemento
	 * 
	 */
	public T removeLastElem(T elem) throws EmptyCollectionException;;


	/**
	 * TODO RECURSIVO: Crea una nueva lista inversa de esta lista. <br>
	 * Si esta lista es vacía devuelve la lista vacía. <br>
	 * 
	 * Ejemplo:<br>
	 * Si una lista l contiene (A B C ): <br>
	 * l.reverse().toString() -> (C B A )
	 * 
	 * @return lista inversa de esta lista
	 */
	public EDList<T> reverse();

	
	/**
	 * TODO RECURSIVO:
	 * Devuelve el toString de la sublista formada por los elementos situados entre las posiciones from hasta until incluidas en el sentido inverso. 
	 * 
	 *  Si from > size() se muestra desde el último de la lista
	 * 
	 *   Ejemplos: 
	 * l1=(A B C D E ) ; l1.toSringFromUntilReverse(3,1)  -> (C B A )  
	 * l1=(A B C D E ) ; l1.toSringFromUntilReverse(10,3)  -> (E D C )  
	 * l1=(A B C D E ) ; l1.toStringFromUntilReverse(20,5) -> (E )  
	 * 
     * @param from posición desde la que se empieza a considerar la lista (incluida)
	 * @param until posición hasta la que se incluyen elementos (incluida)
	 *  
	 * @return String de la sublista formada por los elementos en el rango establecido por los dos parámetros.
	 *
	 * @throws IllegalArgumentException si from o until son <=0 ; o si from < until
	 *
	 */
	public String toStringFromUntilReverse(int from, int until);

	
	/**
	 * TODO RECURSIVO: Devuelve un string con el toString de los elementos que ocupan posición par en la lista seguido del toString de los elementos que ocupan posición impar en orden inverso.
	 * Se debe utilizar un único método recursivo para el recorrido. No sería válido tener un método recursivo para el recorrido de los pares y otro método recursivo para el recorrido de los impares.
	 * <br>
	 * Ejemplos:<br>
	 * l1=(A B C D E F G )  l1.toStringEvenOdd() -> (B D F G E C A ) <br>
	 * l1=(A B C  ) l1.toStringEvenOdd() -> (B C A ) <br>
	 *
	 */	
	public String toStringEvenOdd();
	
}

