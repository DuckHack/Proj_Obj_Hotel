package com.projektowanie.ojektowe.hotel.models;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class Filter {
    @NotEmpty
    private Date start;
    @NotEmpty
    private Date end;
    @NotEmpty
    private Integer personsNumber;
}
