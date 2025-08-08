package com.vulinh.java10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class VarContextualKeywordTest {

  record TestObject(List<Integer> list1, ArrayList<Integer> list2) {}

  @Test
  void testVarKeyword() throws NoSuchFieldException {
    var obj =
        new TestObject(new ArrayList<>(List.of(1, 2, 3, 4)), new ArrayList<>(List.of(5, 6, 7, 8)));

    var field1 = obj.getClass().getDeclaredField("list1");
    var field2 = obj.getClass().getDeclaredField("list2");

    assertEquals(List.class, field1.getType());
    assertEquals(ArrayList.class, field2.getType());

    TestObject obj2 =
        new TestObject(new ArrayList<>(List.of(1, 2, 3, 4)), new ArrayList<>(List.of(5, 6, 7, 8)));

    assertEquals(obj, obj2);
  }
}
