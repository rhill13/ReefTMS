package io.rookware.reefTMSCore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.rookware.reefTMSCore.model.ParameterType;
import io.rookware.reefTMSCore.model.Tank;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
public class ParameterDTO {
    public UUID id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date dateRecorded;
    public ParameterType type;
    public Float value;
    public String tank;
}
