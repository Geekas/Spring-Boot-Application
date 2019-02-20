package lt.ramunas.alksnys.Springbackendportfolioproject.controllers;

import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lt.ramunas.alksnys.Springbackendportfolioproject.JpaService.EmployeeServiceJpa;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Employee;

@Controller
@RequestMapping("/")
public class DefaultBossController {

	@Autowired
	private EmployeeServiceJpa emplServ;

	@GetMapping("/employee-list")
	public String getEmployeeProfile(ModelMap model) {
		List<Employee> emplList = emplServ.getAllEmployeess();
		emplList.sort(Comparator.naturalOrder());
		model.put("Employeess", emplList);
		return "employee-list";
	}

}
