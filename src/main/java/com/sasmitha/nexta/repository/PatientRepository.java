package com.sasmitha.nexta.repository;

import com.sasmitha.nexta.entity.Appointment;
import com.sasmitha.nexta.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Appointment> findByDoctorId(Long id);
}
