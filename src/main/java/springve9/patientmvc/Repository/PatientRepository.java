package springve9.patientmvc.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springve9.patientmvc.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

      Page<Patient> findByNachNameContains(String kw , Pageable pageable);

}
