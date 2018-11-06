package com.projektowanie.ojektowe.hotel.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Data
@Entity
@Table(name = "room", schema = "main")
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    private Integer number;
    @NotEmpty
    private Integer guestPlaces;
}
