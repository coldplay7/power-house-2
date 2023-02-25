package com.progressive.powerplant.service;

import com.progressive.powerplant.dto.BatteryRequest;
import com.progressive.powerplant.dto.BatteryResponse;

import java.util.List;

public interface BatteryService {
    void save(List<BatteryRequest> batteries);

    BatteryResponse getBatteriesByPostCodeRange(Integer from, Integer to);
}
