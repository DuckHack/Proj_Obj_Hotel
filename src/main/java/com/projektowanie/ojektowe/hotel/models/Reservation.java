package com.projektowanie.ojektowe.hotel.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.util.Date;

@Data
@Entity
@Table(name = "reservation", schema = "main")
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer id;
//    @NotEmpty
    private Integer room;
//    @NotEmpty
    private Integer bodies;
//    @NotBlank
    //owner = firstName+secondName;
    private String owner;
    private Integer ownerId;
    @Future
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date start;
    @Future
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date end;
}
