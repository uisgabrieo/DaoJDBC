package application;

import java.time.LocalDate;
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
		
		for (Seller sellerDepartment : listSellers) {
			System.out.println(sellerDepartment);
		}
		
		System.out.println("=== TESTE 3: FindAll ===");
		List<Seller> listSellersAll = sellerDao.findAll();
		
		for (Seller sellerAll : listSellersAll) {
			System.out.println(sellerAll);
		}
		
		System.out.println("=== TESTE 4: Insert ===");
		Seller newSeller = new Seller(null, "Luis Gabriel", "luis@gmail.com", LocalDate.now(), 15000.00, dep);
		sellerDao.insert(newSeller);
		System.out.println("ID new Seller: " + newSeller.getId());
		
	}

}
