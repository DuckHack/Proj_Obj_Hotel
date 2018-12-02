package com.projektowanie.ojektowe.hotel.models.UserModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
@Entity
@Table(name = "user", schema = "main")
@AllArgsConstructor
@NoArgsConstructor
public class User {
//    private static final String systemUser = "User";
//    private static final String systemAdmin = "Admin";

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;
    private String type;
    private String firstName;
    private String lastName;
//    @NotBlank
    @Email
    private String email;
//    @NotBlank
    private String password;
}
