package com.example.absence.Service.Classe;

import com.example.absence.Model.*;
import com.example.absence.Repository.*;
import com.example.absence.Service.Enseignant.EnseignantService;
import com.example.absence.Service.Etudiant.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ClasseServiceImpl implements ClasseService{
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private NiveauRepository niveauRepository;
    @Autowired
    private EnseignantService enseignantService;
    @Override
    public Classe saveClasse(Classe classe){
        return classeRepository.save(classe);
    }
    @Override
    public List<Classe> getClasses(){
        return classeRepository.findAll();
    }
    @Override
    public Set<Etudiant> getEtudiants(int classeId){
        Classe classe = classeRepository.findById(classeId).get();
        return classe.getEtudiants();
    }
    @Override
    public Set<Enseignant> getEnseignants(int classeId){
        Classe classe = classeRepository.findById(classeId).get();
        return classe.getEnseignants();
    }
    @Override
    public void deleteClasse(int classeId){
        Set<Etudiant> etudiants = getEtudiants(classeId);
        Set<Enseignant> enseignants = getEnseignants(classeId);
        for (Etudiant e: etudiants) {
            etudiantService.deleteEtudiant(e.getId());
        }
        for (Enseignant ens: enseignants) {
            enseignantService.deleteClasseFromEnseignant(ens.getId(),classeId);
        }
        classeRepository.deleteById(classeId);
    }
    @Override
    public void updateClasse(int classeId, Classe classe){
        Classe c = classeRepository.findById(classeId).get();

    }
    /*@Override
    public void assignSectionToClasse(int classeId, int sectionId){
        Classe classe = classeRepository.findById(classeId).get();
        Section section = sectionRepository.findById(sectionId).get();
        classe.setSection(section);
        classeRepository.save(classe);
    }
    @Override
    public void assignNiveauToClasse(int classeId, int niveauId){
        Classe classe = classeRepository.findById(classeId).get();
        Niveau niveau = niveauRepository.findById(niveauId).get();
        classe.setNiveau(niveau);
        classeRepository.save(classe);
    }*/
}
