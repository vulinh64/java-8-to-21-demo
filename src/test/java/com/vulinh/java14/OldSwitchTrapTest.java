package com.vulinh.java14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OldSwitchTrapTest {

  @Test
  void testOldSwitchTrap() {
    Assertions.assertEquals("evenodd", buildChoice(2));
    Assertions.assertEquals("odd", buildChoice(3));
  }

  @SuppressWarnings({"java:S131", "java:S128"})
  private static String buildChoice(int number) {
    var builder = new StringBuilder();

    switch (number % 2) {
      case 0:
        builder.append("even");
      case 1:
        builder.append("odd");
    }

    return builder.toString();
  }
}
