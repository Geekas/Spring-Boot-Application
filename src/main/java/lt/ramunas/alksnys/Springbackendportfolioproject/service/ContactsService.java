package lt.ramunas.alksnys.Springbackendportfolioproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Contacts;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Employee;
import lt.ramunas.alksnys.Springbackendportfolioproject.repository.ContactsRepository;
import lt.ramunas.alksnys.Springbackendportfolioproject.repository.EmployeeRepository;

@Service
@Transactional
public class ContactsService {

	@Autowired
	ContactsRepository contRep;
	
	@Autowired 
	EmployeeRepository emplRep;

	public Contacts findById(Long id) {
		return contRep.findById(id);
	}

	public List<Contacts> getAll() {
		return contRep.getAllContacts();
	}

	public Contacts save(Contacts contacts) {
		if (contacts == null) {
			return null;
		}
		return contRep.saveOrUpdate(contacts);
	}

	public void deleteById(Long id) {
		Contacts contacts = contRep.findById(id);
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

	public Employee getAtachedEmployeeById(Long id) {
		return contRep.findById(id).getEmployee();
	}

	public Contacts update(Contacts contacts, Long id) {		
		Contacts contactToUpd = contRep.findById(id);
		contactToUpd.setAllContacts(contacts);

		return contRep.saveOrUpdate(contactToUpd);		
	}

	public boolean checkIfPhoneExist(String number) {
		List<Contacts> allContacts = contRep.getAllContacts();
		for (Contacts contact : allContacts) {
			if (contact.getPhone().equals(number)) {
				return true;
			}
		}
		return false;
	}
	
	public void atachEmployee(Long emplId, Long contId ){
		Employee employee = emplRep.findById(emplId);
		Contacts contacts = contRep.findById(contId);
		employee.setContacts(contacts);
	}
}
