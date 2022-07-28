package io.rookware.reefTMSCore.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LivestockDTO {
    private UUID id;
    private String name;
    private String category;
    private String species;
    private String dateAddedToTank;
    private UUID tank;
}
