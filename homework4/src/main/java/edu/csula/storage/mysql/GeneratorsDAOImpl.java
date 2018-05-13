package edu.csula.storage.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.sql.*;

import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.Database;
import edu.csula.models.Generator;

public class GeneratorsDAOImpl implements GeneratorsDAO {
	private final Database context;

	// TODO: fill the Strings with the SQL queries as "prepated statements" and
	//       use these queries variable accordingly in the method below
	protected static final String getAllQuery = "SELECT * FROM generators;";
	protected static final String getByIdQuery = "SELECT * FROM generators WHERE id = (?)";
	protected static final String setQuery = "UPDATE generators SET id = ?, name = ?, description = ?, rate = ?, base_cost = ?, unlock_at = ? WHERE id = ?";
	protected static final String addQuery = "INSERT INTO generators (id, name, description, rate, base_cost, unlock_at) VALUES (?, ?, ?, ?, ?, ?)";
	protected static final String removeQuery = "DELETE FROM generators WHERE id = (?)";

	public GeneratorsDAOImpl(Database context) {
		this.context = context;
	}

	@Override
	public List<Generator> getAll() {
		// TODO: get all generators from jdbc
		List<Generator> generators = new ArrayList<>();
		try (Connection c = context.getConnection(); Statement stmt = c.createStatement()) {
			ResultSet rs = stmt.executeQuery(getAllQuery);
			while (rs.next()) {
				Generator generator = new Generator(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
				generators.add(generator);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return generators;
	}

	@Override
	public Optional<Generator> getById(int id) {
		// TODO: get specific generator by id
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(getByIdQuery)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			Generator generator = new Generator(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
			return Optional.of(generator);
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public void set(int id, Generator generator) {
		// TODO: update specific generator by id
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(setQuery)) {
			stmt.setInt(1, generator.getId());
			stmt.setString(2, generator.getName());
			stmt.setString(3, generator.getDescription());
			stmt.setInt(4, generator.getRate());
			stmt.setInt(5, generator.getBaseCost());
			stmt.setInt(6, generator.getUnlockAt());
			stmt.setInt(7, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			//TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void add(Generator generator) {
		// TODO: implement jdbc logic to add a new generator
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(addQuery)) {
			stmt.setInt(1, generator.getId());
			stmt.setString(2, generator.getName());
			stmt.setString(3, generator.getDescription());
			stmt.setInt(4, generator.getRate());
			stmt.setInt(5, generator.getBaseCost());
			stmt.setInt(6, generator.getUnlockAt());
			stmt.executeUpdate();
		} catch (SQLException e) {
			//TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void remove(int id) {
		// TODO: implement jdbc logic to remove generator by id
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(removeQuery)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
