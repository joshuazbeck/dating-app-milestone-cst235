package data;

import java.util.List;

import beans.DatingUser;
import beans.User;

public interface DatabaseServiceInterface2 {
	
	void addUsers(User u);
	List<User> getAllUsers();
	User getUserById(User u);
	void updateUser(User u);
	void deleteUser(User u);
	User getUserByUsername(User u);
	
	void addDatingUser(DatingUser du);
	List<DatingUser> getAllDatingUsers ();
}
