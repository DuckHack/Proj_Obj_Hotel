package com.projektowanie.ojektowe.hotel.models;

import com.projektowanie.ojektowe.hotel.models.utils.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private int id;
    private UserType type;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
