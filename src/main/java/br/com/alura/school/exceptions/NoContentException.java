package br.com.alura.school.exceptions;

public class NoContentException extends RuntimeException {

    public NoContentException(String msg) { super(msg); }

    public NoContentException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
