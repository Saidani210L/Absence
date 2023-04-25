package com.example.absence.Controller;

import com.example.absence.Model.Seance;
import com.example.absence.Service.Seance.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seances")
@CrossOrigin
public class SeanceController {
    @Autowired
    private SeanceService seanceService;
    @PostMapping("/add")
    public Seance saveSeance(
            @RequestBody Seance seance
    ){
        return seanceService.saveSeance(seance);
    }
    @GetMapping("/getAll")
    public List<Seance> getSeances(){
        return seanceService.getSeances();
    }
    @GetMapping("/{seanceId}")
    public Seance getSeanceById(
            @PathVariable int seanceId
    ){
        return seanceService.getSeanceById(seanceId);
    }
    @PatchMapping("/{seanceId}/classe/{classeId}")
    public void assignClasseToSeance(
            @PathVariable int seanceId,
            @PathVariable int classeId
    ){
        seanceService.assignClasseToSeance(seanceId,classeId);
    }
    @PatchMapping("/{seanceId}/matiere/{matiereId}")
    public void assignMatiereToSeance(
            @PathVariable int seanceId,
            @PathVariable int matiereId
    ){
        seanceService.assignMatiereToSeance(seanceId,matiereId);
    }
    @PatchMapping("/{seanceId}/enseignant/{enseignantId}")
    public void assignEnseignantToSeance(
            @PathVariable int seanceId,
            @PathVariable int enseignantId
    ){
        seanceService.assignEnseignantToSeance(seanceId,enseignantId);
    }
}
