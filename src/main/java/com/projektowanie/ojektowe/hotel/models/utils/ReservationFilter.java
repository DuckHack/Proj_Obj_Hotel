package com.projektowanie.ojektowe.hotel.models.utils;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class ReservationFilter {
    @Id
    private Integer id;
    @NotEmpty
    private Integer room;
    @NotBlank
    private String reservePerson;
}
