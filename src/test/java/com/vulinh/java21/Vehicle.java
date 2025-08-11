package com.vulinh.java21;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
abstract class Vehicle {

  private String name;

  @Getter
  static class Tank extends Vehicle {

    public Tank(String name, double gunCaliber) {
      super(name);
      this.gunCaliber = gunCaliber;
    }

    private final double gunCaliber;
  }

  @Getter
  static class Ship extends Vehicle {

    private final double waterDisplacement;

    public Ship(String name, double waterDisplacement) {
      super(name);
      this.waterDisplacement = waterDisplacement;
    }
  }

  @Getter
  static class Airplane extends Vehicle {

    private final double takeOffSpeed;

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
