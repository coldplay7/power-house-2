package com.progressive.powerplant.repository;

import com.progressive.powerplant.model.PostCode;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface BatteryMongoRepository extends MongoRepository<BatteryDocument, UUID> {
    List<BatteryDocument> findAllByPostCodeBetween(PostCode from, PostCode to);
}
