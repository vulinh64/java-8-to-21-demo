package com.vulinh.java15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TextBlockTest {

  @SuppressWarnings({"TextBlockMigration", "java:S6126"})
  @Test
  void testTextBlock() {
    var uglyJson =
        "{\n"
            + "  \"status\": \"ok\",\n"
            + "  \"message\": \"object created\",\n"
            + "  \"additional\": {\n"
            + "    \"id\": \"33663e80-3262-4197-aeb4-381a5447bd84\",\n"
            + "  }\n"
            + "}\n";

    var blyatifulJson =
        """
        {
          "status": "ok",
          "message": "object created",
          "additional": {
            "id": "33663e80-3262-4197-aeb4-381a5447bd84",
          }
        }
        """;

    var notCool = "{\n" + "  \"payload\": \"\\\"id\\\": \\\"123456\\\"\"\n" + "}";

    var evenMoreBlyatfulText =
        """
        {
          "payload": "\\"id\\": \\"123456\\""
        }\
        """;

    assertEquals(uglyJson, blyatifulJson);

    assertEquals(notCool, evenMoreBlyatfulText);
  }
}
