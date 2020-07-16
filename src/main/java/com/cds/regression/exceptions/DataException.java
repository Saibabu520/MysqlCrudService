package com.cds.regression.exceptions;

/**
 * Custom exception for handling all data not found errors which 
 * occurs while processing the request.
 * 
 * @author  Saibabu Guntur
 * @Version 1.0.
*/
public class DataException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  
  public DataException(String message) {
    super(message);
  }

  public DataException(String message, Exception e) {
    super(message,e);
  }
} // End of DataNotFoundException.