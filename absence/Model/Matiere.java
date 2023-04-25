package com.example.absence.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Matiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomMatiere;
    /*@ManyToOne(fetch = FetchType.EAGER)
    private Section section;
    @ManyToOne(fetch = FetchType.EAGER)
    private Niveau niveau;*/
    @ManyToMany(mappedBy = "matieres")
    @JsonIgnore
    private Set<Niveau> niveaux = new HashSet<>();
    @OneToMany(mappedBy = "matiere")
    @JsonIgnore
    private Set<Absence> absences;
    @ManyToMany(mappedBy = "matieres")
    @JsonIgnore
    private Set<Enseignant> enseignants = new HashSet<>();
    private int semestre;
    @OneToMany(targetEntity = Seance.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "matiere_id", referencedColumnName = "id")
    @JsonIgnore
    private Set<Seance> seances = new HashSet<>();
}
