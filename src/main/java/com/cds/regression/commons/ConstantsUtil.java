package com.cds.regression.commons;

/**
 * The class which contains all the required constants.
 * 
 * @author Saibabu Guntur
 * @Version 1.0.
*/
public class ConstantsUtil {

  // Status related constants.
  public static final String SUCCESS = "Success";
  public static final String FAILURE = "Failure";
  
  public static final String ISO_DATE = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  
  // Projet related constants.
  public static final String DATA_ALREADY_EXIST = " already exist in the database.";
  public static final String SUCCESS_MSG = "Request processed successfully.";
  
  // Generic error message constants.
  public static final String INTERNAL_ERROR = "Internal error occurred while processing request.";
  public static final String DATA_FOUND = "Data fetched successfully.";
  public static final String DATA_NOT_FOUND = "Data not found.";
  
  public static final String NOT_FOUND = " data not found.";
  public static final String CONNECTION = "Unable to connect to server.";
  
  private ConstantsUtil() {}
} // End of ConstantsUtil.