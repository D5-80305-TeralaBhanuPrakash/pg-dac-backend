package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ManagerDTO;
import com.app.service.ManagerService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="http://localhost:3000")
@Validated
public class ManagerController {
	
	@Autowired
	private ManagerService managService;
	
	@GetMapping("/{managId}")
	public ManagerDTO getManagerDetails(@PathVariable Integer managId){
		return managService.getManagerDetails(managId);
	}
	
	@PostMapping("/login")
	public ManagerDTO loginManager(@RequestParam String email, @RequestParam String password) {
		return managService.loginManager(email,password);
	}
	
	@PostMapping
	public ManagerDTO addManager(@RequestBody ManagerDTO managDto) {
		return managService.addManager(managDto);
	}
	
	
}
