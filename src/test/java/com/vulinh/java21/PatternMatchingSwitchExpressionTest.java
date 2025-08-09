package com.vulinh.java21;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

class PatternMatchingSwitchExpressionTest {

  @Test
  @SuppressWarnings("java:S5778")
  void tetsPatternMatchingSwitchExpression() {
    var tank = new Vehicle.Tank("T-90MS", 125);
    var ship = new Vehicle.Ship("USS Iowa", 45000);
    var airplane = new Vehicle.Airplane("Boeing 747", 296);

    assertEquals("This tank (T-90MS) hurts, with the caliber of 125.0 mm", printInfo(tank));
    assertEquals(
        "This beauty, called USS Iowa can displace about 45000.0 tons of water", printInfo(ship));
    assertEquals(
        "We need to reach at least 296.0 kmh to be able to take off this plane named Boeing 747",
        printInfo(airplane));

    assertThrows(
        UnsupportedOperationException.class, () -> printInfo(new Vehicle.Motorbike("Jupiter MX")));
  }

  private static String printInfo(Vehicle vehicle) {
    var name = vehicle.name;

    return switch (vehicle) {
      case Vehicle.Tank tank ->
          "This tank (%s) hurts, with the caliber of %s mm".formatted(name, tank.gunCaliber);
      case Vehicle.Ship ship ->
          "This beauty, called %s can displace about %s tons of water"
              .formatted(name, ship.waterDisplacement);
      case Vehicle.Airplane airplane ->
          """
          We need to reach at least %s kmh to \
          be able to take off this plane named %s\
          """
              .formatted(airplane.takeOffSpeed, name);
      default -> throw new UnsupportedOperationException("Unexpected vehicle type: " + vehicle);
    };
  }

  @AllArgsConstructor
  static class Vehicle {

    String name;

    static class Tank extends Vehicle {

      public Tank(String name, double gunCaliber) {
        super(name);
        this.gunCaliber = gunCaliber;
      }

      double gunCaliber;
    }

    static class Ship extends Vehicle {

      double waterDisplacement;

      public Ship(String name, double waterDisplacement) {
        super(name);
        this.waterDisplacement = waterDisplacement;
      }
    }

    static class Airplane extends Vehicle {

      double takeOffSpeed;

      public Airplane(String name, double takeOffSpeed) {
        super(name);
        this.takeOffSpeed = takeOffSpeed;
      }
    }

    static class Motorbike extends Vehicle {

      public Motorbike(String name) {
        super(name);
      }
    }
  }
}
