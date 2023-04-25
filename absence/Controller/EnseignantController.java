package com.example.absence.Controller;

import com.example.absence.Model.Enseignant;
import com.example.absence.Model.Etudiant;
import com.example.absence.Model.Matiere;
import com.example.absence.Model.Seance;
import com.example.absence.Service.Enseignant.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/enseignants")
@CrossOrigin
public class EnseignantController {
    @Autowired
    private EnseignantService enseignantService;
    @PostMapping("/add")
    public Enseignant addEnseignant(@RequestBody Enseignant enseignant){
        return enseignantService.saveEnseignant(enseignant);
    }
    @GetMapping("/getAll")
    public List<Enseignant> getEnseignants(){
        return enseignantService.getEnseignants();
    }
    @PatchMapping("/{enseignantId}/classe/{classeId}")
    public String assignClasseToEnseignant(
            @PathVariable int enseignantId,
            @PathVariable int classeId
    ){
        return enseignantService.assignClasseToEnseignant(enseignantId,classeId);
    }
    @PatchMapping("/{enseignantId}/matiere/{matiereId}")
    public String addMatiere(
            @PathVariable int enseignantId,
            @PathVariable int matiereId
    ){
        return enseignantService.addMatiere(enseignantId,matiereId);
    }
    @GetMapping("/{enseignantId}/etudiants")
    public Set<Etudiant> getEtudiants(
            @PathVariable int enseignantId
    ){
        return enseignantService.getEtudiants(enseignantId);
    }
    @GetMapping("/{enseignantId}/classe/{classeId}/etudiants")
    public Set<Etudiant> getEtudiantsParClasse(
            @PathVariable int enseignantId,
            @PathVariable int classeId
    ){
        return enseignantService.getEtudiantsParClasse(enseignantId,classeId);
    }
    @GetMapping("/{enseignantId}/matieres")
    public Set<Matiere> getMatieres(
            @PathVariable int enseignantId
    ){
        return enseignantService.getMatieres(enseignantId);
    }
    @PostMapping("/faireAbsence/matiere/{matiereId}")
    public void faireAbsences(
            @RequestBody List<Integer> etudiantIds,
            @PathVariable int matiereId
    ){
        enseignantService.faireAbsences(etudiantIds,matiereId);
    }
    @GetMapping("/{enseignantId}/seances")
    public Set<Seance> getSeances(
            @PathVariable int enseignantId
    ){
        return enseignantService.getSeances(enseignantId);
    }
}
