package com.jed.jeep.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Jeep {
    int model_pk;
    JeepModel model_id;
    char trim_level;
    int num_doors;
    int wheel_size;
    BigDecimal base_price;

}
