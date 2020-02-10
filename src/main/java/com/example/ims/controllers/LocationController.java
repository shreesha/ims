package com.example.ims.controllers;

import com.example.ims.constants.ImsConstants;
import com.example.ims.views.LocationView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ImsConstants.ApiPaths.LOCATION_ROOT)
public class LocationController {
    @GetMapping
    public ResponseEntity getListOfLocations(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String searchParam){
        //get List of Locations
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping(ImsConstants.ApiPaths.LOCATION_ID)
    public ResponseEntity getLocationForId(@PathVariable int locationId){
        //get location for the given id

        return ResponseEntity.ok().body(new LocationView());
    }

    @PostMapping
    public ResponseEntity createLocation(@RequestBody LocationView locationVew){
        //Create a new location
        return ResponseEntity.ok().body("OK");
    }

    @PutMapping(ImsConstants.ApiPaths.LOCATION_ID)
    public ResponseEntity updateLocation(@PathVariable int locationId, @RequestBody LocationView locationVew){
        //Update location details
        return ResponseEntity.ok().body("OK");
    }

    @DeleteMapping(ImsConstants.ApiPaths.LOCATION_ID)
    public ResponseEntity deleteLocation(@PathVariable int locationId){
        //Delete a location
        return ResponseEntity.ok().body("OK");
    }

    @PutMapping(ImsConstants.ApiPaths.LOCATION_PLACE_PRODUCTS)
    public ResponseEntity placeProductsInLocation(@PathVariable int locationId, @RequestParam String productId, @RequestParam int quantity){
        //Place number of quantities of a product in a given location
        return ResponseEntity.ok().body("OK");
    }

    @PutMapping(ImsConstants.ApiPaths.LOCATION_REMOVE_PRODUCTS)
    public ResponseEntity removeProductsFromLocation(@PathVariable int locationId, @RequestParam String productId, @RequestParam int quantity){
        //Remove number of quantities of a product from the given locaiton
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping(ImsConstants.ApiPaths.LOCATION_PRODUCTS)
    public ResponseEntity getProductsForALocation(@PathVariable int locationId){
        //Get list of products in a given location
        return ResponseEntity.ok().body("OK");
    }


    @GetMapping(ImsConstants.ApiPaths.LOCATION_INDENTS)
    public ResponseEntity getIndentsForALocation(@PathVariable int locationId){
        //Get list of products in a given location
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping(ImsConstants.ApiPaths.LOCATION_EMPTY)
    public ResponseEntity getEmptyLocations(){
        //Get list of products in a given location
        return ResponseEntity.ok().body("OK");
    }
}
