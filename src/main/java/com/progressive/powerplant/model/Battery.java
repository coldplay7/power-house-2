package com.progressive.powerplant.model;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.StringUtils.hasLength;

public record Battery(String name, PostCode postCode, WattCapacity wattCapacity) {
    public Battery(String name, PostCode postCode, WattCapacity wattCapacity) {
        this.name = name;
        this.postCode = postCode;
        this.wattCapacity = wattCapacity;
        this.validate();
    }

    private void validate() {
        if (!hasLength(name)) throw new IllegalArgumentException("Name cannot be null or empty.");
        if (null == postCode) throw new IllegalArgumentException("Post code cannot be null.");
        if (null == wattCapacity) throw new IllegalArgumentException("Watt capacity cannot be null.");
    }

    public static List<Battery> sortByName(List<Battery> batteries) {
        return batteries.stream()
                .sorted(comparing(battery -> battery.name))
                .collect(toList());
    }
}
