package com.vulinh.java9;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"DataFlowIssue", "java:S5778", "OverwrittenKey"})
class CollectionFactoryMethodTest {

  @Test
  void testListOf() {
    // Not accepting null value
    assertThrows(NullPointerException.class, () -> System.out.println(List.of((Object) null)));

    var list = List.of(1, 2, 3);

    // Cannot remove
    assertThrows(UnsupportedOperationException.class, () -> list.remove(1));

    // Cannot add
    assertThrows(UnsupportedOperationException.class, () -> list.add(4));

    // Cannot modify
    assertThrows(UnsupportedOperationException.class, () -> list.set(2, 7));

    Integer[] array = {1, 2, 3, 4};

    var arrayList = Arrays.asList(array);

    // Cannot add
    assertThrows(UnsupportedOperationException.class, () -> arrayList.add(4));

    // Cannot remove
    assertThrows(UnsupportedOperationException.class, () -> arrayList.remove(2));

    // But can be modified
    arrayList.set(0, -1);

    // Modification on the arrayList reflects back to original backing array as well
    assertEquals(-1, array[0]);
  }

  @Test
  void testSetOf() {
    // Not accepting null value
    assertThrows(NullPointerException.class, () -> System.out.println(Set.of((Object) null)));

    var set = Set.of(1, 2, 3);

    // Cannot remove
    assertThrows(UnsupportedOperationException.class, () -> set.remove(1));

    // Cannot add
    assertThrows(UnsupportedOperationException.class, () -> set.add(4));

    // Cannot create set with duplicated elements
    assertThrows(
        IllegalArgumentException.class, () -> System.out.println(Set.of(1, 2, 3, 4, 5, 4)));
  }

  @Test
  void testMapOf() {
    // Not accepting null key
    assertThrows(NullPointerException.class, () -> System.out.println(Map.of(null, 1)));

    // Not accepting null value
    assertThrows(NullPointerException.class, () -> System.out.println(Map.of(1, null)));

    // Support up to 10 pairs of key-value
    var map = Map.of(1, 1);

    assertTrue(map.containsKey(1));

    assertEquals(Integer.valueOf(1), map.get(1));
  }

  @Test
  void testMapOfEntry() {
    // Deliberate error due to direct
    assertThrows(
        NullPointerException.class,
        () -> System.out.println(Map.ofEntries((Map.Entry<?, ?>) null)));

    // Not accepting null value
    assertThrows(
        NullPointerException.class, () -> System.out.println(Map.ofEntries(Map.entry(1, null))));

    // Not accepting null key
    assertThrows(
        NullPointerException.class, () -> System.out.println(Map.ofEntries(Map.entry(null, 1))));
  }

  @Test
  void testManipulateMap() {
    var map = Map.ofEntries(Map.entry(1, 1), Map.entry(2, 2), Map.entry(3, 3));

    assertThrows(UnsupportedOperationException.class, () -> map.remove(1));

    assertThrows(UnsupportedOperationException.class, () -> map.put(4, 4));

    assertThrows(UnsupportedOperationException.class, map::clear);
  }
}
