package com.example.ims.services;

import com.example.ims.models.*;
import com.example.ims.views.ProductView;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    EnterpriceRepository enterpriceRepository;

    @Autowired
    UserRepository userRepository;
    public void createProduct(ProductView productView, int userId) {
        Product product = new Product();

        product.setCategory(productView.getCategory());
        product.setCreated_date(new Date());
//        Optional optional = userRepository.findById( Integer.valueOf( userId));
//        if(optional.ifPresent()){
//            product.setCreatedUser((User) optional.get());
//        }else{
//            throw new InvalidPropertyException(User.class, "User","Created User for the given Id does not exist");
//        }
        product.setCreatedUser((userRepository.getOne(userId)));
        product.setDescription(productView.getDescription());
        product.setHsn_code(productView.getHsnCode());
//        optional = enterpriceRepository.findById( productView.getManufacturer());
//        if(optional.isPresent()){
//            product.setManufacturer((Enterprice) optional.get());
//        }else{
//            throw new InvalidPropertyException(Enterprice.class, "Manufacturer","Manufacturer for the given Id does not exist");
//        }
        product.setManufacturer(enterpriceRepository.getOne(productView.getManufacturer()));
        product.setMeasure(productView.getMeasure());
        product.setName(productView.getName());
        product.setQuantity(productView.getQuantity());
        product.setSku(productView.getSku());
        product.setStatus(productView.getStatus());
        product.setType(productView.getType());
        product.setUnit(productView.getUnit());
        productRepository.save(product);


    }

    public List<ProductView> getListOfProducts(Integer userId, Integer pageNo, Integer size){
        List<Product> products = productRepository.findAll();
        List<ProductView> productViewList = new ArrayList<>();
        for(Product product: products){
            ProductView productView = new ProductView();
            productView.setCategory(product.getCategory());
           // productView.setCreatedBy(product.getCreatedUser().getDisplay_name());
            productView.setCreatedDate(product.getCreated_date());
            productView.setDescription(product.getDescription());
            productView.setHsnCode(product.getHsn_code());
            productView.setManufacturerName(product.getManufacturer().getName());
            productView.setMeasure(product.getMeasure());
            productView.setName(product.getName());
            productView.setSku(product.getSku());
            productView.setQuantity(product.getQuantity());
            productView.setStatus(product.getStatus());

            productViewList.add(productView);
        }
        return productViewList;
    }
}
