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
public class Niveau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomNiveau;
    @OneToMany(targetEntity = Classe.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "niveau_id", referencedColumnName = "id")
    @JsonIgnore
    private Set<Classe> classes = new HashSet<>();
    /*@OneToMany(targetEntity = Matiere.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "niveau_id", referencedColumnName = "id")
    @JsonIgnore
    private Set<Matiere> matieres = new HashSet<>();*/
    @ManyToMany
    @JoinTable(name = "matiere_niveau",
            joinColumns = @JoinColumn(name = "matiere_id"),
            inverseJoinColumns = @JoinColumn(name = "niveau_id"))
    private Set<Matiere> matieres = new HashSet<>();
    @ManyToMany(mappedBy = "niveaux")
    @JsonIgnore
    private Set<Section> sections = new HashSet<>();
}
