package com.domain.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.AppUserData;
import com.domain.dto.ResponseData;
import com.domain.model.entities.AppUser;
import com.domain.services.AppUserService;

@RestController
@RequestMapping("/api/users")
public class AppUserController {
    
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<AppUser>> register(@RequestBody AppUserData appUserData) {

        ResponseData<AppUser> response = new ResponseData<>();

        AppUser appUser = modelMapper.map(appUserData, AppUser.class);

        response.setPayload(appUserService.registerAppUser(appUser));
        response.setStatus(true);
        response.getMessages().add("AppUser saved !");

        return ResponseEntity.ok(response);
    }

}
