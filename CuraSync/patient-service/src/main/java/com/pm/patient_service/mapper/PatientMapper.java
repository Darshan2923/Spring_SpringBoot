package com.pm.patient_service.mapper;

import com.pm.patient_service.dto.PatientReponseDTO;
import com.pm.patient_service.model.Patient;

public class PatientMapper {

    public static PatientReponseDTO toDTO(Patient patient) {
        PatientReponseDTO patientDTO = new PatientReponseDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientDTO;
    }

}
