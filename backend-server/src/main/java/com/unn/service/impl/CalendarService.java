package com.unn.service.impl;

import com.unn.model.Appointment;
import com.unn.model.Calendar;
import com.unn.model.Doctor;
import com.unn.model.Calendar;
import com.unn.repository.AppointmentRepo;
import com.unn.repository.CalendarRepo;
import com.unn.repository.DoctorRepo;
import com.unn.service.ICalendarService;
import com.unn.service.IValidationService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice.Return;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.util.OptionHelper;

@Service
@RequiredArgsConstructor
public class CalendarService implements ICalendarService {
  private final CalendarRepo calendarRepo;
  private final DoctorRepo doctorRepo;
  private final AppointmentRepo appointmentRepo;

  private final ValidationService validationService;

  @Override
  public Optional<Calendar> createCalendar(Long calendarId) {
    /*
    Calendar Calendar = new Calendar();

    Optional<Calendar> calendar = calendarRepo.findById(calendarId);
    if(calendar.isPresent()) {
      Calendar.setCalendar(calendar.get());

      return Optional.of(Calendar);
    }
    */
    return Optional.empty();
  }

  @Override
  public Optional<Calendar> createCalendarByDoctorID(Long doctorId, int startTime, int endTime) {
    /*
    Calendar Calendar = new Calendar();

    if (validationService.validateWorkTime(startTime, endTime)) {
      Map<Long, Appointment> apm;
      Optional<List<Appointment>> appointments = appointmentRepo.findAllByDoctorId(doctorId);

      if (appointments.isPresent()) {
        apm = appointments.get().stream().collect(
              Collectors.toMap(Appointment::getId, appointment -> appointment)
            );

        Optional<Calendar> calendar = calendarRepo.findDoctorById(doctorId);
        calendar.get().setAppointments(apm);
        
        Optional<Doctor> doctor = doctorRepo.findById(doctorId);
        calendar.get().setDoctor(doctor.get());

        Calendar.setCalendar(calendar.get());

          return Optional.of(Calendar);
      }
    }
*/
    return Optional.empty();
  }

  @Override
  public Optional<Calendar> findCalendar(Long calendarId) {
  /*
    Calendar Calendar = new Calendar();

    Optional<Calendar> calendar = calendarRepo.findById(calendarId);
    if (calendar.isPresent()) {
      Calendar.setCalendar(calendar.get());

      return Optional.of(Calendar);  
    }
*/
    return Optional.empty();
  }

  @Override
  public Optional<Calendar> modifyCalendar(Long calendarId) {
  /*
    Calendar Calendar = new Calendar();

    Optional<Calendar> calendar = calendarRepo.findById(calendarId);
    if (calendar.isPresent()) {
      Calendar.setCalendar(calendar.get());

      return Optional.of(Calendar);  
    }
*/
    return Optional.empty();
  }

  @Override
  public Optional<Calendar> deleteCalendar(Long calendarId) {
  /*
    Calendar Calendar = new Calendar();

    Optional<Calendar> calendar = calendarRepo.findById(calendarId);
    if (calendar.isPresent()) {
      calendarRepo.delete(calendar.get());
      Calendar.setCalendar(calendar.get());

      return Optional.of(Calendar);
    }
*/
    return Optional.empty();
  }
}