package edu.csula.storage.servlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.ServletContext;

import edu.csula.models.Generator;
import edu.csula.storage.GeneratorsDAO;

import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GeneratorsDAOImplTest {
	private ServletContext context;
	private GeneratorsDAO dao;

	@Before
	public void setup() {
		context = mock(ServletContext.class);
		dao = new GeneratorsDAOImpl(context);
	}

	@Test
	public void getAll() throws Exception {
		// set up initial mock state
		Collection<Generator> mockList = new ArrayList<>();
		mockList.add(new Generator(1, "name", "desc", 10, 10, 0));
		when(context.getAttribute(GeneratorsDAOImpl.CONTEXT_NAME)).thenReturn(mockList);

		// actual method execution
		Collection<Generator> actual = dao.getAll();

		// assert and verify
		verify(context).getAttribute(GeneratorsDAOImpl.CONTEXT_NAME);
		assertEquals(actual.size(), 1);
	}

	@Test
	public void getAllWhenNull() throws Exception {
		when(context.getAttribute(GeneratorsDAOImpl.CONTEXT_NAME)).thenReturn(null);
		// actual method execution
		Collection<Generator> actual = dao.getAll();

		// should return an empty list
		assertEquals(actual.size(), 0);
	}

	@Test
	public void getById() throws Exception {
		// set up initial mock state
		Collection<Generator> mock = new ArrayList<>();
		mock.add(new Generator(1, "name", "desc", 10, 10, 0));
		when(context.getAttribute(GeneratorsDAOImpl.CONTEXT_NAME)).thenReturn(mock);

		// actual method execution
		Optional<Generator> actual = dao.getById(1);

		// assert and verify
		verify(context).getAttribute(GeneratorsDAOImpl.CONTEXT_NAME);
		assertTrue(actual.isPresent());
		assertEquals(actual.get(), new Generator(1, "name", "desc", 10, 10, 0));
	}

	@Test
	public void set() throws Exception {
		// set up initial mock state
		Collection<Generator> mock = new ArrayList<>();
		mock.add(new Generator(1, "name", "desc", 10, 10, 0));
		when(context.getAttribute(GeneratorsDAOImpl.CONTEXT_NAME)).thenReturn(mock);


		// actual method execution
		dao.set(1, new Generator(1, "new name", "new desc", 20, 50, 10));
		Optional<Generator> actualEvent = dao.getById(1);

		// assert and verify
		verify(context).getAttribute(GeneratorsDAOImpl.CONTEXT_NAME);
		assertTrue(actualEvent.isPresent());
		assertEquals(actualEvent.get(), new Generator(1, "new name", "new desc", 20, 50, 10));
	}

	@Test
	public void add() throws Exception {
		// set up
		when(context.getAttribute(GeneratorsDAOImpl.CONTEXT_NAME)).thenReturn(null);
		Collection<Generator> expected = new ArrayList<>();
		expected.add(new Generator(1, "name", "desc", 10, 10, 0));
		// actual execution
		dao.add(new Generator(1, "name", "desc", 10, 10, 0));
		// verify
		assertEquals(dao.getAll(), expected);
	}
}
