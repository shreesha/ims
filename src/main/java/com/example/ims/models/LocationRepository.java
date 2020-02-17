package com.example.ims.models;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query( "SELECT l FROM location l ")
    Page<Location> getLocationListPaginated(Pageable pageable);

    @Query("SELECT l FROM location l WHERE l.id LIKE :id")
    Location getLocationForId(@Param("id") int id);
}
