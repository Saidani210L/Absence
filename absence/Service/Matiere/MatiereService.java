package com.example.absence.Service.Matiere;

import com.example.absence.Model.Matiere;

import java.util.List;

public interface MatiereService {
    public Matiere saveMatiere(Matiere matiere);
    public List<Matiere> getMatieres();
    /*public void assignNiveauToMatiere(int matiereId, int niveauId);
    public void assignSectionToMatiere(int matiereId, int sectionId);*/
    public void updateSemestre(int matiereId, int semestre);
    //public void assignNiveauToMatiere(int matiereId, int niveauId);
}
