package springve9.patientmvc.Web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springve9.patientmvc.Repository.PatientRepository;
import springve9.patientmvc.entities.Patient;

import java.util.List;


@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientReposi;

    @GetMapping("/")
    public String home(){
        return "redirect:/index"; // redirection vers home par defaut
    }

    @GetMapping("/patients")
    @ResponseBody // serialisation de cette resultat dans le corp
    public List<Patient> getAllPatients(){
        return patientReposi.findAll();
    }

    @GetMapping(path = "/index")
    public String patienten
            (Model model1 ,
             @RequestParam(name = "seite" , defaultValue = "0") int seite,
             @RequestParam(name = "size" , defaultValue = "5") int size,
             @RequestParam(name = "keyword" , defaultValue = "") String keyword){

       // cette Methode permet de returner une vue s appel [vue-patienten], Qund on tape http:localhoset/index
       // Cette vue Base sur le moteur de template Thymeleaf
       // Parceque on utilise le rendu Cote Serveur , on est besoin de declarer un Model de Spring Mvc [Model model1]

        Page<Patient> pagePatienten = patientReposi.findByNachNameContains(keyword ,PageRequest.of(seite,size));
         // La Pagination des Patients on remplace List avec Page
         // keyword pour recherche

        model1.addAttribute("ListVonPatienten" , pagePatienten.getContent());
        // Stocker La liste dans Le Model on Utilisant addAttribute

        model1.addAttribute("Seiten", new int[pagePatienten.getTotalPages()]);
        // ajouter <ul>... </ul> dans html file

        model1.addAttribute("currentPage", seite);
        // Stocker la page current [letzte seite]

        model1.addAttribute("keyword", keyword);


        return "vue-patienten";
    }

    @GetMapping("/löschen")
    // normalment on utilise autre chose que GetMapping
    public String löschen(Long id , String keyword , int page){
        patientReposi.deleteById(id);

        return "redirect:/index?page="+page+"&keyword"+keyword; // redirection vers index
    }


    @GetMapping("/formPatients")
    public String formPatients(Model model2){
        model2.addAttribute("patientobjT", new Patient());
        return "vue-FormPatienten";
    }


    @PostMapping(path = "/save")
    public String save( @Valid @ModelAttribute("patientobjT") Patient patient , BindingResult bindingResult,
     @RequestParam(defaultValue = "0") int page ,
     @RequestParam(defaultValue = "")String keyword){


        if(bindingResult.hasErrors()){
            return "redirect:/index";
        }
      patientReposi.save(patient);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;

    }

    // Edit Methode
    @GetMapping(path = "/editPatient")
    public String editPatient(Model model3 ,Long id , String keyword , int page){

        Patient patientedit =patientReposi.findById(id).orElse(null);
        // patientedit hatakhd data dyal hadak patient li haydir lih update
        if(patientedit==null){
            throw new RuntimeException("Patient not found");
        }
        model3.addAttribute("patientobjEdit", patientedit);
        model3.addAttribute("keyword", keyword);
        model3.addAttribute("page", page);

        return "Vue-EditPatient";
    }




}
