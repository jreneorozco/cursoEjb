package mx.com.itce.testToken.service;

import mx.com.itce.testToken.entity.User;

public interface UserService {

	public User findUserByEmail(String email);
	public void saveUser(User user);
}
