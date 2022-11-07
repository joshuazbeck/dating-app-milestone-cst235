package business;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.User;

/**
 * This service helps to handle authentication for the application
 * @author Josh Beck
 *
 */
@Stateless
@Local(AuthenticationServiceInterface.class)
@LocalBean
@Alternative
public class AuthenticationService implements AuthenticationServiceInterface {
	
	
	//Get an instance of the database service used to handle the "database"
	@Inject
	DatabaseServiceInterface db;
	
	
	/**
	 * This method is used to validate a user exists
	 * @param username - the user name
	 * @param password - the password
	 * @return - a user if one exists matching the user name and password
	 */
	 public User validateUser(String username, String password){
		 // Check the DB for a user with this user name and password
		 User user = this.db.userExists(username, password);
		 
		 // Check if a user was returned
		 if (user != null) {
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
	 public boolean userIsLoggedIn(){
		 //Check the context if a user is logged in
		 System.out.println("logged in " + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userInstance").toString());
		 return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userInstance") != null;
	 }
	 public User getLoggedInUser(){
		 //Check the context if a user is logged in
		 return (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userInstance");
	 }
	 public void invalidateUser(){
		 //Remove the user from the context
		 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("userInstance");
	 }
	  
	 /**
	  * Add a user to the database
	  * @param user - User
	  */
	 public void addUser(User user) {
		 this.db.addUsers(user);
	 }
	  

}
