package com.SpringBoot.RestfullApi.Service;

import com.SpringBoot.RestfullApi.Entity.Insurance;
import com.SpringBoot.RestfullApi.Entity.Patient;

public interface InsuranceService {

    public Patient assignInsuranseToPatient(Insurance insurance, Long patientId);


    public Patient disassoicateInsuranseToPatient(Long patientId);
}
