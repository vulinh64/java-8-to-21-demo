package com.vulinh.java14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NewEnhancedSwitchExpressionTest {

  @Test
  void testNewEnhancedSwitchExpression() {
    var result1 = getDaysOfMonth1(1993, 4); // 30
    var result2 = getDaysOfMonth2(1993, 4); // 30

    var result3 = getDaysOfMonth2(1600, 2); // 29
    var result4 = getDaysOfMonth2(1700, 2); // 28
    var result5 = getDaysOfMonth1(1996, 2); // 29

    assertEquals(30, result1);
    assertEquals(30, result2);

    assertEquals(result1, result2);

    assertEquals(29, result3);
    assertEquals(28, result4);
    assertEquals(29, result5);
  }

  private static int getDaysOfMonth2(int year, int month) {
    if (month < 1 || month > 12) {
      throw new IllegalArgumentException("Invalid month");
    }

    return switch (month) {
      case 2 -> isLeapYear(year) ? 29 : 28;
      case 4, 6, 9, 11 -> 30;
      default -> 31;
    };
  }

  @SuppressWarnings({"EnhancedSwitchMigration", "java:S6208"})
  private static int getDaysOfMonth1(int year, int month) {
    if (month < 1 || month > 12) {
      throw new IllegalArgumentException("Invalid month");
    }

    int result;

    switch (month) {
      case 2:
        result = isLeapYear(year) ? 29 : 28;
        break;
      case 4:
      case 6:
      case 9:
      case 11:
        result = 30;
        break;
      default:
        result = 31;
        break;
    }

    return result;
  }

  private static boolean isLeapYear(int year) {
    return year % 400 == 0 || (year & 3) == 0 && year % 100 != 0;
  }
}
