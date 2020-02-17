package com.example.ims.models;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IndentRepository extends JpaRepository<Indent, Integer> {

    @Query( "SELECT i FROM indent i ")
    Page<Indent> getIndentListPaginated(Pageable pageable);

    @Query("SELECT i FROM indent i WHERE i.id LIKE :id")
    Indent getIndentForId(@Param("id") int id);
}
