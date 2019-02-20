package lt.ramunas.alksnys.Springbackendportfolioproject.JpaService;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Company;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Contacts;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Employee;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Project;
import lt.ramunas.alksnys.Springbackendportfolioproject.jpaRepository.EmployeeRepositoryJpa;

@Service
@Transactional
public class EmployeeServiceJpa {

	@Autowired
	EmployeeRepositoryJpa emplRep;

	@Autowired
	ContactsServiceJpa contService;

	@Autowired
	ProjectsServiceJpa projService;

	@Autowired
	CompanyServiceJpa compService;	

	public List<Employee> getAllEmployeess() {
		return emplRep.findAll();
	}

	public Employee findById(Long id) {
		return emplRep.findById(id).get();		
	}
	
	public Employee findByEmail(String email) {
		return emplRep.findByEmail(email);
	}

	public Employee findByFirstNameAndSecondName(String name) {
		String[] names = name.split(" ");
		String firstName = names[0];
		String secondName = names[1];
		return emplRep.findByFirstNameAndSecondName(firstName, secondName);
	}

	public Employee saveOrUpdate(Employee newEmployee) {
		if (newEmployee == null) {
			return null;
		}		
		Employee employee = emplRep.save(newEmployee);
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
		return emplRep.findById(id).get().getProjects();
	}

	public boolean addToProject(Long projectId, Long emplId) {
		Project project = projService.findById(projectId);
		Employee employee = emplRep.findById(emplId).get();
		if (project != null && employee != null) {
			employee.addProject(project);			
			return true;
		}

		return false;
	}

	public boolean removeProject(Project project, Long id) {
		Employee employee = emplRep.findById(id).get();
		if (project != null && employee.getProjects().contains(project)) {
			emplRep.findById(id).get().removeProject(project);
			return true;
		}
		return false;
	}

	public boolean removeProject(Long projId, Long id) {
		Employee employee = emplRep.findById(id).get();
		Project project = projService.findById(projId);
		if (project != null && employee.getProjects().contains(project)) {
			emplRep.findById(id).get().removeProject(project);
			return true;
		}
		return false;
	}

	public Employee addContacts(Contacts newContacts, Long id) {
		Employee employee = emplRep.findById(id).get();

		if (newContacts != null) {
			Contacts contacts = contService.save(newContacts);
			setContEmpl(employee, contacts);
			return employee;
		}
		return null;
	}

	private void setContEmpl(Employee employee, Contacts contacts) {
		employee.setContacts(contacts);		
	}

	public void removeContacts(Long id) {
		Employee employee = emplRep.findById(id).get();
		Contacts contacts = employee.getContacts();
		contService.delete(contacts);
	}

	public Employee saveWithContacts(Employee newEmployee, Contacts newContacts) {
		if (newEmployee == null || newContacts == null) {
			return null;
		}
		Company company = compService.getCompany();
		Contacts contacts = contService.save(newContacts);		
		Employee employee = emplRep.save(newEmployee);

		employee.setContacts(newContacts);
		employee.setCompany(company);

		return employee;
	}

	public Set<Employee> freeEmployeess(Long projId) {
		List<Employee> allEmployeess = emplRep.findAll();
		Project project = projService.findById(projId);
		Set<Employee> projectsEmpl = project.getEmployeess();
		Set<Employee> distEmpl = new LinkedHashSet<>(allEmployeess);
		distEmpl.removeAll(projectsEmpl);
		return distEmpl;
	}
}
