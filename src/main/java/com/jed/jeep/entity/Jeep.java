package com.jed.jeep.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Jeep {
    long modelPK;
    JeepModel modelId;
    String trimLevel;
    int numDoors;
    int wheelSize;
    BigDecimal basePrice;

}
