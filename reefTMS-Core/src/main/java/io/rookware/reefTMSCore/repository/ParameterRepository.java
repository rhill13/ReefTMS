package io.rookware.reefTMSCore.repository;

import io.rookware.reefTMSCore.model.Parameter;
import io.rookware.reefTMSCore.model.Tank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, UUID> {

    List<Parameter> findAllByTank(Tank tank);
}
