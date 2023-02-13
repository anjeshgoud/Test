package com.accenture.dao;



import org.springframework.data.repository.CrudRepository;

import com.accenture.entity.EmployeeEntityBean;

public interface EmployeeDAO extends CrudRepository<EmployeeEntityBean, Integer>{
	

}
