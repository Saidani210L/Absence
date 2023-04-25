package com.example.absence.Service.Role;

import com.example.absence.Functions.Functions;
import com.example.absence.Model.Enseignant;
import com.example.absence.Model.Etudiant;
import com.example.absence.Model.Role;
import com.example.absence.Repository.EnseignantRepository;
import com.example.absence.Repository.EtudiantRepository;
import com.example.absence.Repository.RoleRepository;
import com.example.absence.Service.Email.EmailService;
import com.example.absence.Service.Enseignant.EnseignantService;
import com.example.absence.Service.Etudiant.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Override
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }
    @Override
    public List<Role> getRoles(){
        return roleRepository.findAll();
    }
    @Override
    public List<Object> login(String userEmail, String userPassword){
        List<Role> roles = getRoles();
        for (Role role: roles) {
            Etudiant etudiant = etudiantRepository.findById(role.getUserId()).orElse(null);
            if (etudiant!=null && etudiant.getEmail().equals(userEmail) && etudiant.getMotDePasse().equals(userPassword) && role.getRole().equals("etudiant")){
                etudiant.setConnected(true);
                etudiantRepository.save(etudiant);
                return List.of(etudiant,role.getRole());
            }else {
                Enseignant enseignant = enseignantRepository.findById(role.getUserId()).orElse(null);
                if (enseignant!=null && enseignant.getEmail().equals(userEmail) && enseignant.getMotDePasse().equals(userPassword) && role.getRole().equals("enseignant")){
                    enseignant.setConnected(true);
                    enseignantRepository.save(enseignant);
                    return List.of(enseignant,role.getRole());
                }
            }
        }
        return null;
    }
    @Override
    public void motDePasseOublier(String email){
        String password = Functions.generateRandomPassword(15);
        String body = "Your new password is:\n\n" + password;
        emailService.sendEmail(email,"Absence account password", body);
        Etudiant etudiants = etudiantRepository.getEtudiantByEmail(email);
        Enseignant enseignant = enseignantRepository.getEnseignantByEmail(email);
    }
}
