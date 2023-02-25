package com.progressive.powerplant.repository;

import com.progressive.powerplant.mapper.BatteryMapper;
import com.progressive.powerplant.model.Battery;
import com.progressive.powerplant.model.PostCode;
import com.progressive.powerplant.model.Unit;
import com.progressive.powerplant.model.WattCapacity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.stream.Collectors;

import static com.progressive.powerplant.helper.BatteryHelper.batteries;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class BatteryRepositoryImplTest {

    @Mock
    private BatteryMapper mapper;
    @Mock
    private BatteryMongoRepository mongoRepository;
    private BatteryRepository repository;

    @BeforeEach
    void setUp() {
        openMocks(this);
        repository = new BatteryRepositoryImpl(mongoRepository, mapper);
    }

    @Test
    void save() {
        when(mapper.entity(any())).thenReturn(new BatteryDocument());
        repository.save(batteries().stream()
                .map(mapper::battery)
                .collect(Collectors.toList()));

        verify(mongoRepository, times(1)).saveAll(any());
    }

    @Test
    void findByPostCodeRange() {
        when(mapper.battery(any(BatteryDocument.class))).thenReturn(new Battery("battery a",
                new PostCode(1234), new WattCapacity(12, Unit.AH)));
        repository.findByPostCodeRange(new PostCode(1111), new PostCode(2222));

        verify(mongoRepository, times(1)).findAllByPostCodeBetween(new PostCode(1111),
                new PostCode(2222) );

    }
}