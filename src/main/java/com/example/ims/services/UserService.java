package com.example.ims.services;

import com.example.ims.authentication.AuthenticationService;
import com.example.ims.models.Department;
import com.example.ims.models.DepartmentRepository;
import com.example.ims.models.User;
import com.example.ims.models.UserRepository;
import com.example.ims.views.DepartmentView;
import com.example.ims.views.UserView;
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
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    DepartmentRepository departmentRepository;

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public UserView registerUser(UserView userView){
        User user = new User();
        user = getUserFromUserView(user, userView,true);
        userRepository.save(user);
        userView.setUserid(user.getUserid());
        return userView;
    }

    private User getUserFromUserView(User user, UserView userView, boolean isCreate){
        user.setIs_active(userView.isActive());
        user.setContact_number(userView.getContactNumber());

        if(isCreate) {
            User createdBy = authenticationService.getLoggedInUserObject();
            user.setCreatedUser(createdBy);
            user.setCreated_date(new Date());
        }else{
            User modifiedBy = authenticationService.getLoggedInUserObject();
            user.setModifiedUser(modifiedBy);
            user.setModified_date(new Date());
        }

        user.setDisplay_name(userView.getDisplayName());
        user.setEmail(userView.getEmail());
        user.setPassword(userView.getPassword());
        user.setName(userView.getName());
        user.setRole(userView.getRole());
        Department department = departmentRepository.getOne(userView.getDepartmentId());
        if(department != null){
            user.setDepartment(department);
        }
        return user;
    }


    UserView updateUser(UserView userView, int userid){
        User user = userRepository.getOne(userView.getUserid());
       user = getUserFromUserView(user, userView, false);
        userRepository.save(user);
        return userView;
    }
    UserView getUserById(int userid){
        User user = userRepository.getOne(userid);
        UserView userView = getUserViewByUser(user);
        return userView;
    }

    UserView getUserViewByUser(User user){
        UserView userView = new UserView();
        userView.setActive(user.isIs_active());
        userView.setContactNumber(user.getContact_number());

        userView.setCreatedDate(user.getCreated_date());
        userView.setDisplayName(user.getDisplay_name());
        userView.setEmail(user.getEmail());
        User createdUser = user.getCreatedUser();
        if(createdUser != null){
            userView.setCreatedBy(createdUser.getDisplay_name());
        }
        User modifiedUser = user.getModifiedUser();
        if(modifiedUser != null){
            userView.setModifiedBy(modifiedUser.getDisplay_name());
        }
        userView.setModifiedDate(user.getModified_date());
        userView.setRole(user.getRole());
        userView.setUserid(user.getUserid());
        if(user.getDepartment() != null) {
            userView.setDepartmentId(user.getDepartment().getId());
            userView.setDepartmentName(user.getDepartment().getName());
        }

        return userView;
    }

    public List<UserView> getAllUsers(int pageNo, int size, String searchparam){

        Pageable pageable = PageRequest.of(pageNo, size);
        Page<User> userList = userRepository.findAll(pageable);
        List<UserView> userViewList = new ArrayList<>();
        for(User user: userList){
            UserView userView = getUserViewByUser(user);
            userViewList.add(userView);
        }
        return userViewList;
    }

    public DepartmentView createDepartment(DepartmentView departmentView, int i) {
        Department department = new Department();
        department.setName(departmentView.getName());
        department.setCreated_date(new Date());
        department.setCreatedUser(authenticationService.getLoggedInUserObject());
        departmentRepository.saveAndFlush(department);
        departmentView.setId(department.getId());
        return departmentView;

    }

    public List<DepartmentView> getListOfDepartment(int pageNo, int pageSize, String searchParam) {
        Pageable pageable =  PageRequest.of(pageNo, pageSize);
        Page<Department> departmentList = departmentRepository.findAll(pageable);
        List<DepartmentView> departmentViewList = new ArrayList<>();

        departmentViewList = departmentList.stream()
                .map( department -> getDepartmentaViewFromDepartment(department))
                .collect(Collectors.toList());

        return departmentViewList;

    }

    public DepartmentView getDepartmentaViewFromDepartment(Department department){
        DepartmentView departmentView = new DepartmentView();
        departmentView.setId(department.getId());
        departmentView.setCreatedDate(department.getCreated_date());
        departmentView.setModifiedDate(department.getModified_date());
        if(department.getCreatedUser() != null) {
            departmentView.setCreatedUserId(department.getCreatedUser().getUserid());
            departmentView.setCreatedUserName(department.getCreatedUser().getDisplay_name());
        }
       if(department.getModifiedUser() != null) {
           departmentView.setModifiedUserId(department.getModifiedUser().getUserid());
           departmentView.setModifiedUserName(department.getModifiedUser().getDisplay_name());
       }
       return departmentView;
    }

    public UserView getUserForId(int userId) {
        return null;
    }
}
