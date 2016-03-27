package com.smt.threenationkill.exception;

/**
 * This exception is thrown when the number of players is out of the range of the point system
 * e.g Currently the point system doesn't apply to 9, 10 person games because there is not enough data
 * @author Mingtao Sun
 */
public class InvalidPlayerNumException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param str the error message 
	 */
	public InvalidPlayerNumException(String str){
		super(str);
	}
}
