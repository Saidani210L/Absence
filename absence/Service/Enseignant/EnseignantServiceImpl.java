package com.example.absence.Service.Enseignant;

import com.example.absence.Model.*;
import com.example.absence.Repository.ClasseRepository;
import com.example.absence.Repository.EnseignantRepository;
import com.example.absence.Repository.MatiereRepository;
import com.example.absence.Service.Absence.AbsenceService;
import com.example.absence.Service.Role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EnseignantServiceImpl implements EnseignantService{
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private AbsenceService absenceService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MatiereRepository matiereRepository;
    @Override
    public Enseignant saveEnseignant(Enseignant enseignant){
        enseignant.setConnected(false);
        enseignantRepository.save(enseignant);
        Role role = new Role();
        role.setRole("enseignant");
        role.setUserId(enseignant.getId());
        roleService.saveRole(role);
        return enseignantRepository.save(enseignant);
    }
    @Override
    public List<Enseignant> getEnseignants(){
        return enseignantRepository.findAll();
    }
    @Override
    public String assignClasseToEnseignant(int enseignantId, int classeId){
        Enseignant enseignant = enseignantRepository.findById(enseignantId).get();
        Classe classe = classeRepository.findById(classeId).get();
        Set<Classe> classesSet = enseignant.getClasses();
        classesSet.add(classe);
        enseignant.setClasses(classesSet);
        enseignantRepository.save(enseignant);
        return "Classe affectée";
    }
    @Override
    public String addMatiere(int enseignantId, int matiereId){
        Enseignant enseignant = enseignantRepository.findById(enseignantId).get();
        Matiere matiere = matiereRepository.findById(matiereId).get();
        Set<Matiere> matieresSet = enseignant.getMatieres();
        matieresSet.add(matiere);
        enseignant.setMatieres(matieresSet);
        enseignantRepository.save(enseignant);
        return "Matiere ajoutée";
    }
    @Override
    public Set<Etudiant> getEtudiants(int enseignantId){
        Enseignant enseignant = enseignantRepository.findById(enseignantId).get();
        Set<Etudiant> etudiantSet = new HashSet<>();
        for (Classe classe: enseignant.getClasses()) {
            etudiantSet.addAll(classe.getEtudiants());
        }
        return etudiantSet;
    }
    @Override
    public Set<Matiere> getMatieres(int enseignantId){
        Enseignant enseignant = enseignantRepository.findById(enseignantId).get();
        return enseignant.getMatieres();
    }
    @Override
    public Set<Etudiant> getEtudiantsParClasse(int enseignantId, int classeId){
        Enseignant enseignant = enseignantRepository.findById(enseignantId).get();
        Set<Etudiant> etudiantSet = new HashSet<>();
        for (Classe classe: enseignant.getClasses()) {
            if (classe.getId()==classeId) etudiantSet.addAll(classe.getEtudiants());
        }
        return etudiantSet;
    }
    @Override
    public void deleteClasseFromEnseignant(int enseignantId, int classeId){
        Enseignant enseignant = enseignantRepository.findById(enseignantId).get();
        Set<Classe> classes = enseignant.getClasses();
        classes.removeIf(c->c.getId()==classeId);
        enseignant.setClasses(classes);
        enseignantRepository.save(enseignant);
    }
    @Override
    public void faireAbsences(List<Integer> etudiantIds, int matiereId){
        for (int etudiantId: etudiantIds) {
            absenceService.incrementAbsence(etudiantId, matiereId);
        }
    }
    @Override
    public Set<Seance> getSeances(int enseignantId){
        Enseignant enseignant = enseignantRepository.findById(enseignantId).get();
        return enseignant.getSeances();
    }
}
