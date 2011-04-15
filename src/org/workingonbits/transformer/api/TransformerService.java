package org.workingonbits.transformer.api;

/**
 * Transformer service interface
 * @author Eduardo Fernández <eduardo@workingonbits.com>
 *
 */
public interface TransformerService {
	
	/**
	 * Performs the transformation over message passed by parameter and returns the result
	 * @param message
	 * @return
	 */
	public String transform(String message);
	
	/**
	 * Indicates the type of transformation
	 * @return
	 */
	public String getType();

}
