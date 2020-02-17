package com.example.ims.controllers;

import com.example.ims.constants.ImsConstants;
import com.example.ims.services.LocationService;
import com.example.ims.views.IndentView;
import com.example.ims.views.LocationView;
import com.example.ims.views.ProductView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ImsConstants.ApiPaths.LOCATION_ROOT)
public class LocationController {
    @Autowired
    LocationService locationService;
    @GetMapping
    public ResponseEntity getListOfLocations(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String searchParam){
        //get List of Locations
        List<LocationView> locationViewList;
        try {
           locationViewList = locationService.getListOfLocations(pageNo, pageSize, searchParam);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(locationViewList);
    }

    @GetMapping(ImsConstants.ApiPaths.LOCATION_ID)
    public ResponseEntity getLocationForId(@PathVariable int locationId){
        //get location for the given id
        LocationView locationView;
        try{
            locationView = locationService.getLocationForId(locationId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(locationView);
    }

    @PostMapping
    public ResponseEntity createLocation(@RequestBody LocationView locationVew){
        //Create a new location
        try{
            locationService.createLocation(locationVew);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(locationVew);
    }

    @PutMapping(ImsConstants.ApiPaths.LOCATION_ID)
    public ResponseEntity updateLocation(@PathVariable int locationId, @RequestBody LocationView locationVew){
        //Update location details
        try{
            locationService.updateLocation(locationVew, locationId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(locationVew);
    }

    @DeleteMapping(ImsConstants.ApiPaths.LOCATION_ID)
    public ResponseEntity deleteLocation(@PathVariable int locationId){
        //Delete a location
        try{
            locationService.softDeleteLocation(locationId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(locationId);
    }

    @PutMapping(ImsConstants.ApiPaths.LOCATION_PLACE_PRODUCTS)
    public ResponseEntity placeProductsInLocation(@PathVariable int locationId, @RequestParam String productId, @RequestParam int quantity){
        //Place number of quantities of a product in a given location
        try {
            locationService.placeProducts(locationId, productId, quantity);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body("OK");
    }

    @PutMapping(ImsConstants.ApiPaths.LOCATION_REMOVE_PRODUCTS)
    public ResponseEntity removeProductsFromLocation(@PathVariable int locationId, @RequestParam String productId, @RequestParam int quantity){
        //Remove number of quantities of a product from the given locaiton
        try {
            locationService.removeProductsFromLocation(locationId, productId, quantity);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping(ImsConstants.ApiPaths.LOCATION_PRODUCTS)
    public ResponseEntity getProductsForALocation(@PathVariable int locationId){
        //Get list of products in a given location
        List<ProductView> productViewList;
        try{
            productViewList = locationService.getProductsForLocation(locationId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(productViewList);
    }


    @GetMapping(ImsConstants.ApiPaths.LOCATION_INDENTS)
    public ResponseEntity getIndentsForALocation(@PathVariable int locationId){
        //Get list of products in a given location
        List<IndentView> indentViewList;
        try {
            indentViewList = locationService.getIndentsForALocation(locationId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(indentViewList);
    }

    @GetMapping(ImsConstants.ApiPaths.LOCATION_EMPTY)
    public ResponseEntity getEmptyLocations(){
        //Get list of products in a given location
        List<LocationView> locationViewList;
        try {
            locationViewList = locationService.getEmptyLocations();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(locationViewList);

    }
}
