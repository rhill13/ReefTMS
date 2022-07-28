package io.rookware.reefTMSCore.repository;

import io.rookware.reefTMSCore.model.Livestock;
import io.rookware.reefTMSCore.model.Tank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivestockRepository extends JpaRepository<Livestock, UUID> {

    public List<Livestock> findAllByTank(Tank tank);
}
