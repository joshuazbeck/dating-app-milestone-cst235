package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.User;
import business.AuthenticationService;
import business.AuthenticationServiceInterface;

/**
 * The controller in charge of manipulating the Login Form
 * It handles logging in and logging out a user by accessing the session and interacting
 * with the AuthenticationService
 * @author Josh Beck
 *
 */
@ManagedBean
@SessionScoped
public class LoginController {
	
	@Inject
	AuthenticationServiceInterface authService;
	
	/**
	 * This code handles logging in a user
	 * @return - A page either success or failure
	 */
	public String login() {
	
		// Get the users from the input form
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		//Check if the user name and password fields are set
		if (user.getUsername() != null && user.getPassword() != null) {
			
			//Get the authenticator to handle accessing and setting the session
			User u = this.authService.validateUser(user.getUsername(), user.getPassword());
			
			if (u != null) {
				//There was a user for the user name and password so return success
				return "products.xhtml";
			} else {
				//Fail login as no users matched
				return "login_fail.xhtml";
			}
		} else {
			//Fail login
			return "login_fail.xhtml";
		}

		
	}
	
	/**
	 * Logout user
	 * @return
	 */
	public String logout() {
		
		this.authService.invalidateUser();
		return "index.xhtml";
	}
	
}
