package com.forex.kolodziejek.client.client.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.forex.kolodziejek.client.client.dao.RolesDao;
import com.forex.kolodziejek.client.client.dao.UserDao;
import com.forex.kolodziejek.client.client.entities.Roles;
import com.forex.kolodziejek.client.client.entities.User;




@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserDao userRepository;
    @Autowired
    private RolesDao roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
 /*   @Autowired
    PasswordTokenDao passwordTokenRepository;*/

    
  /*  @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }*/
    
    @Override
    public void save(User user) {
        user.setPass(bCryptPasswordEncoder.encode(user.getPass()));
        Set<Roles> roleSet = new HashSet<>();
        roleSet.add(roleRepository.findByName("USER"));
        user.setRoles(roleSet);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	/*@Override
	public void changeUserPassword(User user, String pass) {
		user.setPass(bCryptPasswordEncoder.encode(pass));
		userRepository.save(user);
		
	}*/
}
