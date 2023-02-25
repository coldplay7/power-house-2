package com.progressive.powerplant.repository;

import com.progressive.powerplant.model.Battery;
import com.progressive.powerplant.model.PostCode;
import com.progressive.powerplant.mapper.BatteryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class BatteryRepositoryImpl implements BatteryRepository {
    private final BatteryMongoRepository mongoRepository;
    private final BatteryMapper mapper;

    @Override
    public void save(List<Battery> batteries) {
        mongoRepository.saveAll(batteries.stream().map(mapper::entity).collect(Collectors.toList()));
    }

    @Override
    public List<Battery> findByPostCodeRange(PostCode from, PostCode to) {
        return mongoRepository.findAllByPostCodeBetween(from, to)
                .stream()
                .map(mapper::battery)
                .collect(Collectors.toList());
    }
}
