package ma.enset.privatehospital.security.services;

import ma.enset.privatehospital.security.entities.AppRole;
import ma.enset.privatehospital.security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username, String password, String rePassword);
    AppRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUserName(String username);


}
