package com.projektowanie.ojektowe.hotel.models.UserModels;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUser {
    private String email;
    private String password;
}
