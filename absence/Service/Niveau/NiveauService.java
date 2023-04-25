package com.example.absence.Service.Niveau;

import com.example.absence.Model.Niveau;

import java.util.List;

public interface NiveauService {
    public Niveau saveNiveau(Niveau niveau);
    public List<Niveau> getNiveaux();
    public void updateNiveau(int niveauId, Niveau niveau);
}
