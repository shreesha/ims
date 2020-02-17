package com.example.ims.models;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, Integer>,
        PagingAndSortingRepository<Product, Integer> {

    @Query( "SELECT p FROM product p ")
    Page<Product> getProductListPaginated(Pageable pageable);

    @Query("SELECT p FROM product p WHERE p.id LIKE :id")
    Product getProductForId(@Param("id") int id);

}
