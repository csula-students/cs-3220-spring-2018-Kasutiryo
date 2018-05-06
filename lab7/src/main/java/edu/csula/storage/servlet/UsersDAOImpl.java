package edu.csula.storage.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import edu.csula.models.User;
import edu.csula.storage.UsersDAO;

/**
 * To abstract the storage access from the business layer using HttpSession
 */
public class UsersDAOImpl implements UsersDAO {
	private final HttpSession context;
	protected static final String CONTEXT_NAME = "users";
	private User admin = new User(0, "admin", "cs3220password");

	public UsersDAOImpl(HttpSession context) {
		this.context = context;
	}

	@Override
	public boolean authenticate(String username, String password) {
		// TODO: check if username/password combination is valid and store the
		//       username/password into the session

		if(admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
			context.setAttribute(CONTEXT_NAME, admin);
			return true;
		}
		return false;
	}

	@Override
	public Optional<User> getAuthenticatedUser() {
		// TODO: return the authenticated user if there is any
		if (context.getAttribute(CONTEXT_NAME) == null) {
			return Optional.empty();
		}
		return Optional.of(admin);
	}

	@Override
	public void logout() {
		// TOOD: log user out using `invalidate`
		context.invalidate();
	}
}
