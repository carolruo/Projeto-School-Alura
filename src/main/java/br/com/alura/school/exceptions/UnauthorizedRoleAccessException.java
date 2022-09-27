package br.com.alura.school.exceptions;

public class UnauthorizedRoleAccessException extends RuntimeException {

    public UnauthorizedRoleAccessException(String msg) {
        super(msg);
    }

    public UnauthorizedRoleAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
