package controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.DatingUser;
import beans.User;
import business.DatabaseServiceInterface;

/**
 * The controller in charge of manipulating the FormController
 * @author Josh Beck
 *
 */
@ManagedBean
@ViewScoped
public class ProductController {
	//TODO: Eventual feature to display products
	@Inject
	DatabaseServiceInterface service;
	
	public DatabaseServiceInterface getService() {
		return service;
	}
}