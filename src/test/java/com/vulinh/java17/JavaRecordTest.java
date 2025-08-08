package com.vulinh.java17;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.With;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

class JavaRecordTest {

  @Test
  void testJavaRecord() {
    var point1 = new Point(1, 1);

    // to the opposite quadrant
    var point2 = point1.withX(-point1.x()).withY(-point1.y());

    assertEquals(1, point1.x());
    assertEquals(1, point1.y());

    assertEquals(-1, point2.x());
    assertEquals(-1, point2.y());
  }

  @Test
  void testJavaRecordCompactConstructor() {
    var container = new StudentContainer(null);

    assertNotNull(container.names());

    var container2 = new StudentContainer(new ArrayList<>(List.of("Alice", "Bob")));

    // Each call to #names() creates a different object
    assertNotSame(container2.names(), container2.names());

    // Their contents are the same
    assertEquals(container2.names(), container2.names());

    var copyOfName = container2.names();

    copyOfName.set(0, "Alisa");

    assertEquals("Alice", container2.names().getFirst());
    assertEquals("Alisa", copyOfName.getFirst());
  }

  @With
  @Builder
  public record Point(double x, double y) {}

  public record StudentContainer(List<String> names) {

    // Compact constructor
    public StudentContainer {
      names = CollectionUtils.isEmpty(names) ? new ArrayList<>() : names;
    }

    // Defensive copy
    @Override
    public List<String> names() {
      return new ArrayList<>(names);
    }
  }
}
