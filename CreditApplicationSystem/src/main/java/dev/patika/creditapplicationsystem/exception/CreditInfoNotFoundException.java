package dev.patika.creditapplicationsystem.exception;

public class CreditInfoNotFoundException extends RuntimeException{
    public CreditInfoNotFoundException(String msg){
        super(msg);
    }
}
