package com.vulinh.java21;

// No need to explicitly specify permits clause
sealed interface Shape {

  record Circle(double radius) implements Shape {}

  record Rectangle(double width, double height) implements Shape {}

  record Triangle(double a, double b, double c) implements Shape {}
}
