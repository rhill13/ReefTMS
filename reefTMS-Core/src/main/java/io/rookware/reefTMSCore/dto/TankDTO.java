package io.rookware.reefTMSCore.dto;

import io.rookware.reefTMSCore.model.AppUser;
import io.rookware.reefTMSCore.model.CapacityType;
import lombok.Data;

import java.util.UUID;

@Data
public class TankDTO {
    private UUID id;
    private String name;
    private Integer capacity;
    private CapacityType capacityType;
    private Float desiredSalinity;
    private Float desiredNitrate;
    private Float desiredCalcium;
    private Float desiredAlkalinity;
    private Float desiredMagnesium;
    private Float desiredPh;
    private UUID appUser;
}
