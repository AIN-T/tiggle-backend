package com.gamja.tiggle.program.adapter.out.persistence;


import com.gamja.tiggle.program.adapter.out.persistence.Entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaLocationRepository extends JpaRepository<LocationEntity, Long> {
    Boolean existsByLocationName(String locationName);

    List<LocationEntity> findAllById (Long id);
}
