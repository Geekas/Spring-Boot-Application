package lt.ramunas.alksnys.Springbackendportfolioproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Company;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Employee;
import lt.ramunas.alksnys.Springbackendportfolioproject.repository.CompanyRepository;

@Service
@Transactional
public class CompanyService {

	@Autowired
	CompanyRepository compRep;

	public String getName() {
		List<Company> allCompanies = compRep.getAllCompanies();
		String name = allCompanies.get(0).getName();
		return name;
	}

	public int workerSize() {
		List<Company> allCompanies = compRep.getAllCompanies();
		Company company = allCompanies.get(0);
		return company.getEmployee().size();
	}

	public Company getCompany() {
		List<Company> allCompanies = compRep.getAllCompanies();
		Company company = allCompanies.get(0);
		return company;
	}

	public Company addOrUpdate(Company company) {
		return compRep.saveOrUpdate(company);
	}

	public void deleteById(Long id) {
		Company company = compRep.findById(id);
		if (company != null) {
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
		List<Company> allCompanies = compRep.getAllCompanies();
		Company company = allCompanies.get(0);
		return company.getEmployee().contains(employee);
	}
}
