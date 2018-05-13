package edu.csula.models;

public class User {
	private final int id;
	private final String username;
	private final String password;

	public User(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		final User user = (User) obj;
		return this.id == user.id && this.username.equals(user.username) &&
			this.password.equals(user.password);
	}

	@Override
	public int hashCode() {
		return this.id;
	}
}
