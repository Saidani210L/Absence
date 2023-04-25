package com.example.absence.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String jour;
    private String heureDebut;
    private String heureFin;
    @ManyToOne(fetch = FetchType.EAGER)
    private Classe classe;
    @ManyToOne(fetch = FetchType.EAGER)
    private Matiere matiere;
    @ManyToOne(fetch = FetchType.EAGER)
    private Enseignant enseignant;
}
