package com.cds.regression.exceptions;

import org.springframework.stereotype.Controller;
import org.springframework.web.client.ResourceAccessException;

import com.cds.regression.commons.ConstantsUtil;
import com.cds.regression.commons.Response;

/**
 * The class which will provide exception handling logic to create failure message 
 * with provided message.
 * 
 * @author  Saibabu.
 * @version 1.0.
*/
@Controller
public class ExceptionProcessor {
  
  /**
   * The method will create failure response status and message 
   * and return it to controller class.
   * 
   * @param: exception
  */ 
  public Response processException(final Exception e) {
    
    Response response = Response.builder().status(ConstantsUtil.FAILURE).build();

    if (e instanceof DataException) {
      response.setMessage(e.getMessage());
    } else if (e instanceof ResourceAccessException) {
      response.setMessage(ConstantsUtil.CONNECTION);
    } else {
      response.setMessage(ConstantsUtil.INTERNAL_ERROR);
    }
    return response;
  } // End of processException.
} // End of ExceptionProcessor.