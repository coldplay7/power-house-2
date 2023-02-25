package com.progressive.powerplant.controller;

import com.progressive.powerplant.dto.BatteryRequest;
import com.progressive.powerplant.dto.BatteryResponse;
import com.progressive.powerplant.service.BatteryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/battery")
@AllArgsConstructor
@Validated
public class BatteryController {

    private final BatteryService service;

    @PostMapping
    void save(@RequestBody @NotEmpty List<@Valid BatteryRequest> batteries) {
        service.save(batteries);
    }

    @GetMapping
    public ResponseEntity<BatteryResponse> batteriesByPostCodeRange(@NotNull @RequestParam("from-postcode") Integer from,
                                                                    @NotNull @RequestParam("to-postcode") Integer to) {
        return ok(service.getBatteriesByPostCodeRange(from, to));
    }

}
