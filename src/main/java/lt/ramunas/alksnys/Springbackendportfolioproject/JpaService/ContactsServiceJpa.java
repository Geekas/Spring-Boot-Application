package lt.ramunas.alksnys.Springbackendportfolioproject.JpaService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Contacts;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Employee;
import lt.ramunas.alksnys.Springbackendportfolioproject.jpaRepository.ContactsRepositoryJpa;
import lt.ramunas.alksnys.Springbackendportfolioproject.jpaRepository.EmployeeRepositoryJpa;

@Service
@Transactional
public class ContactsServiceJpa {

	@Autowired
	ContactsRepositoryJpa contRep;
	
	@Autowired 
	EmployeeRepositoryJpa emplRep;

	public Contacts update(Contacts contacts, Long id) {
		Optional<Contacts> contactToUpd = contRep.findById(id);
		contactToUpd.get().setAllContacts(contacts);

		return contRep.save(contactToUpd.get());
	}

	public Contacts save(Contacts contacts) {
		if (contacts == null) {
			return null;
		}
		return contRep.save(contacts);
	}

	public void deleteById(Long id) {
		Contacts contacts = contRep.findById(id).get();
		if (contacts != null) {
			detachEmployee(contacts);
			contRep.deleteById(id);
		}
	}

	public void delete(Contacts contacts) {
		if (contacts != null) {
			detachEmployee(contacts);
			contRep.delete(contacts);
		}
	}

	private void detachEmployee(Contacts contacts) {
		Employee employee = contacts.getEmployee();
		employee.setContacts(null);
	}

	public Contacts findById(Long id) {
		return contRep.findById(id).get();
	}

	public List<Contacts> getAll() {
		return  contRep.findAll();
	}

	public Employee getEmployeeById(Long id) {
		return contRep.findById(id).get().getEmployee();
	}

	public boolean checkIfPhoneExist(String number) {
		List<Contacts> allContacts = contRep.findAll();
		for (Contacts contact : allContacts) {
			if (contact.getPhone().equals(number)) {
				return true;
			}
		}
		return false;
	}
	
	public void atachEmployee(Long emplId, Long contId ){
		Employee employee = emplRep.findById(emplId).get();
		Contacts contacts = contRep.findById(contId).get();
		employee.setContacts(contacts);
	}
}
