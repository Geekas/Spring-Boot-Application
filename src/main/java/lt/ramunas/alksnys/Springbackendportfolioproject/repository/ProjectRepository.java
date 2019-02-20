package lt.ramunas.alksnys.Springbackendportfolioproject.repository;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Project;

@Repository
public class ProjectRepository {

	@Autowired
	EntityManager em;

	public Project findById(Long id) {
		return em.find(Project.class, id);
	}

	public List<Project> getAllProjects() {
		return em.createQuery("Select p from Project p", Project.class).getResultList();
	}

	public Project saveOrUpdate(Project project) {
		if (project.getId() == null) {
			em.persist(project);
		} else {
			em.merge(project);
		}
		return project;
	}
	
	public void delete(Project project){
		if (project.getId() != null){
			em.remove(project);
		}
	}

	public void deleteById(Long id) {
		Project project = em.find(Project.class, id);
		em.remove(project);
	}
}
