package com.projektowanie.ojektowe.hotel.models.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomFilter {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date end;
    private Double rating;
    private Integer startPrice;
    private Integer endPrice;
    private Boolean wiFi;
    private Boolean conditioning;
    private Boolean petFriendly;
    private Double roomClass;
    private Integer group;
}
