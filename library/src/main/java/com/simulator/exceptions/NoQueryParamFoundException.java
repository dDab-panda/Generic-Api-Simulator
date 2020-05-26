package com.simulator.exceptions;

public class NoQueryParamFoundException extends RuntimeException {
    public NoQueryParamFoundException(String method) {
        super("No query parameters provided for given method: " + method + ". " + 
        			"The method " + method + " requires atleast one query parameter.");
    }
}