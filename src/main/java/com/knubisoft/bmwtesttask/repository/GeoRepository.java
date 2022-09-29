package com.knubisoft.bmwtesttask.repository;

import com.knubisoft.bmwtesttask.db_model.Geo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoRepository extends JpaRepository<Geo, Long> {
}
