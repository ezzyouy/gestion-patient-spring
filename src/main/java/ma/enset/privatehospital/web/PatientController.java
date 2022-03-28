package ma.enset.privatehospital.web;

import lombok.AllArgsConstructor;
import ma.enset.privatehospital.entities.Patient;
import ma.enset.privatehospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
@Controller
@AllArgsConstructor
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping(path = "/index")
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

    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        List<Patient> patients=patientRepository.findAll();
        return patients;

    }
}
