package lt.ramunas.alksnys.Springbackendportfolioproject.entities;

import java.util.Comparator;
import java.util.Date;
import static java.lang.Math.toIntExact;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee implements Comparable<Employee> {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	@NotEmpty(message = "*Please provide your first name")
	private String firstName;

	@Column(nullable = false)
	@NotEmpty(message = "*Please provide your last name")
	private String secondName;

	@Column(nullable = false)
	@NotNull(message = "*Please provide salary size")
	private Long salary;

	@Column(nullable = false)
	@NotNull(message = "*Please provide work start date")
	private Date workStart;	

	@Column(nullable = false, unique=false)
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;

	@Column(name = "active")
	private int active;

	@Column(nullable = false)
	@NotEmpty(message = "*Please provide a position")
	private String position;

	@Column(nullable = false)
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	@JsonIgnore
	private String password;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JsonIgnore
	private Company company;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Contacts contacts;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JsonIgnore
	private Set<Project> projects = new LinkedHashSet<>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinTable(name = "employee_role", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public Employee() {
	}	

	public String getEmail() {
		return email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	public void setRole(Set<Role> roles) {
		this.roles = roles;
	}

	public void removeRole(Role role) {
		this.roles.remove(role);
	}

	public Employee(String firstName, String secondName, Long salary, Date workStart,
			@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email,
			@NotEmpty(message = "*Please provide a position") String position,
			@Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String password,
			int active) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.salary = salary;
		this.workStart = workStart;
		this.email = email;
		this.position = position;
		this.password = password;
		this.active = active;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public Date getWorkStart() {
		return workStart;
	}

	public void setWorkStart(Date workStart) {
		this.workStart = workStart;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Contacts getContacts() {
		return contacts;
	}

	public void setContacts(Contacts contacts) {
		this.contacts = contacts;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void addProject(Project project) {
		projects.add(project);
	}

	public void removeProject(Project project) {
		projects.remove(project);
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", salary=" + salary
				+ ", workStart=" + workStart + ", email=" + email + ", position=" + position + "]";
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
		Employee other = (Employee) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final Long prime = 53l;
		Long result = 3l;
		result = prime * result + id;
		return toIntExact(result);
	}

	@Override
	public int compareTo(Employee o) {
		return Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getSecondName).compare(this, o);
	}

}
