package com.vulinh.java16;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@SuppressWarnings({"ClassCanBeRecord", "unused"})
@Value
@Builder
@With
public class Point {

  double x;
  double y;
}
