package com.progressive.powerplant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BatteryRequest {

    @NotBlank
    private String name;
    @NotNull
    @Min(value = 999, message = "Post code must have minimum of 4 digits.")
    @JsonProperty(value = "post_code")
    private Integer postCode;
    @NotNull
    @JsonProperty(value = "watt_capacity")
    @Valid
    private WattCapacityRequest wattCapacity;
}

