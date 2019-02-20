package lt.ramunas.alksnys.Springbackendportfolioproject.REST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lt.ramunas.alksnys.Springbackendportfolioproject.JpaService.EmployeeServiceJpa;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Employee;
import lt.ramunas.alksnys.Springbackendportfolioproject.exceptions.EmployeeNotFoundException;

@RestController
@RequestMapping("/employee/rest")
public class EmployeeController {

	@Autowired
	EmployeeServiceJpa emplServ;
	
	@GetMapping("/employee")
	List<Employee> all(){
		return emplServ.getAllEmployeess();
	}
	
	@GetMapping("/employee/{id}")
	Employee one(@PathVariable Long id) {
		Employee employee = emplServ.findById(id);
		if(employee == null){
			throw new EmployeeNotFoundException(id);
		}

		return employee;
	}
	
	@PutMapping("/employees")
	void replaceEmployee(@RequestBody Employee newEmployee) {		
		if(emplServ.findByEmail(newEmployee.getEmail()) == null){
			emplServ.saveOrUpdate(newEmployee);
		}		
	}
	
	@DeleteMapping("/employee/{id}")
	void deleteEmployee(@PathVariable Long id) {
		emplServ.deleteById(id);
	}
	
}
