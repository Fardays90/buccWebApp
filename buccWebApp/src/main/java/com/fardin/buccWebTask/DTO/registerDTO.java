package com.fardin.buccWebTask.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class registerDTO {
    public String username;
    public String firstName;
    public String lastName;
    public String password;
    public Integer age;
    public String personalExp;
}
