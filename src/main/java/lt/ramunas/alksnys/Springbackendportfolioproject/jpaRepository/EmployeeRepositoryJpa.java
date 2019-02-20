package lt.ramunas.alksnys.Springbackendportfolioproject.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Contacts;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Employee;
public interface EmployeeRepositoryJpa extends JpaRepository<Employee, Long>{
	Employee findByFirstNameAndSecondName(String firstName, String secondName);
	Employee findByEmail(String email);
	public void deleteByContacts(Contacts contacts);
}
