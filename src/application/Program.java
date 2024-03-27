package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TESTE 1: FindById ===");
		Seller seller = sellerDao.findById(1);
		System.out.println(seller.toString());
		System.out.println("=== TESTE 2: FindByDepartment ===");
		Department dep = new Department(2, null);
		List<Seller> listSellers = sellerDao.findByDepartment(dep);
		
		for (Seller seller2 : listSellers) {
			System.out.println(seller2);
		}
		
		
	}

}
