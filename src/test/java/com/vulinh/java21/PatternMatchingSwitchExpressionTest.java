package com.vulinh.java21;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.vulinh.java21.Vehicle.Airplane;
import com.vulinh.java21.Vehicle.Ship;
import com.vulinh.java21.Vehicle.Tank;
import org.junit.jupiter.api.Test;

class PatternMatchingSwitchExpressionTest {

  @Test
  @SuppressWarnings("java:S5778")
  void tetsPatternMatchingSwitchExpression() {
    var tank = new Tank("T-90MS", 125);
    var ship = new Ship("USS Iowa", 45000);
    var airplane = new Airplane("Boeing 747", 296);

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
    var name = vehicle.getName();

    return switch (vehicle) {
      case Tank tank ->
          "This tank (%s) hurts, with the caliber of %s mm".formatted(name, tank.getGunCaliber());
      case Ship ship ->
          "This beauty, called %s can displace about %s tons of water"
              .formatted(name, ship.getWaterDisplacement());
      case Airplane airplane ->
          """
          We need to reach at least %s kmh to \
          be able to take off this plane named %s\
          """
              .formatted(airplane.getTakeOffSpeed(), name);
      default -> throw new UnsupportedOperationException("Unexpected vehicle type: " + vehicle);
    };
  }
}
