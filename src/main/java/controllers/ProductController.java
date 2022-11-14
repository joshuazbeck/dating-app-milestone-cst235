package controllers;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.DatingUser;
import beans.User;
import business.AuthenticationServiceInterface;
import data.DatabaseServiceInterface2;

/**
 * The controller in charge of manipulating the FormController
 * @author Josh Beck
 *
 */
@ManagedBean
@SessionScoped
public class ProductController {
	
	@Inject
	DatabaseServiceInterface2 service;
	
	@Inject
	AuthenticationServiceInterface authService;

	
	public DatabaseServiceInterface2 getService() {
		return service;
	}
	
	
	public String add()  {
		FacesContext context = FacesContext.getCurrentInstance();
		DatingUser datingUser = context.getApplication().evaluateExpressionGet(context, "#{datingUser}", DatingUser.class);
		
		if (datingUser != null) {
			//Add a user
			try {
				datingUser.setUserRef(service.getAllUsers().get(0));
				service.addDatingUser(datingUser);
			} catch (RuntimeException | SQLException e) {
				FacesContext context1 = FacesContext.getCurrentInstance();
				context1.addMessage( null, new FacesMessage( "There was an issue connecting to the database.  Try again later." ));
				System.out.println(e.getLocalizedMessage());
				return "";
			}
			
		} 
		
		return "products.xhtml";
	}
	
	public String showUpdateForm(DatingUser dU) {
		System.out.println("Datinguser being edited with first name: " + dU.getFirstName() + dU.getId());
		
		//put dating user info into the form
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("datingUser", dU);
		
		return "products_update.xhtml";
	}
	
	public String updateDatingUser() {
		//update our user!
		FacesContext context = FacesContext.getCurrentInstance();
		DatingUser dU = context.getApplication().evaluateExpressionGet(context, "#{datingUser}", DatingUser.class);
		
		System.out.println("Updated: " + dU.getFirstName());
		System.out.println("ID: " + dU.getId());
		try {
			service.updateDatingUser(dU);
		} catch (RuntimeException | SQLException e) {
			FacesContext context1 = FacesContext.getCurrentInstance();
			context1.addMessage( null, new FacesMessage( "There was an issue connecting to the database.  Try again later." ));
			System.out.println(e.getLocalizedMessage());
			return "";
		}
		
		return "products.xhtml";
	}
	public String deleteDatingUser(DatingUser dU) {
		System.out.println("Deleting datinguser");
		
		System.out.println("name: " + dU.getFirstName() + " ID: " + dU.getId());
		try {
			service.deleteDatingUser(dU);
		} catch (RuntimeException | SQLException e) {
			FacesContext context1 = FacesContext.getCurrentInstance();
			context1.addMessage( null, new FacesMessage( "There was an issue connecting to the database.  Try again later." ));
			System.out.println(e.getLocalizedMessage());
			return "";
		}
		
		return "products.xhtml";
	}
}