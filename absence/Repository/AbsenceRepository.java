package com.example.absence.Repository;

import com.example.absence.Model.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence,Integer> {
    @Query("select u from Absence u where u.etudiant.id =?1")
    public List<Absence> getAbsenceByEtudiantId(int etudiantId);
}
