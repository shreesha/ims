package com.example.ims.controllers;

import com.example.ims.constants.ImsConstants;
import com.example.ims.views.EnterpriceView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ImsConstants.ApiPaths.ENTERPRISE_ROOT)
public class EnterpriceController {

    @GetMapping
    public ResponseEntity getListOfEnterprices(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String searchParam){
        //get List of Enterprices
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping(ImsConstants.ApiPaths.ENTERPRICE_ID)
    public ResponseEntity getEnterpriceForId(@PathVariable int enterpriceId){
        //get Enterprice for the given id

        return ResponseEntity.ok().body(new EnterpriceView());
    }

    @PostMapping
    public ResponseEntity createEnterprice(@RequestBody EnterpriceView enterpriceView){
        //Create a new Enterprice
        return ResponseEntity.ok().body("OK");
    }

    @PutMapping(ImsConstants.ApiPaths.ENTERPRICE_ID)
    public ResponseEntity updateEnterprice(@PathVariable int enterpriceId, @RequestBody EnterpriceView enterpriceView){
        //Create a new Enterprice
        return ResponseEntity.ok().body("OK");
    }

    @DeleteMapping(ImsConstants.ApiPaths.ENTERPRICE_ID)
    public ResponseEntity deleteEnterprice(@PathVariable int enterpriceId){
        //Create a new Enterprice
        return ResponseEntity.ok().body("OK");
    }
}
