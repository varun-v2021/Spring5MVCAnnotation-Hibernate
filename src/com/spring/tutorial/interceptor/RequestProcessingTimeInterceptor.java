package com.spring.tutorial.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * Spring HandlerInterceptor declares three methods based on where we want to intercept the HTTP request.

boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler): This method is used to intercept the request before it’s handed over to the handler method. This method should return ‘true’ to let Spring know to process the request through another spring interceptor or to send it to handler method if there are no further spring interceptors.
If this method returns ‘false’ Spring framework assumes that request has been handled by the spring interceptor itself and no further processing is needed. We should use response object to send response to the client request in this case.

Object handler is the chosen handler object to handle the request. This method can throw Exception also, in that case Spring MVC Exception Handling should be useful to send error page as response.

void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView): This HandlerInterceptor interceptor method is called when HandlerAdapter has invoked the handler but DispatcherServlet is yet to render the view. This method can be used to add additional attribute to the ModelAndView object to be used in the view pages. We can use this spring interceptor method to determine the time taken by handler method to process the client request.
void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex): This is a HandlerInterceptor callback method that is called once the handler is executed and view is rendered.
 * 
 * */

/*
 * preHandle(): It is executed before actual handler is executed. 
postHandle(): It is executed after handler is executed. 
afterCompletion(): It is executed after the complete request is finished. 
 * */

public class RequestProcessingTimeInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long startTime = System.currentTimeMillis();
		System.out.println(
				"Request URL::" + request.getRequestURL().toString() + ":: Start Time=" + System.currentTimeMillis());
		request.setAttribute(" startTime", startTime);
		// if returned false, we need to make sure 'response' is sent
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Request URL::" + request.getRequestURL().toString() + " Sent to Handler :: Current Time="
				+ System.currentTimeMillis());
		// we can add attributes in the modelAndView and use that in the view
		// page
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute(" startTime");
		System.out.println(
				"Request URL::" + request.getRequestURL().toString() + ":: End Time=" + System.currentTimeMillis());
		System.out.println("Request URL::" + request.getRequestURL().toString() + ":: Time Taken="
				+ (System.currentTimeMillis() - startTime));
	}
}
