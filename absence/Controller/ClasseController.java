package com.example.absence.Controller;

import com.example.absence.Model.Classe;
import com.example.absence.Model.Etudiant;
import com.example.absence.Service.Classe.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/classes")
@CrossOrigin
public class ClasseController {
    @Autowired
    private ClasseService classeService;
    @PostMapping("/add")
    public Classe add(@RequestBody Classe classe){
        return classeService.saveClasse(classe);
    }
    @GetMapping("/getAll")
    public List<Classe> getAll(){
        return classeService.getClasses();
    }
    @GetMapping("/{classeId}/etudiants")
    public Set<Etudiant> getEtudiants(
            @PathVariable int classeId
    ){
        return classeService.getEtudiants(classeId);
    }
    @DeleteMapping("/{classeId}/delete")
    public void deleteClasse(
            @PathVariable int classeId
    ){
        classeService.deleteClasse(classeId);
    }
    /*@PutMapping("/{classeId}/section/{sectionId}")
    public void assignSectionToClasse(
            @PathVariable int classeId,
            @PathVariable int sectionId
    ){
        classeService.assignSectionToClasse(classeId,sectionId);
    }
    @PutMapping("/{classeId}/niveau/{niveauId}")
    public void assignNiveauToClasse(
            @PathVariable int classeId,
            @PathVariable int niveauId
    ){
        classeService.assignNiveauToClasse(classeId,niveauId);
    }*/
}
