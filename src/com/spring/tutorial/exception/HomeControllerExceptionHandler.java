package com.spring.tutorial.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.spring.tutorial.controller.HomeController;

@ControllerAdvice(assignableTypes = { HomeController.class })
public class HomeControllerExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView processException(Exception e) {
		ModelAndView mav = new ModelAndView("exceptionPage");
		mav.addObject("name", e.getClass().getSimpleName());
		mav.addObject("message", e.getMessage());
		return mav;
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
	@ExceptionHandler(IOException.class)
	public void handleIOException(){
		System.out.println("IOException handler executed");
		//returning 404 error code
	}
}
