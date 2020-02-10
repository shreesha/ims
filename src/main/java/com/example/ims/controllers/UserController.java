package com.example.ims.controllers;

import com.example.ims.authentication.AppUserDetailService;
import com.example.ims.authentication.JwtUtils;
import com.example.ims.authentication.TokenResponse;
import com.example.ims.constants.ImsConstants;

import com.example.ims.services.UserService;
import com.example.ims.views.AuthUser;
import com.example.ims.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
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

        return ResponseEntity.ok().body(userService.getAllUsers(1,10,""));
    }

    @GetMapping(ImsConstants.ApiPaths.USER_ID)
    public ResponseEntity getUserForId(@PathVariable int userId){
        //get User for the given id

        return ResponseEntity.ok().body(new UserView());
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserView userView){
        //Create a new User
        UserView user = userService.registerUser(userView, 1);
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

        //  If we reached this far, we can ask the JwtUtils to provide us the token
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


}
