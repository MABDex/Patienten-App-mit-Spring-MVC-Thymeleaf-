package springve9.patientmvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springve9.patientmvc.Repository.PatientRepository;
import springve9.patientmvc.entities.Patient;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }

    //@Bean

    //@Bean : Utilisez cette annotation une seule fois pour insérer les données dans la base de données.
    // Ensuite, commentez l annotation pour éviter de réinsérer ces données à chaque démarrage de l'application.

    CommandLineRunner commandLineRunner(PatientRepository patientRep) {

        return args -> {
        patientRep.save(new Patient(null , "Hassan" , new Date() , false , 120));
        patientRep.save(new Patient(null , "JanaM" , new Date() , true , 320));
        patientRep.save(new Patient(null , "MedMab" , new Date() , false , 1000));
        patientRep.save(new Patient(null , "AlexM" , new Date() , false , 500));

        patientRep.findAll().forEach(p->{
            System.out.println(p.getId());
            System.out.println(p.getNachName());
            System.out.println(p.getGeburtsdatum());

        });



        };
    }

}
