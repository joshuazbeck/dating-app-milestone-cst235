package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.DatingUser;
import beans.User;
import business.AuthenticationServiceInterface;
import business.DatabaseServiceInterface;

/**
 * The controller in charge of manipulating the FormController
 * @author Josh Beck
 *
 */
@ManagedBean
@ViewScoped
public class ProductController {
	
	@Inject
	DatabaseServiceInterface service;
	
	@Inject
	AuthenticationServiceInterface authService;

	public DatabaseServiceInterface getService() {
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