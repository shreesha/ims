package com.example.ims.models;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnterpriceRepository extends JpaRepository<Enterprice, Integer> {
    @Query( "SELECT e FROM enterprise e ")
    Page<Enterprice> getEnterpriceListPaginated(Pageable pageable);

    @Query("SELECT e FROM enterprise e WHERE e.id LIKE :id")
    Enterprice getEnterpriceForId(@Param("id") int id);
}
