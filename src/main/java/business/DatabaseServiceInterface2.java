package business;

import beans.User;

public interface DatabaseServiceInterface2 {

	User findAllUsers(User user);

	User findUserById(User user);

	void updateUser(User user);

	void deleteUser(User user);

}
