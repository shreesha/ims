package com.example.ims.controllers;

import com.example.ims.constants.ImsConstants;
import com.example.ims.services.EnterpriceService;
import com.example.ims.views.EnterpriceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ImsConstants.ApiPaths.ENTERPRISE_ROOT)
public class EnterpriceController {
    @Autowired
    EnterpriceService enterpriceService;

    @GetMapping
    public ResponseEntity getListOfEnterprices(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String searchParam){
        //get List of Enterprices
        List<EnterpriceView> enterpriceViewList;
        try{
            enterpriceViewList = enterpriceService.getListOfEnterprices(pageNo, pageSize,searchParam);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(enterpriceViewList);
    }

    @GetMapping(ImsConstants.ApiPaths.ENTERPRICE_ID)
    public ResponseEntity getEnterpriceForId(@PathVariable int enterpriceId){
        //get Enterprice for the given id
        EnterpriceView enterpriceView;
        try{
            enterpriceView = enterpriceService.getEnterpriceForId(enterpriceId);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(enterpriceView);
    }

    @PostMapping
    public ResponseEntity createEnterprice(@RequestBody EnterpriceView enterpriceView){
        //Create a new Enterprice
        EnterpriceView enterprice;
        try{
            enterprice = enterpriceService.createEnterprice(enterpriceView);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(enterprice);
    }

    @PutMapping(ImsConstants.ApiPaths.ENTERPRICE_ID)
    public ResponseEntity updateEnterprice(@PathVariable int enterpriceId, @RequestBody EnterpriceView enterpriceView){
        //Create a new Enterprice
        EnterpriceView enterprice;
        try{
            enterprice = enterpriceService.updateEnterprice(enterpriceView, enterpriceId);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(enterprice);
    }

    @DeleteMapping(ImsConstants.ApiPaths.ENTERPRICE_ID)
    public ResponseEntity deleteEnterprice(@PathVariable int enterpriceId){
        //Create a new Enterprice
        try{

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body("OK");
    }
}
