package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.DatingUser;
import business.AuthenticationServiceInterface;
import data.DatabaseServiceInterface2;

/**
 * The controller in charge of manipulating the FormController
 * @author Josh Beck
 *
 */
@ManagedBean
@ViewScoped
public class ProductController {
	
	@Inject
	DatabaseServiceInterface2 service;
	
	@Inject
	AuthenticationServiceInterface authService;

	public DatabaseServiceInterface2 getService() {
		return service;
	}
	public String add() {
		FacesContext context = FacesContext.getCurrentInstance();
		DatingUser datingUser = context.getApplication().evaluateExpressionGet(context, "#{datingUser}", DatingUser.class);

		if (datingUser != null) {
			//Add a user
			datingUser.setUserRef(service.getAllUsers().get(0));
			
			service.addDatingUser(datingUser);
		} 
		
		return "products.xhtml";
	}
}