package lt.ramunas.alksnys.Springbackendportfolioproject.JpaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Company;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Employee;
import lt.ramunas.alksnys.Springbackendportfolioproject.jpaRepository.CompanyRepositoryJpa;

@Service
@Transactional
public class CompanyServiceJpa {

	@Autowired
	CompanyRepositoryJpa compRep;

	public String getName() {
		List<Company> allCompanies = compRep.findAll();
		if (allCompanies.size() > 0) {
			String name = allCompanies.get(0).getName();
			return name;
		}
		return null;
	}

	public int workerSize() {
		List<Company> allCompanies = compRep.findAll();
		if (allCompanies.size() > 0) {
			Company company = allCompanies.get(0);
			return company.getEmployee().size();
		}
		return -1;
	}

	public Company getCompany() {
		List<Company> allCompanies = compRep.findAll();
		Company company = allCompanies.get(0);
		if (company != null) {
			return company;
		}
		return null;
	}

	public Company addOrUpdate(Company company) {
		if (company != null) {
			return compRep.save(company);
		}
		return null;
	}

	public void deleteById(Long id) {
		if (compRep.existsById(id)) {
			Company company = compRep.findById(id).get();

			detachEmployee(company);
			compRep.deleteById(id);
		}

	}

	public void deleteCompany(Company company) {
		if (company != null && company.getId() > 0) {
			detachEmployee(company);
			compRep.delete(company);
		}
	}

	private void detachEmployee(Company company) {
		for (Employee employee : company.getEmployee()) {
			employee.setCompany(null);
		}
	}

	public boolean isRealEmployee(Employee employee) {
		List<Company> allCompanies = compRep.findAll();
		if (employee != null && allCompanies.size() > 0) {
			Company company = allCompanies.get(0);
			return company.getEmployee().contains(employee);
		}
		return false;
	}
}
