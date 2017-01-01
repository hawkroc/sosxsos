package com.sosxsos.ssm.exception;
/**
 * 
 * @author sosxsos
 *
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
    
    

}
