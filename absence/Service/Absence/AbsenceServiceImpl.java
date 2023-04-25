package com.example.absence.Service.Absence;

import com.example.absence.Model.Absence;
import com.example.absence.Model.Etudiant;
import com.example.absence.Model.Matiere;
import com.example.absence.Repository.AbsenceRepository;
import com.example.absence.Repository.EtudiantRepository;
import com.example.absence.Repository.MatiereRepository;
import com.example.absence.Service.Email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AbsenceServiceImpl implements AbsenceService{
    @Autowired
    private AbsenceRepository absenceRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private MatiereRepository matiereRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Override
    public Absence saveAbsence(Absence absence){
        return absenceRepository.save(absence);
    }
    @Override
    public List<Absence> getAbsences(){
        return absenceRepository.findAll();
    }
    @Override
    public void assignMatiereToAbsence(int absenceId, int matiereId){
        Absence absence = absenceRepository.findById(absenceId).get();
        Matiere matiere = matiereRepository.findById(matiereId).get();
        absence.setMatiere(matiere);
        absenceRepository.save(absence);
    }
    @Override
    public void assignEtudiantToAbsence(int absenceId, int etudiantId){
        Absence absence = absenceRepository.findById(absenceId).get();
        Etudiant etudiant = etudiantRepository.findById(etudiantId).get();
        absence.setEtudiant(etudiant);
        absenceRepository.save(absence);
    }
    @Override
    public void addAbsence(int absenceId){
        Absence absence = absenceRepository.findById(absenceId).get();
        absence.setNbAbsence(absence.getNbAbsence() + 1);
        if (absence.getNbAbsence() == 3){
            String toEmail = absence.getEtudiant().getEmail();
            String subject = "Alert d'absence";
            String body = "Dear " + absence.getEtudiant().getPrenom() + " " + absence.getEtudiant().getNom()+", \n" +
                    "\n" +
                    "We are writing to inform you that you have reached the maximum number of absences allowed in "+ absence.getMatiere().getNomMatiere()+". As per our school policy, students are only permitted a certain number of absences in each subject, and unfortunately you have exceeded that limit.\n" +
                    "\n" +
                    "Going forward, we urge you to make every effort to attend all classes for "+ absence.getMatiere().getNomMatiere() +" to ensure that you do not fall behind on your coursework. If you have any concerns or questions, please do not hesitate to contact us.\n" +
                    "\n" +
                    "Thank you for your attention to this matter.\n" +
                    "\n" +
                    "Sincerely,";
            emailService.sendEmail(toEmail, subject, body);
        }
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("Africa/Tunis")).withNano(0).withSecond(0);
        String date = DateTimeFormatter.ISO_LOCAL_DATE.format(dateTime);
        String time = DateTimeFormatter.ISO_LOCAL_TIME.format(dateTime);
        Set<String> dateTimeSet = new HashSet<>();
        dateTimeSet.add(date);
        dateTimeSet.add(time);
        Set<Set<String>> dateSet = absence.getDate();
        dateSet.add(dateTimeSet);
        absence.setDate(dateSet);
        absenceRepository.save(absence);
    }
    @Override
    public void incrementAbsence(int etudiantId, int matiereId){
        List<Absence> absenceList = getAbsences();
        for (Absence absence: absenceList) {
            if (absence.getEtudiant().getId()==etudiantId && absence.getMatiere().getId()==matiereId){
                addAbsence(absence.getId());
            }
        }
    }
    @Override
    public List<Absence> getAbsencesByEtudiantId(int etudiantId){
        List<Absence> list = new ArrayList<>();
        List<Absence> absences = absenceRepository.findAll();
        for (Absence abs:absences) {
            if (abs.getEtudiant().getId()==etudiantId){
                list.add(abs);
            }
        }
        return list;
    }
    @Override
    public void updateNbAbsences(int nbAbsence, int etudiantId, int matiereId){
        Etudiant etudiant = etudiantRepository.findById(etudiantId).get();
        Matiere matiere = matiereRepository.findById(matiereId).get();
        List<Absence> absencesByEtudiantId = getAbsencesByEtudiantId(etudiantId);
        for (Absence abs: absencesByEtudiantId) {
            if (abs.getMatiere().getId() == matiereId){
                abs.setNbAbsence(nbAbsence);
                absenceRepository.save(abs);
            }
        }
    }

}
