package io.rookware.reefTMSCore.repository;

import io.rookware.reefTMSCore.model.Tank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TankRepository extends JpaRepository<Tank, UUID> {

}
