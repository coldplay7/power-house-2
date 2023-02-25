package com.progressive.powerplant.dto;

import com.progressive.powerplant.model.Unit;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WattCapacityRequest {

    @Min(0)
    private double value;
    @NotNull
    private Unit unit;
}
