package com.knubisoft.bmwtesttask.repository;

import com.knubisoft.bmwtesttask.dto.Geo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoRepository extends JpaRepository<Geo, Long> {
}
