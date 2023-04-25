package com.example.absence.Service.Etudiant;

import com.example.absence.Functions.Functions;
import com.example.absence.Model.*;
import com.example.absence.Repository.AbsenceRepository;
import com.example.absence.Repository.ClasseRepository;
import com.example.absence.Repository.EtudiantRepository;
import com.example.absence.Repository.RoleRepository;
import com.example.absence.Service.Absence.AbsenceService;
import com.example.absence.Service.Email.EmailService;
import com.example.absence.Service.Role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EtudiantServiceImpl implements EtudiantService{
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private AbsenceRepository absenceRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AbsenceService absenceService;
    @Override
    public Etudiant saveEtudiant(Etudiant etudiant, int classeId){
        etudiant.setConnected(false);
        etudiantRepository.save(etudiant);
        assignClasseToEtudiant(etudiant.getId(), classeId);
        Role role = new Role();
        role.setRole("etudiant");
        role.setUserId(etudiant.getId());
        roleService.saveRole(role);
        return etudiantRepository.save(etudiant);
    }
    @Override
    public List<Etudiant> getEtudiants(){
        return etudiantRepository.findAll();
    }
    @Override
    public void assignClasseToEtudiant(int etudiantId, int classeId){
        Etudiant etudiant = etudiantRepository.findById(etudiantId).get();
        Classe classe = classeRepository.findById(classeId).get();
        Set<Matiere> matieresSet = classe.getNiveau().getMatieres();
        for (Matiere mat: matieresSet) {
            Absence ab = new Absence();
            absenceService.saveAbsence(ab);
            absenceService.assignEtudiantToAbsence(ab.getId(),etudiantId);
            absenceService.assignMatiereToAbsence(ab.getId(),mat.getId());
        }
        etudiant.setClasse(classe);
        etudiantRepository.save(etudiant);
    }
    @Override
    public void logout(int etudiantId){
        Etudiant etudiant = etudiantRepository.findById(etudiantId).get();
        etudiant.setConnected(false);
        etudiantRepository.save(etudiant);
    }
    @Override
    public void deleteEtudiant(int etudiantId){
        etudiantRepository.findById(etudiantId).ifPresent(etudiant -> {
            Role role = roleRepository.getRoleByUserIdAndRole(etudiantId, "etudiant");
            roleRepository.deleteById(role.getId());
            List<Absence> absences = absenceService.getAbsencesByEtudiantId(etudiantId);
            for (Absence abs: absences) {
                absenceRepository.deleteById(abs.getId());
            }
            etudiantRepository.deleteById(etudiantId);
        });
    }

    @Override
    public void updateEtudiant(int etudiantId, Etudiant etudiant){
        Etudiant e = etudiantRepository.findById(etudiantId).get();
        e.setNom(etudiant.getNom());
        e.setPrenom(etudiant.getPrenom());
        e.setEmail(etudiant.getEmail());
        etudiantRepository.save(e);
        if (etudiant.getClasse().getId()!=e.getClasse().getId()){
            List<Absence> absences = absenceService.getAbsencesByEtudiantId(etudiantId);
            for (Absence abs: absences) {
                absenceRepository.deleteById(abs.getId());
            }
            assignClasseToEtudiant(etudiantId,etudiant.getClasse().getId());
        }
    }

}
