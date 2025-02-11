package com.example.booking_app_be.dto.request;

import com.example.booking_app_be.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "INVALID_USERNAME")
    String username;

    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;

    String fullName;
    String phone;
    String gender;

    @DobConstraint(message = "INVALID_DOB")
    LocalDate dateOfBirth;

    String birthPlace;
    String address;
    String role = "USER";
    boolean active = true;
    Date onCreate = new Date();
    Date onUpdate = new Date();
}
