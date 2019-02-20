package lt.ramunas.alksnys.Springbackendportfolioproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Employee;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Project;
import lt.ramunas.alksnys.Springbackendportfolioproject.jpaRepository.EmployeeRepositoryJpa;
import lt.ramunas.alksnys.Springbackendportfolioproject.repository.ProjectRepository;

@Service
@Transactional
public class ProjectsService {

	@Autowired
	ProjectRepository projRep;
	
	@Autowired
	EmployeeRepositoryJpa emplRep;

	public List<Project> getAll() {
		return projRep.getAllProjects();
	}

	public Project findById(Long id) {
		return projRep.findById(id);
	}

	public void deleteByIdProject(Long id) {
		Project project = projRep.findById(id);
		detachEmployeess(project);
		projRep.deleteById(id);
	}

	private void detachEmployeess(Project project) {
		Set<Employee> employeess = project.getEmployeess();
		for (Employee employee : employeess) {
			employee.removeProject(project);
		}
	}

	public void delete(Project project) {
		detachEmployeess(project);
		projRep.delete(project);
	}

	public Project saveOrUpdate(Project project) {
		if (project == null) {
			return null;
		}
		return projRep.saveOrUpdate(project);
	}

	public Set<Employee> getAtachedEmployeessById(Long id) {
		return projRep.findById(id).getEmployeess();
	}
	
	public List<Project> projectsEmployeeNotIn(Long id){
		List<Project> allProjects = projRep.getAllProjects();
		Employee employee = emplRep.findById(id).get();
		Set<Project> emplProjects = employee.getProjects();
		List<Project> distProjects = new ArrayList<Project>(allProjects);
		distProjects.removeAll(emplProjects);
		return distProjects;
	}	
}
