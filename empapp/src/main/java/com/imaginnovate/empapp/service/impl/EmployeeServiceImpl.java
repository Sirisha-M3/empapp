package com.imaginnovate.empapp.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.imaginnovate.empapp.exception.ResourceNotFoundExceptiont;
import com.imaginnovate.empapp.model.Employee;
import com.imaginnovate.empapp.model.EmployeeTax;
import com.imaginnovate.empapp.payload.EmployeeDto;
import com.imaginnovate.empapp.repository.EmployeeRepository;
import com.imaginnovate.empapp.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	@Autowired
	private EmployeeRepository emprepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EmployeeDto createEmployee(Object employeedto) {
		logger.debug("Enter createEmployee");
		Employee employee = modelMapper.map(employeedto, Employee.class);
		EmployeeDto empDto = null;
		Employee emp = null;
		try {
			emp = emprepository.save(employee);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new ResourceNotFoundExceptiont("record allready exists");
		}
		empDto = modelMapper.map(emp, EmployeeDto.class);
		return empDto;
	}

	@Override
	public List<EmployeeDto> getAllEmployee(int pageno, int pagesize, String sortby, String sortdrc) {
		Sort sort = sortdrc.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortby).ascending()
				: Sort.by(sortby).descending();
		Pageable pag = PageRequest.of(pageno, pagesize, sort);
		Page<Employee> allemploylist = this.emprepository.findAll(pag);
		List<EmployeeDto> allemploydtolist = allemploylist.stream()
				.map((Employee) -> modelMapper.map(Employee, EmployeeDto.class)).collect(Collectors.toList());
		return allemploydtolist;
	}

	@Override
	public Double taxDeductionYear(Long id) {
		Double empTaxDedcution = 0.0;
		Double empTaxCess = 0.0;
		EmployeeTax employeeTax = new EmployeeTax();
		Employee employeebyid = emprepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptiont("emp record not found"));

		EmployeeDto empDto = modelMapper.map(employeebyid, EmployeeDto.class);

		LocalDate empStartDate = empDto.getDoj();
		LocalDate empEndDate = LocalDate.now();
		long totalemployeemonthsworking = ChronoUnit.MONTHS.between(empStartDate, empEndDate);
		Double totalsalaryofemployee = empDto.getSalary() * totalemployeemonthsworking;

		if (totalsalaryofemployee > 250000 && totalsalaryofemployee <= 500000) {

			empTaxDedcution = (totalsalaryofemployee - 250000) * 0.05;

		} else if (totalsalaryofemployee > 500000 && totalsalaryofemployee <= 1000000) {
			empTaxDedcution = 12500 + (totalsalaryofemployee - 500000) * 0.1;
		} else if (totalsalaryofemployee > 1000000) {

			empTaxDedcution = 112500 + (totalsalaryofemployee - 1000000) * 0.2;
		}

		if (totalsalaryofemployee > 2500000) {
			empTaxCess = (totalsalaryofemployee - 2500000) * 0.02;
		}
		double empTax = empTaxDedcution + empTaxCess;
		employeeTax.setEmployeeID(empDto.getEmployeeID());
		employeeTax.setFirstName(empDto.getFirstname());
		employeeTax.setLastName(empDto.getLastname());
		employeeTax.setEmail(empDto.getEmail());
		employeeTax.setdOJ(empDto.getDoj());
		employeeTax.setSalary(empDto.getSalary());
		employeeTax.setTotalTexAmount(empTaxDedcution);
		employeeTax.setTotalCessAmount(empTaxCess);
		employeeTax.setPhoneNumber(empDto.getPhoneNumbers());

		return empTax;
	}

}
