package data;

import beans.User;

public interface DatabaseServiceInterface2 {
	
	void addUsers(User user);
	User getAllUsers(User user);
	User findUserById(User user);
	void updateUser(User user);
	void deleteUser(User user);
	User findUserByUsername(User u);
	
//	void addDatingUser(User user);
//	DatingUser findAllDatingUsers (User user);
//	DatingUser findDatingUserById (User user);
//	void updateDatingUser (User user);
//	void deleteDatingUser (User user);
//	
//	void addHobby(DatingUser du);
//	void updateHobby(DatingUser du);
//	void deleteHobby(DatingUser du);
//	
//	void addAddress(User user);
//	void findAddress(User user);
//	void updateAddress(User user);
//	void deleteAddress(User user);
}