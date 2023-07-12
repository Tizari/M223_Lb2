package ch.zli.m223.ksh20.lb2.service.exception;

public class NoOutputException extends RuntimeException {
    public NoOutputException() {
        super("", null, true, false);
    }
}
