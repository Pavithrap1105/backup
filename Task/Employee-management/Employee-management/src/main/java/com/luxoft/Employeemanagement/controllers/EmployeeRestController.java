package com.luxoft.Employeemanagement.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxoft.Employeemanagement.entity.Employee;
import com.luxoft.Employeemanagement.services.EmployeeService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	private static String[] columns = { "EmployeeID", "Name", "Employee Salary" };

	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployeeDetails(@Valid @RequestBody Employee emp) {
		Employee savedEmployee = employeeService.saveEmployee(emp);
		return new ResponseEntity<Employee>(savedEmployee, HttpStatus.CREATED);
	}

	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<Employee>> getListOfEmployees() {
		List<Employee> listOfEmployees = employeeService.getAllEmployees();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Employee details");
		
		int rowNum = 1;

		Map<Integer, Object[]> map = new TreeMap<Integer, Object[]>();
		int employeeCount = 0;
		for (Employee emp : listOfEmployees) {
			Object[] obj = new Object[] {emp.getEmployeeId(),emp.getName(),emp.getSalary(),emp.getAddress()};
			map.put(employeeCount++, obj);

		}
		Set<Integer> keySet = map.keySet();

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Header Row
		Row headerRow = sheet.createRow(0);

		// create cells
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		for (Integer key : map.keySet())
		{
		    Row row = sheet.createRow(rowNum++);
		    Object[] emp = map.get(key);
		    int cellNum = 0;
		    for (Object obj : emp)
		    {
		        Cell cell = row.createCell(cellNum++);
		        if(obj instanceof Integer)
		            cell.setCellValue((Integer)obj);
		        else if(obj instanceof String)
		            cell.setCellValue((String)obj);
		        else if(obj instanceof Float)
		        cell.setCellValue((Float) obj);
		    }
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File("Employee.xlsx"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<List<Employee>>(listOfEmployees, HttpStatus.FOUND);
	}

	@GetMapping("/getEmployee/{empId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("empId") Integer empId) {
		Employee employeeById = employeeService.getEmployeeById(empId);
		Link link = linkTo(methodOn(EmployeeRestController.class).getListOfEmployees()).withSelfRel();
		employeeById.add(link);
		return new ResponseEntity<Employee>(employeeById, HttpStatus.OK);
	}



	@PutMapping("updateEmployee/{empId}")
	public ResponseEntity<Employee> updateEmployeeById(@Valid @RequestBody Employee emp,
			@PathVariable("empId") Integer empId) {
		Employee updated = employeeService.updateEmployeeById(emp, empId);
		return new ResponseEntity<Employee>(updated, HttpStatus.OK);
	}

	@DeleteMapping("deleteEmployee/{empId}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("empId") Integer empId) {
		String deleted = employeeService.deleteEmployeeById(empId);
		return new ResponseEntity<String>(deleted, HttpStatus.OK);
	}
	
	@PostConstruct
	public void readFile() {
		FileInputStream inputStream=null;
		try {
			inputStream=new FileInputStream(new File("Employee.xlsx"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFWorkbook workbook=null;
		try {
			 workbook= new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet= workbook.getSheetAt(0);
		Iterator<Row> rowIterator= sheet.iterator();
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch(cell.getCellType()) {
				case NUMERIC:
					System.out.println(cell.getNumericCellValue()+"\t");
					break;
				case STRING:
					System.out.println(cell.getStringCellValue()+"\t");
					break;
				}
			}
			System.out.println("");
		}
		try {
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
