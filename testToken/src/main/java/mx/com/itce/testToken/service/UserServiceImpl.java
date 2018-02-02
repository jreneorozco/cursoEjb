package mx.com.itce.testToken.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.itce.testToken.entity.Role;
import mx.com.itce.testToken.entity.RoleRepository;
import mx.com.itce.testToken.entity.User;
import mx.com.itce.testToken.entity.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;

	@Override
	public User findUserByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(user.getPassword());
		user.setActive(1);
		Role userRole=roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
		
	}

}
