package com.example.ims.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationProductsRepository extends JpaRepository<LocationProducts, Integer> {

   @Query("SELECT lp FROM location_products lp WHERE lp.product.id = ?2 AND lp.location.id = ?1")
   LocationProducts getLocationProductForIds( int locationId,  int productId);


   @Query("SELECT ind FROM location_products lp LEFT JOIN indent ind WHERE lp.location.id = ?1")
   List<Indent> getIndentsForLocation(int location_id);
}
