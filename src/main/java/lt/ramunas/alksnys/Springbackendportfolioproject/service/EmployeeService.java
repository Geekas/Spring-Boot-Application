package lt.ramunas.alksnys.Springbackendportfolioproject.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Company;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Contacts;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Employee;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Project;
import lt.ramunas.alksnys.Springbackendportfolioproject.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	EmployeeRepository emplRep;

	@Autowired
	ContactsService contService;

	@Autowired
	ProjectsService projService;

	@Autowired
	CompanyService compService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<Employee> getAllEmployeess() {
		return emplRep.getAllEmployeess();
	}

	public Employee findById(Long id) {
		return emplRep.findById(id);
	}

	public Employee saveOrUpdate(Employee newEmployee) {
		if (newEmployee == null) {
			return null;
		}
		newEmployee.setPassword(bCryptPasswordEncoder.encode(newEmployee.getPassword()));
		Employee employee = emplRep.saveOrUpdate(newEmployee);
		if (employee.getCompany() == null) {
			employee.setCompany(compService.getCompany());
		}

		return employee;
	}

	public void deleteById(Long id) {
		emplRep.deleteById(id);
	}

	public void delete(Employee employee) {
		emplRep.delete(employee);
	}

	public Set<Project> getAssignedProjectById(Long id) {
		return emplRep.findById(id).getProjects();
	}

	public boolean addToProject(Long projectId, Long emplId) {
		Project project = projService.findById(projectId);
		Employee employee = emplRep.findById(emplId);
		if (project != null && employee != null) {			
				employee.addProject(project);			
				return true;			
		}
		return false;
	}

	public boolean removeProject(Project project, Long id) {
		Employee employee = emplRep.findById(id);
		if (project != null && employee.getProjects().contains(project)) {
			emplRep.findById(id).removeProject(project);
			return true;
		}
		return false;
	}

	public boolean removeProject(Long projId, Long id) {
		Project project = projService.findById(projId);
		Employee employee = emplRep.findById(id);
		if (project != null && employee.getProjects().contains(project)) {
			emplRep.findById(id).removeProject(project);
			return true;
		}
		return false;
	}

	public Employee addContacts(Contacts newContacts, Long id) {
		Employee employee = emplRep.findById(id);

		if (newContacts != null) {
			Contacts contacts = contService.save(newContacts);
			setContEmpl(employee, contacts);
			return employee;
		}
		return null;
	}

	private void setContEmpl(Employee employee, Contacts contacts) {
		employee.setContacts(contacts);
		contacts.setEmployee(employee);
	}

	public void removeContacts(Long id) {
		Employee employee = emplRep.findById(id);
		Contacts contacts = employee.getContacts();
		contService.delete(contacts);
	}

	public Employee saveWithContacts(Employee newEmployee, Contacts newContacts) {
		if (newEmployee == null || newContacts == null) {
			return null;
		}
		Company company = compService.getCompany();
		Contacts contacts = contService.save(newContacts);
		Employee employee = emplRep.saveOrUpdate(newEmployee);

		employee.setContacts(newContacts);	
		employee.setCompany(company);		

		return employee;
	}
	
	public Set<Employee> freeEmployeess(Long projId) {
		List<Employee> allEmployeess = emplRep.getAllEmployeess();
		Project project = projService.findById(projId);
		Set<Employee> projectsEmpl = project.getEmployeess();
		Set<Employee> distEmpl = new LinkedHashSet<>(allEmployeess);
		distEmpl.removeAll(projectsEmpl);
		return distEmpl;
	}
}
