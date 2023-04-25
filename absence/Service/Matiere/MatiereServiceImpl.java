package com.example.absence.Service.Matiere;

import com.example.absence.Model.Matiere;
import com.example.absence.Model.Niveau;
import com.example.absence.Model.Section;
import com.example.absence.Repository.MatiereRepository;
import com.example.absence.Repository.NiveauRepository;
import com.example.absence.Repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatiereServiceImpl implements MatiereService{
    @Autowired
    private MatiereRepository matiereRepository;
    @Autowired
    private NiveauRepository niveauRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Override
    public Matiere saveMatiere(Matiere matiere){
        return matiereRepository.save(matiere);
    }
    @Override
    public List<Matiere> getMatieres(){
        return matiereRepository.findAll();
    }
    /*@Override
    public void assignNiveauToMatiere(int matiereId, int niveauId){
        Matiere matiere = matiereRepository.findById(matiereId).get();
        Niveau niveau = niveauRepository.findById(niveauId).get();
        matiere.setNiveau(niveau);
        matiereRepository.save(matiere);
    }
    @Override
    public void assignSectionToMatiere(int matiereId, int sectionId){
        Matiere matiere = matiereRepository.findById(matiereId).get();
        Section section = sectionRepository.findById(sectionId).get();
        matiere.setSection(section);
        matiereRepository.save(matiere);
    }*/
    @Override
    public void updateSemestre(int matiereId, int semestre){
        Matiere mat = matiereRepository.findById(matiereId).get();
        mat.setSemestre(semestre);
        matiereRepository.save(mat);
    }
    /*@Override
    public void assignNiveauToMatiere(int matiereId, int niveauId){
        Niveau niveau = niveauRepository.findById(niveauId).get();
        Matiere matiere = matiereRepository.findById(matiereId).get();
        Set<Niveau> niveauSet = matiere.getNiveaux();
        niveauSet.add(niveau);
        matiere.setNiveaux(niveauSet);
        matiereRepository.save(matiere);
    }*/
}
