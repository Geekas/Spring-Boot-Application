package lt.ramunas.alksnys.Springbackendportfolioproject.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ramunas.alksnys.Springbackendportfolioproject.entities.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}