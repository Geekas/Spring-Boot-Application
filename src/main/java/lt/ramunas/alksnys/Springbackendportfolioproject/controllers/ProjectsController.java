package lt.ramunas.alksnys.Springbackendportfolioproject.controllers;


import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lt.ramunas.alksnys.Springbackendportfolioproject.JpaService.EmployeeServiceJpa;
import lt.ramunas.alksnys.Springbackendportfolioproject.JpaService.ProjectsServiceJpa;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Employee;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectsController {

	@Autowired
	private EmployeeServiceJpa emplServ;

	@Autowired
	private ProjectsServiceJpa projServ;

	@GetMapping("/projects")
	public String getProjects(ModelMap model) {
		List<Project> allProjects = projServ.getAll();
		allProjects.sort(Comparator.naturalOrder());
		model.put("projects", allProjects);
		return "projects-list";
	}

	@GetMapping("/new-project")
	public String newProjectPage(ModelMap model) {
		model.put("project", new Project("name", "description", 1000l));
		model.put("action", "Add");
		return "project";
	}

	@PostMapping("/new-project")
	public String addNewProject(ModelMap model, @Valid Project project, BindingResult result) {
		if (result.hasErrors()) {
			return "project";
		}
		projServ.saveOrUpdate(project);
		return "redirect:/projects/projects";
	}

	@GetMapping("/project-update")
	public String updateProjectPage(ModelMap model, @RequestParam Long id) {
		model.put("project", projServ.findById(id));
		model.put("action", "Update ");
		return "project";
	}

	@PostMapping("/project-update")
	public String updateProject(ModelMap model, @Valid Project project, BindingResult result) {
		projServ.saveOrUpdate(project);
		return "redirect:/projects/projects";
	}

	@GetMapping("/project-add-employee")
	public String addEmployeeToProjectPage(ModelMap model, @RequestParam Long id) {
		printAndAddToModelAddPage(model, id);
		return "manageEmployee";
	}

	@GetMapping("/project-add-employee-many")
	public String addEmployeeToProject(ModelMap model, @RequestParam Long projId, @RequestParam Long emplId) {
		Employee employee = emplServ.findById(emplId);
		employee.addProject(projServ.findById(projId));
		emplServ.saveOrUpdate(employee);

		printAndAddToModelAddPage(model, projId);
		return "manageEmployee";
	}

	private void printAndAddToModelAddPage(ModelMap model, Long id) {
		model.put("project", projServ.findById(id));
		Set<Employee> freeEmployeess = emplServ.freeEmployeess(id);
		List<Employee> tempEmployeess = freeEmployeess.stream().sorted().collect(Collectors.toList());
		model.put("employeess", tempEmployeess);
		model.put("action", "Add");
	}
	
	@GetMapping("/project-remove-employee")
	public String removeEmployeeFromProjectPage(ModelMap model, @RequestParam Long id) {
		printRemoveListAddModetDelStuff(model, id);
		return "manageEmployee";
	}
	
	@GetMapping("/project-remove-employee-many")
	public String removeEmployeeFromProject(ModelMap model, @RequestParam Long projId, @RequestParam Long emplId) {
		Employee employee = emplServ.findById(emplId);
		employee.removeProject(projServ.findById(projId));
		emplServ.saveOrUpdate(employee);
		
		printRemoveListAddModetDelStuff(model, projId);
		return "manageEmployee";
	}
	
	private void printRemoveListAddModetDelStuff(ModelMap model, Long id) {
		model.put("project", projServ.findById(id));
		Set<Employee> employeess = projServ.findById(id).getEmployeess();
		List<Employee> tempEmployeess = employeess.stream().sorted().collect(Collectors.toList());
		model.put("employeess", tempEmployeess);
		model.put("action", "Remove");
	}

	@GetMapping("/project-delete")
	public String deleteEmployee(@RequestParam Long id) {
		projServ.deleteByIdProject(id);
		return "redirect:/projects/projects";
	}

}
