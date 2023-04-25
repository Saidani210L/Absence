package com.example.absence.Service.Section;

import com.example.absence.Model.Matiere;
import com.example.absence.Model.Niveau;
import com.example.absence.Model.Section;

import java.util.List;

public interface SectionService {
    public Section saveSection(Section section);
    public List<Section> getSections();
    //public void assignMatiereToSection(int sectionId, int matiereId);
    public void updateSection(int sectionId, Section section); // il faut modifier cette fonction !!!!
    public void deleteSection(int SectionId);
    public String addNiveau(int sectionId, Niveau niveau);
    public String addMatiere(int sectionId, int niveauId, Matiere matiere);

    //public void ajouterMatiere(int sectionId, Matiere matiere);
}
