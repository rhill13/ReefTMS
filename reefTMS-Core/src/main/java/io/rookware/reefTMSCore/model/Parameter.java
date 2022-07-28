package io.rookware.reefTMSCore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.rookware.reefTMSCore.dto.ParameterDTO;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @Column(name="id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    public UUID id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date dateRecorded;
    @Enumerated(EnumType.STRING)
    public ParameterType type;
    public Float value;
    @ManyToOne
    public Tank tank;

    public ParameterDTO generateParameterDTO() {
        ParameterDTO dto = new ParameterDTO();
        dto.setId(this.getId());
        dto.setDateRecorded(this.getDateRecorded());
        dto.setType(this.getType());
        dto.setValue(this.getValue());
        dto.setTank(this.getTank().getId().toString());
        return dto;
    }
}
