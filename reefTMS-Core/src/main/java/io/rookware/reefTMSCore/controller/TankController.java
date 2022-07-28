package io.rookware.reefTMSCore.controller;

import io.rookware.reefTMSCore.dto.NewTankDTO;
import io.rookware.reefTMSCore.dto.TankDTO;
import io.rookware.reefTMSCore.model.AppUser;
import io.rookware.reefTMSCore.model.CapacityType;
import io.rookware.reefTMSCore.model.Tank;
import io.rookware.reefTMSCore.repository.AppUserRepository;
import io.rookware.reefTMSCore.repository.TankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/tank")
public class TankController {

    private final TankRepository tankRepository;
    private final AppUserRepository appUserRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTank(@PathVariable("id") String id) {
        Optional<Tank> optional = tankRepository.findById(UUID.fromString(id));
        if (optional.isPresent()) {
            TankDTO tank = optional.get().generateTankDTO();
            return ResponseEntity.status(HttpStatus.OK).body(tank);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No tank with that id");
    }

    @PostMapping("/new")
    @Transactional
    public ResponseEntity<TankDTO> createTank(@RequestBody NewTankDTO newTankDTO, @RequestAttribute("userId") String user) {
        AppUser appUser = appUserRepository.findById(UUID.fromString(user)).get();
        Tank tank = new Tank();
        tank.setName(newTankDTO.getName());
        tank.setCapacity(newTankDTO.getCapacity());
        tank.setCapacityType(CapacityType.valueOf(newTankDTO.getCapacityType()));
        tank.setDesiredSalinity(newTankDTO.getDesiredSalinity());
        tank.setDesiredNitrate(newTankDTO.getDesiredNitrate());
        tank.setDesiredCalcium(newTankDTO.getDesiredCalcium());
        tank.setDesiredAlkalinity(newTankDTO.getDesiredAlkalinity());
        tank.setDesiredMagnesium(newTankDTO.getDesiredMagnesium());
        tank.setDesiredPh(newTankDTO.getDesiredPh());
        tank.setAppUser(appUser);
        tankRepository.save(tank);
        return ResponseEntity.status(HttpStatus.CREATED).body(tank.generateTankDTO());
    }
}
