package br.com.alura.school.exceptions;

public class DuplicateObjectException extends RuntimeException {

    public DuplicateObjectException(String msg) {
        super(msg);
    }

    public DuplicateObjectException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
