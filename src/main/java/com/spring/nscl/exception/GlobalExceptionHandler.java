package com.spring.nscl.exception;

import com.spring.nscl.payload.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//global exception for common exception
    @ExceptionHandler(Exception.class)
    public  ResponseEntity <ErrorDetails> handlerException (Exception exception,WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // handle specific exception (ResourceNotFound)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity <ErrorDetails> handlerResourceNotFoundException (ResourceNotFoundException resourceNotFoundException, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),resourceNotFoundException.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    // handle specific exception (BlogAPIException)
    @ExceptionHandler(BlogAPIException.class)
    public  ResponseEntity <ErrorDetails> handlerBlogAPIException (BlogAPIException blogAPIException,WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),blogAPIException.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_GATEWAY);
    }


    // for not valid arguments in request link
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map <String,String > errors =new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                (error)-> {
                    String fieldname = ((FieldError)error).getField();
                    String message  = error.getDefaultMessage();
                    errors.put(fieldname,message);
                }
        );
        return new ResponseEntity<> (errors,HttpStatus.BAD_GATEWAY);
    }
}
