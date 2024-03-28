package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import db.DBException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection connection;

	public DepartmentDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Department obj) {
		ResultSet resultSet;
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(
				"INSERT INTO department "
				+"(Name) "
				+"VALUES "
				+"(?)", Statement.RETURN_GENERATED_KEYS)) {
				
			
			preparedStatement.setString(1, obj.getName());
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			if (rowsAffected > 0) {
				resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					int id = resultSet.getInt(1);
					obj.setId(id);
				}
			}
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
		
	}

	@Override
	public void update(Department obj) {
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(
				"UPDATE department "
				+"SET Name = ? "
				+"WHERE Id = ?")) {
			
			preparedStatement.setString(1, obj.getName());
			preparedStatement.setInt(2, obj.getId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
		
	}

	@Override
	public void deleteById(int id) {
		try(PreparedStatement preparedStatement = connection.prepareStatement(
				"DELETE FROM department "
				+"WHERE Id = ?")) {
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
		
	}

	@Override
	public Department findById(int id) {
		ResultSet resultSet;
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT * "
				+"FROM department "
				+"WHERE Id = ?")) {
			
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				Department department = instantiateDepartment(resultSet);
				
				return department;
			}
			return null;
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}

	@Override
	public List<Department> findAll() {
		ResultSet resultSet;
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT * "
				+"FROM department")) {
			
			resultSet = preparedStatement.executeQuery();
			
			List<Department> departments = new ArrayList<>();
			
			while(resultSet.next()) {
				Department department = instantiateDepartment(resultSet);
				departments.add(department);
			}
			
			return departments;
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}

	public Department instantiateDepartment(ResultSet resultSet) throws SQLException {
		Department department = new Department();

		department.setId(resultSet.getInt("Id"));
		department.setName(resultSet.getString("Name"));

		return department;
	}

}
