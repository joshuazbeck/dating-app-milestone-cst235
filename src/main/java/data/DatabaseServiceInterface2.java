package data;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import beans.DatingUser;
import beans.User;

public interface DatabaseServiceInterface2 {
	
	void addUsers(User u) throws RuntimeException, SQLException;
	List<User> getAllUsers() throws RuntimeException, SQLException;
	User getUserById(int id) throws RuntimeException, SQLException;
	void updateUser(User u) throws RuntimeException, SQLException;
	void deleteUser(User u) throws RuntimeException, SQLException;
	User getUserByUsername(User u) throws RuntimeException, SQLException;
	
	void addDatingUser(DatingUser du) throws RuntimeException, SQLException;
	List<DatingUser> getAllDatingUsers () throws RuntimeException, SQLException;
	void updateDatingUser(DatingUser du) throws RuntimeException, SQLException;
	void deleteDatingUser(DatingUser du) throws RuntimeException, SQLException;

}
