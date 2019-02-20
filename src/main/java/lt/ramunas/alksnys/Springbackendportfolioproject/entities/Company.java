package lt.ramunas.alksnys.Springbackendportfolioproject.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@OneToMany(mappedBy = "company")
	private Set<Employee> employeess = new HashSet<>();

	public Company() {
	}

	public Company(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public Set<Employee> getEmployee() {
		return employeess;
	}

	public void addEmployee(Employee employee) {
		employeess.add(employee);
	}

	public void removeEmployee(Employee employee) {
		employeess.remove(employee);
	}

	@Override
	public String toString() {
		return "Company [name=" + name + "]";
	}
}
