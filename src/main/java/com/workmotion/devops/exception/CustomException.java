package com.workmotion.devops.exception;

import java.io.Serializable;

public class CustomException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 3801492962288172716L;
	private static final String MESSAGE="Sorry an error occurred";
	
	public CustomException()
    {
        super(MESSAGE);
    }

    public CustomException(Throwable cause)
    {
        super(MESSAGE, cause);
    }

    public CustomException(String message)
    {
        super(message);
    }

    public CustomException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
