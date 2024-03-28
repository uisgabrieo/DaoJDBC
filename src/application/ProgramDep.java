package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class ProgramDep {

	public static void main(String[] args) {
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== TESTE 1: FindById ===");
		Department department = departmentDao.findById(5);
		System.out.println(department);

		System.out.println("=== TESTE 2: FindAll ===");
		List<Department> departments = departmentDao.findAll();
		for(Department dep : departments) {
			System.out.println(dep);
		}
		
		System.out.println("=== TESTE 3: Insert ===");
		Department newDepartment = new Department(null, "Tv");
		departmentDao.insert(newDepartment);
		System.out.println("Id new department: " + newDepartment.getId());
		
		System.out.println("=== TESTE 4: Delete ===");
		departmentDao.deleteById(6);
		System.out.println("Department deleted");
		
		System.out.println("=== TESTE 5: Update ===");
		newDepartment.setName("Pens");
		departmentDao.update(newDepartment);
		System.out.println(newDepartment);
	}

}
