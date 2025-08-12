package com.vulinh.java10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vulinh.other.LifeCycleChange;
import com.vulinh.other.LifeCycleChange.VehicleInfo;
import com.vulinh.other.LifeCycleChange.VehicleType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class VarContextualKeywordTest {

  record TestObject(List<Integer> list1, ArrayList<Integer> list2) {}

  @Test
  void testVarKeyword() throws NoSuchFieldException {
    var obj =
        new TestObject(new ArrayList<>(List.of(1, 2, 3, 4)), new ArrayList<>(List.of(5, 6, 7, 8)));

    var field1 = obj.getClass().getDeclaredField("list1");
    var field2 = obj.getClass().getDeclaredField("list2");

    assertEquals(List.class, field1.getType());
    assertEquals(ArrayList.class, field2.getType());

    TestObject obj2 =
        new TestObject(new ArrayList<>(List.of(1, 2, 3, 4)), new ArrayList<>(List.of(5, 6, 7, 8)));

    assertEquals(obj, obj2);
  }

  @Test
  void testVarKeyword2() {
    var changes =
        List.of(
            VehicleInfo.builder()
                .name("2024 Jeep Wrangler")
                .ecuGeneration("ABBXXXX")
                .vehicleType(VehicleType.SUV)
                .build(),
            VehicleInfo.builder()
                .name("2023 Toyota Camry")
                .ecuGeneration("TCM1234")
                .vehicleType(VehicleType.SEDAN)
                .build(),
            VehicleInfo.builder()
                .name("2025 Ford Mustang Convertible")
                .ecuGeneration("FMC5678")
                .vehicleType(VehicleType.CONVERTIBLE)
                .build(),
            VehicleInfo.builder()
                .name("2024 Honda Civic Hatchback")
                .ecuGeneration(null)
                .vehicleType(VehicleType.HATCHBACK)
                .build(),
            VehicleInfo.builder()
                .name("2022 BMW M4 Coupe")
                .ecuGeneration("BMWM4XX")
                .vehicleType(VehicleType.COUPE)
                .build(),
            VehicleInfo.builder()
                .name("2023 Kia Sportage")
                .ecuGeneration(null)
                .vehicleType(VehicleType.SUV)
                .build(),
            VehicleInfo.builder()
                .name("2025 Audi A4")
                .ecuGeneration("AUDIA4XX")
                .vehicleType(VehicleType.SEDAN)
                .build(),
            VehicleInfo.builder()
                .name("2024 Mazda MX-5 Miata")
                .ecuGeneration(null)
                .vehicleType(VehicleType.CONVERTIBLE)
                .build(),
            VehicleInfo.builder()
                .name("2022 Volkswagen Golf")
                .ecuGeneration("VWGF123")
                .vehicleType(VehicleType.HATCHBACK)
                .build(),
            VehicleInfo.builder()
                .name("2025 Chevrolet Corvette")
                .ecuGeneration("CHVCRV99")
                .vehicleType(VehicleType.COUPE)
                .build(),
            VehicleInfo.builder()
                .name("2024 Subaru Outback")
                .ecuGeneration(null)
                .vehicleType(VehicleType.SUV)
                .build(),
            VehicleInfo.builder()
                .name("2023 Mercedes-Benz C-Class")
                .ecuGeneration("MBC3000")
                .vehicleType(VehicleType.SEDAN)
                .build(),
            VehicleInfo.builder()
                .name("2025 Porsche 911 Cabriolet")
                .ecuGeneration("P911CAB1")
                .vehicleType(VehicleType.CONVERTIBLE)
                .build(),
            VehicleInfo.builder()
                .name("2022 Hyundai i30")
                .ecuGeneration(null)
                .vehicleType(VehicleType.HATCHBACK)
                .build(),
            VehicleInfo.builder()
                .name("2024 Lexus LC 500")
                .ecuGeneration("LXSLC500")
                .vehicleType(VehicleType.COUPE)
                .build(),
            VehicleInfo.builder()
                .name("2025 Nissan Pathfinder")
                .ecuGeneration(null)
                .vehicleType(VehicleType.SUV)
                .build(),
            VehicleInfo.builder()
                .name("2023 Volvo S60")
                .ecuGeneration("VOLS6001")
                .vehicleType(VehicleType.SEDAN)
                .build(),
            VehicleInfo.builder()
                .name("2024 Mini Cooper Convertible")
                .ecuGeneration(null)
                .vehicleType(VehicleType.CONVERTIBLE)
                .build(),
            VehicleInfo.builder()
                .name("2022 Ford Fiesta")
                .ecuGeneration("FRDFST22")
                .vehicleType(VehicleType.HATCHBACK)
                .build(),
            VehicleInfo.builder()
                .name("2025 Aston Martin Vantage")
                .ecuGeneration("AMVNTG25")
                .vehicleType(VehicleType.COUPE)
                .build());

    var result = LifeCycleChange.map(changes);

    assertEquals(5, result.size());
    assertEquals(13, result.values().stream().mapToLong(Collection::size).sum());
  }
}
