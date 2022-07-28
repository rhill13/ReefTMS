package io.rookware.reefTMSCore.controller;

import io.rookware.reefTMSCore.dto.AppUserDTO;
import io.rookware.reefTMSCore.dto.LoginDTO;
import io.rookware.reefTMSCore.dto.NewAppUserDTO;
import io.rookware.reefTMSCore.model.AppUser;
import io.rookware.reefTMSCore.repository.AppUserRepository;
import io.rookware.reefTMSCore.util.JwtServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appuser")
public class AppUserController {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final JwtServices jwtServices;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserInfo(@PathVariable("id") String id) {
        Optional<AppUser> optional = appUserRepository.findById(UUID.fromString(id));
        if (optional.isPresent()) {
            AppUserDTO dto = optional.get().generateAppUserDTO();
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find user with id of: " + id);
    }

    @PostMapping("/new")
    public ResponseEntity<AppUserDTO> createNewAppUser(@RequestBody NewAppUserDTO newAppUserDTO) {
        AppUser appUser = new AppUser();
        appUser.setEmail(newAppUserDTO.getEmail());
        appUser.setFirstName(newAppUserDTO.getFirstName());
        appUser.setLastName(newAppUserDTO.getLastName());
        appUser.setPassword(hashPassword(newAppUserDTO.getPassword()));
        appUserRepository.save(appUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(appUser.generateAppUserDTO());
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginAppUser(@RequestBody LoginDTO loginDTO) {
        Optional<AppUser> optional = appUserRepository.findByEmail(loginDTO.getEmail());
        if (optional.isPresent()) {
            if (bCryptPasswordEncoder.matches(loginDTO.getPassword(), optional.get().getPassword())) {
                AppUserDTO dto = optional.get().generateAppUserDTO();
                String jws = jwtServices.createToken(dto.getId().toString(), dto.convertToMap());
                return ResponseEntity.status(HttpStatus.OK).body(jws);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email or password incorrect.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email or password incorrect.");
    }

    private String hashPassword(String plainPassword) {
        return bCryptPasswordEncoder.encode(plainPassword);
    }

}
