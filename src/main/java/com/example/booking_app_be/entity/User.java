package com.example.booking_app_be.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String username;
    String password;
    String fullName;
    String phone;
    String gender;
    LocalDate dateOfBirth;
    String birthPlace;
    String address;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = true)
    Role role;

    boolean active;
    Date onCreate;
    Date onUpdate;
}
