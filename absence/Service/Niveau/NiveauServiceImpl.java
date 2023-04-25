package com.example.absence.Service.Niveau;

import com.example.absence.Model.Niveau;
import com.example.absence.Model.Section;
import com.example.absence.Repository.NiveauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NiveauServiceImpl implements NiveauService{
    @Autowired
    private NiveauRepository niveauRepository;
    @Override
    public Niveau saveNiveau(Niveau niveau){
        return niveauRepository.save(niveau);
    }
    @Override
    public List<Niveau> getNiveaux(){
        return niveauRepository.findAll();
    }
    @Override
    public void updateNiveau(int niveauId, Niveau niveau){
        Niveau niv = niveauRepository.findById(niveauId).get();
        niv.setNomNiveau(niveau.getNomNiveau());
        niveauRepository.save(niv);
    }
}
