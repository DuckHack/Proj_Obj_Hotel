package com.projektowanie.ojektowe.hotel.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "room", schema = "main")
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    private Integer number;
    private Double rating;
    private Integer price;
    private Boolean wiFi;
    private Boolean conditioning;
    private Boolean petFriendly;
    private Double roomClass;
}
