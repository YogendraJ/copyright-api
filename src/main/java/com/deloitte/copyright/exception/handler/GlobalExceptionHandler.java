package com.deloitte.copyright.exception.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.deloitte.copyright.constants.CopyRightConstants;
import com.deloitte.copyright.exception.MandatoryFieldMissingException;
import com.deloitte.copyright.model.ExceptionResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * GlobalExceptionHandler is the root Exception handler used in the 
 * CopyRight Application. This class contains Custom ExceptionHandlers
 * required to gracefully show the errors encountered by the users of 
 * the API
 * */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Exception Handler for the Custom MandatoryFieldMissingException. 
	 * This Exception is thrown when the input JSON doesn't contain
	 * the mandatory 'data:' element as part of JSON Request 
	 * @param ex - MandatoryFieldMissingException.class
	 * @param request - WebRequest which is propagated to this class through different layers
	 * of the Application
	 * @return ResponseEntity which is returned back as a JSON Response to user / system
	 */
	@ExceptionHandler(MandatoryFieldMissingException.class)
	public final ResponseEntity<ExceptionResponse> handleMandatoryFieldMissingException(MandatoryFieldMissingException ex,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ExceptionResponse error = new ExceptionResponse(CopyRightConstants.BAD_REQUEST, details);
		
		log.error("Mandatory Field Missing: ", ex);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Exception Handler for all Unhandled Exceptions in CopyRight Application 
	 * @param Exception which is propagated to this class through different layers
	 * of the Application
	 * @param request - WebRequest which is propagated to this class through different layers
	 * of the Application
	 * @return ResponseEntity which is returned back as a JSON Response to user / system
	 */
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        
		List<String> details = new ArrayList<>();
        details.add(CopyRightConstants.INTERNAL_SERVER_ERROR_MSG + ex);
        ExceptionResponse error = new ExceptionResponse(CopyRightConstants.INTERNAL_SERVER_ERROR, details);
        
        log.error(CopyRightConstants.INTERNAL_SERVER_ERROR_MSG, ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}