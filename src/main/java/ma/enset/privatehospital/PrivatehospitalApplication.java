package ma.enset.privatehospital;

import ma.enset.privatehospital.entities.Patient;
import ma.enset.privatehospital.repositories.PatientRepository;
import ma.enset.privatehospital.security.services.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PrivatehospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrivatehospitalApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    //@Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(
                    new Patient(null,"Hassan",new Date(),false,12));
            patientRepository.save(
                    new Patient(null,"Mohammed",new Date(),true,321));
            patientRepository.save(
                    new Patient(null,"Yasmine",new Date(),true,65));
            patientRepository.save(
                    new Patient(null,"Hanae",new Date(),false,32));
            patientRepository.save(
                    new Patient(null,"Ahmed",new Date(),false,32));
            patientRepository.save(
                    new Patient(null,"Redaa",new Date(),true,55));
            patientRepository.save(
                    new Patient(null,"Amjad",new Date(),true,74));
            patientRepository.save(
                    new Patient(null,"Chahine",new Date(),false,90));
            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };
    }

   // @Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("redouane","1234","1234");
            securityService.saveNewUser("noura","1234","1234");
            securityService.saveNewUser("youssef","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("redouane", "USER");
            securityService.addRoleToUser("redouane", "ADMIN");
            securityService.addRoleToUser("noura", "USER");
            securityService.addRoleToUser("youssef", "USER");
        };
    }
}
