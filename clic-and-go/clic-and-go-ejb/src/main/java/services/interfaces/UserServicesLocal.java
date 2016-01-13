package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.User;

@Local
public interface UserServicesLocal {
	Boolean addUser(User u);

	Boolean deleteUserById(int id);

	User updateUser(int id);

	User updateReadingSpeed(int id, int speed);

	List<User> findAllUsers();

	User findUserById(int id);

	User authenticate(String name, String password);

	List<User> findUserByName(String name);
	
}
