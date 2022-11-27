package business;

import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.User;
import beans.UserDataModel;
import data.DatabaseServiceInterface2;

/**
 * This service helps to handle authentication for the application
 * @author Josh Beck
 *
 */
@Path("/user")
@Stateless
@Local(AuthenticationServiceInterface.class)
@LocalBean
@Alternative
public class AuthenticationService implements AuthenticationServiceInterface {
	
	
	//Get an instance of the database service used to handle the "database"
	@Inject
	DatabaseServiceInterface2 db;
	
	@GET
	@Path("/logout")
	public void logout() {
		System.out.println("Hey let's go!!");
	}
	
	/**
	 * This method is used to validate a user exists
	 * @param username - the user name
	 * @param password - the password
	 * @return - a user if one exists matching the user name and password
	 */
	 public User validateUser(User u) throws RuntimeException, SQLException{
		 // Check the DB for a user with this user name and password
		 User user = this.db.getUserByUsername(u);
		 
		 // Check if a user was returned
		 if (user.getUsername() != null) {
			 //Set the logged in value of the user to true
			 user.setLoggedIn(true);
			 //Save the user in the context to indicate a logged in user
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userInstance", user);
			 System.out.println("validate user " + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userInstance").toString());
			 return user;
		 } else {
			 return null;
		 }
		 
	 }
	 /**
	  * Returns if a user is logged in
	  */
	 public boolean userIsLoggedIn(){
		 //Check the context if a user is logged in
		 System.out.println("logged in " + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userInstance").toString());
		 return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userInstance") != null;
	 }
	 
	 /**
	  * Getter for logged in user
	  */
	 public User getLoggedInUser(){
		 //Check the context if a user is logged in
		 return (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userInstance");
	 }
	  
	 /**
	  * Add a user to the database
	  * @param user - User
	  */
	 public void addUser(User user) throws RuntimeException, SQLException {

		 this.db.addUsers(user);

	 }
	  

}
