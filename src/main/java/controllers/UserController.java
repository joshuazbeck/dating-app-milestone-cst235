package controllers;

import java.sql.SQLException;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.User;
import business.AuthenticationServiceInterface;
import data.DatabaseServiceInterface2;

@ManagedBean
@ViewScoped
public class UserController {
	@Inject
	AuthenticationServiceInterface authService;
	@Inject
	DatabaseServiceInterface2 service;
	
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
	
	public String deleteUser (User u) {
		System.out.println("Deleting user with:");
		
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
