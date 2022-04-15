package ma.enset.privatehospital.web;

import lombok.AllArgsConstructor;
import ma.enset.privatehospital.entities.Patient;
import ma.enset.privatehospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@Controller
@AllArgsConstructor
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping(path = "/user/index")
    public String index(Model model,
                        @RequestParam(name="page", defaultValue = "0") int page,
                        @RequestParam(name="size", defaultValue = "5")int size,
                        @RequestParam(name="keyword", defaultValue = "")String keyword){
        Page<Patient> patients= patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients", patients.getContent());
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping(path = "/admin/delete")
    public String delete(Long id, String keyword, int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/user/patients")
    public List<Patient> listPatients(){
        List<Patient> patients=patientRepository.findAll();
        return patients;

    }
    @GetMapping("/admin/formPatient")
    public String formPatient(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatient";
    }

    @PostMapping("/admin/save")
    public String save(@Valid Patient patient, BindingResult bindingResult,
                       @RequestParam (defaultValue = "0") int page,
                       @RequestParam (defaultValue = "") String keyword){

        if(bindingResult.hasErrors()){
            if (patient.getId()==null)  return "formPatient";

        }
        patientRepository.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/edit")
    public String edit(Model model, Long id, int page, String keyword){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient==null) throw new RuntimeException("Patient Introvable");
        model.addAttribute("patient", patient);
        model.addAttribute("page", page);
        model.addAttribute("keyword",keyword);
        return "editPatient";
    }
    @PutMapping(path = "/admin/update")
    public String update(@Valid @ModelAttribute Patient patient, BindingResult bindingResult,
                       @RequestParam (defaultValue = "0") int page,
                       @RequestParam (defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formPatient";
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

}
