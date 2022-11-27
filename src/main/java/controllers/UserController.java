package controllers;

import java.security.Principal;
import java.sql.SQLException;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import beans.User;
import business.AuthenticationServiceInterface;
import data.DatabaseServiceInterface2;

/**
 * Handles various controller functions for manipulating the user controller
 * @author Josh Beck
 *
 */
@ManagedBean
@SessionScoped
public class UserController {
	
	//Injects the correct values
	@Inject
	AuthenticationServiceInterface authService;
	@Inject
	public DatabaseServiceInterface2 service;
	
	public DatabaseServiceInterface2 getService() {
		return this.service;
	}
	
	
	private User user = null;
	
	/**
	 * Invalidate the session to remove the principal user
	 */
	public String logOff() {
		// Invalidate the Session to clear the security token
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "index.xhtml";
	}
	
	public String showUpdateForm(User u) {
		
		//put dating user info into the form
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", u);
		
		return "user_update.xhtml";
	}
	
	public String updateUser() {
		//update our user!
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			service.updateUser(this.user);
		} catch (RuntimeException | SQLException e) {
			FacesContext context1 = FacesContext.getCurrentInstance();
			context1.addMessage( null, new FacesMessage( "There was an issue connecting to the database.  Try again later." ));
			System.out.println(e.getLocalizedMessage());
			return "";
		}
		
		return "products.xhtml";
	}
	
	public String deleteUser () {
		System.out.println("Deleting user with:");
		
		FacesContext context = FacesContext.getCurrentInstance();
		User u = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		
		System.out.println("name: " + u.getFirstName() + " ID: " + u.getId());
		try {
			service.deleteUser(u);
		} catch (RuntimeException | SQLException e) {
			FacesContext context1 = FacesContext.getCurrentInstance();
			context1.addMessage( null, new FacesMessage( "There was an issue connecting to the database.  Try again later." ));
			System.out.println(e.getLocalizedMessage());
			return "";
		}
		
		return "products.xhtml";
	}
	
	/************** HELPER FUNCITONS FOR SOME JSF CHECKS ***********/
	boolean userSet = false;
	
	/**
	 * This method tracks if there is a user stored in the principle and database or if not by returning the
	 * tracking variable (userSet) after calling getUser() (this will keep the tracking
	 * variable updated)
	 * @return
	 */
	public boolean userSet() {
		getUser();
		return this.userSet;
	}
	
	/**
	 * Get the linked user from the database based on the principals username
	 * @return
	 */
	public User getUser() {
		if (this.user == null) {
			try {
				Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
				if (principal == null) {
					//The principal is null so return default user
					this.user = this.service.getAllUsers().get(0);
					userSet = true;
					return this.user;
				}
				User usernameOnlyUser = new User();
				usernameOnlyUser.setUsername(principal.getName());
				User linkedUser = service.getUserByUsername(usernameOnlyUser);
				if (linkedUser != null) {
					//There is a user object linked to the principal username
					this.user = linkedUser; 
					userSet = true;
					return linkedUser;
				} else {
					//We were unable to find a linked user so return null
					this.user = null;
					userSet = false;
					return null;
				}
				
			} catch (RuntimeException | SQLException e) {
				e.printStackTrace();
				userSet = false;
				return null;
			}
		}
		return this.user;
	}
	
	public void setUser(User user) {
		this.userSet = true;
		this.user = user;
	}
	
	/**
	 * Check if a principal user exists indicating log in
	 * @return
	 */
	public boolean loggedIn() {
		return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null;
	}
	
}


