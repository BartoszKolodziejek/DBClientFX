package com.forex.kolodziejek.client.client.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forex.kolodziejek.client.client.dao.UserDao;
import com.forex.kolodziejek.client.client.entities.Roles;
import com.forex.kolodziejek.client.client.entities.User;



@Service
public class UserDatailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserDao userdao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  User user = userdao.findByUsername(username);

	        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	        for (Roles role : user.getRoles()){
	            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
	        }

	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPass(), grantedAuthorities);
	    }
	}


