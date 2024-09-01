package org.example.shippingservicespringbootk8s.customException;

public class CityAlreadyExistsException extends RuntimeException{
    private String message;

    public CityAlreadyExistsException(){}

    public CityAlreadyExistsException(String msg){
        super(msg);
        this.message = msg;
    }
}
