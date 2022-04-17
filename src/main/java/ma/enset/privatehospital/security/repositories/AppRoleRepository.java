package ma.enset.privatehospital.security.repositories;

import ma.enset.privatehospital.security.entities.AppRole;
import ma.enset.privatehospital.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long>{

    AppRole findByRolename(String rolename);
}
