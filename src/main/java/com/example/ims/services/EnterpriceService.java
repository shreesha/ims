package com.example.ims.services;

import com.example.ims.authentication.AuthenticationService;
import com.example.ims.constants.ImsConstants;
import com.example.ims.models.*;
import com.example.ims.views.LocationView;
import com.example.ims.views.EnterpriceView;
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
public class EnterpriceService {

    @Autowired
    EnterpriceRepository enterpriceRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserRepository userRepository;
    public EnterpriceView createEnterprice(EnterpriceView enterpriceView) {
        Enterprice enterprice = new Enterprice();
        enterprice = getEnterpriceFromEnterpriceView(enterprice, enterpriceView, true);
        enterpriceRepository.save(enterprice);
        enterpriceView.setId(enterprice.getId());
        return enterpriceView;

    }

    public EnterpriceView updateEnterprice(EnterpriceView enterpriceView, int enterpriceId){
        Enterprice enterprice = enterpriceRepository .getEnterpriceForId(enterpriceId);
        enterprice = getEnterpriceFromEnterpriceView(enterprice, enterpriceView, false);
        enterpriceRepository .save(enterprice);

        return enterpriceView;
    }

    public void softDeleteEnterprice(int enterpriceId){
        Enterprice enterprice = enterpriceRepository .getEnterpriceForId(enterpriceId);
        //enterprice.setStatus(ImsConstants.EnterpriceStatus.DELETED.toString());
        //enterprice.set
        enterpriceRepository .save(enterprice);

    }

    public List<EnterpriceView> getListOfEnterprices(Integer pageNo, Integer size, String searchParam){
        Pageable paging = PageRequest.of(pageNo, size);
        Page<Enterprice> enterprices = enterpriceRepository .getEnterpriceListPaginated(paging);
        List<EnterpriceView> enterpriceViewList = new ArrayList<>();
        enterpriceViewList = enterprices.stream()
                .map( enterprice -> getEnterpriceViewFromEnterprice(enterprice))
                .collect(Collectors.toList());
        return enterpriceViewList;
    }

    Enterprice getEnterpriceFromEnterpriceView(Enterprice enterprice, EnterpriceView enterpriceView, boolean isCreate){
         enterprice.setName(enterpriceView.getName());

        if(isCreate) {
            enterprice.setCreatedUser(authenticationService.getLoggedInUserObject());
            enterprice.setCreated_date(new Date());
        }else{
            enterprice.setModifiedUser(authenticationService.getLoggedInUserObject());
            enterprice.setModified_date(new Date());
        }
        enterprice.setAddress(enterpriceView.getAddress());
        enterprice.setContact_number(enterpriceView.getContactNumber());
        enterprice.setGst(enterpriceView.getGst());
        enterprice.setPan(enterpriceView.getPan());
        enterprice.setType(enterpriceView.getType());


        return enterprice;
    }

    public EnterpriceView getEnterpriceViewFromEnterprice(Enterprice enterprice){
        EnterpriceView enterpriceView = new EnterpriceView();
        enterpriceView.setAddress(enterprice.getAddress());
        enterpriceView.setType(enterprice.getType());

        User createdByUser = enterprice.getCreatedUser();
        if(createdByUser!=null){
            enterpriceView.setCreatedBy(createdByUser.getDisplay_name());
        }

        User modifiedByUser = enterprice.getModifiedUser();
        if(modifiedByUser!=null){
            enterpriceView.setModifiedBy(modifiedByUser.getDisplay_name());
        }

        enterpriceView.setContactNumber(enterprice.getContact_number());
        enterpriceView.setGst(enterprice.getGst());
        enterpriceView.setId(enterprice.getId());
        if(enterprice.getModified_date()!=null) {
            enterpriceView.setModifiedDate(enterprice.getModified_date().toString());
        }
        if(enterprice.getCreated_date()!=null) {
            enterpriceView.setCreatedBy(enterprice.getCreated_date().toString());
        }
        enterpriceView.setName(enterprice.getName());
        enterpriceView.setPan(enterprice.getPan());

        return enterpriceView;
    }

    public EnterpriceView getEnterpriceForId(int enterpriceId){
        Enterprice enterprice = enterpriceRepository .getEnterpriceForId(enterpriceId);
        EnterpriceView enterpriceView = getEnterpriceViewFromEnterprice(enterprice);
        return enterpriceView;
    }


}
