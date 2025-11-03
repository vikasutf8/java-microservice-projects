package com.SpringBoot.RestfullApi.Service.Implementation;

import com.SpringBoot.RestfullApi.Entity.Appointment;
import com.SpringBoot.RestfullApi.Entity.Doctor;
import com.SpringBoot.RestfullApi.Entity.Patient;
import com.SpringBoot.RestfullApi.Repository.AppointmentRepository;
import com.SpringBoot.RestfullApi.Repository.DoctorRepository;
import com.SpringBoot.RestfullApi.Repository.PatientRepository;
import com.SpringBoot.RestfullApi.Service.AppointmentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Override
    @Transactional
    public void createNewAppointment(Appointment appointment, Long doctorId, Long patientId) {
        Doctor doctor =doctorRepository.findById(doctorId)
                .orElseThrow(()-> new EntityNotFoundException("Doctor Id is not exist"+doctorId));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("Patient Id is not exist"+patientId));

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

//        to maintain bidirectional;
        patient.getAppointments().add(appointment);
        doctor.getAppointments().add(appointment);

//Cascading ?>>>
        appointmentRepository.save(appointment);
    }

    @Override
    @Transactional
    public Appointment reassignAppointmentToAontherDocter(Long appointmentId, Long doctorId) {
        Doctor doctor =doctorRepository.findById(doctorId)
                .orElseThrow(()-> new EntityNotFoundException("Doctor Id is not exist"+doctorId));

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new EntityNotFoundException("Doctor Id is not exist"+doctorId));

        appointment.setDoctor(doctor);

//        bidirectional
        doctor.getAppointments().add(appointment);

        return appointment;
    }
}
