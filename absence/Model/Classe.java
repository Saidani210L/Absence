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
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomClasse;
    @ManyToOne(fetch = FetchType.EAGER)
    private Section section;
    @ManyToOne(fetch = FetchType.EAGER)
    private Niveau niveau;
    @OneToMany(targetEntity = Etudiant.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "classe_id", referencedColumnName = "id")
    @JsonIgnore
    private Set<Etudiant> etudiants = new HashSet<>();
    @ManyToMany(mappedBy = "classes")
    @JsonIgnore
    private Set<Enseignant> enseignants = new HashSet<>();
    @OneToMany(targetEntity = Seance.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "classe_id", referencedColumnName = "id")
    @JsonIgnore
    private Set<Seance> seances = new HashSet<>();
}
