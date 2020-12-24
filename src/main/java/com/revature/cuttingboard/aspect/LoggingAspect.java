package com.revature.cuttingboard.aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Aspect class to handle all logging in a single place
 * @author nom.com
 * @since 1.0
 *
 */

@Aspect
@Component
public class LoggingAspect {
	
	@Autowired(required = true)
	private HttpServletRequest req;
	
	private Logger logger = Logger.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(* com.revature.cuttingboard.controller.*.*(..))")
	public void allMethodsInController() {}
	
	@Before("allMethodsInController()")
	public void beforeAllControllerMethods() {
		logger.info(req.getMethod() + " Request recevied to " + req.getServletPath());
	}
}
