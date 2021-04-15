package com.jed.jeep.controller;

import com.jed.jeep.entity.Jeep;
import com.jed.jeep.entity.JeepModel;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import java.util.List;

@OpenAPIDefinition(
    info = @Info(title = "Jeep Sales Service"),
    servers = {@Server(url = "http://localhost:8080", description = "Local Server")})
public interface JeepSalesController {

  @Operation(
      summary = "Returns a list of Jeeps",
      description = "Returns a list of Jeeps given an optional model and/or trim",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A list of Jeeps is returned.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Jeep.class))),
        @ApiResponse(
            responseCode = "400",
            description = "The request parameters are invalid.",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "404",
            description = "No Jeeps were found using the input criteria.",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "500",
            description = "Unplanned error occurred.",
            content = @Content(mediaType = "application/json"))
      },
      parameters = {
        @Parameter(
            name = "model",
            allowEmptyValue = false,
            required = false,
            description = "The model name (i.e., 'WRANGLER')"),
        @Parameter(
            name = "trim",
            allowEmptyValue = false,
            required = false,
            description = "The trim level (i.e., 'Sport')")
      })
  List<Jeep> fetchJeeps(JeepModel model, String trim);
}
