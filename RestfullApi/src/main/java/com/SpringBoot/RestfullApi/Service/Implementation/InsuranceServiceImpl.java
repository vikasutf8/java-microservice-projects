package com.SpringBoot.RestfullApi.Service.Implementation;


import com.SpringBoot.RestfullApi.Entity.Insurance;
import com.SpringBoot.RestfullApi.Entity.Patient;
import com.SpringBoot.RestfullApi.Repository.InsuranceRepository;
import com.SpringBoot.RestfullApi.Repository.PatientRepository;
import com.SpringBoot.RestfullApi.Service.InsuranceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceServiceImpl implements InsuranceService {


    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;


    @Override
    @Transactional
    public Patient assignInsuranseToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("Patient Id is not exist"+patientId));

//        what this line doing
//        1. first taking  patient in persistence state
//        2 . on adding make it dirty -> first create insurence --> updated
        patient.setInsurance(insurance);// setting insurance

//        to maintain bidirectional consistency
        insurance.setPatient(patient);

//        we have to save that in db -- cascading meant any happing with parent so how child entity works/happened behind the scene


        return  patient;
    }

    @Override
    @Transactional
    public Patient disassoicateInsuranseToPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("Patient Id is not exist"+patientId));

        patient.setInsurance(null); // we just want ot remove insuranse

        return patient;
    }
}
