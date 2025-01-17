package springve9.patientmvc.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @NotEmpty
 @Size(min = 4, max = 40)
 private String nachName;

 @Temporal(TemporalType.DATE)
 @DateTimeFormat(pattern = "yyyy-MM-dd")
 private Date geburtsdatum;

 private boolean istkrank ;

 @DecimalMin("100")
 private int ergebnis;


}
