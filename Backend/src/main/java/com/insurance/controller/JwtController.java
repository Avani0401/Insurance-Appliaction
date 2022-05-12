package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.entity.JwtRequest;
import com.insurance.entity.JwtResponse;
import com.insurance.service.JwtService;

// TODO: Auto-generated Javadoc
/**
 * The Class JwtController.
 */
@RestController
@CrossOrigin
public class JwtController {

    /** The jwt service. */
    @Autowired
    private JwtService jwtService;

    /**
     * Creates the jwt token.
     *
     * @param jwtRequest the jwt request
     * @return the jwt response
     * @throws Exception the exception
     */
    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
    	
        return jwtService.createJwtToken(jwtRequest);
    }
}
