package com.example.absence.Service.Section;

import com.example.absence.Model.Matiere;
import com.example.absence.Model.Niveau;
import com.example.absence.Model.Section;
import com.example.absence.Repository.MatiereRepository;
import com.example.absence.Repository.NiveauRepository;
import com.example.absence.Repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SectionServiceImpl implements SectionService{
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private MatiereRepository matiereRepository;
    @Autowired
    private NiveauRepository niveauRepository;
    @Override
    public Section saveSection(Section section){
        return sectionRepository.save(section);
    }
    @Override
    public List<Section> getSections(){
        return sectionRepository.findAll();
    }
    @Override
    public void updateSection(int sectionId, Section section){
        Section sec = sectionRepository.findById(sectionId).get();
        sec.setNomSection(section.getNomSection());
        sectionRepository.save(sec);
    }
    @Override
    public void deleteSection(int SectionId){

    }
    @Override
    public String addNiveau(int sectionId, Niveau niveau){
        boolean trouve = false;
        Section section = sectionRepository.findById(sectionId).get();
        Set<Niveau> niveauSet = section.getNiveaux();
        for (Niveau niv: niveauSet) {
            if(niv.getNomNiveau().equals(niveau.getNomNiveau())) trouve = true;
        }
        if (!trouve){
            niveauRepository.save(niveau);
            niveauSet.add(niveau);
            section.setNiveaux(niveauSet);
            sectionRepository.save(section);
            return "Niveau ajouté";
        }
        return "Niveau existe";
    }
    @Override
    public String addMatiere(int sectionId, int niveauId, Matiere matiere){
        boolean trouve = false;
        Section section = sectionRepository.findById(sectionId).get();
        Set<Niveau> niveauSet = section.getNiveaux();
        for (Niveau niv: niveauSet) {
            if(niv.getId()==niveauId){
                Set<Matiere> matiereSet = niv.getMatieres();
                for (Matiere mat:matiereSet) {
                    if (mat.getNomMatiere().equals(matiere.getNomMatiere())) trouve = true;
                }
                if (!trouve){
                    matiereRepository.save(matiere);
                    matiereSet.add(matiere);
                    niv.setMatieres(matiereSet);
                    section.setNiveaux(niveauSet);
                    sectionRepository.save(section);
                    return "Matiere ajouté";
                }
            }
        }
        return "Cette matiere existe déja";
    }
    /*@Override
    public void assignMatiereToSection(int sectionId, int matiereId){
        Section section = sectionRepository.findById(sectionId).get();
        Matiere matiere = matiereRepository.findById(matiereId).get();
        Set<Matiere> matiereSet = section.getMatieres();
        matiereSet.add(matiere);
        section.setMatieres(matiereSet);
        sectionRepository.save(section);
    }*/
    /*@Override
    public void ajouterMatiere(int sectionId, Matiere matiere){
        Section section = sectionRepository.findById(sectionId).get();
        Set<Matiere> matiereSet = section.getMatieres();
        matiereSet.add(matiere);
        section.setMatieres(matiereSet);
        sectionRepository.save(section);
    }
    @Override
    public void updateMatiere(int sectionId, int matiereId, Matiere mat){
        Section section = sectionRepository.findById(sectionId).get();
        Set<Matiere> matiereSet = section.getMatieres();
        matiereSet.forEach(element -> {
            if(element.getId() == matiereId){
                element.setNiveaux(mat.getNiveaux());
                element.setNomMatiere(mat.getNomMatiere());
                element.setSemestre(mat.getSemestre());
            }
        });
        section.setMatieres(matiereSet);
        sectionRepository.save(section);
    }*/
}
