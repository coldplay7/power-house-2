package com.progressive.powerplant.repository;

import com.progressive.powerplant.model.Battery;
import com.progressive.powerplant.model.PostCode;

import java.util.List;

public interface BatteryRepository {
    void save(List<Battery> battery);

    List<Battery> findByPostCodeRange(PostCode from, PostCode to);
}
