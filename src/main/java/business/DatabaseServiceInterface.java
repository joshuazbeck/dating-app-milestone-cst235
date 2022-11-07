package business;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import beans.DatingUser;
import beans.User;

@Local
public interface DatabaseServiceInterface {

	
	/**
	 * Add a user to the database
	 * @param user - User
	 */
	public void addUsers(User user);
	
	/**
	 * Check if a user exists
	 * @param username - the username to check for
	 * @param password - the password to check for
	 * @return
	 */
	public User userExists(String username, String password);
	
	public List<User> getAllUsers();
	
	/**
	 * Add a user to the database
	 * @param user - User
	 */
	public void addDatingUser(DatingUser user);
	
	public List<DatingUser> getDatingUsers();

	
}
