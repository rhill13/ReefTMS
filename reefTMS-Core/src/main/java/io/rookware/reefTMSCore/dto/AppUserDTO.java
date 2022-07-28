package io.rookware.reefTMSCore.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class AppUserDTO {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;

    // Used for creating jwts
    public Map<String, Object> convertToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("email", this.getEmail());
        map.put("firstName", this.getFirstName());
        map.put("lastName", this.getLastName());
        return map;
    }
}
