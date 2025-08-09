package com.vulinh.java21;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vulinh.java21.Shape.Circle;
import com.vulinh.java21.Shape.Rectangle;
import com.vulinh.java21.Shape.Triangle;
import org.junit.jupiter.api.Test;

class DeconstructingForRecordTest {

  private static double calculatePerimeterOld(Shape shape) {
    return switch (shape) {
      case Circle c -> Math.PI * 2 * c.radius();
      case Rectangle r -> 2 * (r.width() + r.height());
      case Triangle t -> t.a() + t.b() + t.c();
    };
  }

  private static double calculatePerimeter(Shape shape) {
    return switch (shape) {
      case Circle(var r) -> Math.PI * 2 * r;
      case Rectangle(var w, var h) -> 2 * (w + h);
      case Triangle(var a, var b, var c) -> a + b + c;
    };
  }

  @SuppressWarnings("DeconstructionCanBeUsed")
  private static double calculateAreaOld(Shape shape) {
    if (shape instanceof Circle c) {
      var r = c.radius();

      return Math.PI * r * r;
    }

    if (shape instanceof Rectangle r) {
      return r.width() * r.height();
    }

    // Heron's formula for calculating the area of a triangle
    if (shape instanceof Triangle t) {
      var a = t.a();
      var b = t.b();
      var c = t.c();

      var halfPerimeter = (a + b + c) / 2;

      return Math.sqrt(
          halfPerimeter * (halfPerimeter - a) * (halfPerimeter - b) * (halfPerimeter - c));
    }

    // A method must return or throw something
    throw new IllegalArgumentException("Unknown shape: " + shape.getClass().getName());
  }

  private static double calculateArea(Shape shape) {
    if (shape instanceof Circle(var r)) {
      return Math.PI * r * r;
    }

    if (shape instanceof Rectangle(var w, var h)) {
      return w * h;
    }

    // Heron's formula for calculating the area of a triangle
    if (shape instanceof Triangle(var a, var b, var c)) {
      var halfPerimeter = (a + b + c) / 2;

      return Math.sqrt(
          halfPerimeter * (halfPerimeter - a) * (halfPerimeter - b) * (halfPerimeter - c));
    }

    // A method must return or throw something
    throw new IllegalArgumentException("Unknown shape: " + shape.getClass().getName());
  }

  @Test
  void testCircleCalculations() {
    var circle = new Circle(5.0);

    var expectedPerimeter = 2 * Math.PI * 5.0;

    assertEquals(expectedPerimeter, calculatePerimeter(circle), 0.001);

    assertEquals(expectedPerimeter, calculatePerimeterOld(circle), 0.001);

    var expectedArea = Math.PI * 5.0 * 5.0;

    assertEquals(expectedArea, calculateArea(circle), 0.001);

    assertEquals(expectedArea, calculateAreaOld(circle), 0.001);
  }

  @Test
  void testRectangleCalculations() {
    var rectangle = new Rectangle(4.0, 6.0);

    var expectedPerimeter = 2 * (4.0 + 6.0);

    assertEquals(expectedPerimeter, calculatePerimeter(rectangle), 0.001);

    assertEquals(expectedPerimeter, calculatePerimeterOld(rectangle), 0.001);

    var expectedArea = 4.0 * 6.0;

    assertEquals(expectedArea, calculateArea(rectangle), 0.001);

    assertEquals(expectedArea, calculateAreaOld(rectangle), 0.001);
  }

  @Test
  void testTriangleCalculations() {
    var triangle = new Triangle(3.0, 4.0, 5.0);

    var expectedPerimeter = 3.0 + 4.0 + 5.0;

    assertEquals(expectedPerimeter, calculatePerimeter(triangle), 0.001);

    assertEquals(expectedPerimeter, calculatePerimeterOld(triangle), 0.001);

    var expectedArea = Math.sqrt(6.0 * (6.0 - 3.0) * (6.0 - 4.0) * (6.0 - 5.0));

    assertEquals(expectedArea, calculateArea(triangle), 0.001);

    assertEquals(expectedArea, calculateAreaOld(triangle), 0.001);
  }
}
