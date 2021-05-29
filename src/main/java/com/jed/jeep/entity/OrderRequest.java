package com.jed.jeep.entity;

import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class OrderRequest {
  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[A-Z_]*")
  private String customer;

  @NotNull private JeepModel model;

  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[A-Z_]*")
  private String trim;

  @Positive
  @Min(2)
  @Max(4)
  private int doors;

  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[A-Z0-9_]*")
  private String color;

  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[A-Z0-9_]*")
  private String engine;

  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[A-Z0-9_]*")
  private String tire;

  private List<@NotNull @Length(max = 30) @Pattern(regexp = "[A-Z0-9_]*") String> options;
}
