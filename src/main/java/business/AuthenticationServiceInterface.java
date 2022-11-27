package business;

import java.sql.SQLException;

import javax.ejb.Local;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;

import beans.User;

@Local
public interface AuthenticationServiceInterface {
	
	/**
	 * This method is used to validate a user exists
	 * @param username - the user name
	 * @param password - the password
	 * @return - a user if one exists matching the user name and password
	 */
	 public User validateUser(User user) throws RuntimeException, SQLException;
	 
	 /**
	  * Get the logged in user
	  * @return = the logged in user or null if none
	  */
	 public User getLoggedInUser();
	 
	 /**
	  * Check if the user is logged in
	  * @return - true or false if user was logged in
	  */
	 public boolean userIsLoggedIn();
	  
	 /**
	  * Add a user to the database
	  * @param user - User
	 * @throws ServletException 
	  */
	 public void addUser(User user) throws RuntimeException, SQLException;
	 
}
