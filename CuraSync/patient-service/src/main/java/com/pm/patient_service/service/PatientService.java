package com.pm.patient_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pm.patient_service.dto.PatientReponseDTO;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientReponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        // List<PatientReponseDTO>patientReponseDTOs=patients.stream().map(patient->PatientMapper.toDTO(patient)).toList();
        // //below is same
        List<PatientReponseDTO> patientReponseDTOs = patients.stream().map(PatientMapper::toDTO).toList();
        return patientReponseDTOs;

    }

}
