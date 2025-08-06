package com.paaulooo.boardtarefas.exception;

public class TaskFinishedException extends RuntimeException{
    public TaskFinishedException(final String message) {
        super(message);
    }
}
