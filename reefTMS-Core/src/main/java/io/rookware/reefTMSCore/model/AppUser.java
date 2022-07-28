package io.rookware.reefTMSCore.model;

import io.rookware.reefTMSCore.dto.AppUserDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @Column(name="id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    public UUID id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;


    public AppUserDTO generateAppUserDTO() {
        AppUserDTO dto = new AppUserDTO();
        dto.setId(this.getId());
        dto.setEmail(this.getEmail());
        dto.setFirstName(this.getFirstName());
        dto.setLastName(this.getLastName());
        return dto;
    }

}
