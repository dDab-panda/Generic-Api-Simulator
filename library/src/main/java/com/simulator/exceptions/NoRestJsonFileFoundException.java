package com.simulator.exceptions;

public class NoRestJsonFileFoundException  extends RuntimeException {
    public NoRestJsonFileFoundException() {
        super("The JSON Accept header not found");
    }

}
