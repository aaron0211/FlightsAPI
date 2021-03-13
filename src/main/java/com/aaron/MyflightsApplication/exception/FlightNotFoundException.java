package com.aaron.MyflightsApplication.exception;

/**
 * Excepciones de vuelo no encontrado
 */
public class FlightNotFoundException extends RuntimeException{

    public FlightNotFoundException(){
        super();
    }

    public FlightNotFoundException(String message){
        super(message);
    }

    public FlightNotFoundException(long id){
        super("Flight not found: " + id);
    }
}
