package com.SpringBoot.RestfullApi;


import com.SpringBoot.RestfullApi.Entity.Insurance;
import com.SpringBoot.RestfullApi.Entity.Patient;
import com.SpringBoot.RestfullApi.Service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

@SpringBootTest
public class InsurenceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Test
    public void testInsurence(){
        Insurance insurance= new Insurance().builder()
                .policyNumber("fgasd45325")
                .provider("asdfas")
                .validUntil(LocalDate.of(3943,3,24))
                .build();

        Patient patient =insuranceService.assignInsuranseToPatient(insurance,1L);
        System.out.println(patient+"patient updated");

        Patient patient1 =insuranceService.disassoicateInsuranseToPatient(patient.getId());
        System.out.println(patient1);
    }
}
