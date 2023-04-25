package com.example.absence.Service.Absence;

import com.example.absence.Model.Absence;
import com.example.absence.Model.Etudiant;

import java.util.List;

public interface AbsenceService {
    public Absence saveAbsence(Absence absence);
    public List<Absence> getAbsences();
    public void assignMatiereToAbsence(int absenceId, int matiereId);
    public void assignEtudiantToAbsence(int absenceId, int etudiantId);
    public void addAbsence(int absenceId);
    public List<Absence> getAbsencesByEtudiantId(int etudiantId);
    public void incrementAbsence(int etudiantId, int matiereId);
    public void updateNbAbsences(int nbAbsence, int etudiantId, int matiereId);
}
