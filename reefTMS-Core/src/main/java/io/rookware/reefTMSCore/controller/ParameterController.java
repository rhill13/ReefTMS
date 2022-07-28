package io.rookware.reefTMSCore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.rookware.reefTMSCore.dto.NewParameterDTO;
import io.rookware.reefTMSCore.dto.ParameterDTO;
import io.rookware.reefTMSCore.model.Parameter;
import io.rookware.reefTMSCore.model.ParameterType;
import io.rookware.reefTMSCore.model.Tank;
import io.rookware.reefTMSCore.repository.ParameterRepository;
import io.rookware.reefTMSCore.repository.TankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/parameter")
public class ParameterController {

    private final ParameterRepository parameterRepository;
    private final TankRepository tankRepository;

    @PostMapping("/add")
    public ResponseEntity<String> createParameter(@RequestBody NewParameterDTO newParameterDTO) {
        Optional<Tank> optionalTank = tankRepository.findById(UUID.fromString(newParameterDTO.getTank()));
        if (!optionalTank.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tank ID invalid");
        Parameter parameter = new Parameter();
        parameter.setDateRecorded(Date.from(Instant.now()));
        parameter.setType(ParameterType.valueOf(newParameterDTO.getType()));
        parameter.setValue(newParameterDTO.getValue());
        parameter.setTank(optionalTank.get());
        parameterRepository.save(parameter);
        return ResponseEntity.status(HttpStatus.CREATED).body("Parameter created");
    }

    @GetMapping("/all/{tank_id}")
    public ResponseEntity<Object> getAllParameters(@PathVariable("tank_id") String tankId) throws JsonProcessingException {
        System.out.println(tankId);
        Optional<Tank> optionalTank = tankRepository.findById(UUID.fromString(tankId));
        if (optionalTank.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find tank with that id");
        List<Parameter> list = parameterRepository.findAllByTank(optionalTank.get());
        Set<ParameterDTO> dtos = new HashSet<>();
        for (Parameter param : list) {
            dtos.add(param.generateParameterDTO());
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(dtos);
    }
}
