package com.accenture.ui;

import java.util.Date;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.accenture.dao.EmployeeDAO;
import com.accenture.entity.EmployeeEntityBean;

public class UITester {

	public static void main(String[] args) {

		EmployeeDAO employeeDAOIMPL = null;
		try {

			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/accenture/resource/jpa_spring1.xml");
			employeeDAOIMPL = (EmployeeDAO) applicationContext.getBean("employeeDAO");
			
			// 1 Add Employee
			//addEmployee(employeeDAOIMPL);

			// 2 Get Employee Employee
			getEmployeeDetails(employeeDAOIMPL);

			// 3 Update Employee
			//updateEmployeeDetails(employeeDAOIMPL);

			// 4 Delete Employee
			//deleteEmployee(employeeDAOIMPL);
			
			// 5 Get Employee List
			//getEmployeeList(employeeDAOIMPL);
			
			((ClassPathXmlApplicationContext)applicationContext).close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void addEmployee(EmployeeDAO daoimpl) {
		EmployeeEntityBean bean = new EmployeeEntityBean();
		bean.setInsertTime(new Date());
		bean.setName("New Employee");
		bean.setRole("Sr.Analyst");
		bean.setSalary(10.0);
		int id = daoimpl.save(bean).getId();
		System.out.println("Employee Added Successfully: " + id);
	}

	public static void getEmployeeDetails(EmployeeDAO daoimpl) {
		Optional<EmployeeEntityBean> optional = daoimpl.findById(1002);
		optional.ifPresent(foundEmployeeEntityBean->System.out.println("Employee Reterived: "+foundEmployeeEntityBean));
	}

	public static void updateEmployeeDetails(EmployeeDAO daoimpl) {
		Optional<EmployeeEntityBean> optional = daoimpl.findById(1002);
		optional.ifPresent(foundEmployeeEntityBean->{
			foundEmployeeEntityBean.setSalary(1111.2);
			daoimpl.save(foundEmployeeEntityBean);
			System.out.println("Employee Updated : "+foundEmployeeEntityBean);
		});
	}
	
	public static void deleteEmployee(EmployeeDAO daoimpl) {
		Optional<EmployeeEntityBean> optional = daoimpl.findById(1001);
		optional.ifPresent(foundEmployeeEntityBean->{
			daoimpl.delete(foundEmployeeEntityBean);
			System.out.println("Employee Deleted : "+foundEmployeeEntityBean);
		});
	}
	
	public static void getEmployeeList(EmployeeDAO daoimpl) {
		Iterable<EmployeeEntityBean> iterable = daoimpl.findAll();
		iterable.forEach(System.out::println);
	}
}