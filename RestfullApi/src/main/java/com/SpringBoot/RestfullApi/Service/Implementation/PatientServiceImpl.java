package com.SpringBoot.RestfullApi.Service.Implementation;

import com.SpringBoot.RestfullApi.Entity.Patient;
import com.SpringBoot.RestfullApi.Repository.PatientRepository;
import com.SpringBoot.RestfullApi.Service.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;
//    IMPORTANT
//    @Transactional used for complete process of method --commit -- or any failure it rollback
    @Override
    @Transactional
    public Patient getPatientById(Long Id) {

        Patient p1=patientRepository.findById(Id).orElseThrow();
        Patient p2=patientRepository.findById(Id).orElseThrow();

//        System.out.println(p1 ==p2); true == same object--- smae memeory refrence
return p1;
    }
}
