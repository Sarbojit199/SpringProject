package com.code.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<ApiResponse> resourseNotFoundExceptionHandler(ResourseNotFoundException ex){
		String message=ex.getMessage();
		ApiResponse apiResponse =new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse> (apiResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
		Map<String,String> resp =new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			String fieldName=((FieldError) error).getField();
			String message=error.getDefaultMessage();
			resp.put(fieldName,message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}

}
