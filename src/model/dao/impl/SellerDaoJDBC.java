package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DBException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection connection;
	public SellerDaoJDBC(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(int id) {
		ResultSet resultSet = null;
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT seller.*, department.Name as DepName "
				+"FROM seller INNER JOIN department "
				+"ON seller.DepartmentId = department.Id "
				+"WHERE seller.Id = ?")){
			
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				Department department = new Department();
				Seller seller = new Seller();
				
				department.setId(resultSet.getInt("DepartmentId"));
				department.setName(resultSet.getString("DepName"));
				
				seller.setId(resultSet.getInt("Id"));
				seller.setName(resultSet.getString("Name"));
				seller.setEmail(resultSet.getString("Email"));
				seller.setDate(resultSet.getDate("BirthDate").toLocalDate());
				seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
				seller.setDepartment(department);
				
				return seller;
				
			}
			return null;
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
		
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
