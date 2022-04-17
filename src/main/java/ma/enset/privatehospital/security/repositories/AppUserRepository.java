package ma.enset.privatehospital.security.repositories;

import ma.enset.privatehospital.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String>{

    AppUser findByUsername(String username);
}
