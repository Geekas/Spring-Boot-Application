package lt.ramunas.alksnys.Springbackendportfolioproject.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Company;

public interface CompanyRepositoryJpa extends JpaRepository<Company, Long>{
	Company findByName(String name);
}
