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
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private boolean connected;
    @ManyToMany
    @JoinTable(name = "enseignant_classe",
            joinColumns = @JoinColumn(name = "enseignant_id"),
            inverseJoinColumns = @JoinColumn(name = "classe_id"))
    private Set<Classe> classes = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "enseignant_matiere",
            joinColumns = @JoinColumn(name = "enseignant_id"),
            inverseJoinColumns = @JoinColumn(name = "matiere_id"))
    private Set<Matiere> matieres = new HashSet<>();
    @OneToMany(targetEntity = Seance.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "enseignant_id", referencedColumnName = "id")
    @JsonIgnore
    private Set<Seance> seances = new HashSet<>();
}
