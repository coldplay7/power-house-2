package com.progressive.powerplant.service;

import com.progressive.powerplant.mapper.BatteryMapper;
import com.progressive.powerplant.mapper.BatteryMapperImpl;
import com.progressive.powerplant.model.PostCode;
import com.progressive.powerplant.repository.BatteryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.stream.Collectors;

import static com.progressive.powerplant.helper.BatteryHelper.batteries;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class BatteryServiceTest {

    @Mock
    private BatteryRepository repository;
    private BatteryService service;
    private BatteryMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new BatteryMapperImpl();
        openMocks(this);
        service = new BatteryServiceImpl(repository, mapper);
    }

    @Test
    void givenListOfBatteries_whenSave_shouldInvokeRepository() {
        var batteries = batteries();
        service.save(batteries);
        verify(repository, times(1)).save(batteries.stream()
                .map(mapper::battery)
                .collect(Collectors.toList()));
    }

    @Test
    void givenPostCodeRange_whenList_thenShouldReturnSortedBatteryList() {
        when(repository.findByPostCodeRange(new PostCode(1000), new PostCode(4444)))
                .thenReturn(batteries().stream().map(mapper::battery).collect(Collectors.toList()));
        var response = service.getBatteriesByPostCodeRange(1000, 4444);

        assertNotNull(response);
        assertEquals(4, response.getBatteries().size());
        assertEquals("battery a", response.getBatteries().get(0).getName());
        assertEquals("battery b", response.getBatteries().get(1).getName());
        assertEquals("battery c", response.getBatteries().get(2).getName());
        assertEquals("battery d", response.getBatteries().get(3).getName());
        assertEquals(75.25, response.getAvgWattCapacity());
        assertEquals(301, response.getTotalWattCapacity());

    }

}