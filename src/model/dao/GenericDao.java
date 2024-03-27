package model.dao;

import java.util.List;

public interface GenericDao<T> {
	void insert(T obj);
	void update(T obj);
	void deleteById(int obj);
	T findById(int obj);
	List<T> findAll();
}
