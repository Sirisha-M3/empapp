package com.imaginnovate.empapp.service;

import java.util.List;

import com.imaginnovate.empapp.payload.EmployeeDto;

public interface EmployeeService {
	public Object createEmployee(Object dto);
	public 	List<EmployeeDto> getAllEmployee(int pageNo, int pageSize, String sortBy, String sortDesc);
	public Double taxDeductionYear(Long id);

}
