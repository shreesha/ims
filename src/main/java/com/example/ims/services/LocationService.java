package com.example.ims.services;

import com.example.ims.authentication.AuthenticationService;
import com.example.ims.models.*;
import com.example.ims.views.IndentView;
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
public class LocationService {
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    ProductService productService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    LocationProductsRepository locationProductsRepository;
    @Autowired
    IndentService indentService;

    public List<LocationView> getListOfLocations(int pageNo, int pageSize, String searchString){
        List<LocationView> locationViewList = new ArrayList<>();
       Pageable paging = PageRequest.of(pageNo, pageSize);
//        Page<Location> locations = locationRepository.findAll(paging);
        Page<Location> locations = locationRepository.getLocationListPaginated(paging);
        locationViewList = locations.stream()
                .map( location -> getLocationViewForLocation(location))
                .collect(Collectors.toList());
        return locationViewList;
    }

    public LocationView getLocationForId(int locationId){
        LocationView locationView= new LocationView();
        Location location = locationRepository.getLocationForId(Integer.valueOf(locationId));
        locationView = getLocationViewForLocation(location);
        return locationView;
    }

    public LocationView createLocation(LocationView locationView){

        Location location = new Location();
        location = getLocationForLocationView(locationView, location, true);
        locationRepository.save(location);
        locationView.setId(location.getId());
        return locationView;
    }

    public LocationView updateLocation(LocationView locationView, int locationId){
        Location location = locationRepository.getLocationForId(locationId);
        location = getLocationForLocationView(locationView, location, false);
        locationRepository.save(location);
        return locationView;
    }

    public void softDeleteLocation(int locationId){
        Location location = locationRepository.getLocationForId(locationId);
        location.set_active(false);
        locationRepository.save(location);


    }

    public List<ProductView> getProductsForLocation(int locationId){
        List<Product> products = new ArrayList<>();
        List<ProductView> productViewList = new ArrayList<>();
        Location location = locationRepository.getLocationForId(locationId);
        products = location.getProducts();
        for(Product product: products){
            productViewList.add(productService.getProductViewFromProduct(product));
        }
        return productViewList;
    }

    LocationView getLocationViewForLocation(Location location){
        LocationView locationView = new LocationView();
        locationView.setDescription(location.getDescription());
        locationView.setType(location.getType());
        locationView.setName(location.getName());
        locationView.setId(location.getId());
        locationView.setActive(location.is_active());
        User modifiedUser = location.getModifiedUser();
        if(modifiedUser!= null){
            locationView.setModifiedBy(modifiedUser.getDisplay_name());
        }
        User createdUser = location.getCreatedUser();
        if(createdUser != null){
            locationView.setCreatedBy(createdUser.getDisplay_name());
        }
        locationView.setCreatedDate(location.getCreated_date());
        locationView.setModifiedDate(location.getCreated_date());

        return locationView;
    }

    Location getLocationForLocationView(LocationView locationView, Location location, boolean isCreate){

        if(isCreate){
            location.setCreated_date(new Date());
            location.setCreatedUser(authenticationService.getLoggedInUserObject());
            location.set_active(true);
        }else{
            location.setModified_date(new Date());
            location.setModifiedUser(authenticationService.getLoggedInUserObject());
            location.set_active(locationView.isActive());
        }
        location.setDescription(locationView.getDescription());
        location.setName(locationView.getName());
        location.setType(locationView.getType());

        return location;
    }

    public List<IndentView> getIndentsForALocation(int locationId) {
       List<Indent> indentList = locationProductsRepository.getIndentsForLocation(locationId);
       List<IndentView> indentViews = new ArrayList<>();
       for(Indent indent: indentList){
           IndentView indentView = new IndentView();
           indentView = indentService.getIndentViewFromIndent(indent);
           indentViews.add(indentView);
       }
       return   indentViews;
    }

    public void placeProducts(int locationId, String productId, int quantity) {
    }

    public void removeProductsFromLocation(int locationId, String productId, int quantity) {
    }

    public List<LocationView> getEmptyLocations() {
        return null;
    }
}
