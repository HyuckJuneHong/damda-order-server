package kr.co.damdaorder.error.exception;

import kr.co.damdaorder.error.model.ErrorCode;

public class BadRequestException extends BusinessLogicException{

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BadRequestException(String message) {
        super(String.format("Bad Request : %s", message));
    }
}
