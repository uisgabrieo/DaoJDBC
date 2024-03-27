package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao {
	void insert(Seller obj);
	void update(Seller obj);
	void deleteById(int obj);
	Seller findById(int obj);
	List<Seller> findAll();
}
