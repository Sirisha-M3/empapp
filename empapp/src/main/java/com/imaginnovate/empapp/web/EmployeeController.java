package com.imaginnovate.empapp.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.empapp.payload.EmployeeDto;
import com.imaginnovate.empapp.service.impl.EmployeeServiceImpl;

@RestController
@RequestMapping("/imaginnovate/api")
public class EmployeeController {
	@Autowired
	private EmployeeServiceImpl empservice;
	
	Logger logger =LoggerFactory.getLogger(EmployeeController.class);

	@PostMapping("/employees")
	public ResponseEntity<?> addemploye(@Validated @RequestBody Object employeedto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> validationsMap = new HashMap<String, String>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				validationsMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(validationsMap, HttpStatus.BAD_REQUEST);

		} else {

			EmployeeDto st = empservice.createEmployee(employeedto);
	        logger.debug("A DEBUG Message");
			return new ResponseEntity<EmployeeDto>(st, HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDto>> getallemployee(
			@RequestParam(value = "pageno", defaultValue = "0", required = false) Integer pageno,
			@RequestParam(value = "pagesize", defaultValue = "10", required = false) Integer pagesize,
			@RequestParam(value = "sotrby", defaultValue = "empid", required = false) String sotrby,
			@RequestParam(value = "sotrdsc", defaultValue = "asc", required = false) String sotrdsc) {
		List<EmployeeDto> employee = empservice.getAllEmployee(pageno, pagesize, sotrby, sotrdsc);

		return new ResponseEntity<List<EmployeeDto>>(employee, HttpStatus.OK);

	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<?> taxDeductionCurrentYear(@PathVariable("id")Long id){
		return new ResponseEntity<Double>(empservice.taxDeductionYear(id),HttpStatus.OK);
		
	}
	
}
