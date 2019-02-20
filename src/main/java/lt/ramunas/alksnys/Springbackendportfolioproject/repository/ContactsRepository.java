package lt.ramunas.alksnys.Springbackendportfolioproject.repository;

import java.util.List;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Contacts;

@Repository
public class ContactsRepository {

	@Autowired
	EntityManager em;

	public Contacts findById(Long id) {
		return em.find(Contacts.class, id);
	}

	public List<Contacts> getAllContacts() {
		return em.createQuery("Select c from Contacts c", Contacts.class).getResultList();
	}

	public Contacts saveOrUpdate(Contacts contacts) {
		if (contacts.getId() == null) {
			em.persist(contacts);
		} else {
			em.merge(contacts);
		}
		return contacts;
	}

	public void delete(Contacts contacts) {
		if (contacts.getId() != null) {
			em.remove(contacts);
		}
	}

	public void deleteById(Long id) {
		Contacts contacts = em.find(Contacts.class, id);
		em.remove(contacts);
	}
}
