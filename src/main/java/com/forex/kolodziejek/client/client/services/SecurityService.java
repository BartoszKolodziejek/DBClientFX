package com.forex.kolodziejek.client.client.services;

public interface SecurityService {

	String findLoggedInUsername();

	void autologin(String username, String password);

	/*String validatePasswordResetToken(long id, String token);*/

}
