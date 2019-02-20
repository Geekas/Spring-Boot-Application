package lt.ramunas.alksnys.Springbackendportfolioproject.JpaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Employee;
import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Role;
import lt.ramunas.alksnys.Springbackendportfolioproject.jpaRepository.RoleRepository;

@Service
@Transactional
public class RoleServiceJpa {

	@Autowired
	RoleRepository roleRep;
	
	@Autowired
	EmployeeServiceJpa emplServ;

	public Role findById(int id) {			
		return roleRep.findById(id).get();
	}

	public List<Role> rolesEmployeeNotIn(Long emplId) {
		List<Role> allRoles = roleRep.findAll();
		Employee employee = emplServ.findById(emplId);
		Set<Role> emplRoles = employee.getRoles();
		List<Role> distRoles = new ArrayList<Role>(allRoles);
		distRoles.removeAll(emplRoles);
		return distRoles;
	}

}
