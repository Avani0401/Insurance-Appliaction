package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.entity.Role;
import com.insurance.service.RoleService;

// TODO: Auto-generated Javadoc
/**
 * The Class RoleController.
 */
@RestController
public class RoleController {

    /** The role service. */
    @Autowired
    private RoleService roleService;


}
