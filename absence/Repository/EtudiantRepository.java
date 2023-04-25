package com.example.absence.Repository;

import com.example.absence.Model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Integer> {

    @Query("select u from Etudiant u where u.email=?1")
    public Etudiant getEtudiantByEmail(String email);
}
