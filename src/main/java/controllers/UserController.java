package controllers;

import java.security.Principal;
import java.sql.SQLException;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.User;
import business.AuthenticationServiceInterface;
import data.DatabaseServiceInterface2;

@ManagedBean
@SessionScoped
public class UserController {
	@Inject
	AuthenticationServiceInterface authService;
	@Inject
	public DatabaseServiceInterface2 service;
	
	public DatabaseServiceInterface2 getService() {
		return this.service;
	}
	
	private User user = null;
	public boolean userSet() {
		return true;
//		return this.getUser() == null;
	}
	public User getUser() {
		System.out.println("Get users");
		if (this.user == null) {
			try {
				Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
				if (principal == null) {
					System.out.println("The principal is currently null");
					this.user = this.service.getAllUsers().get(0);
					return this.user;
				}
				User usernameOnlyUser = new User();
				usernameOnlyUser.setUsername(principal.getName());
				User linkedUser = service.getUserByUsername(usernameOnlyUser);
				if (linkedUser != null) {
					System.out.println("We found a linked user");
					this.user = linkedUser; 
					return linkedUser;
				} else {
					System.out.println("We were not able to find a linked user so returning the first user.");
					this.user = null;
					return this.user;
				}
				
			} catch (RuntimeException | SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Check if a principal user exists indicating log in
	 * @return
	 */
	public boolean loggedIn() {
		return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null;
	}
	
//	/**
//	 * Invalidate the session to remove the principal user
//	 */
//	public void logOff() {
//		// Invalidate the Session to clear the security token
//		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//	}
	
	public String showUpdateForm(User u) {
		
		//put dating user info into the form
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", u);
		
		return "user_update.xhtml";
	}
	
	public String updateUser() {
		//update our user!
		FacesContext context = FacesContext.getCurrentInstance();
		User u = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		try {
			service.updateUser(u);
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
}
