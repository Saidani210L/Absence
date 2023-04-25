package com.example.absence.Service.Etudiant;

import com.example.absence.Model.Etudiant;

import java.util.List;

public interface EtudiantService {
    public Etudiant saveEtudiant(Etudiant etudiant, int classeId);
    public List<Etudiant> getEtudiants();
    public void assignClasseToEtudiant(int etudiantId, int classeId);
    public void logout(int etudiantId);
    public void deleteEtudiant(int etudiantId);
    public void updateEtudiant(int etudiantId, Etudiant etudiant);

}
