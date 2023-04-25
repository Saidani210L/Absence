package com.example.absence.Service.Seance;

import com.example.absence.Model.Seance;

import java.util.List;

public interface SeanceService {
    public Seance saveSeance(Seance seance);
    public List<Seance> getSeances();
    public Seance getSeanceById(int seanceId);
    public void assignClasseToSeance(int seanceId, int classeId);
    public void assignMatiereToSeance(int seanceId, int matiereId);
    public void assignEnseignantToSeance(int seanceId, int enseignantId);
}
