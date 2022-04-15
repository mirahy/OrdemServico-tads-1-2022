package br.edu.ifms.ordem.services.exceptions;

public class DataBaseExcepetion extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataBaseExcepetion(String message) {
        super(message);
    }
}