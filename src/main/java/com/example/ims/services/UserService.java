package com.example.ims.services;

import com.example.ims.models.User;
import com.example.ims.models.UserRepository;
import com.example.ims.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public UserView registerUser(UserView userView, int userid){
        User user = new User();
        user.set_active(userView.isActive());
        user.setContact_number(userView.getContactNumber());
        user.setCreated_date(new Date());
        User createdBy = userRepository.getOne(userid);
        user.setCreatedUser(createdBy);
        user.setDisplay_name(userView.getDisplayName());
        user.setEmail(userView.getEmail());
        user.setPassword(userView.getPassword());
        user.setName(userView.getName());
        user.setRole(userView.getRole());

        userRepository.save(user);
        userView.setUserid(user.getUserid());
        return userView;
    }


    UserView updateUser(UserView userView, int userid){
        User user = userRepository.getOne(userView.getUserid());
        user.set_active(userView.isActive());
        user.setContact_number(userView.getContactNumber());
        user.setDisplay_name(userView.getDisplayName());
        user.setEmail(userView.getEmail());
        user.setPassword(userView.getPassword());
        user.setName(userView.getName());
        user.setRole(userView.getRole());
        user.setModified_date(new Date());
        user.setModifiedUser(userRepository.getOne(userid));
        userRepository.save(user);
        return userView;
    }
    UserView getUserById(int userid){
        User user = userRepository.getOne(userid);
        UserView userView = new UserView();
        userView.setActive(user.is_active());
        userView.setContactNumber(user.getContact_number());
        userView.setCreatedBy(user.getCreatedUser().getDisplay_name());
        userView.setCreatedDate(user.getCreated_date());
        userView.setDisplayName(user.getDisplay_name());
        userView.setEmail(user.getEmail());
        userView.setModifiedBy(user.getModifiedUser().getDisplay_name());
        userView.setModifiedDate(user.getModified_date());
        userView.setRole(user.getRole());
        userView.setUserid(user.getUserid());
        return userView;
    }

    public List<UserView> getAllUsers(int pageNo, int size, String searchparam){
        //ToDo apply pagination and searchparam filtering
        List<User> userList = userRepository.findAll();
        List<UserView> userViewList = new ArrayList<>();
        for(User user: userList){
            UserView userView = new UserView();
            userView.setActive(user.is_active());
            userView.setContactNumber(user.getContact_number());
//            userView.setCreatedBy(user.getCreatedUser().getDisplay_name());
            userView.setCreatedDate(user.getCreated_date());
            userView.setDisplayName(user.getDisplay_name());
            userView.setEmail(user.getEmail());
   //         userView.setModifiedBy(user.getModifiedUser().getDisplay_name());
            userView.setModifiedDate(user.getModified_date());
            userView.setRole(user.getRole());
            userView.setUserid(user.getUserid());
            userView.setDisplayName(user.getDisplay_name());
            userView.setName(user.getName());
            userViewList.add(userView);
        }
        return userViewList;
    }
}
