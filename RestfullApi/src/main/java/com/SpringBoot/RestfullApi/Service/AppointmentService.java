package com.SpringBoot.RestfullApi.Service;

import com.SpringBoot.RestfullApi.Entity.Appointment;

public interface  AppointmentService {

    public void createNewAppointment(Appointment appointment, Long doctorId, Long patientId);

    public Appointment reassignAppointmentToAontherDocter(Long appointmentId, Long doctorId);
}
