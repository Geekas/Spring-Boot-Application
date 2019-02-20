package lt.ramunas.alksnys.Springbackendportfolioproject.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Company;

@Repository
@Transactional
public class CompanyRepository {

	@Autowired
	EntityManager em;

	public Company findById(Long id) {
		return em.find(Company.class, id);
	}

	public List<Company> getAllCompanies() {
		return em.createQuery("Select c from Company c", Company.class).getResultList();
	}

	public Company saveOrUpdate(Company company) {
		if (company.getId() == null && company != null) {
			em.persist(company);
		} else {
			em.merge(company);
		}
		return company;
	}
	public void delete(Company company){
		if (company.getId() != null && company != null){
			em.remove(company);
		}
	}
	public void deleteById(Long id) {
		Company company = em.find(Company.class, id);
		em.remove(company);
	}
}
