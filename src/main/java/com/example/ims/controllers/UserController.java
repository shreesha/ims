package com.example.ims.controllers;

import com.example.ims.authentication.AppUserDetailService;
import com.example.ims.authentication.JwtUtils;
import com.example.ims.authentication.TokenResponse;
import com.example.ims.constants.ImsConstants;

import com.example.ims.services.UserService;
import com.example.ims.views.AuthUser;
import com.example.ims.views.DepartmentView;
import com.example.ims.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(ImsConstants.ApiPaths.USER_ROOT)
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AppUserDetailService appUserDetailService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity getListOfUsers(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String searchParam){
        //get List of Users
        List<UserView> userList;
        try {
            userList = userService.getAllUsers(pageNo,pageSize,searchParam);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(userList);
    }

    @GetMapping(ImsConstants.ApiPaths.USER_ID)
    public ResponseEntity getUserForId(@PathVariable int userId){
        //get User for the given id
        UserView user;
        try {
            user = userService.getUserForId(userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserView userView){
        //Create a new User
        UserView user;
        try {
            user = userService.registerUser(userView);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(user);
    }

    @PutMapping(ImsConstants.ApiPaths.USER_ID)
    public ResponseEntity updateUser(@PathVariable int userId, @RequestBody UserView userView){
        //Create a new User
        return ResponseEntity.ok().body("OK");
    }

    @DeleteMapping(ImsConstants.ApiPaths.USER_ID)
    public ResponseEntity deleteUser(@PathVariable int userId){
        //Create a new User
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping(ImsConstants.ApiPaths.LOGIN)
    public ResponseEntity login(@RequestBody AuthUser user) throws Exception {
        //validate user and generate token

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect userid or password", e);
        }
        final UserDetails userDetails = appUserDetailService
                .loadUserByUsername(user.getUsername());

        final String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new TokenResponse(jwt));
    }

    @PostMapping(ImsConstants.ApiPaths.CHANGE_PASSWORD)
    public ResponseEntity changePassword(@RequestBody UserView UserView){
        //Create a new User
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping(ImsConstants.ApiPaths.DEPARTMENTS)
    public ResponseEntity createDepartment(@RequestBody DepartmentView departmentView){
        //Create a new User
        DepartmentView department;
        try {
            department = userService.createDepartment(departmentView, 1);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(department);
    }

    @GetMapping(ImsConstants.ApiPaths.DEPARTMENTS)
    public ResponseEntity getListOfDepartments(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String searchParam){
        //get List of Dep
        List<DepartmentView> departmentList;
        try {
             departmentList = userService.getListOfDepartment(pageNo, pageSize, searchParam);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(departmentList);
    }


}
