package io.rookware.reefTMSCore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.rookware.reefTMSCore.dto.LivestockDTO;
import io.rookware.reefTMSCore.dto.NewLivestockDTO;
import io.rookware.reefTMSCore.model.Livestock;
import io.rookware.reefTMSCore.model.LivestockCategory;
import io.rookware.reefTMSCore.model.Tank;
import io.rookware.reefTMSCore.repository.LivestockRepository;
import io.rookware.reefTMSCore.repository.TankRepository;
import io.rookware.reefTMSCore.util.DateServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor @Slf4j
@RequestMapping(path = "/livestock")
public class LivestockController {

    private final LivestockRepository livestockRepository;
    private final TankRepository tankRepository;

    @GetMapping("/all/{tankId}")
    public ResponseEntity<Object> getAllLivestockInTank(@PathVariable String tankId) throws JsonProcessingException {
        Optional<Tank> optionalTank = tankRepository.findById(UUID.fromString(tankId));
        if (optionalTank.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find tank");
        Tank tank = optionalTank.get();
        Set<LivestockDTO> dtos = new HashSet<>();
        livestockRepository.findAllByTank(tank).forEach((livestock) -> {
            dtos.add(livestock.generateLivestockDTO());
        });
        return ResponseEntity.status(HttpStatus.FOUND).body(dtos);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createLivestock(@RequestBody NewLivestockDTO dto) {

            Optional<Tank> optionalTank = tankRepository.findById(UUID.fromString(dto.getTank()));
            if (optionalTank.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find tank");

            Date dateAdded = DateServices.parseStringToDate(dto.getDateAddedToTank());

            Livestock livestock = new Livestock();
            livestock.setName(dto.getName());
            livestock.setCategory(LivestockCategory.valueOf(dto.getCategory()));
            livestock.setSpecies(dto.getSpecies());
            livestock.setDateAddedToTank(dateAdded);
            livestock.setTank(optionalTank.get());
            livestockRepository.save(livestock);
            return ResponseEntity.status(HttpStatus.CREATED).body("Livestock added to tank");

    }
}
