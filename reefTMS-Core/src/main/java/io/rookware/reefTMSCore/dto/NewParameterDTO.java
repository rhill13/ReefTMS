package io.rookware.reefTMSCore.dto;

import io.rookware.reefTMSCore.model.ParameterType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NewParameterDTO {
    private String type;
    private Float value;
    private String tank;
}
