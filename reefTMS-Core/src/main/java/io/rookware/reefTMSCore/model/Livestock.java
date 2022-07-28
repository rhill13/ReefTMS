package io.rookware.reefTMSCore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.rookware.reefTMSCore.dto.LivestockDTO;
import io.rookware.reefTMSCore.util.DateServices;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Livestock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @Column(name="id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private LivestockCategory category;
    private String species;
    @Nullable @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateAddedToTank;
    @Nullable
    private String photoUrl;
    @ManyToOne
    private Tank tank;

    public LivestockDTO generateLivestockDTO() {
        LivestockDTO dto = new LivestockDTO();
        dto.setId(this.getId());
        dto.setName(this.getName());
        dto.setCategory(this.getCategory().toString());
        dto.setSpecies(this.getSpecies());
        dto.setDateAddedToTank(DateServices.generateString(this.getDateAddedToTank()));
        dto.setTank(this.getTank().getId());
        return dto;
    }

}
