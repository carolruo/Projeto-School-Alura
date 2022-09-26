package br.com.alura.school.exceptions;

public class DuplicateEnrollmentException extends RuntimeException {

    public DuplicateEnrollmentException(String msg) {
        super(msg);
    }

    public DuplicateEnrollmentException(String msg, Throwable cause) {
        super(msg, cause);
    }
}