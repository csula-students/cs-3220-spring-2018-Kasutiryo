package edu.csula.storage.servlet;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

/**
 * To abstract the storage access from the business layer using ServletContext
 * (application scope). This implementation will store the underlying data under
 * the application scope and read from it accordingly.
 *
 * As ServletContext is like a global HashMap, it's important for you to add a
 * context name to separate out the different section of data (e.g. events vs
 * generators) so that you can have the application scope looks like below:
 *
 * ```json
 * {
 *   "events": [
 *     { "id": 0, "name": "event-1", "description": "..." }
 *   ],
 *   "generators": [
 *     { "id": 0, "name": "generator-1", "description": "..." }
 *   ]
 * }
 * ```
 */
public class EventsDAOImpl implements EventsDAO {
	private final ServletContext context;
	protected static final String CONTEXT_NAME = "events";

	public EventsDAOImpl(ServletContext context) {
		this.context = context;
	}

	@Override
	public List<Event> getAll() {
		// TODO: read a list of events from context
		Object events = context.getAttribute(CONTEXT_NAME);
		if (events == null) {
			return new ArrayList<>();
		}
		return (ArrayList<Event>) events;
	}

	@Override
	public Optional<Event> getById(int id) {
		// TODO: get a certain event given its id from context (see getAll() on
		// getting a list first and get a certain one from the list)
		ArrayList<Event> list = (ArrayList<Event>) getAll();
		for (Event item: list) {
			if (item.getId() == id) 
				return Optional.of(item);
		}
		return Optional.empty();
	}

	@Override
	public void set(int id, Event event) {
		// TODO: set a certain event given id to be different from context
		ArrayList<Event> list = (ArrayList<Event>) getAll();
		int i = 0;
		while(list.get(i).getId() != id) {
			i++;
		}
		list.set(i, event);
		context.setAttribute(CONTEXT_NAME, list);
	}

	@Override
	public void add(Event event) {
		// TODO: add a new event to the context
		ArrayList<Event> list = (ArrayList<Event>) getAll();
		list.add(event);
		context.setAttribute(CONTEXT_NAME, list);
	}

	@Override
	public void remove(int id) {
		// TODO: remove a single event given id
		ArrayList<Event> list = (ArrayList<Event>) getAll();
		int i = 0;
		while(list.get(i).getId() != id) {
			i++;
		}
		list.remove(i);
		context.setAttribute(CONTEXT_NAME, list);
	}
}
