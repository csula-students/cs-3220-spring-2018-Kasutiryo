package edu.csula.storage.servlet;

import javax.servlet.http.HttpSession;

import edu.csula.models.User;
import edu.csula.storage.UsersDAO;

import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UsersDAOImplTest {
	private HttpSession context;
	private UsersDAO dao;

	@Before
	public void setup() {
		context = mock(HttpSession.class);
		dao = new UsersDAOImpl(context);
	}

	@Test
	public void authenticate() throws Exception {
		// set up
		when(context.getAttribute(UsersDAOImpl.CONTEXT_NAME)).thenReturn(null);
		// verify
		assertEquals(dao.authenticate("admin", "cs3220password"), true);
		User authenticatedUser = new User(0, "admin", "cs3220password");
		// should set the new user into the HttpSession
		verify(context).setAttribute(UsersDAOImpl.CONTEXT_NAME, authenticatedUser);
	}

	@Test
	public void getAuthenticatedUser() throws Exception {
		// set up
		when(context.getAttribute(UsersDAOImpl.CONTEXT_NAME)).thenReturn(null);
		// verify
		assertEquals(dao.authenticate("admin", "cs3220password"), true);
		User authenticatedUser = new User(0, "admin", "cs3220password");
		// should set the new user into the HttpSession
		verify(context).setAttribute(UsersDAOImpl.CONTEXT_NAME, authenticatedUser);
		// verify
		assertEquals(dao.getAuthenticatedUser().get(), authenticatedUser);
	}

	@Test
	public void logout() throws Exception {
		assertEquals(dao.authenticate("admin", "cs3220password"), true);
		dao.logout();
		verify(context).invalidate();
		assertFalse(dao.getAuthenticatedUser().isPresent());
	}
}
