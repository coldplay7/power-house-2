package com.progressive.powerplant.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressive.powerplant.dto.BatteryRequest;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class BatteryHelper {

    @SneakyThrows
    public static List<BatteryRequest> batteries() {
        String json = new String(BatteryHelper.class.getResourceAsStream("/batteries.json").readAllBytes(), StandardCharsets.UTF_8);
        return List.of(new ObjectMapper().readValue(json, BatteryRequest[].class));
    }


}
