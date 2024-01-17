package com.interqu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.interqu.db.RoleRepository;
import com.interqu.roles.Role;
import com.interqu.user.PendingUser;

@Controller
@RequestMapping("/admin/")
public class AdminController {
    
    @Autowired 
    private RoleRepository roleRepo;

	@GetMapping("/manualAdd")
	public ModelAndView manualAddUser(){
		 ModelAndView mvc = new ModelAndView("manual-user-creation");
         mvc.addObject("pendingUser", new PendingUser());
         List<Role> roles = roleRepo.findAll();
         mvc.addObject("roles", roles);
         return mvc;
	}

}
