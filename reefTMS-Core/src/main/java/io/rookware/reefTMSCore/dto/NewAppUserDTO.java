package io.rookware.reefTMSCore.dto;

import lombok.Data;

@Data
public class NewAppUserDTO {

    private String firstName;
    public String lastName;
    public String email;
    public String password;
}
