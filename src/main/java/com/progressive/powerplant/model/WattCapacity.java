package com.progressive.powerplant.model;

import java.util.List;

public record WattCapacity(double value, Unit unit) {
    public WattCapacity(double value, Unit unit) {
        this.value = value;
        this.unit = unit;
        this.validate();
    }

    private void validate() {
        if (this.value < 0)
            throw new IllegalArgumentException("Watt capacity value cannot be less than zero.");
        if (null == unit)
            throw new IllegalArgumentException("Watt capacity unit cannot be null.");
    }

    public static double totalCapacity(List<WattCapacity> capacities) {
        return capacities.stream().map(wattCapacity -> wattCapacity.value).reduce((double) 0, Double::sum);
    }

    public static double avgCapacity(List<WattCapacity> capacities) {
        return capacities.stream().mapToDouble(wattCapacity -> wattCapacity.value)
                .average()
                .orElse(0);
    }


}


