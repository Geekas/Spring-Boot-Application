package lt.ramunas.alksnys.Springbackendportfolioproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Contacts {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	@NotEmpty(message = "*Please provide street")
	private String streetAddress;
	
	@Column(nullable=false)
	@NotEmpty(message = "*Please provide city")
	private String town;
	
	@Column(nullable=false)
	@NotEmpty(message = "*Please provide phone")
	private String phone;
	
	@OneToOne(mappedBy="contacts")
	private Employee employee;
	
	public Contacts() {}

	public Contacts(String streetAddress, String town, String phone) {		
		this.streetAddress = streetAddress;
		this.town = town;
		this.phone = phone;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setAllContacts(Contacts contacts){
		this.phone = contacts.getPhone();
		this.streetAddress = contacts.getStreetAddress();
		this.town = contacts.getTown();
	}

	@Override
	public String toString() {
		return "Contacts [id=" + id + ", streetAddress=" + streetAddress + ", town=" + town + ", phone=" + phone + "]";
	}	
	
}
