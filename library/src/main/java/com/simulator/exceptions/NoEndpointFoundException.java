package com.simulator.exceptions;

public final class NoEndpointFoundException extends RuntimeException {
    public NoEndpointFoundException(){
        super("No configuration found for given endpoint!!!");
    }
}