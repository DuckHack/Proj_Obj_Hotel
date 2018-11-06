package com.projektowanie.ojektowe.hotel.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    private Integer id;
    @NotEmpty
    private Integer roomNumber;
    @NotEmpty
    private Integer bodiesNumber;
    @NotBlank
    private String reservePerson;
    @Future
    private Date start;
    @Future
    private Date end;
}
