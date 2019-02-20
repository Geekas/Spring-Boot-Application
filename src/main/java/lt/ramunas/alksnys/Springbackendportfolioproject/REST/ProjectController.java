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

import lt.ramunas.alksnys.Springbackendportfolioproject.JpaService.ProjectsServiceJpa;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Project;

@RestController
@RequestMapping("/projects/rest")
public class ProjectController {

	@Autowired
	ProjectsServiceJpa projServ;
	
	@GetMapping("/projects")
	List<Project> all(){
		return projServ.getAll();
	}
	
	@GetMapping("/project/{id}")
	Project one(@PathVariable Long id) {
		Project project = projServ.findById(id);
		if(project == null){
			return null;
		}

		return project;
	}
	
	@PutMapping("/project")
	void replaceEmployee(@RequestBody Project project) {		
		projServ.saveOrUpdate(project);		
	}
	
	@DeleteMapping("/project/{id}")
	void deleteEmployee(@PathVariable Long id) {
		projServ.deleteByIdProject(id);
	}
	
}
