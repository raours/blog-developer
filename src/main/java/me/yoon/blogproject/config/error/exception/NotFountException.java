package me.yoon.blogproject.config.error.exception;

import me.yoon.blogproject.config.error.ErrorCode;

public class NotFountException extends BusinessBaseException{

    public NotFountException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }

    public NotFountException() {
        super(ErrorCode.NOT_FOUND);
    }
}
