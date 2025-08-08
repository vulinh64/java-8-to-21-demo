package com.vulinh.java9;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

class PrivateMethodInsideInterfaceTest {

  interface Marker {

    @SuppressWarnings("java:S3457")
    default String introduce(String name, String... strings) {
      // Text block will ALWAYS use LF as line break, regardless of the platform
      // So using the recommended %n here will NOT work
      return "Hi, my name is %s. This is my introduction:\n\n%s"
          .formatted(name, toSentence(strings));
    }

    private String toSentence(String... words) {
      if (ArrayUtils.isEmpty(words)) {
        return StringUtils.EMPTY;
      }

      return StringUtils.capitalize(String.join(" ", words));
    }
  }

  @Test
  void testInternalMethod() {
    var marker = new Marker() {};

    assertEquals(
        """
        Hi, my name is Linh. This is my introduction:

        I am the storm that is approaching\
        """,
        marker.introduce("Linh", "i", "am", "the", "storm", "that", "is", "approaching"));
  }
}
