package lt.ramunas.alksnys.Springbackendportfolioproject.repository;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Employee;

@Repository
public class EmployeeRepository {

	@Autowired
	EntityManager em;

	public Employee findById(Long id) {
		return em.find(Employee.class, id);
	}

	public List<Employee> getAllEmployeess() {
		return em.createQuery("Select e from Employee e", Employee.class).getResultList();
	}

	public Employee saveOrUpdate(Employee employee) {
		if (employee.getId() == null) {
			em.persist(employee);
		} else {
			em.merge(employee);
		}
		return employee;
	}

	public void deleteById(Long id) {
		Employee employee = em.find(Employee.class, id);
		em.remove(employee);
	}
	
	public void delete(Employee employee){
		em.remove(employee);
	}
}
