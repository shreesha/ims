package com.example.ims.services;

import com.example.ims.authentication.AuthenticationService;
import com.example.ims.constants.ImsConstants;
import com.example.ims.models.*;
import com.example.ims.views.IndentLineItemView;
import com.example.ims.views.IndentView;
import com.example.ims.views.ReturnIndentView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndentService {

    @Autowired
    IndentRepository indentRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    LineItemReposiotry lineItemReposiotry;

    @Autowired
    ReturnIndentRepository returnIndentRepository;

    @Autowired
    ReturnLineItemRepository returnLineItemRepository;

    @Autowired
    LocationProductsRepository locationProductsRepository;

    @Autowired
    UserRepository userRepository;
    public IndentView createIndent(IndentView indentView) throws NotFoundException, ParseException {
        Indent indent = new Indent();
        indent = getIndentFromIndentView(indent, indentView, true);
        IndentLineItem lineItem;
        List<IndentLineItem> lineItemList = new ArrayList<>();
        indentRepository.save(indent);
        for(IndentLineItemView lineItemView: indentView.getIndentLineItemViewList()){
            lineItemView.setIndentId(indent.getId());
            lineItem = getLineItemFromLineItemView(lineItemView);
            lineItemReposiotry.save(lineItem);
            //update product count and it's location.
            manageIndentProducts(indent,lineItem);
        }
        indentView.setId(indent.getId());
        return indentView;

    }

    /**
     *
     * @param indent
     * @param lineItem
     * @throws NotFoundException
     */
    void manageIndentProducts(Indent indent, IndentLineItem lineItem) throws NotFoundException {
        //Product count management after incoming/outgoing indent
        int productQuantity = lineItem.getProduct().getQuantity();
        if(indent.getType().equals(ImsConstants.IndentTypes.INCOMING)){
            lineItem.getProduct().setQuantity(lineItem.getQuantity() + productQuantity);
            //Update the location of the incoming product
            createOrUpdateLocationDetailsOfproduct(indent, lineItem);
        }else{
            int remainingQuantity =  productQuantity -lineItem.getQuantity();
            if(remainingQuantity >= 0) {
                lineItem.getProduct().setQuantity(remainingQuantity);
            }else{
                throw new NotFoundException("Insufficient Quantity in store for the product:%s"+lineItem.getProduct().getName());
            }
        }
    }

    private void createOrUpdateLocationDetailsOfproduct(Indent indent, IndentLineItem lineItem){
//        LocationProducts locationProduct = locationProductsRepository.getLocationProductForIds(indent.getLocation().getId(),
//                lineItem.getProduct().getId());
//        if(locationProduct == null) {
//             locationProduct = new LocationProducts();
//        }
//        locationProduct.setIndent(indent);
//        locationProduct.setQuantity(lineItem.getQuantity());
//        locationProduct.setLocation(indent.getLocation());
//        locationProduct.setProduct(lineItem.getProduct());
//        locationProduct.setCreatedUser(authenticationService.getLoggedInUserObject());
//
//        locationProductsRepository.save(locationProduct);
    }

    public IndentView updateIndent(IndentView indentView, int indentId) throws NotFoundException, ParseException {
        Indent indent = indentRepository .getIndentForId(indentId);
        indent = getIndentFromIndentView(indent, indentView, false);
        indentRepository .save(indent);

        return indentView;
    }

    public List<IndentView> getListOfIndents(Integer pageNo, Integer size, String searchParam){
        Pageable paging = PageRequest.of(pageNo, size);
        Page<Indent> indents = indentRepository .getIndentListPaginated(paging);
        List<IndentView> indentViewList = new ArrayList<>();
        indentViewList = indents.stream()
                .map( indent -> getIndentViewFromIndent(indent))
                .collect(Collectors.toList());
        return indentViewList;
    }

    public IndentView getIndentForId(int indentId){
        Indent indent = indentRepository .getIndentForId(indentId);
        IndentView indentView = getIndentViewFromIndent(indent);
        return indentView;
    }


    Indent getIndentFromIndentView(Indent indent, IndentView indentView, boolean isCreate)
            throws NotFoundException, ParseException {
        Date deliveryDate=new SimpleDateFormat("dd/MM/yyyy").parse(indentView.getDeliveryDate());
        indent.setDelivery_date(deliveryDate);

        if(isCreate) {
            indent.setCreatedUser(authenticationService.getLoggedInUserObject());
            indent.setCreated_date(new Date());
        }else{
            indent.setModifiedUser(authenticationService.getLoggedInUserObject());
            indent.setModified_date(new Date());
            List<IndentLineItem> indentLineItemList = new ArrayList<>();
            for(IndentLineItemView lineItemView: indentView.getIndentLineItemViewList()){
                indentLineItemList.add( getLineItemFromLineItemView(lineItemView));
            }
            indent.setLineItemList(indentLineItemList);
        }
        if(indentView.getLocationId() != null) {
            Location location = locationRepository.getLocationForId(indentView.getLocationId());
            if(location != null) {
                indent.setLocation(location);
            }else{
                throw new NotFoundException("Location record for the given id does not exist");
            }
        }
        indent.setRemarks(indentView.getRemarks());
        indent.setStatus(indentView.getStatus());
        indent.setType(indentView.getType());
        indent.setType(indentView.getType());



        return indent;
    }

    IndentLineItemView getLineItemViewFromLineItem(IndentLineItem lineItem){
        IndentLineItemView lineItemView = new IndentLineItemView();
        lineItemView.setIndentId(lineItem.getIndent().getId());
        lineItemView.setProductId(lineItem.getProduct().getId());
        lineItemView.setQuantity(lineItem.getQuantity());
        lineItemView.setUnitPrice(Double.valueOf(lineItem.getUnit_price()));
        return lineItemView;
    }

    IndentLineItem getLineItemFromLineItemView(IndentLineItemView lineItemView)
            throws NotFoundException{
        IndentLineItem lineItem = new IndentLineItem();
        lineItem.setQuantity(lineItemView.getQuantity());
        lineItem.setUnit_price(lineItemView.getUnitPrice().floatValue());

        Indent indent = indentRepository.getIndentForId(lineItemView.getIndentId());
        if (indent != null) {
            lineItem.setIndent(indent);
        } else {
            throw new NotFoundException("Indent for the given id does not exist");
        }


        Product product = productRepository.getProductForId(lineItemView.getProductId());
        if(product!=null) {
            lineItem.setProduct(product);
        }else{
            throw new NotFoundException("Product for the given id does not exist");
        }

        return lineItem;
    }

    public IndentView getIndentViewFromIndent(Indent indent){
        IndentView indentView = new IndentView();
        List<IndentLineItem> indentLineItemList = indent.getLineItemList();

        List<IndentLineItemView> lineItemViewList =indentLineItemList.stream().
                map(lineItem-> getLineItemViewFromLineItem(lineItem)).collect(Collectors.toList());

        indentView.setIndentLineItemViewList(lineItemViewList);
        indentView.setType(indent.getType());

        User createdByUser = indent.getCreatedUser();
        if(createdByUser!=null){
            indentView.setCreatedBy(createdByUser.getDisplay_name());
        }

        User modifiedByUser = indent.getModifiedUser();
        if(modifiedByUser!=null){
            indentView.setModifiedBy(modifiedByUser.getDisplay_name());
        }

        indentView.setId(indent.getId());
        if(indent.getDelivery_date()!=null) {
            indentView.setDeliveryDate(indent.getDelivery_date().toString());
        }
        indentView.setId(indent.getId());
        if(indent.getModified_date()!=null) {
            indentView.setModifiedDate(indent.getModified_date().toString());
        }
        indentView.setCreatedDate(indent.getCreated_date());
        indentView.setRemarks(indent.getRemarks());
        indentView.setStatus(indent.getStatus());

        return indentView;
    }
    //==============================================================
    //Return Indent Service methods follow
    public List<ReturnIndentView> getListOfReturnIndents(int pageNo, int pageSize, String searchParam){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<ReturnIndent> indents = returnIndentRepository .findAll(paging);
        List<ReturnIndentView> indentViewList = new ArrayList<>();
        indentViewList = indents.stream()
                .map( indent -> getReturnIndentViewFromReturnIndent(indent))
                .collect(Collectors.toList());
        return indentViewList;
    }

    public ReturnIndentView createReturnIndent(ReturnIndentView indentView) throws NotFoundException, ParseException {
        ReturnIndent indent = new ReturnIndent();
        indent = getReturnIndentFromReturnIndentView(indent, indentView, true);
        ReturnLineItem lineItem;
        List<ReturnLineItem> lineItemList = new ArrayList<>();
        returnIndentRepository.save(indent);
        for(IndentLineItemView lineItemView: indentView.getIndentLineItemViewList()){
            lineItemView.setIndentId(indent.getId());
            lineItem = getReturnLineItemFromReturnLineItemView(lineItemView);
            returnLineItemRepository.save(lineItem);
            //createOrUpdateLocationDetailsOfproduct(indent, lineItem);
        }
        indentView.setId(indent.getId());
        return indentView;
    }

    public ReturnIndentView getReturnIndentForId(int indentId){
        ReturnIndent indent = returnIndentRepository .getOne(indentId);
        ReturnIndentView indentView = getReturnIndentViewFromReturnIndent(indent);
        return indentView;
    }

    ReturnIndent getReturnIndentFromReturnIndentView(ReturnIndent indent, ReturnIndentView indentView, boolean isCreate)
            throws NotFoundException, ParseException {
        Date deliveryDate=new SimpleDateFormat("dd/MM/yyyy").parse(indentView.getDeliveryDate());
        indent.setDelivery_date(deliveryDate);

        if(isCreate) {
            indent.setCreatedUser(authenticationService.getLoggedInUserObject());
            indent.setCreated_date(new Date());
        }

        indent.setRemarks(indentView.getRemarks());
        indent.setStatus(indentView.getStatus());
        indent.setType(indentView.getType());
        indent.setType(indentView.getType());
        return indent;
    }

    IndentLineItemView getLineItemViewFromReturnLineItem(ReturnLineItem lineItem){
        IndentLineItemView lineItemView = new IndentLineItemView();
        lineItemView.setIndentId(lineItem.getIndent().getId());
        lineItemView.setProductId(lineItem.getProduct().getId());
        lineItemView.setQuantity(lineItem.getQuantity());
        return lineItemView;
    }

    ReturnLineItem getReturnLineItemFromReturnLineItemView(IndentLineItemView lineItemView)
            throws NotFoundException{
        ReturnLineItem lineItem = new ReturnLineItem();
        lineItem.setQuantity(lineItemView.getQuantity());

        ReturnIndent indent = returnIndentRepository.getOne(lineItemView.getIndentId());
        if (indent != null) {
            lineItem.setIndent(indent);
        } else {
            throw new NotFoundException("Indent for the given id does not exist");
        }


        Product product = productRepository.getProductForId(lineItemView.getProductId());
        if(product!=null) {
            lineItem.setProduct(product);
        }else{
            throw new NotFoundException("Product for the given id does not exist");
        }

        return lineItem;
    }

    public ReturnIndentView getReturnIndentViewFromReturnIndent(ReturnIndent indent){
        ReturnIndentView indentView = new ReturnIndentView();
        List<ReturnLineItem> indentLineItemList = indent.getLineItemList();

        List<IndentLineItemView> lineItemViewList =indentLineItemList.stream().
                map(lineItem-> getLineItemViewFromReturnLineItem(lineItem)).collect(Collectors.toList());

        indentView.setIndentLineItemViewList(lineItemViewList);
        indentView.setType(indent.getType());

        User createdByUser = indent.getCreatedUser();
        if(createdByUser!=null){
            indentView.setCreatedBy(createdByUser.getDisplay_name());
        }


        indentView.setId(indent.getId());
        if(indent.getDelivery_date()!=null) {
            indentView.setDeliveryDate(indent.getDelivery_date().toString());
        }
        indentView.setId(indent.getId());

        indentView.setCreatedDate(indent.getCreated_date());
        indentView.setRemarks(indent.getRemarks());
        indentView.setStatus(indent.getStatus());

        return indentView;
    }


}
