package com.forex.kolodziejek.client.client.services;

import java.util.Arrays;
import java.util.Calendar;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;




@Service
public class SecurityServiceImpl implements SecurityService  {
	
	/*@Autowired
	PasswordTokenDao passwordTokenRepository;*/
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }

        return null;
    }

    @Override
    public void autologin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", username));
        }
    }


	/*@Override
	public String validatePasswordResetToken(long id, String token) {
		PasswordResetToken passToken = 
			      passwordTokenRepository.findByToken(token);
			    if ((passToken == null) || (passToken.getUser()
			        .getId() != id)) {
			        return "invalidToken";
			    }
			 
			    Calendar cal = Calendar.getInstance();
			    if ((passToken.getExpiryDate()
			        .getTime() - cal.getTime()
			        .getTime()) <= 0) {
			        return "expired";
			    }
			 
			    Pacjenci user = passToken.getUser();
			    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
			      user, null, Arrays.asList(
			      new SimpleGrantedAuthority("USER")));
			    SecurityContextHolder.getContext().setAuthentication(auth);
			    return null;
	}*/
}


