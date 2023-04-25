package com.example.absence.Service.Enseignant;

import com.example.absence.Model.Enseignant;
import com.example.absence.Model.Etudiant;
import com.example.absence.Model.Matiere;
import com.example.absence.Model.Seance;

import java.util.List;
import java.util.Set;

public interface EnseignantService {
    public Enseignant saveEnseignant(Enseignant enseignant);
    public List<Enseignant> getEnseignants();
    public String assignClasseToEnseignant(int enseignantId, int classeId);
    public String addMatiere(int enseignantId, int matiereId);
    public Set<Etudiant> getEtudiants(int enseignantId);
    public Set<Matiere> getMatieres(int enseignantId);
    public Set<Etudiant> getEtudiantsParClasse(int enseignantId, int classeId);
    public void faireAbsences(List<Integer> etudiantIds, int matiereId);
    public Set<Seance> getSeances(int enseignantId);
    public void deleteClasseFromEnseignant(int enseignantId, int classeId);
}
