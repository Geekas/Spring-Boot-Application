package lt.ramunas.alksnys.Springbackendportfolioproject.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lt.ramunas.alksnys.Springbackendportfolioproject.JpaService.EmployeeServiceJpa;



@Controller
@RequestMapping("/")
public class DefaultEmplController {
	
	@Autowired
	private EmployeeServiceJpa emplServ;
	
	private Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
	
	@GetMapping("/employee-profile")
	public String getEmployeeProfile(ModelMap model) {		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {						
			model.put("employee", emplServ.findByEmail(((UserDetails) principal).getUsername()));
		}
		return "employeeProfile";
	}
	
}
