package com.progressive.powerplant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressive.powerplant.dto.BatteryRequest;
import com.progressive.powerplant.dto.BatteryResponse;
import com.progressive.powerplant.dto.WattCapacityRequest;
import com.progressive.powerplant.model.Unit;
import com.progressive.powerplant.service.BatteryService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.progressive.powerplant.helper.BatteryHelper.batteries;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BatteryController.class)
class BatteryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BatteryService service;

    @Test
    @SneakyThrows
    void givenEmptyListOfBatteries_whenPost_thenShouldReturnBadRequest() {
        mockMvc.perform(post("/api/v1/battery")
                        .contentType(APPLICATION_JSON)
                        .content(contentWithEmptyBatteryList()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("httpStatusCode").value(400))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    void givenInvalidBatteryContent_whenPost_thenShouldReturnBadRequest() {
        mockMvc.perform(post("/api/v1/battery")
                        .contentType(APPLICATION_JSON)
                        .content(contentWithInvalidBatteryValues()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("httpStatusCode").value(400))
                .andExpect(jsonPath("errors").isNotEmpty())
                .andExpect(jsonPath("errors[*].field")
                        .value(containsInAnyOrder(
                                "save.arg0[0].postCode",
                                "save.arg0[0].name",
                                "save.arg0[0].wattCapacity")))
                .andExpect(jsonPath("errors[*].message")
                        .value(containsInAnyOrder("must not be blank",
                                "must not be null",
                                "must not be null")))
                .andDo(print());

    }

    @Test
    @SneakyThrows
    void givenValidBatteries_whenPost_shouldReturnOk() {
        mockMvc.perform(post("/api/v1/battery")
                        .contentType(APPLICATION_JSON)
                        .content(contentWithValidBatteriesList()))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void givenNoPostCodeRange_whenGet_thenShouldReturnBadRequest() {
        when(service.getBatteriesByPostCodeRange(any(), any())).thenReturn(batteryResponse());
        mockMvc.perform(get("/api/v1/battery"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("httpStatusCode").value(400))
                .andExpect(jsonPath("message")
                        .value("Required request parameter 'from-postcode' for method parameter type Integer is not present"));
    }

    @Test
    @SneakyThrows
    void givenPostCodeRange_whenGet_shouldReturnBatteryResponse() {
        when(service.getBatteriesByPostCodeRange(any(), any())).thenReturn(batteryResponse());
        mockMvc.perform(get("/api/v1/battery")
                        .queryParam("from-postcode", "1")
                        .queryParam("to-postcode", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("avg_watt_capacity").isNumber())
                .andExpect(jsonPath("total_watt_capacity").isNumber())
                .andExpect(jsonPath("batteries").isNotEmpty());
    }

    private BatteryResponse batteryResponse() {
        return BatteryResponse.builder()
                .batteries(batteries())
                .avgWattCapacity(1.0)
                .totalWattCapacity(1.0)
                .build();
    }

    @SneakyThrows
    private static byte[] contentWithInvalidBatteryValues() {
        var battery = BatteryRequest.builder().build();
        return new ObjectMapper().writeValueAsBytes(List.of(battery));
    }

    @SneakyThrows
    private static byte[] contentWithEmptyBatteryList() {
        return new ObjectMapper().writeValueAsBytes(List.of());
    }

    @SneakyThrows
    private static byte[] contentWithValidBatteriesList() {
        var battery = BatteryRequest.builder().name("battery-1").wattCapacity(WattCapacityRequest
                .builder().value(1)
                .unit(Unit.AH)
                .build()).postCode(1111).build();
        return new ObjectMapper().writeValueAsBytes(List.of(battery));
    }

}