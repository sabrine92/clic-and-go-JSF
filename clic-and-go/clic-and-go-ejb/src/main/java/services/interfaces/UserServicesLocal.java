package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.User;

@Local
public interface UserServicesLocal {

	
	Boolean addUser(User u);
	Boolean deleteUserById(int id);
	User updateUser(int id);
	List<User> findAllUsers();
	User findUserById(int id);
	
}
