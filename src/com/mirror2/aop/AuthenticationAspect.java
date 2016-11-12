package com.mirror2.aop;

import com.mirror2.security.model.User;
import com.mirror2.util.MirrorConstants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.servlet.ModelAndView;

import com.mirror2.security.SessionUtil;

@Aspect
public class AuthenticationAspect {

	@Pointcut("within(@org.springframework.stereotype.Controller *) " +
			"&& !within(@org.springframework.stereotype.Controller com.mirror2.guest.AuthenticationController) ")
	public void allControllers(){}

	@Pointcut("execution(* *(..))")
    public void methodPointcut() {}

	@Around("allControllers() && methodPointcut()")
	public Object proceedToAction(ProceedingJoinPoint proceedJoinPoint) throws Throwable {
		if(SessionUtil.getSession().getAttribute(MirrorConstants.SESSION_USER) instanceof User){
            return proceedJoinPoint.proceed();
		} else {
            return new ModelAndView("redirect:/");
		}
	}

}
