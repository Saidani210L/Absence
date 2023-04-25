package com.example.absence.Repository;

import com.example.absence.Model.Enseignant;
import com.example.absence.Model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant,Integer> {
    @Query("select u from Enseignant u where u.email=?1")
    public Enseignant getEnseignantByEmail(String email);
}
