package io.rookware.reefTMSCore.model;

import io.rookware.reefTMSCore.dto.TankDTO;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Tank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @Column(name="id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    private UUID id;
    private String name;
    private Integer capacity;
    @Enumerated(EnumType.STRING)
    private CapacityType capacityType;
    private Float desiredSalinity;
    private Float desiredNitrate;
    private Float desiredCalcium;
    private Float desiredAlkalinity;
    private Float desiredMagnesium;
    private Float desiredPh;
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;

    public TankDTO generateTankDTO() {
        TankDTO dto = new TankDTO();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setCapacity(this.capacity);
        dto.setCapacityType(this.capacityType);
        dto.setDesiredSalinity(this.desiredSalinity);
        dto.setDesiredNitrate(this.desiredNitrate);
        dto.setDesiredCalcium(this.desiredCalcium);
        dto.setDesiredAlkalinity(this.desiredAlkalinity);
        dto.setDesiredMagnesium(this.desiredMagnesium);
        dto.setDesiredPh(this.desiredPh);
        dto.setAppUser(this.appUser.getId());
        return dto;
    }
}
