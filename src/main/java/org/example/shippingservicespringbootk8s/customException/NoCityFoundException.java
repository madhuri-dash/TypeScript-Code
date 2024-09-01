package org.example.shippingservicespringbootk8s.customException;

public class NoCityFoundException extends RuntimeException{

    private String message;

    public NoCityFoundException(){}

    public NoCityFoundException(String msg){
        super(msg);
        this.message = msg;
    }
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
