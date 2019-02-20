package lt.ramunas.alksnys.Springbackendportfolioproject.entities;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import static java.lang.Math.toIntExact;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project implements Comparable<Project>{

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	@NotEmpty(message = "*Please provide name")
	private String name;
	
	@Column(nullable=false)
	@NotEmpty(message = "*Please provide description")
	private String description;

	@Column(nullable=false)
	@NotNull(message = "*Please provide budget size")
	private Long budget;

	@ManyToMany(mappedBy = "projects")
	@JsonIgnore
	private Set<Employee> employeess = new LinkedHashSet<>();

	public Project() {
	}

	public Project(String name, String description, Long budget) {
		this.name = name;
		this.description = description;
		this.budget = budget;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getBudget() {
		return budget;
	}

	public void setBudget(Long budget) {
		this.budget = budget;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Employee> getEmployeess() {
		return employeess;
	}

	public void addEmployee(Employee employee) {
		employeess.add(employee);
	}

	public void removeEmployee(Employee employee) {
		employeess.remove(employee);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Project other = (Project) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		final Long prime = 31l;
		Long result = 1l;
		result = prime * result + id;
		return toIntExact(result);
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", description=" + description + ", budget=" + budget + "]";
	}

	@Override
	public int compareTo(Project o) {
		return Comparator.comparing(Project::getName).compare(this, o);
	}

}
