package com.unn.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import com.unn.constants.UserTypes;
import com.unn.dto.SignupRequest;
import com.unn.model.Doctor;
import com.unn.model.Patient;
import com.unn.model.User;
import com.unn.service.impl.ResponseService;
import com.unn.service.impl.UserService;
import com.unn.service.impl.ValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ValidationService validationService;
    private final ResponseService responseService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return responseService.handleGetResponse(userService.findUser(id));
    }

    @GetMapping("/current")
    public ResponseEntity<SignupRequest> getCurretnUser(Authentication auth) {
        return responseService.handleGetResponse(userService.findUser(auth));
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable("id") Long id) {
        return responseService.handleGetResponse(userService.getDoctor(id));
    }

    @PostMapping("/doctor/{doctor_id}/facility/{facility_id}")
    public ResponseEntity<Doctor> attachDoctorToFacility(
        @PathVariable("doctor_id") Long doctorId,
        @PathVariable("facility_id") Long facilityId
    ) {
        return responseService.handlePostResponse(userService.attachDoctorToFacility(doctorId, facilityId));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable("id") Long id) {
        return responseService.handleGetResponse(userService.getPatient(id));
    }

    @GetMapping("/mail/{mail}")
    public ResponseEntity<User> getUser(@PathVariable("mail") String mail) {
        Optional<User> user = userService.findUser(mail);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<User> edit(@RequestBody @Valid User user) {
        if (validationService.validateUserUpdate(user)) {
            userService.updateUser(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        Optional<User> deletedUser = userService.deleteUser(id);
        if (deletedUser.isPresent()) {
            return ResponseEntity.ok(deletedUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/mail/{mail}/delete")
    public ResponseEntity<User> deleteUser(@PathVariable("mail") String mail) {
        Optional<User> deletedUser = userService.deleteUser(mail);
        if (deletedUser.isPresent()) {
            return ResponseEntity.ok(deletedUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/patients")
    public ResponseEntity<List<User>> getAllPatients() {
        Optional<List<User>> doctors = userService.getAllByType(UserTypes.PATIENT);
        if (doctors.isPresent()) {
            return ResponseEntity.ok(doctors.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
