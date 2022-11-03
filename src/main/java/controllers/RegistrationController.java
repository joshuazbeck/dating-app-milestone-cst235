package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.User;
import business.AuthenticationService;
import business.AuthenticationServiceInterface;

/**
 * This controller is in charge of handling user registration
 * @author Josh Beck
 *
 */
@ManagedBean
@ViewScoped
public class RegistrationController {
	
	@Inject
	AuthenticationServiceInterface authService;
	
	/**
	 * The code that is used to register the user
	 * @return - The home page
	 */
	public String register() {

		// Get the users passed from the form
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		if (user != null) {
			this.authService.addUser(user);
		}

		return "index.xhtml";
	}
	
}
