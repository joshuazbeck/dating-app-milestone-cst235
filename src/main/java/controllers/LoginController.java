package controllers;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
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
	
	boolean loggedIn = false;
	public boolean getLoggedIn() {
		return this.loggedIn;
	}
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
			User u;
			try {
				u = this.authService.validateUser(user);
			} catch (RuntimeException | SQLException e) {
				FacesContext context1 = FacesContext.getCurrentInstance();
				context1.addMessage( null, new FacesMessage( "There was an issue connecting to the database.  Try again later." ));
				System.out.println(e.getLocalizedMessage());
				return "";
			}
			
			if (u != null) {
				//There was a user for the user name and password so return success
				loggedIn = true;
				
				return "products.xhtml";
			} else {
				//Fail login as no users matched
				loggedIn = false;
				return "login_fail.xhtml";
			}
		} else {
			//Fail login
			loggedIn = false;
			return "login_fail.xhtml";
		}

		
	}
	
	/**
	 * Logout user
	 * @return
	 */
	public String logout() {
		
		this.authService.invalidateUser();
		loggedIn = false;
		return "index.xhtml";
	}
	
}
