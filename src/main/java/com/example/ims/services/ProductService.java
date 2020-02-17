package com.example.ims.services;

import com.example.ims.authentication.AuthenticationService;
import com.example.ims.constants.ImsConstants;
import com.example.ims.models.*;
import com.example.ims.views.LocationView;
import com.example.ims.views.ProductView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    EnterpriceRepository enterpriceRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserRepository userRepository;
    public ProductView createProduct(ProductView productView) {
        Product product = new Product();
        product = getProductFromProductView(product, productView, true);
        productRepository.save(product);
        return productView;

    }

    public ProductView updateProduct(ProductView productView, int productId){
        Product product = productRepository.getProductForId(productId);
        product = getProductFromProductView(product, productView, false);
        productRepository.save(product);

        return productView;
    }

    public void softDeleteProduct(int productId){
        Product product = productRepository.getProductForId(productId);
        product.setStatus(ImsConstants.ProductStatus.DELETED.toString());
        productRepository.save(product);

    }

    public List<ProductView> getListOfProducts(Integer pageNo, Integer size){
        Pageable paging = PageRequest.of(pageNo, size);
        Page<Product> products = productRepository.findAll(paging);
        List<ProductView> productViewList = new ArrayList<>();
        productViewList = products.stream()
                .map( product -> getProductViewFromProduct(product))
                .collect(Collectors.toList());
        return productViewList;
    }

    Product getProductFromProductView(Product product, ProductView productView, boolean isCreate){
        product.setCategory(productView.getCategory());
        product.setCreated_date(new Date());
        if(isCreate) {
            product.setCreatedUser(authenticationService.getLoggedInUserObject());
        }else{
            product.setModifiedUser(authenticationService.getLoggedInUserObject());
        }
        product.setDescription(productView.getDescription());
        product.setHsn_code(productView.getHsnCode());
        Enterprice enterprice = enterpriceRepository.getOne(productView.getManufacturer());
        if(enterprice != null) {
            product.setManufacturerObj(enterprice);
        }
        product.setMeasure(productView.getMeasure());
        product.setName(productView.getName());
        product.setQuantity(productView.getQuantity());
        product.setSku(productView.getSku());
        product.setStatus(ImsConstants.ProductStatus.ACTIVE.toString());
        product.setType(productView.getType());
        product.setUnit(productView.getUnit());

        return product;
    }

    public ProductView getProductViewFromProduct(Product product){
        ProductView productView = new ProductView();
        productView.setCategory(product.getCategory());
        User createdByUser = product.getCreatedUser();
        if(createdByUser!=null){
            productView.setCreatedBy(createdByUser.getDisplay_name());
        }

        User modifiedByUser = product.getModifiedUser();
       // int userId = product.getModified_by();
        //int manufacturerId = product.getManufacturer();
        if(modifiedByUser!=null){
            productView.setModifiedBy(modifiedByUser.getDisplay_name());
        }

        productView.setCreatedDate(product.getCreated_date());
        productView.setDescription(product.getDescription());
        productView.setHsnCode(product.getHsn_code());
        if(product.getManufacturerObj()!=null) {
            productView.setManufacturerName(product.getManufacturerObj().getName());
        }
        productView.setMeasure(product.getMeasure());
        productView.setName(product.getName());
        productView.setSku(product.getSku());
        productView.setQuantity(product.getQuantity());
        productView.setStatus(product.getStatus());
        return productView;
    }

    public ProductView getProductForId(int productId){
            Product product = productRepository.getProductForId(productId);
            ProductView productView = getProductViewFromProduct(product);
            return productView;
    }

    public List<LocationView> getLocationsForAProduct(int productId){
        Product product = productRepository.getProductForId(productId);
        List<Location> locations =  product.getLocations();
        List<LocationView> locationViewList = new ArrayList<>();
        for(Location location: locations){
            LocationView locationView = new LocationView();
            locationView.setId(location.getId());
            locationView.setName(location.getName());
            locationView.setType(location.getType());
            locationView.setDescription(location.getDescription());
            locationViewList.add(locationView);
        }
        return locationViewList;
    }


}
