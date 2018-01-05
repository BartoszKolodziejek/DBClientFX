package com.forex.kolodziejek.client.client.services;

import com.forex.kolodziejek.client.client.entities.User;

public interface UserService {

	void save(User user);

	User findByUsername(String username);

	/*void createPasswordResetTokenForUser(User user, String token);

	void changeUserPassword(User pacjenci, String pass);
*/
}
