package com.aaron.MyflightsApplication.controller;

import com.aaron.MyflightsApplication.domain.Flight;
import com.aaron.MyflightsApplication.exception.FlightNotFoundException;
import com.aaron.MyflightsApplication.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.aaron.MyflightsApplication.controller.Response.NOT_FOUND;


/**
 * Controlador para vuelos
 */
@RestController
@Tag(name = "Flights", description = "Catálogo de vuelos")
public class FlightController {

    private final Logger logger = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    private FlightService flightService;

    @Operation(summary = "Obtiene el listado de vuelos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de vuelos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Flight.class))))
    })
    @GetMapping(value = "/flights", produces = "application/json")
    public ResponseEntity<Set<Flight>> getFlights(){
        logger.info("Inicio getFlights");
        Set<Flight> flights = null;
        flights = flightService.findAll();

        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un vuelo determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Existe el vuelo", content = @Content(schema = @Schema(implementation = Flight.class))),
            @ApiResponse(responseCode = "404", description = "El vuelo no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/flights/{id}", produces = "application/json")
    public ResponseEntity<Flight> getFlight(@PathVariable long id){
        Flight flight = flightService.findById(id)
                .orElseThrow(()-> new FlightNotFoundException(id));

        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo vuelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registra el vuelo", content = @Content(schema = @Schema(implementation = Flight.class)))
    })
    @PostMapping(value = "/flights", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight){
        Flight addedFlight = flightService.addFlight(flight);
        return new ResponseEntity<>(addedFlight, HttpStatus.OK);
    }

    @Operation(summary = "Modifica un vuelo del catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el vuelo", content = @Content(schema = @Schema(implementation = Flight.class))),
            @ApiResponse(responseCode = "404", description = "El vuelo no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/flights/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Flight> modifyFlight(@PathVariable long id, @RequestBody Flight newFlight){
        logger.info("Modificado vuelo "+id);
        Flight flight = flightService.modifyFlight(id,newFlight);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un vuelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el vuelo", content = @Content(schema = @Schema(implementation = Flight.class))),
            @ApiResponse(responseCode = "404", description = "El vuelo no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/flights/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteFlight(@PathVariable long id){
        logger.info("Borrado vuelo "+id);
        flightService.deleteFlight(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene los vuelos por destino")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el destino", content = @Content(schema = @Schema(implementation = Flight.class))),
            @ApiResponse(responseCode = "404", description = "El destino no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/flights/destino/{destination}", produces = "application/json")
    public ResponseEntity<List<Flight>> getFlightsByDestination(@PathVariable String destination){
        List<Flight> flights = flightService.findByDestination(destination);

        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene los vuelos de una compañia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la compañia", content = @Content(schema = @Schema(implementation = Flight.class))),
            @ApiResponse(responseCode = "404", description = "La compañia no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/flights/company/{company}", produces = "application/json")
    public ResponseEntity<List<Flight>> getFlightsByCompany(@PathVariable String company){
        List<Flight> flights = flightService.findByCompany(company);

        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene los vuelos por salida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Existe la salida", content = @Content(schema = @Schema(implementation = Flight.class))),
            @ApiResponse(responseCode = "404", description = "La salida no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/flights/departure/{departure}", produces = "application/json")
    public ResponseEntity<List<Flight>> getFlightsByDeparture(@PathVariable String departure){
        List<Flight> flights = flightService.findByDeparture(departure);

        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene los vuelos por números de escalas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el número de escalas", content = @Content(schema = @Schema(implementation = Flight.class))),
            @ApiResponse(responseCode = "404", description = "No existe el número de escalas", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/flights/stopovers/{stopovers}", produces = "application/json")
    public ResponseEntity<List<Flight>> getFlightsByStopover(@PathVariable int stopovers){
        List<Flight> flights = flightService.findByStopover(stopovers);

        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(FlightNotFoundException fnfe){
        Response response = Response.errorResponse(NOT_FOUND, fnfe.getMessage());
        logger.error(fnfe.getMessage(),fnfe);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
