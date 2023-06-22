package ule.edi.tree;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.border.EmptyBorder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTests {

  /*
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
   */
  private BinarySearchTreeImpl<Integer> ejemplo = null;

  /*
   * 10
   * | 5
   * | | 2
   * | | | ∅
   * | | | ∅
   * | | ∅
   * | 20
   * | | 15
   * | | | 12
   * | | | | ∅
   * | | | | ∅
   * | | ∅
   */
  private BinarySearchTreeImpl<Integer> other = null;

  private BinarySearchTreeImpl<Integer> ejemplo2 = null;

  private BinarySearchTreeImpl<Integer> ejemplo3 = null;

  private BinarySearchTreeImpl<Integer> empty = null;

  @Before
  public void setupBSTs() {

    ejemplo = new BinarySearchTreeImpl<Integer>();
    ejemplo.insert(10, 20, 5, 2, 15, 30);
    Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");

    other = new BinarySearchTreeImpl<Integer>();
    other.insert(10, 20, 5, 2, 15, 12);
    Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}");

    ejemplo2 = new BinarySearchTreeImpl<Integer>();
    ejemplo2.insert(50, 30, 30, 10, 40, 80, 80, 60);
    Assert.assertEquals("{50, {30(2), {10, ∅, ∅}, {40, ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}", ejemplo2.toString());

    ejemplo3 = new BinarySearchTreeImpl<Integer>();
    ejemplo3.insert(30, 10, 5, 2, 20, 15, 12);
    Assert.assertEquals("{30, {10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}, ∅}", ejemplo3.toString());

    empty = new BinarySearchTreeImpl<Integer>();
  }

  @Test
  public void testRemoveCountMayor1() {
    ejemplo.insert(20);
    ejemplo.insert(20);
    Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20(3), {15, ∅, ∅}, {30, ∅, ∅}}}");
    ejemplo.remove(20);
    Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20(2), {15, ∅, ∅}, {30, ∅, ∅}}}");
  }

  @Test
  public void testRemoveCountMayor1HastaVaciar() {
    ejemplo.insert(20);
    ejemplo.insert(20);
    Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20(3), {15, ∅, ∅}, {30, ∅, ∅}}}", ejemplo.toString());
    ejemplo.remove(20);
    Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20(2), {15, ∅, ∅}, {30, ∅, ∅}}}", ejemplo.toString());
    ejemplo.remove(20);
    Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}", ejemplo.toString());
    ejemplo.remove(20);
    Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {30, {15, ∅, ∅}, ∅}}", ejemplo.toString());
  }

  @Test
  public void testRemoveHoja() {
    ejemplo.remove(30);
    Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, ∅}}", ejemplo.toString());
  }

  @Test
  public void testRemove1Hijo() {
    ejemplo.insert(40);
    Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, {40, ∅, ∅}}}}", ejemplo.toString());
    ejemplo.remove(5);
    Assert.assertEquals("{10, {2, ∅, ∅}, {20, {15, ∅, ∅}, {30, ∅, {40, ∅, ∅}}}}", ejemplo.toString());
    ejemplo.remove(30);
    Assert.assertEquals("{10, {2, ∅, ∅}, {20, {15, ∅, ∅}, {40, ∅, ∅}}}", ejemplo.toString());
  }

  @Test
  public void testRemove2Hijos() {
    ejemplo.insert(40);
    Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, {40, ∅, ∅}}}}", ejemplo.toString());
    ejemplo.remove(20);
    Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {30, {15, ∅, ∅}, {40, ∅, ∅}}}", ejemplo.toString());
  }

  @Test
  public void testRemoveUntilEmpty() {
    ejemplo.remove(5);
    Assert.assertEquals("{10, {2, ∅, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}", ejemplo.toString());

    ejemplo.remove(20);
    Assert.assertEquals("{10, {2, ∅, ∅}, {30, {15, ∅, ∅}, ∅}}", ejemplo.toString());

    ejemplo.remove(10);
    Assert.assertEquals("{15, {2, ∅, ∅}, {30, ∅, ∅}}", ejemplo.toString());

    ejemplo.remove(15);
    Assert.assertEquals("{30, {2, ∅, ∅}, ∅}", ejemplo.toString());

    ejemplo.remove(30);
    Assert.assertEquals("{2, ∅, ∅}", ejemplo.toString());

    ejemplo.remove(2);
    Assert.assertEquals("∅", ejemplo.toString());

    Assert.assertTrue(ejemplo.isEmpty());
  }

  @Test
  public void testRemoveIsItOrganized() {
    empty.insert(20, 10, 5, 2, 8);
    Assert.assertEquals("{20, {10, {5, {2, ∅, ∅}, {8, ∅, ∅}}, ∅}, ∅}", empty.toString());

    empty.remove(10);
    Assert.assertEquals("{20, {5, {2, ∅, ∅}, {8, ∅, ∅}}, ∅}", empty.toString());
  }

  @Test
  public void testRemoveRootChildHasOneChild() {
    empty.insert(10, 5, 2);
    Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, ∅}", empty.toString());

    empty.remove(10);
    Assert.assertEquals("{5, {2, ∅, ∅}, ∅}", empty.toString());
  }

  @Test
  public void testRemoveRootChildHasTwoChildren() {
    empty.insert(10, 5, 2, 8);
    Assert.assertEquals("{10, {5, {2, ∅, ∅}, {8, ∅, ∅}}, ∅}", empty.toString());

    empty.remove(10);
    Assert.assertEquals("{5, {2, ∅, ∅}, {8, ∅, ∅}}", empty.toString());
  }

  @Test
  public void testRemoveCorrectFather() {
    ejemplo.remove(5);
    Assert.assertEquals("{10, {2, ∅, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}", ejemplo.toString());
    Assert.assertTrue(ejemplo.getLeftBST().father.equals(ejemplo)); // Father of 2 is 10

    ejemplo.remove(20);
    Assert.assertEquals("{10, {2, ∅, ∅}, {30, {15, ∅, ∅}, ∅}}", ejemplo.toString());
    Assert.assertTrue(ejemplo.getRightBST().father.equals(ejemplo)); // Father of 30 is 10
    Assert.assertTrue(ejemplo.getRightBST().getLeftBST().father.equals(ejemplo.getRightBST())); // Father of 15 is 30

    ejemplo.remove(10);
    Assert.assertEquals("{15, {2, ∅, ∅}, {30, ∅, ∅}}", ejemplo.toString());
    Assert.assertNull(ejemplo.father); // New root does not have father
  }

  @Test
  public void testRemoveElements() {
    Assert.assertEquals(3, ejemplo.remove(null, 99, 10, 5, 30));
  }

  @Test
  public void testRemoveAll() {
    ejemplo2.removeAll(80);
    Assert.assertEquals("{50, {30(2), {10, ∅, ∅}, {40, ∅, ∅}}, {60, ∅, ∅}}", ejemplo2.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveAllNullElement() {
    ejemplo2.removeAll(null);
  }

  @Test(expected = NoSuchElementException.class)
  public void testRemoveAllNoSuchElement() {
    empty.removeAll(80);
  }

  @Test
  public void testRemoveInstances() {
    ejemplo2.insert(30);
    ejemplo2.remove(30, 2);
    Assert.assertEquals("{50, {30, {10, ∅, ∅}, {40, ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}", ejemplo2.toString());
    ejemplo2.remove(80, 5);
    Assert.assertEquals("{50, {30, {10, ∅, ∅}, {40, ∅, ∅}}, {60, ∅, ∅}}", ejemplo2.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveInstancesNullElement() {
    ejemplo2.remove(null, 5);
  }

  @Test(expected = NoSuchElementException.class)
  public void testRemoveInstancesNoSuchElement() {
    empty.remove(80, 5);
  }

  @Test
  public void testTagHeightLeafEjemplo() {
    other.tagHeightLeaf();
    other.filterTags("height");
    Assert.assertEquals("{10, {5, {2 [(height, 3)]," + " ∅, ∅}, ∅}, {20, {15, {12 [(height, 4)]," + " ∅, ∅}, ∅}, ∅}}",
        other.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInsertException() {
    Integer i = null;
    other.insert(i);
  }

  @Test
  public void testContains() {
    Assert.assertTrue(other.contains(20));
    Assert.assertFalse(other.contains(37));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testContainsNull() {
    other.contains(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNullElement() {
    Integer i = null;
    other.remove(i);
  }

  @Test(expected = NoSuchElementException.class)
  public void testRemoveNoSuchElement() {
    other.remove(11);
  }

  @Test
  public void testInsert() {
    other.insert(20);
    Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20(2), {15, {12, ∅, ∅}, ∅}, ∅}}");
  }

  @Test
  public void testInsertCollection() {
    Collection<Integer> collection = new LinkedList<Integer>();
    collection.add(10);
    collection.add(20);
    collection.add(5);
    collection.add(2);
    collection.add(15);
    collection.add(null);
    collection.add(30);
    empty.insert(collection);
    Assert.assertEquals(empty.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");
  }

  @Test
  public void testInsertElements() {
    empty.insert(10, 20, 5, 2, 15, null, 30);
    Assert.assertEquals(empty.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");
  }

  @Test
  public void testSize() {
    Assert.assertEquals(0, empty.size());
    other.insert(20);
    other.insert(20);
    Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20(3), {15, {12, ∅, ∅}, ∅}, ∅}}");
    Assert.assertEquals(6, other.size());
  }

  @Test
  public void testInstancesCount() {
    Assert.assertEquals(0, empty.instancesCount());
    other.insert(20);
    other.insert(20);
    Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20(3), {15, {12, ∅, ∅}, ∅}, ∅}}");
    Assert.assertEquals(8, other.instancesCount());
  }

  @Test
  public void testCopy() {
    other.insert(20);
    BinarySearchTreeImpl<Integer> copy = other.copy();
    Assert.assertEquals(copy.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20(2), {15, {12, ∅, ∅}, ∅}, ∅}}");
  }

  @Test
  public void testIteratorWidth() {
    Iterator<Integer> it = ejemplo2.iteratorWidth();
    StringBuilder output = new StringBuilder();

    if (it.hasNext()) {
      output.append(it.next().toString());

    }

    while (it.hasNext()) {
      output.append(", ").append(it.next().toString());
    }
    Assert.assertEquals("50, 30, 80, 10, 40, 60", output.toString());

    it = empty.iteratorWidth();
    Assert.assertFalse(it.hasNext());
  }

  @Test
  public void testIteratorWidthInstances() {
    Iterator<Integer> it = ejemplo2.iteratorWidthInstances();
    StringBuilder output = new StringBuilder();

    if (it.hasNext()) {
      output.append(it.next().toString());

    }

    while (it.hasNext()) {
      output.append(", ").append(it.next().toString());
    }
    Assert.assertEquals("50, 30, 30, 80, 80, 10, 40, 60", output.toString());

    it = empty.iteratorWidthInstances();
    Assert.assertFalse(it.hasNext());
  }

  @Test(expected = NoSuchElementException.class)
  public void testGetSubtreeWithPath() {
    Assert.assertEquals("{10, ∅, ∅}", ejemplo2.getSubtreeWithPath("LL").toString());
    Assert.assertEquals("{80(2), {60, ∅, ∅}, ∅}", ejemplo2.getSubtreeWithPath("R").toString());
    Assert.assertEquals("{50, {30(2), {10, ∅, ∅}, {40, ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}",
        ejemplo2.getSubtreeWithPath("").toString());

    empty.getSubtreeWithPath("RR").toString();
  }

  @Test(expected = NoSuchElementException.class)
  public void testGetPath() {
    Assert.assertEquals("LL", ejemplo2.getPath(10));
    Assert.assertEquals("RL", ejemplo2.getPath(60));
    Assert.assertEquals("RL", ejemplo2.getPath(60));
    empty.getPath(60);
  }

  @Test
  public void testTagPosDescend() {
    ejemplo2.tagPosDescend();
    Assert.assertEquals(
        "{50 [(descend, 3)], {30(2) [(descend, 5)], {10 [(descend, 6)], ∅, ∅}, {40 [(descend, 4)], ∅, ∅}}, {80(2) [(descend, 1)], {60 [(descend, 2)], ∅, ∅}, ∅}}",
        ejemplo2.toString());
  }

  @Test
  public void testTagInternalInorder() {
    Assert.assertEquals(5, ejemplo3.tagInternalInorder());
    Assert.assertEquals(
        "{30 [(internal, 7)], {10 [(internal, 3)], {5 [(internal, 2)], {2, ∅, ∅}, ∅}, {20 [(internal, 6)], {15 [(internal, 5)], {12, ∅, ∅}, ∅}, ∅}}, ∅}",
        ejemplo3.toString());
  }

  @Test
  public void testTagOnlySonPreorder() {
    Assert.assertEquals(4, ejemplo3.tagOnlySonPreorder());
    Assert.assertEquals(
        "{30, {10 [(onlySon, 2)], {5, {2 [(onlySon, 4)], ∅, ∅}, ∅}, {20, {15 [(onlySon, 6)], {12 [(onlySon, 7)], ∅, ∅}, ∅}, ∅}}, ∅}",
        ejemplo3.toString());
  }

  @Test
  public void testGetRoadUpRight() {
    empty.insert(10, 5, 2, 7, 20, 15, 12, 30);
    Assert.assertEquals("{10, {5, {2, ∅, ∅}, {7, ∅, ∅}}, {20, {15, {12, ∅, ∅}, ∅}, {30, ∅, ∅}}}", empty.toString());
    Assert.assertEquals("30", empty.getRoadUpRight(7, 2, 2).toString());
    Assert.assertEquals(
        "{10 [(road, 3)], {5 [(road, 2)], {2, ∅, ∅}, {7 [(road, 1)], ∅, ∅}}, {20 [(road, 4)], {15, {12, ∅, ∅}, ∅}, {30 [(road, 5)], ∅, ∅}}}",
        empty.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testGetRoadUpRightWrongUp() {
    other.getRoadUpRight(12, 5, 1);
  }

  @Test(expected = NoSuchElementException.class)
  public void testGetRoadUpRightWrongRight() {
    other.getRoadUpRight(12, 1, 2);
  }

  @Test(expected = NoSuchElementException.class)
  public void testGetRoadUpRightEmptyResult() {
    other.getRoadUpRight(12, 1, 1);
  }

  @Test(expected = NoSuchElementException.class)
  public void testGetRoadUpRightDoesNotContainElement() {
    empty.getRoadUpRight(7, 2, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetRoadUpRightElemNull() {
    other.getRoadUpRight(null, 2, 2);
  }
}