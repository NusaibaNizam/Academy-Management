package com.bjit.nusaiba.final_project.advice;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import com.bjit.nusaiba.final_project.exception.UserNotFoundExeption;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@ControllerAdvice(annotations = RestController.class)
class APIControllerAdvice extends AbstractMappingJacksonResponseBodyAdvice{
	@Autowired
	private ObjectMapper objectMapper;
	
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Object> allExeceptions(Exception e) {
		ObjectNode bodyOfResponse=objectMapper.createObjectNode();
    	bodyOfResponse.put("timestamp", (new Timestamp(System.currentTimeMillis())).toString());
    	bodyOfResponse.put("status",HttpStatus.BAD_REQUEST.value());
    	bodyOfResponse.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
    	bodyOfResponse.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyOfResponse);
    }
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	@ResponseBody
    public ResponseEntity<Object> userNameTakenExeceptions(Exception e) {
		ObjectNode bodyOfResponse=objectMapper.createObjectNode();
    	bodyOfResponse.put("timestamp", (new Timestamp(System.currentTimeMillis())).toString());
    	bodyOfResponse.put("status",HttpStatus.CONFLICT.value());
    	bodyOfResponse.put("error", HttpStatus.CONFLICT.getReasonPhrase());
    	bodyOfResponse.put("message", "Email or Name Taken");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(bodyOfResponse);
    }
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseBody
    public ResponseEntity<Object> invalidExeceptions(Exception e) {
		ObjectNode bodyOfResponse=objectMapper.createObjectNode();
    	bodyOfResponse.put("timestamp", (new Timestamp(System.currentTimeMillis())).toString());
    	bodyOfResponse.put("status",HttpStatus.UNPROCESSABLE_ENTITY.value());
    	bodyOfResponse.put("error", HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
    	bodyOfResponse.put("message", "Invalid Data");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(bodyOfResponse);
    }
	
	@ExceptionHandler(value = UserNotFoundExeption.class)
    @ResponseBody
    public ResponseEntity<Object> userNotFoundExeceptions(UserNotFoundExeption e) {
		ObjectNode bodyOfResponse=objectMapper.createObjectNode();
    	bodyOfResponse.put("timestamp", (new Timestamp(System.currentTimeMillis())).toString());
    	bodyOfResponse.put("status",HttpStatus.NOT_FOUND.value());
    	bodyOfResponse.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
    	bodyOfResponse.put("message", e.getMessage());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    }
	

	@Override
	protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
			MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
	}

	
	
	
	
}
