package com.progressive.powerplant.service;

import com.progressive.powerplant.dto.BatteryRequest;
import com.progressive.powerplant.dto.BatteryResponse;
import com.progressive.powerplant.mapper.BatteryMapper;
import com.progressive.powerplant.model.Battery;
import com.progressive.powerplant.model.PostCode;
import com.progressive.powerplant.model.WattCapacity;
import com.progressive.powerplant.repository.BatteryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BatteryServiceImpl implements BatteryService {

    private final BatteryRepository repository;
    private final BatteryMapper mapper;

    @Override
    public void save(List<BatteryRequest> batteries) {
        repository.save(batteries.stream().map(mapper::battery).collect(Collectors.toList()));
    }

    @Override
    public BatteryResponse getBatteriesByPostCodeRange(Integer from, Integer to) {
        var sortedBatteries = Battery.sortByName(repository.findByPostCodeRange(new PostCode(from),
                new PostCode(to)));
        var capacities = sortedBatteries.stream()
                .map(Battery::wattCapacity)
                .collect(Collectors.toList());

        return BatteryResponse.builder()
                .batteries(sortedBatteries.stream().map(mapper::request).collect(Collectors.toList()))
                .avgWattCapacity(WattCapacity.avgCapacity(capacities))
                .totalWattCapacity(WattCapacity.totalCapacity(capacities))
                .build();
    }
}
