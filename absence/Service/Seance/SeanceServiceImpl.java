package com.example.absence.Service.Seance;

import com.example.absence.Model.Classe;
import com.example.absence.Model.Enseignant;
import com.example.absence.Model.Matiere;
import com.example.absence.Model.Seance;
import com.example.absence.Repository.ClasseRepository;
import com.example.absence.Repository.EnseignantRepository;
import com.example.absence.Repository.MatiereRepository;
import com.example.absence.Repository.SeanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeanceServiceImpl implements SeanceService{
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private MatiereRepository matiereRepository;
    @Override
    public Seance saveSeance(Seance seance){
        return seanceRepository.save(seance);
    }
    @Override
    public List<Seance> getSeances(){
        return seanceRepository.findAll();
    }
    @Override
    public void assignClasseToSeance(int seanceId, int classeId){
        Seance seance = seanceRepository.findById(seanceId).get();
        Classe classe = classeRepository.findById(classeId).get();
        seance.setClasse(classe);
        seanceRepository.save(seance);
    }
    @Override
    public Seance getSeanceById(int seanceId){
        return seanceRepository.findById(seanceId).orElse(null);
    }
    @Override
    public void assignMatiereToSeance(int seanceId, int matiereId){
        Seance seance = seanceRepository.findById(seanceId).get();
        Matiere matiere = matiereRepository.findById(matiereId).get();
        seance.setMatiere(matiere);
        seanceRepository.save(seance);
    }
    @Override
    public void assignEnseignantToSeance(int seanceId, int enseignantId){
        Seance seance = seanceRepository.findById(seanceId).get();
        Enseignant enseignant = enseignantRepository.findById(enseignantId).get();
        seance.setEnseignant(enseignant);
        seanceRepository.save(seance);
    }
}
