package com.example.absence.Controller;

import com.example.absence.Model.Matiere;
import com.example.absence.Service.Matiere.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matieres")
@CrossOrigin
public class MatiereController {
    @Autowired
    private MatiereService matiereService;
    @PostMapping("/add")
    public String add(@RequestBody Matiere matiere){
        matiereService.saveMatiere(matiere);
        return "Nouvelle matiere ajoutée";
    }
    @GetMapping("/getAll")
    public List<Matiere> getAll(){
        return matiereService.getMatieres();
    }
    /*@PutMapping("/{matiereId}/section/{sectionId}")
    public void assignSectionToMatiere(
            @PathVariable int matiereId,
            @PathVariable int sectionId
    ){
        matiereService.assignSectionToMatiere(matiereId,sectionId);
    }
    @PutMapping("/{matiereId}/niveau/{niveauId}")
    public void assignNiveauToMatiere(
            @PathVariable int matiereId,
            @PathVariable int niveauId
    ){
        matiereService.assignNiveauToMatiere(matiereId,niveauId);
    }*/
    @PatchMapping("/{matiereId}/update/semestre/{sem}")
    public void updateMatiere(
            @PathVariable int matiereId,
            @PathVariable int sem
    ){
        matiereService.updateSemestre(matiereId,sem);
    }
    /*@PutMapping("/{matiereId}/niveau/{niveauId}")
    public void assignNiveauToMatiere(
            @PathVariable int matiereId,
            @PathVariable int niveauId
    ){
        matiereService.assignNiveauToMatiere(matiereId,niveauId);
    }*/
}
