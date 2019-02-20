package lt.ramunas.alksnys.Springbackendportfolioproject.controllers;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lt.ramunas.alksnys.Springbackendportfolioproject.JpaService.ContactsServiceJpa;
import lt.ramunas.alksnys.Springbackendportfolioproject.JpaService.EmployeeServiceJpa;
import lt.ramunas.alksnys.Springbackendportfolioproject.JpaService.ProjectsServiceJpa;
import lt.ramunas.alksnys.Springbackendportfolioproject.JpaService.RoleServiceJpa;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Contacts;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Employee;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Project;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Role;

@Controller
@RequestMapping("/employee")
public class EmployeePageController {

	@Autowired
	private EmployeeServiceJpa emplServ;

	@Autowired
	private ContactsServiceJpa contServ;

	@Autowired
	private ProjectsServiceJpa projServ;

	@Autowired
	private RoleServiceJpa roleServ;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	private HashMap<Long, Set<Role>> roleMap = new HashMap<>();

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/employeess")
	public String getEmployeePage(ModelMap model) {
		List<Employee> emplList = emplServ.getAllEmployeess();
		emplList.sort(Comparator.naturalOrder());
		model.put("Employeess", emplList);
		return "employee-list";
	}	

	@GetMapping("/add-employee")
	public String showAddEmployeePage(ModelMap model) {
		model.addAttribute("employee",
				new Employee("firstName", "lastName", 1000l, new Date(), "test@test.lt", "Devoloper", "password", 1));
		model.put("contacts", new Contacts("Street", "Town", "Phone"));
		model.put("action", "Add ");
		return "employee";
	}

	@PostMapping("/add-employee")
	public String addTodo(ModelMap model, @Valid Employee employee, BindingResult resultEmployee,
			@Valid Contacts contacts, BindingResult resultContacts) {
		if (resultEmployee.hasErrors() || resultContacts.hasErrors()) {			
			return "employee";
		}		
		if (emplServ.findByEmail(employee.getEmail()) != null) {
			resultEmployee.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
			return "employee";
		}
		employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
		emplServ.saveWithContacts(employee, contacts);
		return "redirect:/employee/employeess";
	}

	@GetMapping("/update-employee")
	public String updateEmployee(ModelMap model, @RequestParam Long id, @RequestParam Long contId) {
		Employee employee = emplServ.findById(id);
		roleMap.put(employee.getId(), employee.getRoles());
		model.put("employee", employee);
		model.put("action", "Update ");
		model.put("roles", employee.getRoles());
		if (contId != null) {
			Contacts contacts = contServ.findById(contId);

			model.put("contacts", contacts);
		} else {
			model.put("contacts", new Contacts("Street", "Town", "Phone"));
		}
		return "employee";
	}

	@PostMapping("/update-employee")
	public String addEmployee(ModelMap model, @Valid Employee employee, BindingResult resultEmployee,
			@Valid Contacts contacts, BindingResult resultContacts, @RequestParam Long contId) {
		if (resultEmployee.hasErrors() || resultContacts.hasErrors()) {
			return "employee";
		}
		employee.setRole(roleMap.get(employee.getId()));
		emplServ.saveOrUpdate(employee);
		if (contId != null) {
			contServ.update(contacts, contId);
			contServ.atachEmployee(employee.getId(), contId);
		} else {
			emplServ.addContacts(contacts, employee.getId());
		}
		return "redirect:/employee/employeess";
	}

	@GetMapping("/add-project-employee")
	public String addProjectEmployeePage(ModelMap model, @RequestParam Long id) {
		addModel(model, id);
		return "manageProject";
	}

	@GetMapping("/add-project-employee-many")
	public String addProjectEmployee(ModelMap model, @RequestParam Long projId, @RequestParam Long emplId) {
		Employee employee = emplServ.findById(emplId);
		employee.addProject(projServ.findById(projId));
		emplServ.saveOrUpdate(employee);

		addModel(model, emplId);
		return "manageProject";
	}

	private void addModel(ModelMap model, Long emplId) {
		Employee employee = emplServ.findById(emplId);
		model.put("employee", employee);
		model.put("action", "Add");
		List<Project> projects = projServ.projectsEmployeeNotIn(emplId);
		projects.sort(Comparator.naturalOrder());
		model.put("projects", projects);
	}

	@GetMapping("/add-role-employee")
	public String addRoleEmployeePage(ModelMap model, @RequestParam Long id) {
		addRoleModel(model, id);
		return "manageRoles";
	}

	@GetMapping("/add-role-employee-many")
	public String addRoletEmployee(ModelMap model, @RequestParam int roleId, @RequestParam Long emplId) {
		Employee employee = emplServ.findById(emplId);
		employee.addRole(roleServ.findById(roleId));
		emplServ.saveOrUpdate(employee);

		addRoleModel(model, emplId);
		return "manageRoles";
	}

	private void addRoleModel(ModelMap model, Long emplId) {
		Employee employee = emplServ.findById(emplId);
		model.put("employee", employee);
		model.put("action", "Add");
		List<Role> roles = roleServ.rolesEmployeeNotIn(emplId);
		model.put("roles", roles);
	}

	@GetMapping("/delete-role-employee")
	public String deleteRoleEmployeePage(ModelMap model, @RequestParam Long id) {
		removeRoleModelStuff(model, id);
		return "manageRoles";
	}

	@GetMapping("/delete-role-employee-many")
	public String deleteRoleEmployee(ModelMap model, @RequestParam int roleId, @RequestParam Long emplId) {
		Employee employee = emplServ.findById(emplId);
		employee.removeRole(roleServ.findById(roleId));
		emplServ.saveOrUpdate(employee);

		removeRoleModelStuff(model, emplId);
		return "manageRoles";
	}

	private void removeRoleModelStuff(ModelMap model, Long emplId) {
		Employee employee = emplServ.findById(emplId);
		model.put("employee", employee);
		model.put("action", "Delete");
		Set<Role> roles = employee.getRoles();
		System.out.println("Roles: " + roles);
		model.put("roles", roles);
	}
	
	@GetMapping("/delete-project-employee")
	public String deleteProjectEmployeePage(ModelMap model, @RequestParam Long id) {
		removeModelStuff(model, id);
		return "manageProject";
	}

	@GetMapping("/delete-project-employee-many")
	public String deleteProjectEmployee(ModelMap model, @RequestParam Long projId, @RequestParam Long emplId) {
		Employee employee = emplServ.findById(emplId);
		employee.removeProject(projServ.findById(projId));
		emplServ.saveOrUpdate(employee);

		removeModelStuff(model, emplId);
		return "manageProject";
	}

	private void removeModelStuff(ModelMap model, Long emplId) {
		Employee employee = emplServ.findById(emplId);
		model.put("employee", employee);
		model.put("action", "Delete");
		Set<Project> projects = emplServ.getAssignedProjectById(emplId);
		List<Project> tempProjects = projects.stream().sorted().collect(Collectors.toList());
		model.put("projects", tempProjects);
	}
	
	@GetMapping("/delete-employee")
	public String deleteEmployee(@RequestParam Long id) {
		emplServ.deleteById(id);
		return "redirect:/employee/employeess";
	}
}
