package lt.ramunas.alksnys.Springbackendportfolioproject.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Project;

public interface ProjectRepositoryJpa extends JpaRepository<Project, Long>{
	Project findByName(String name);
}
