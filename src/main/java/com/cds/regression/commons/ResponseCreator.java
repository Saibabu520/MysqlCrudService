package com.cds.regression.commons;

import org.springframework.stereotype.Controller;

@Controller
public class ResponseCreator {

  /**
   * Method which will prepare response for get calls.
   * @param <T>
   * 
   * @param- status,message,data.
   * @return- response.
  */
  public Response prepareGetCallResponse(final Object data) {
    
    return Response.builder().status(ConstantsUtil.SUCCESS)
                             .message(ConstantsUtil.DATA_FOUND)
                             .data(data).build();
  } // End of prepareResponseForGet.
  
  /**
   * Method which will prepare response for update requests.
   * 
   * @param- message.
   * @return- response.
  */
  public Response prepareUpdateResponse(final String message) {

    return Response.builder().status(ConstantsUtil.SUCCESS).message(message).build();
  } // End of prepareResponseForGet.
} // End of ResponseCreator.