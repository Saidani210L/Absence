package com.example.absence.Controller;

import com.example.absence.Model.Matiere;
import com.example.absence.Model.Niveau;
import com.example.absence.Model.Section;
import com.example.absence.Service.Matiere.MatiereService;
import com.example.absence.Service.Niveau.NiveauService;
import com.example.absence.Service.Section.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sections")
@CrossOrigin
public class SectionController {
    @Autowired
    private SectionService sectionService;
    @Autowired
    private NiveauService niveauService;
    @Autowired
    private MatiereService matiereService;
    @PostMapping("/add")
    public String add(@RequestBody Section section){
        sectionService.saveSection(section);
        return "Nouvelle section ajoutée";
    }
    @GetMapping("/getAll")
    public List<Section> getAll(){
        return sectionService.getSections();
    }
    @PatchMapping("/{sectionId}/update")
    public void updateSection(
            @PathVariable int sectionId,
            @RequestBody Section section
    ){
        sectionService.updateSection(sectionId, section);
    }
    @PutMapping("/{sectionId}/niveaux/add")
    public String addNiveau(
            @PathVariable int sectionId,
            @RequestBody Niveau niveau
            ){
        return sectionService.addNiveau(sectionId, niveau);
    }
    @PatchMapping("/{sectionId}/niveau/{niveauId}/matieres/add")
    public String addMatiere(
            @PathVariable int sectionId,
            @PathVariable int niveauId,
            @RequestBody Matiere matiere
            ){
        return sectionService.addMatiere(sectionId,niveauId,matiere);
    }


    /*@PatchMapping("/{sectionId}/matiere/{matiereId}/update")
    public void updateMatiere(
            @RequestBody Matiere matiere,
            @PathVariable int sectionId,
            @PathVariable int matiereId
    ){
        sectionService.updateMatiere(sectionId,matiereId,matiere);
    }
    @PatchMapping("/{sectionId}/matiere/add")
    public void ajouterMatiere(
            @RequestBody Matiere matiere,
            @PathVariable int sectionId
    ){
        matiereService.saveMatiere(matiere);
        sectionService.ajouterMatiere(sectionId, matiere);
    }*/
}
