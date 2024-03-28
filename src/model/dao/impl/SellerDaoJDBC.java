package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DBException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection connection;

	public SellerDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Seller obj) {
		ResultSet resultSet;

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"INSERT INTO seller "
				+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) " 
				+ "VALUES " + "(?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, obj.getName());
			preparedStatement.setString(2, obj.getEmail());
			preparedStatement.setDate(3, Date.valueOf(obj.getDate()));
			preparedStatement.setDouble(4, obj.getBaseSalary());
			preparedStatement.setInt(5, obj.getDepartment().getId());

			int rowsAffected = preparedStatement.executeUpdate();
			
			if (rowsAffected > 0) {
				resultSet = preparedStatement.getGeneratedKeys();
				
				if (resultSet.next()) {
					int id = resultSet.getInt(1);
					obj.setId(id);
				}
				else {
					throw new DBException("Unexpected error! No rows affected!");
				}
			}

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}

	}

	@Override
	public void update(Seller obj) {
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(
				"UPDATE seller "
				+"SET name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
				+"WHERE id = ?")){

			preparedStatement.setString(1, obj.getName());
			preparedStatement.setString(2, obj.getEmail());
			preparedStatement.setDate(3, Date.valueOf(obj.getDate()));
			preparedStatement.setDouble(4, obj.getBaseSalary());
			preparedStatement.setInt(5, obj.getDepartment().getId());
			preparedStatement.setInt(6, obj.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}


	}

	@Override
	public void deleteById(int obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(int id) {
		ResultSet resultSet;

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(
				"SELECT seller.*, department.Name as DepName " 
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?")) {

			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				Department department = instantiateDepartment(resultSet);
				Seller seller = instantiateSeller(resultSet, department);

				return seller;
			}
			return null;

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}

	@Override
	public List<Seller> findAll() {
		ResultSet resultSet;

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT seller.*, department.Name as DepName " 
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id " 
				+ "ORDER BY Name")) {

			resultSet = preparedStatement.executeQuery();

			List<Seller> sellers = new ArrayList<>();
			Map<Integer, Department> mapDepartment = new HashMap<>();

			while (resultSet.next()) {

				Department dep = mapDepartment.get(resultSet.getInt("DepartmentId"));

				if (dep == null) {
					dep = instantiateDepartment(resultSet);
					mapDepartment.put(resultSet.getInt("DepartmentId"), dep);
				}

				Seller seller = instantiateSeller(resultSet, dep);

				sellers.add(seller);
			}

			return sellers;
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		ResultSet resultSet;

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT seller.*, department.Name as DepName " 
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id " 
				+ "WHERE DepartmentId = ? " + "ORDER BY Name")) {

			preparedStatement.setInt(1, department.getId());

			resultSet = preparedStatement.executeQuery();

			List<Seller> sellers = new ArrayList<>();
			Map<Integer, Department> mapDepartment = new HashMap<>();

			while (resultSet.next()) {

				Department dep = mapDepartment.get(resultSet.getInt("DepartmentId"));

				if (dep == null) {
					dep = instantiateDepartment(resultSet);
					mapDepartment.put(resultSet.getInt("DepartmentId"), dep);
				}

				Seller seller = instantiateSeller(resultSet, dep);

				sellers.add(seller);
			}

			return sellers;
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}

	public Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
		Seller seller = new Seller();

		seller.setId(resultSet.getInt("Id"));
		seller.setName(resultSet.getString("Name"));
		seller.setEmail(resultSet.getString("Email"));
		seller.setDate(resultSet.getDate("BirthDate").toLocalDate());
		seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
		seller.setDepartment(department);

		return seller;
	}

	public Department instantiateDepartment(ResultSet resultSet) throws SQLException {
		Department department = new Department();

		department.setId(resultSet.getInt("DepartmentId"));
		department.setName(resultSet.getString("DepName"));

		return department;
	}

}
