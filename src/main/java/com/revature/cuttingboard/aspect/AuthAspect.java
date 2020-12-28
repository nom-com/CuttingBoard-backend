package com.revature.cuttingboard.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.utils.TokenUtility;

import io.jsonwebtoken.Claims;

/**
 * Apsect class to handle authentication prior to running controller methods that are authenticated
 * endpoints
 * @author nom.com
 * @since 1.0
 *
 */
@Aspect
@Component
public class AuthAspect {
	
	@Autowired(required = true)
	private HttpServletRequest req;
	@Autowired(required = true)
	private HttpServletResponse res;
	@Autowired
	private TokenUtility tokenUtility;
	
	@Around("@annotation(com.revature.cuttingboard.annotation.AuthenticatedEndpoint)")
	public Object aroundAuthenticatedEndpoints(ProceedingJoinPoint jp) {
		try {
			String token = req.getHeader("Token");
			Claims claim = tokenUtility.decodeJWT(token);
			SystemUser user = new SystemUser();
			user.setUsername(claim.getSubject());
			user.setId(Integer.valueOf(claim.getId()));
			
			jp.getArgs()[jp.getArgs().length -1] = user;
			Object result = jp.proceed(jp.getArgs());
			return result;
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		} catch (Throwable e) {
			res.setStatus(401);
			return null;
		}
	}
}
