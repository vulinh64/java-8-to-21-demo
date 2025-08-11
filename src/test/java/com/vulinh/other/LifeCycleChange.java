package com.vulinh.other;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public record LifeCycleChange(Vehicle vehicle, String type) {

  public record Vehicle(VehicleInfo vehicleInfo) {}

  public record VehicleInfo(String ecuGeneration) {}

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

  public Map<String, List<LifeCycleChange>> map(List<LifeCycleChange> lifeCycleChanges) {
    return lifeCycleChanges.stream().collect(Collectors.groupingBy(LifeCycleChange::type));
  }
}
