package com.aaron.MyflightsApplication.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Un vuelo del catálogo
 * @author aaron
 * @version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "flights")
public class Flight {

    @Schema(description = "Identificador del vuelo", example = "1",required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Compañia", example = "Iberia",required = true)
    @NotBlank
    @Column
    private String company;

    @Schema(description = "Salida del vuelo", example = "Zaragoza", required = true)
    @NotBlank
    @Column
    private String departure;

    @Schema(description = "Destino del vuelo", example = "Madrid", required = true)
    @NotBlank
    @Column
    private String destination;

    @Schema(description = "Número de escalas", example = "2", defaultValue = "0")
    @Column
    @Min(value = 0)
    private int stopovers;

    @Schema(description = "Precio", example = "15.50", defaultValue = "0.00")
    @Column
    @Min(value = 0)
    private float price;
}
