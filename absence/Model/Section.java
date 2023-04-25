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
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomSection;
    @OneToMany(targetEntity = Classe.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    @JsonIgnore
    private Set<Classe> classes = new HashSet<>();
    /*@OneToMany(targetEntity = Matiere.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    @JsonIgnore
    private Set<Matiere> matieres = new HashSet<>();*/
    @ManyToMany
    @JoinTable(name = "section_niveau",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "niveau_id"))
    private Set<Niveau> niveaux = new HashSet<>();
}
