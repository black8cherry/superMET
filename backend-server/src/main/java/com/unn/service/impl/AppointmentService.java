package com.unn.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.unn.model.Appointment;
import com.unn.model.Doctor;
import com.unn.model.Patient;
import com.unn.repository.AppointmentRepo;
import com.unn.repository.DoctorRepo;
import com.unn.repository.DocumentRepo;
import com.unn.repository.PatientRepo;
import com.unn.service.IAppointmentService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {
  private final AppointmentRepo appointmentRepo;
  private final DoctorRepo doctorRepo;
  private final PatientRepo patientRepo;

  @Override
  public Optional<Appointment> createAppointment(Long doctorId, Date date) {
    Optional<Doctor> doctor = doctorRepo.findById(doctorId);
    if (doctor.isPresent()) {
        Appointment appointment = new Appointment(doctor.get(), date);
        return Optional.of(appointment);
    } else {
        return Optional.empty();
    }
  }

  @Override
  public Optional<Appointment> findAppointment(Long appointmentId) {
    return appointmentRepo.findById(appointmentId);
  }

  @Override
  public Optional<Appointment> findAppointment(Long doctorId, Long patientId) {
    return appointmentRepo.findByDoctorIdAndPatientId(doctorId, patientId);
  }
  
  @Override
  public Optional<Appointment> deleteAppointment(Long appointmentId) {
    Optional<Appointment> appointment = appointmentRepo.findById(appointmentId);
    if (appointment.isPresent())
      appointmentRepo.deleteById(appointmentId);
    return appointment;
  }

  @Override
  public Optional<Appointment> deleteAppointment(
    Long doctorId,
    Long patientId
  ) {
    Optional<Appointment> appointment = appointmentRepo.findByDoctorIdAndPatientId(doctorId, patientId);
    if (appointment.isPresent())
      appointmentRepo.deleteByDoctorIdAndPatientId(doctorId, patientId);
    return appointment;
  }

  @Override
  public Optional<Appointment> newResult(Long appointmentId, Long documentId) {
    // TODO:  implement method
    return null;
  }

  @Override
  public Optional<Appointment> newResult(
    Long appointmentId,
    Long doctorId,
    Long patientId
  ) {
    // TODO:  implement method
    return null;
  }

  @Override
  public Optional<List<Appointment>> findAllBusyDoctorAppointment(Long doctorId) {
    return appointmentRepo.findAllByDoctorIdAndBusy(doctorId, true);
  }

  @Override
  public Optional<List<Appointment>> findAllFreeDoctorAppointment(Long doctorId) {
    return appointmentRepo.findAllByDoctorIdAndBusy(doctorId, false);
  }

  @Override
  public Optional<List<Appointment>> findAllBusyPatientAppointment(Long patientId) {
    return appointmentRepo.findAllByPatientIdAndBusy(patientId, true);
  }

  @Override
  public Optional<List<Appointment>> findAllFreePatientAppointment(Long patientId) {
    return appointmentRepo.findAllByPatientIdAndBusy(patientId, false);
  }
}
