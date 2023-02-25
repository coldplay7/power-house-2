package com.progressive.powerplant.repository;

import com.progressive.powerplant.model.PostCode;
import com.progressive.powerplant.model.WattCapacity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("BATTERY")
@Data
public class BatteryDocument {
    @Id
    private String id;

    private String name;
    private PostCode postCode;
    private WattCapacity wattCapacity;

}
