package ule.edi.tree;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * arbol binario de busqueda (binary search tree, BST).
 * 
 * El codigo fuente esta en UTF-8, y la constante EMPTY_TREE_MARK definida en
 * AbstractTreeADT del proyecto API deberia ser el simbolo de conjunto vacio:
 * ∅
 * 
 * Si aparecen caracteres "raros", es porque el proyecto no esta bien
 * configurado en Eclipse para usar esa codificacion de caracteres.
 *
 * En el toString() que esta ya implementado en AbstractTreeADT se usa el
 * formato:
 * 
 * Un arbol vaci­o se representa como "∅". Un Ã¡rbol no vacio como
 * "{(informacion rai­z), sub-arbol 1, sub-arbol 2, ...}".
 * 
 * Por ejemplo, {A, {B, ∅, ∅}, ∅} es un arbol binario con rai­z "A" y un
 * unico sub-arbol, a su izquierda, con rai­z "B".
 * 
 * El metodo render() tambien representa un arbol, pero con otro formato; por
 * ejemplo, un arbol {M, {E, ∅, ∅}, {S, ∅, ∅}} se muestra como:
 * 
 * M
 * | E
 * | | ∅
 * | | ∅
 * | S
 * | | ∅
 * | | ∅
 * 
 * Cualquier nodo puede llevar asociados pares (clave,valor) para adjuntar
 * informacion extra. Si es el caso, tanto toString() como render() mostraran
 * los pares asociados a cada nodo.
 * 
 * Con {@link #setTag(String, Object)} se inserta un par (clave,valor) y con
 * {@link #getTag(String)} se consulta.
 * 
 * 
 * Con <T extends Comparable<? super T>> se pide que exista un orden en los
 * elementos. Se necesita para poder comparar elementos al insertar.
 * 
 * Si se usara <T extends Comparable<T>> seria muy restrictivo; en su lugar se
 * permiten tipos que sean comparables no solo con exactamente T sino tambien
 * con tipos por encima de T en la herencia.
 * 
 * @param <T> tipo de la informacion en cada nodo, comparable.
 */
public class BinarySearchTreeImpl<T extends Comparable<? super T>> extends AbstractBinaryTreeADT<T> {

  BinarySearchTreeImpl<T> father; // referencia a su nodo padre)
  int count; // contador de instancias

  /**
   * Devuelve el arbol binario de busqueda izquierdo.
   */
  protected BinarySearchTreeImpl<T> getLeftBST() {
    // El atributo leftSubtree es de tipo AbstractBinaryTreeADT<T> pero
    // aqui­ se sabe que es ademas BST (binario de busqueda)
    //
    return (BinarySearchTreeImpl<T>) leftSubtree;
  }

  protected void setLeftBST(BinarySearchTreeImpl<T> left) {
    this.leftSubtree = left;
  }

  /**
   * Devuelve el arbol binario de busqueda derecho.
   */
  protected BinarySearchTreeImpl<T> getRightBST() {
    return (BinarySearchTreeImpl<T>) rightSubtree;
  }

  protected void setRightBST(BinarySearchTreeImpl<T> right) {
    this.rightSubtree = right;
  }

  /**
   * arbol BST vaci­o
   */
  public BinarySearchTreeImpl() {
    this.father = null;
    this.count = 0;
    this.content = null;
    this.setLeftBST(null);
    this.setRightBST(null);

  }

  public BinarySearchTreeImpl(BinarySearchTreeImpl<T> father) {
    this.father = father;
    this.count = 0;
    this.content = null;
    this.setLeftBST(null);
    this.setRightBST(null);
  }

  private BinarySearchTreeImpl<T> emptyBST(BinarySearchTreeImpl<T> father) {
    // Devuelve un nodo vacío
    return new BinarySearchTreeImpl<T>(father);
  }

  /**
   * Inserta los elementos que no sean null, de una coleccion en el arbol.
   * (si alguno es 'null', no lo inserta)
   * 
   * No se permiten elementos null.
   * 
   * @param elements valores a insertar.
   * @return numero de elementos insertados en el arbol (elementos diferentes de
   *         null)
   */
  public int insert(Collection<T> elements) {
    int counter = 0;

    for (T element : elements) {
      if (element != null) {
        insert(element);
        counter++;
      }
    }

    return counter;
  }

  /**
   * Inserta los elementos que no sean null, de un array en el arbol.
   * (si alguno es 'null', no lo inserta)
   * 
   * No se permiten elementos null.
   * 
   * @param elements elementos a insertar.
   * @return numero de elementos insertados en el arbol (elementos diferentes de
   *         null)
   */
  public int insert(T... elements) {
    int counter = 0;

    for (T element : elements) {
      if (element != null) {
        insert(element);
        counter++;
      }
    }

    return counter;
  }

  /**
   * Inserta (como hoja) un nuevo elemento en el arbol de busqueda.
   * 
   * Debe asignarse valor a su atributo father (referencia a su nodo padre o null
   * si es la rai­z)
   * 
   * No se permiten elementos null. Si element es null dispara
   * excepcion:IllegalArgumentException
   * Si el elemento ya existe en el arbol
   * no inserta un nodo nuevo, sino que incrementa el atributo count del nodo que
   * tiene igual contenido.
   * 
   * @param element valor a insertar.
   * @return true si se insertó en un nuevo nodo (no existia ese elemento en el
   *         arbol),
   *         false en caso contrario
   * @throws IllegalArgumentException si element es null
   */
  public boolean insert(T element) {
    if (element == null) {
      throw new IllegalArgumentException();
    }

    BinarySearchTreeImpl<T> node = find(this, element);

    if (node.content != null) {
      node.count++;
      return false;
    } else {
      node.setContent(element);
      node.count++;
      node.setLeftBST(emptyBST(node));
      node.setRightBST(emptyBST(node));

      return true;
    }
  }

  private BinarySearchTreeImpl<T> find(BinarySearchTreeImpl<T> node, T element) {
    if (!node.isEmpty()) {
      if (node.getContent().compareTo(element) == 0) {
        return node;
      } else if (node.getContent().compareTo(element) < 0) {
        return find(node.getRightBST(), element);
      } else {
        return find(node.getLeftBST(), element);
      }
    }

    return node;
  }

  /**
   * Busca el elemento en el arbol.
   * 
   * No se permiten elementos null.
   * 
   * @param element valor a buscar.
   * @return true si el elemento esta en el arbol, false en caso contrario
   * @throws IllegalArgumentException si element es null
   *
   */
  public boolean contains(T element) {
    if (element == null) {
      throw new IllegalArgumentException();
    }

    BinarySearchTreeImpl<T> node = find(this, element);

    return node.getContent() != null;
  }

  /**
   * devuelve la cadena formada por el contenido del árbol teniendo en cuenta que
   * si un nodo tiene su atributo count>1 pone entre paréntesis su valor justo
   * detrás del atributo elem
   * También debe mostrar las etiquetas que tenga el nodo (si las tiene)
   * 
   * CONSEJO: REVISAR LA IMPLEMENTACIÓN DE TOSTRING DE LA CLASE AbstractTreeADT
   * 
   * Por ejemplo: {M, {E(2), ∅, ∅}, {K(5), ∅, ∅}}
   * 
   * @return cadena con el contenido del árbol incluyendo su atributo count entre
   *         paréntesis si elemento tiene más de 1 instancia
   */
  public String toString() {
    if (!isEmpty()) {
      StringBuffer output = new StringBuffer();

      // Raíz
      output.append("{" + content.toString());

      if (count > 1) {
        output.append("(").append(count).append(")");
      }

      if (!tags.isEmpty()) {
        output.append(" [");

        List<String> sk = new LinkedList<String>(tags.keySet());

        Collections.sort(sk);
        for (String k : sk) {
          output.append("(" + k + ", " + tags.get(k) + "), ");
        }
        output.delete(output.length() - 2, output.length());
        output.append("]");
      }

      // Y cada sub-árbol
      for (int i = 0; i < getMaxDegree(); i++) {
        output.append(", " + getSubtree(i).toString());
      }
      // Cierra la "}" de este árbol
      output.append("}");

      return output.toString();
    } else {
      return AbstractTreeADT.EMPTY_TREE_MARK;
    }

  }

  /**
   * Importante: Solamente se puede recorrer el arbol una vez
   * 
   * Etiqueta cada nodo hoja con la etiqueta "height" y el valor correspondiente a
   * la
   * altura del nodo.
   * 
   * Por ejemplo, sea un arbol "A":
   * 
   * {10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}
   * 
   * 10
   * | 5
   * | | 2
   * | | | ∅
   * | | | ∅
   * | | ∅
   * | 20
   * | | 15
   * | | | ∅
   * | | | ∅
   * | | 30
   * | | | ∅
   * | | | ∅
   * 
   * 
   * el arbol quedara etiquetado:
   * 
   * {10,
   * {5, {2 [(height, 3)],∅, ∅}, ∅},
   * {20,{15, {12 [(height, 4)], ∅, ∅}, ∅}, ∅}
   * }
   * 
   */
  public void tagHeightLeaf() {
    tagHeightLeafRec(this, 1);
  }

  private void tagHeightLeafRec(BinarySearchTreeImpl<T> node, int height) {
    if (node.isLeaf()) {
      node.setTag("height", height);
    } else if (!node.isEmpty()) {
      height++;
      tagHeightLeafRec(node.getLeftBST(), height);
      tagHeightLeafRec(node.getRightBST(), height);
    }
  }

  /**
   * Devuelve un iterador que recorre los elementos (sin tener en cuenta el número
   * de instancias)del arbol por niveles segun
   * el recorrido en anchura
   * 
   * Por ejemplo, con el arbol
   * 
   * {50, {30(2), {10, ∅, ∅}, {40, ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}
   * 
   * y devolvera el iterador que recorrera los nodos en el orden: 50, 30, 80, 10,
   * 40, 60
   * 
   * 
   * 
   * @return iterador para el recorrido en anchura
   */
  public Iterator<T> iteratorWidth() {
    Queue<BinarySearchTreeImpl<T>> queue = new LinkedList<BinarySearchTreeImpl<T>>();
    Queue<T> result = new LinkedList<T>();

    if (!isEmpty()) {
      queue.add(this);

      while (!queue.isEmpty()) {
        BinarySearchTreeImpl<T> aux = queue.remove();

        if (!aux.isEmpty()) {
          result.add(aux.content);
          queue.add(aux.getLeftBST());
          queue.add(aux.getRightBST());
        }
      }
    }

    return result.iterator();
  }

  /**
   * Devuelve un iterador que recorre los elementos (teniendo en cuenta el número
   * de instancias)del arbol por niveles segun
   * el recorrido en anchura
   * 
   * Por ejemplo, con el arbol
   * 
   * {50, {30(2), {10, ∅, ∅}, {40, ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}
   * 
   * y devolvera el iterador que recorrera los nodos en el orden: 50, 30, 30, 80,
   * 80, 10, 40, 60
   * 
   * @return iterador para el recorrido en anchura
   */
  public Iterator<T> iteratorWidthInstances() {
    Queue<BinarySearchTreeImpl<T>> queue = new LinkedList<BinarySearchTreeImpl<T>>();
    Queue<T> result = new LinkedList<T>();

    if (!isEmpty()) {
      queue.add(this);

      while (!queue.isEmpty()) {
        BinarySearchTreeImpl<T> aux = queue.remove();

        if (!aux.isEmpty()) {
          int amount = aux.count;

          while (amount > 0) {
            result.add(aux.content);
            amount--;
          }

          queue.add(aux.getLeftBST());
          queue.add(aux.getRightBST());
        }
      }
    }

    return result.iterator();
  }

  /**
   * Cuenta el número de elementos diferentes del arbol (no tiene en cuenta las
   * instancias)
   * 
   * Por ejemplo, con el arbol
   * 
   * {50, {30(2), {10, ∅, ∅}, {40(4), ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}
   * 
   * la llamada a ejemplo.instancesCount() devolvera 6
   * 
   * @return el numero de elementos diferentes del arbol
   */
  public int size() {
    if (isEmpty()) {
      return 0;
    } else {
      return sizeRec(this);
    }
  }

  private int sizeRec(BinarySearchTreeImpl<T> node) {
    if (node.isEmpty()) {
      return 0;
    } else {
      return 1 + sizeRec(node.getLeftBST()) + sizeRec(node.getRightBST());
    }
  }

  /**
   * Cuenta el número de instancias de elementos diferentes del arbol
   * 
   * Por ejemplo, con el arbol ejemplo=
   * 
   * {50, {30(2), {10, ∅, ∅}, {40(4), ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}
   * 
   * la llamada a ejemplo.instancesCount() devolvera 11
   * 
   * @return el número de instancias de elementos del arbol
   */
  public int instancesCount() {
    if (isEmpty()) {
      return 0;
    } else {
      return instancesCountRec(this);
    }
  }

  private int instancesCountRec(BinarySearchTreeImpl<T> node) {
    if (node.isEmpty()) {
      return 0;
    } else {
      return node.count + instancesCountRec(node.getLeftBST()) + instancesCountRec(node.getRightBST());
    }
  }

  /**
   * Devuelve el sub-árbol indicado. (para tests)
   * path será el camino para obtener el sub-arbol. Está formado por L y R.
   * Si se codifica "bajar por la izquierda" como "L" y
   * "bajar por la derecha" como "R", el camino desde un
   * nodo N hasta un nodo M (en uno de sus sub-árboles) será la
   * cadena de Ls y Rs que indica cómo llegar desde N hasta M.
   *
   * Se define también el camino vacío desde un nodo N hasta
   * él mismo, como cadena vacía.
   * 
   * Por ejemplo, con el arbol ejemplo=
   * 
   * {50, {30(2), {10, ∅, ∅}, {40(4), ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}
   * 
   * la llamada a ejemplo.getSubtreeWithPath("LL").toString() devolvera "{10, ∅,
   * ∅}"
   * la llamada a ejemplo.getSubtreeWithPath("R").toString() devolvera "{80(2),
   * {60, ∅, ∅}, ∅}"
   * la llamada a ejemplo.getSubtreeWithPath("").toString() devolvera "{50,
   * {30(2), {10, ∅, ∅}, {40(4), ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}"
   * la llamada a ejemplo.getSubtreeWithPath("RR").toString() disparará excepción
   * NoSuchElementException
   * 
   * Si el subarbol no existe lanzará la excepción NoSuchElementException.
   * 
   * @param path
   * @return
   * @throws NoSuchElementException si el subarbol no existe
   */
  public BinarySearchTreeImpl<T> getSubtreeWithPath(String path) {
    BinarySearchTreeImpl<T> result = getSubTreeWithPathRec(this, path);

    if (result.isEmpty())
      throw new NoSuchElementException();

    return result;
  }

  private BinarySearchTreeImpl<T> getSubTreeWithPathRec(BinarySearchTreeImpl<T> node, String path) {
    if (!node.isEmpty() && !path.equals("")) {
      if (path.charAt(0) == 'L') {
        return getSubTreeWithPathRec(node.getLeftBST(), path.substring(1));
      } else {
        return getSubTreeWithPathRec(node.getRightBST(), path.substring(1));
      }
    }

    return node;
  }

  /**
   * Devuelve el String que representa el camino formado por L's y R's desde
   * la raiz hasta el elemento pasado como parámetro.
   * Se codifica "bajar por la izquierda" como "L" y
   * "bajar por la derecha" como "R", el camino desde un
   * nodo N hasta un nodo M (en uno de sus sub-árboles) será la
   * cadena de Ls y Rs que indica cómo llegar desde N hasta M.
   *
   * Se define también el camino vacío desde un nodo N hasta
   * él mismo, como cadena vacía.
   * 
   * Por ejemplo, con el arbol ejemplo=
   * 
   * {50, {30(2), {10, ∅, ∅}, {40(4), ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}
   * 
   * la llamada a ejemplo.getPath(10) devolvera "LL"
   * la llamada a ejemplo.getPath(60) devolvera "RL"
   * la llamada a ejemplo.getPath(50) devolvera ""
   * la llamada a ejemplo.getPath(100) disparará excepción NoSuchElementException
   * 
   * Si el elemento no existe lanzará la excepción NoSuchElementException.
   * 
   * @param elem
   * @return camino hasta el elemento
   * @throws NoSuchElementException si el elemento no existe
   */
  public String getPath(T elem) {
    if (!contains(elem))
      throw new NoSuchElementException();

    StringBuilder output = new StringBuilder();
    getPathRec(this, elem, output);

    return output.toString();
  }

  public void getPathRec(BinarySearchTreeImpl<T> node, T element, StringBuilder output) {
    if (node.getContent().compareTo(element) == 0) {
      return;
    } else if (node.getContent().compareTo(element) < 0) {
      output.append("R");
      getPathRec(node.getRightBST(), element, output);
    } else {
      output.append("L");
      getPathRec(node.getLeftBST(), element, output);
    }
  }

  /**
   * Importante: Solamente se puede recorrer el arbol una vez
   * 
   * Recorre en orden descendente el arbol etiquetando todos sus nodos con la
   * etiqueta "descend" y
   * el valor correspondiente a la posición en dicho recorrido.
   * 
   * 
   * Por ejemplo, sea el arbol ejemplo
   * 
   * {50, {30(2), {10, ∅, ∅}, {40(4), ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}
   * 
   * la llamada a ejemplo.tagPosDescend() el arbol quedaria etiquetado:
   * 
   * {50 [(descend, 3)], {30(2) [(descend, 5)], {10 [(descend, 6)], ∅, ∅},
   * {40(4) [(descend, 4)], ∅, ∅}}, {80(2) [(descend, 1)], {60 [(descend, 2)], ∅,
   * ∅}, ∅}}
   * 
   */
  public void tagPosDescend() {
    int[] counter = new int[] { 1 };
    tagPosDescendRec(this, counter);
  }

  private void tagPosDescendRec(BinarySearchTreeImpl<T> node, int[] counter) {
    if (!node.isEmpty()) {
      tagPosDescendRec(node.getRightBST(), counter);
      node.setTag("descend", counter[0]);
      counter[0] += 1;
      tagPosDescendRec(node.getLeftBST(), counter);
    }
  }

  /**
   * Importante: Solamente se puede recorrer el arbol una vez
   * 
   * Calcula y devuelve el numero de nodos internos del árbol (no sean hojas) y
   * etiqueta cada
   * nodo interno con la etiqueta "internal" y el valor correspondiente a su
   * posicion segun el
   * recorrido inorden en este arbol.
   * 
   * La rai­z se considera nodo interno.
   * 
   * Por ejemplo, sea un arbol ejemplo:
   * 
   * {30, {10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}, ∅}
   * 
   * tras la llamada a ejemplo.tagInternalInorder() devolvería 5 y dejaría el
   * arbol etiquetado:
   * 
   * {30[(internal, 7)], {10 [(internal, 3)], {5[(internal, 2)], {2, ∅, ∅}, ∅},
   * {20 [(internal, 6)],
   * {15 [(internal, 5)], {12, ∅, ∅}, ∅}, ∅}, ∅}
   * 
   * 
   */
  public int tagInternalInorder() {
    int[] counter = new int[] { 1, 0 };
    tagInternalInorderRec(this, counter);

    return counter[1];
  }

  private void tagInternalInorderRec(BinarySearchTreeImpl<T> node, int[] counter) {
    if (!node.isEmpty()) {
      tagInternalInorderRec(node.getLeftBST(), counter);

      if (!node.isLeaf()) {
        node.setTag("internal", counter[0]);
        counter[1] += 1;
      }

      counter[0] += 1;
      tagInternalInorderRec(node.getRightBST(), counter);
    }
  }

  /**
   * Importante: Solamente se puede recorrer el arbol una vez
   * 
   * Calcula y devuelve el numero de nodos que son hijos unicos y etiqueta cada
   * nodo que sea hijo unico (no tenga hermano hijo del mismo padre) con la
   * etiqueta "onlySon" y el valor correspondiente a su posicion segun el
   * recorrido preorden en este arbol.
   * 
   * La rai­z no se considera hijo unico.
   * 
   * Por ejemplo, sea un arbol ejemplo, que tiene 3 hijos unicos,
   * la llamada a ejemplo.tagOnlySonPreorder() devuelve 3 y los va etiquetando
   * segun su recorrido en preorden.
   * 
   * {30, {10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}, ∅}
   * 
   *
   * el arbol quedari­a etiquetado:
   * 
   * {30, {10 [(onlySon, 2)], {5, {2 [(onlySon, 4)], ∅, ∅}, ∅}, {20, {15
   * [(onlySon, 6)], {12
   * [(onlySon, 7)], ∅, ∅}, ∅}, ∅}, ∅}
   * 
   */
  public int tagOnlySonPreorder() {
    int[] counter = new int[] { 1, 0 };
    tagOnlySonPreorderRec(this, new BinarySearchTreeImpl<T>(this), counter);

    return counter[1];
  }

  private void tagOnlySonPreorderRec(BinarySearchTreeImpl<T> node, BinarySearchTreeImpl<T> brother, int[] counter) {
    if (!node.isEmpty()) {
      if (brother.isEmpty() && node != this) {
        node.setTag("onlySon", counter[0]);
        counter[1] += 1;
      }

      counter[0] += 1;

      tagOnlySonPreorderRec(node.getLeftBST(), node.getRightBST(), counter);

      tagOnlySonPreorderRec(node.getRightBST(), node.getLeftBST(), counter);
    }
  }

  /**
   * Busca y devuelve a partir del nodo que contiene el elemento pasado como
   * parámetro
   * el elemento que está up posiciones hacia arriba y right hacia abajo bajando
   * por la rama derecha.
   * Primero debe encontrar el elemento y despues comprueba si el nodo que
   * contiene ese elemento
   * tiene nodo a través del camino indicado por los otros dos parámetros.
   * Debe etiquetar desde el nodo que contiene el elemento, hasta su objetivo, los
   * nodos del camino
   * con un número consecutivo empezando por el 1 en el elemento buscado.
   * 
   * Por ejemplo: para el árbol ejemplo= {10, {5, {2, ∅, ∅}, {7,∅, ∅},}, {20, {15,
   * {12, ∅, ∅}, ∅ },{30, ∅, ∅}}}.
   * 
   * Si se hace ejemplo.getRoadUpRight("7",2,2) devolverá el elemento 30 y
   * etiquetará los nodos 7, 5, 10, 20, 30 con numeros consecutivos
   * y la etiqueta road.
   * 
   * Así el árbol quedaría etiquetado: 10 [(road, 3)],{5[(road, 2)], {2, ∅, ∅}, {7
   * [(road, 1)],∅, ∅},}, {20 [(road, 4)], {15, {12, ∅, ∅}, ∅},{30 [(road, 5)], ∅,
   * ∅}}}
   * siendo el nodo que contiene el 30 el nodo que devuelve.
   * 
   * @throws NoSuchElementException   si el elemento a comprobar no esta en el
   *                                  arbol
   * @throws IllegalArgumentException si element es null
   */
  public T getRoadUpRight(T elem, int up, int right) {
    if (elem == null)
      throw new IllegalArgumentException();
    else if (!contains(elem))
      throw new NoSuchElementException();

    int counter = 1;
    BinarySearchTreeImpl<T> node = find(this, elem);

    while (up > 0) {
      node.setTag("road", counter);
      if (node.father == null)
        throw new NoSuchElementException();
      node = node.father;
      counter++;
      up--;
    }

    while (right > 0) {
      node.setTag("road", counter);
      if (node.getRightBST() == null)
        throw new NoSuchElementException();
      node = node.getRightBST();
      counter++;
      right--;
    }

    node.setTag("road", counter); // Tag last element

    if (node.isEmpty())
      throw new NoSuchElementException();

    return node.content;
  }

  /**
   * Crea y devuelve un árbol exactamente igual que el this
   * 
   * @return un arbol exactamente igual (misma estructura y contenido) que el
   *         arbol this
   */
  public BinarySearchTreeImpl<T> copy() {
    BinarySearchTreeImpl<T> node = new BinarySearchTreeImpl<T>();

    if (!isEmpty()) {
      node.setContent(content);
      node.count = count;
      node.setLeftBST(getLeftBST().copy());
      node.setRightBST(getRightBST().copy());
    }

    return copyRec(node);
  }

  private BinarySearchTreeImpl<T> copyRec(BinarySearchTreeImpl<T> nodeFather) {
    BinarySearchTreeImpl<T> node = new BinarySearchTreeImpl<T>(nodeFather);

    if (!isEmpty()) {
      node.setContent(content);
      node.count = count;
      node.setLeftBST(getLeftBST().copyRec(node));
      node.setRightBST(getRightBST().copyRec(node));
    }

    return node;
  }

  /**
   * Elimina los valores en un array del Arbol.
   * Devuelve el número de elementos que pudo eliminar del árbol
   * (no podrá eliminar los elemenots 'null' o que no los contiene el arbol)
   * 
   * return numero de elementos eliminados del arbol
   */
  public int remove(T... elements) {
    int numRemoved = 0;

    for (T element : elements) {
      if (element != null && contains(element)) {
        remove(element);
        numRemoved++;
      }
    }
    return numRemoved;
  }

  /**
   * Elimina un elemento del arbol. Si el atributo count del nodo que contiene el
   * elemento es >1, simplemente se decrementará este valor en una unidad
   * 
   * Si hay que eliminar el nodo, y tiene dos hijos, se tomara el criterio de
   * sustituir el
   * elemento por el menor de sus mayores y eliminar el menor de los mayores.
   * 
   * @throws NoSuchElementException   si el elemento a eliminar no esta en el
   *                                  arbol
   * @throws IllegalArgumentException si element es null
   *
   */
  public void remove(T element) {
    if (element == null)
      throw new IllegalArgumentException();
    else if (!contains(element))
      throw new NoSuchElementException();

    BinarySearchTreeImpl<T> node = find(this, element);

    if (node.count > 1) {
      node.count--;
    } else if (node.getDegree() == 2) { // Node has two children
      removeTwoChild(node);
    } else if (node.isLeaf()) {
      removeLeaf(node, node.content);
    } else { // Node has one child
      removeOneChild(node, node.content);
    }
  }

  private void removeLeaf(BinarySearchTreeImpl<T> node, T element) {
    node.count = 0;
    node.content = null;
    node.setLeftBST(null);
    node.setRightBST(null);
  }

  private void removeOneChild(BinarySearchTreeImpl<T> node, T element) {
    BinarySearchTreeImpl<T> child = null;

    if (!node.getLeftBST().isEmpty()) { // Node has one child to the left
      child = node.getLeftBST();
    } else { // Node has one child to the right
      child = node.getRightBST();
    }

    if (node.father == null) {
      node.setContent(child.content);
      node.count = child.count;

      if (child.getDegree() == 2) { // Child has two children
        node.setLeftBST(child.getLeftBST());
        node.setRightBST(child.getRightBST());
      } else if (child.isLeaf()) {
        removeLeaf(child, child.content);
      } else { // Child has one child
        removeOneChild(child, child.content);
      }
    } else if (isNodeLeftSide(node, element)) {
      node.father.setLeftBST(child);
    } else {
      node.father.setRightBST(child);
    }

    child.father = node.father;
  }

  private void removeTwoChild(BinarySearchTreeImpl<T> node) {
    BinarySearchTreeImpl<T> minNode = getMinNode(node.getRightBST());

    node.setContent(minNode.content);
    node.count = minNode.count;

    if (minNode.isLeaf()) {
      removeLeaf(minNode, minNode.content);
    } else {
      removeOneChild(minNode, minNode.content);
    }
  }

  private boolean isNodeLeftSide(BinarySearchTreeImpl<T> node, T element) {
    return !node.father.getLeftBST().isEmpty() && node.father.getLeftBST().getContent().compareTo(element) == 0;
  }

  private BinarySearchTreeImpl<T> getMinNode(BinarySearchTreeImpl<T> node) {
    if (!node.getLeftBST().isEmpty()) {
      return getMinNode(node.getLeftBST());
    }

    return node;
  }

  /**
   * Decrementa el número de instancias del elemento en num unidades.
   * Si count queda en cero o negativo, se elimina el elemento del arbol.
   * 
   * 
   * Si hay que eliminar el nodo, y tiene dos hijos, se tomara el criterio de
   * sustituir el
   * elemento por el menor de sus mayores y eliminar el menor de los mayores.
   * 
   * @throws NoSuchElementException   si el elemento a eliminar no esta en el
   *                                  arbol
   * @throws IllegalArgumentException si element es null
   */
  public void remove(T element, int num) {
    if (element == null)
      throw new IllegalArgumentException();
    else if (!contains(element))
      throw new NoSuchElementException();

    while (num > 0 && contains(element)) {
      remove(element);
      num--;
    }

  }

  /**
   * Elimina todas las instancias del elemento en el árbol
   * eliminando del arbol el nodo que contiene el elemento .
   * 
   * 
   * Se tomara el criterio de sustituir el elemento por el menor de sus mayores
   * y eliminar el menor de los mayores.
   * 
   * @throws NoSuchElementException   si el elemento a eliminar no esta en el
   *                                  arbol
   * @throws IllegalArgumentException si element es null
   */
  public int removeAll(T element) {
    if (element == null)
      throw new IllegalArgumentException();
    else if (!contains(element))
      throw new NoSuchElementException();

    int numRemoved = 0;

    while (contains(element)) {
      remove(element);
      numRemoved++;
    }

    return numRemoved;

  }

}
