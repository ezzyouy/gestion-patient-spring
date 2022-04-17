package ma.enset.privatehospital.security.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.privatehospital.security.entities.AppRole;
import ma.enset.privatehospital.security.entities.AppUser;
import ma.enset.privatehospital.security.repositories.AppRoleRepository;
import ma.enset.privatehospital.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("Passwords not match");
        String hashPWD= passwordEncoder.encode(password);

        AppUser appUser= new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashPWD);
        appUser.setActive(true);
        AppUser savedAppUser=appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole=appRoleRepository.findByRolename(roleName);
        if(appRole!=null) throw new RuntimeException("Role "+roleName+" already exist");
        appRole=new AppRole();
        appRole.setRolename(roleName);
        appRole.setDescription(description);

        AppRole savedAppRole=appRoleRepository.save(appRole);

        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("User not found");
        AppRole appRole=appRoleRepository.findByRolename(roleName);
        if(appRole==null) throw new RuntimeException("Role not found");
        appUser.getAppRoles().add(appRole);
        //appUserRepository.save(appUser);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("User not found");
        AppRole appRole=appRoleRepository.findByRolename(roleName);
        if(appRole==null) throw new RuntimeException("Role not found");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUserName(String username) {

        return appUserRepository.findByUsername(username);
    }
}
