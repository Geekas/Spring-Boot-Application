package lt.ramunas.alksnys.Springbackendportfolioproject.JpaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Employee;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Project;
import lt.ramunas.alksnys.Springbackendportfolioproject.jpaRepository.EmployeeRepositoryJpa;
import lt.ramunas.alksnys.Springbackendportfolioproject.jpaRepository.ProjectRepositoryJpa;

@Service
@Transactional
public class ProjectsServiceJpa {

	@Autowired
	ProjectRepositoryJpa projRep;
	
	@Autowired
	EmployeeRepositoryJpa emplRep;

	public List<Project> getAll() {
		return projRep.findAll();
	}

	public Project findById(Long id) {
		return projRep.findById(id).get();
	}
	
	public Project findIdByName(String name){
		return projRep.findByName(name);
	}

	public void deleteByIdProject(Long id) {
		Project project = projRep.findById(id).get();
		detachEmployeess(project);
		projRep.deleteById(id);
	}

	public void delete(Project project) {
		detachEmployeess(project);
		projRep.delete(project);
	}

	private void detachEmployeess(Project project) {
		Set<Employee> employeess = project.getEmployeess();
		for (Employee employee : employeess) {
			employee.removeProject(project);
		}
	}

	public Project saveOrUpdate(Project project) {
		if (project == null) {
			return null;
		}
		return projRep.save(project);
	}

	public Set<Employee> getAtachedEmployeessById(Long id) {
		return projRep.findById(id).get().getEmployeess();
	}
	
	public List<Project> projectsEmployeeNotIn(Long id){
		List<Project> allProjects = projRep.findAll();
		Employee employee = emplRep.findById(id).get();
		Set<Project> emplProjects = employee.getProjects();
		List<Project> distProjects = new ArrayList<Project>(allProjects);
		distProjects.removeAll(emplProjects);
		return distProjects;
	}		
}
