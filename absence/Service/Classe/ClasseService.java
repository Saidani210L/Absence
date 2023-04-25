package com.example.absence.Service.Classe;

import com.example.absence.Model.Classe;
import com.example.absence.Model.Enseignant;
import com.example.absence.Model.Etudiant;

import java.util.List;
import java.util.Set;

public interface ClasseService {
    public Classe saveClasse(Classe classe);
    public List<Classe> getClasses();
    public Set<Etudiant> getEtudiants(int classeId);
    public Set<Enseignant> getEnseignants(int classeId);
    public void deleteClasse(int classeId);
    public void updateClasse(int classeId, Classe classe);
    /*public void assignSectionToClasse(int classeId, int sectionId);
    public void assignNiveauToClasse(int classeId, int niveauId);*/
}
