/**
 * 
 */
package com.project.weather.travel.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.project.weather.travel.exception.NoDataFoundException;

@Aspect
@Component
public class MonitorService {
	
	Logger log = LoggerFactory.getLogger(MonitorService.class);
	
	/**
	 * Before executing any method in the controller, this advice is called
	 * @param point - object which provides information of the join point 
	 * 
	 */
	@Before("execution(* com..*Controller.*(..))")
	public void logBefore(JoinPoint point) {
		log.debug("Target: {} " + point.getTarget().toString());
		log.debug("Before advice called");
	}
	
	/**
	 * After executing any method in the controller, this advice is called
	 * @param point - object which provides information of the join point 
	 * 
	 */
	@After("execution(* com..*Controller.*(..))")
	public void logAfter(JoinPoint point) {
		log.debug("Target: {}" + point.getTarget().toString());
		log.debug("After advice called");
	}
	
	/**
	 * After throwing exception in any method in the controller, this advice is called
	 * @param point - object which provides information of the join point 
	 * @param dataEx - object of No Data Found Exception
	 */
	@AfterThrowing(pointcut="execution(* com..*Controller.*(..))", throwing="dataEx")
	public void logExceptions(JoinPoint point, NoDataFoundException dataEx) {
		dataEx.printStackTrace();
		log.debug("Exception advice invoked after throwing NoDataFound Exception");
	
	}
}
