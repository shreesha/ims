package com.example.ims.controllers;

import com.example.ims.constants.ImsConstants;
import com.example.ims.models.User;
import com.example.ims.services.ProductService;
import com.example.ims.views.ProductView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(ImsConstants.ApiPaths.PRODUCT_ROOT)
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity getListOfProducts(@RequestParam(required=false) Integer pageNo,
                                            @RequestParam(required=false) Integer pageSize,
                                            @RequestParam(required = false) String searchParam){
        //get List of Products
        List<ProductView> productViewList = productService.getListOfProducts(1, 1, 10);
        return ResponseEntity.ok().body(productViewList);
    }

    @GetMapping(ImsConstants.ApiPaths.PRODUCT_ID)
    public ResponseEntity getProductForId(@PathVariable int productId){
        //get product for the given id

        return ResponseEntity.ok().body(new ProductView());
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductView productView){
        //Create a new Product

        productService.createProduct(productView,1);
        return ResponseEntity.ok().body("OK");
    }

    @PutMapping(ImsConstants.ApiPaths.PRODUCT_ID)
    public ResponseEntity updateProduct(@PathVariable int productId, @RequestBody ProductView productView){
        //Create a new Product
        return ResponseEntity.ok().body("OK");
    }

    @DeleteMapping(ImsConstants.ApiPaths.PRODUCT_ID)
    public ResponseEntity deleteProduct(@PathVariable int productId){
        //Create a new Product
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping(ImsConstants.ApiPaths.PRODUCT_LOCATIONS)
    public ResponseEntity getLocationsForAProduct(@PathVariable int productId){
        //get Listo f locations for the given product id

        return ResponseEntity.ok().body(new ProductView());
    }


    @GetMapping(ImsConstants.ApiPaths.PRODUCT_LOWSTOCK)
    public ResponseEntity getListOfLowStockProducts(@RequestParam int pageNo, @RequestParam int pageSize){
        //Dashboard API
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping(ImsConstants.ApiPaths.PRODUCT_SLOWMOVING)
    public ResponseEntity getListOfSlowMovingProducts(@RequestParam int pageNo, @RequestParam int pageSize,
    @RequestParam Date fromDate, @RequestParam Date toDate){
        //Dashboard API
        return ResponseEntity.ok().body("OK");
    }


    @GetMapping(ImsConstants.ApiPaths.PRODUCT_FASTMOVING)
    public ResponseEntity getListOfFastMovingProducts(@RequestParam int pageNo, @RequestParam int pageSize,
                                                      @RequestParam Date fromDate, @RequestParam Date toDate){
        //Dashboard API
        return ResponseEntity.ok().body("OK");
    }

}
