package com.example.absence.Controller;

import com.example.absence.Model.Absence;
import com.example.absence.Service.Absence.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/absences")
@CrossOrigin
public class AbsenceController {
    @Autowired
    private AbsenceService absenceService;
    @PostMapping("/add")
    public String add(@RequestBody Absence absence){
        absenceService.saveAbsence(absence);
        return "Nouvelle absence ajouté";
    }
    @GetMapping("/getAll")
    public List<Absence> getAll(){
        return absenceService.getAbsences();
    }
   /* @PutMapping("/{absenceId}/matiere/{matiereId}")
    public void assignMatiereToAbsence(
            @PathVariable int absenceId,
            @PathVariable int matiereId
    ){
        absenceService.assignMatiereToAbsence(absenceId,matiereId);
    }
    @PutMapping("/{absenceId}/etudiant/{etudiantId}")
    public void assignEtudiantToAbsence(
            @PathVariable int absenceId,
            @PathVariable int etudiantId
    ){
        absenceService.assignEtudiantToAbsence(absenceId,etudiantId);
    }*/
    @PatchMapping("/{absenceId}/nbAbsence/add")
    public void addAbsence(
            @PathVariable int absenceId
    ){
        absenceService.addAbsence(absenceId);
    }
    @GetMapping("/etudiant/{etudiantId}/matieres")
    public List<Absence> getAbsencesByEtudiantId(
            @PathVariable int etudiantId
    ){
        return absenceService.getAbsencesByEtudiantId(etudiantId);
    }

}
