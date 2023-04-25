package com.example.absence.Controller;

import com.example.absence.Functions.Functions;
import com.example.absence.Model.Etudiant;
import com.example.absence.Service.Etudiant.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
@CrossOrigin
public class EtudiantController {
    @Autowired
    private EtudiantService etudiantService;
    @PostMapping("/add/setClasse/{classeId}")
    public String add(@RequestBody Etudiant etudiant, @PathVariable int classeId){
        etudiantService.saveEtudiant(etudiant, classeId);
        return "Nouveau Etudiant ajouté";
    }
    @GetMapping("/getAll")
    public List<Etudiant> getAll(){
        return etudiantService.getEtudiants();
    }
    /*@PutMapping("/{etudiantId}/classe/{classeId}")
    public void assignClasseToEtudiant(
            @PathVariable int etudiantId,
            @PathVariable int classeId
    ){
        etudiantService.assignClasseToEtudiant(etudiantId,classeId);
    }*/
    @PostMapping("/{etudiantId}/logout")
    public String logout(
            @PathVariable int etudiantId
    ){
        etudiantService.logout(etudiantId);
        return "Disconnected";
    }
    @DeleteMapping("/{etudiantId}/delete")
    public void deleteEtudiant(
            @PathVariable int etudiantId
    ){
        etudiantService.deleteEtudiant(etudiantId);
    }
    @PatchMapping("/{etudiantId}/update")
    public void updateEtudiant(
            @PathVariable int etudiantId,
            @RequestBody Etudiant etudiant
    ){
        etudiantService.updateEtudiant(etudiantId, etudiant);
    }
}
