package com.example.absence.Controller;

import com.example.absence.Model.Niveau;
import com.example.absence.Model.Section;
import com.example.absence.Service.Niveau.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/niveaux")
@CrossOrigin
public class NiveauController {
    @Autowired
    private NiveauService niveauService;
    @PostMapping("/add")
    public String add(@RequestBody Niveau niveau){
        niveauService.saveNiveau(niveau);
        return "Nouveau niveau ajouté";
    }
    @GetMapping("/getAll")
    public List<Niveau> getAll(){
        return niveauService.getNiveaux();
    }
    @PatchMapping("/{niveauId}/update")
    public void updateNiveau(
            @PathVariable int niveauId,
            @RequestBody Niveau niveau
    ){
        niveauService.updateNiveau(niveauId, niveau);
    }
}
