package br.com.ibm.healthplusapi.service.exceptions;

public class ExistingEmailException extends RuntimeException {

    public ExistingEmailException() {
        super("E-mail already registered");
    }
}
