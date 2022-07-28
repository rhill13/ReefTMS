package io.rookware.reefTMSCore.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class NewLivestockDTO {
    private String name;
    private String category;
    private String species;
    private String dateAddedToTank;
    private String tank;
}
