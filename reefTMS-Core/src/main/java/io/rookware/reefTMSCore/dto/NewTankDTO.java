package io.rookware.reefTMSCore.dto;

import lombok.Data;

@Data
public class NewTankDTO {
    private String name;
    private Integer capacity;
    private String capacityType;
    private float desiredSalinity;
    private float desiredNitrate;
    private float desiredCalcium;
    private float desiredAlkalinity;
    private float desiredMagnesium;
    private float desiredPh;
}
