package ma.enset.privatehospital;

import ma.enset.privatehospital.entities.Patient;
import ma.enset.privatehospital.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PrivatehospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrivatehospitalApplication.class, args);
    }
    @Bean
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
                    new Patient(null,"Reda",new Date(),true,55));
            patientRepository.save(
                    new Patient(null,"Amjad",new Date(),true,74));
            patientRepository.save(
                    new Patient(null,"Chahine",new Date(),false,90));
            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };
    }
}
