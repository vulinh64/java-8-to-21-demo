package com.vulinh.other;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.With;

@Builder
@With
public record LifeCycleChange(Vehicle vehicle) {

  public enum VehicleType {
    SUV,
    SEDAN,
    CONVERTIBLE,
    HATCHBACK,
    COUPE
  }

  @Builder
  @With
  public record Vehicle(VehicleInfo vehicleInfo) {}

  @Builder
  @With
  public record VehicleInfo(String name, String ecuGeneration, VehicleType vehicleType) {}

  @SuppressWarnings({"Convert2MethodRef", "java:S1612"})
  public void add1(Map<String, Object> attribute, LifeCycleChange lifeCycleChange) {
    Optional.ofNullable(lifeCycleChange.vehicle())
        .map(s -> s.vehicleInfo())
        .map(s -> s.ecuGeneration())
        .ifPresent(s -> attribute.put("ecuGeneration", s));
  }

  public void add2(Map<String, Object> attribute, LifeCycleChange lifeCycleChange) {
    if (lifeCycleChange.vehicle() != null
        && lifeCycleChange.vehicle().vehicleInfo() != null
        && lifeCycleChange.vehicle().vehicleInfo().ecuGeneration() != null) {
      attribute.put("ecuGeneration", lifeCycleChange.vehicle().vehicleInfo().ecuGeneration());
    }
  }

  public void add3(Map<String, Object> attribute, LifeCycleChange lifeCycleChange) {
    Optional.ofNullable(lifeCycleChange.vehicle())
        .map(Vehicle::vehicleInfo)
        .map(VehicleInfo::ecuGeneration)
        .ifPresent(s -> attribute.put("ecuGeneration", s));
  }

  public static Map<VehicleType, List<VehicleInfo>> map(List<VehicleInfo> lifeCycleChanges) {
    return lifeCycleChanges.stream()
        .filter(s -> s.ecuGeneration != null)
        .collect(Collectors.groupingBy(VehicleInfo::vehicleType));
  }
}
