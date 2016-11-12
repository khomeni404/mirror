package com.mirror2.guest;

import javax.servlet.http.HttpSession;

import com.mirror2.security.service.BootStrap;
import com.mirror2.security.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mirror2.security.service.AuthenticationAndAuthorizationService;


@Controller
@RequestMapping("/auth")
public class AuthenticationController{
	
	HttpSession userSession;
	
	@Autowired
	private AuthenticationAndAuthorizationService authService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
	private BootStrap bootStrap;
	
	@RequestMapping(method=RequestMethod.GET, value="/login.erp")
	public ModelAndView login() throws Exception {
        bootStrap.createDefaultAdmin();
		return new ModelAndView("authentication/login");
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/authenticateUser.erp")
	public ModelAndView authenticateUser(@RequestParam("username") String username,
			@RequestParam("password") String password) throws Exception {
		
		// Call the service method to retrieve the user object by username and password.
		boolean isAuthenticated = authService.authenticate(username, password);
		
		if(isAuthenticated){
			return new ModelAndView("redirect:/home.erp");
		} else {
			return new ModelAndView("redirect:/");
		}
	}
}
