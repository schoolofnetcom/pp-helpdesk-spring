package com.schoolofnet.Helpdesk.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolofnet.Helpdesk.models.Role;
import com.schoolofnet.Helpdesk.services.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("list", this.roleService.findAll());
		
		return "roles/index";
	}
	
	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("role", new Role());
		return "roles/create";		
	}

	@PostMapping
	public String save(@Valid @ModelAttribute("role") Role role, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "roles/create";
		}
		
		Role roleCreated = this.roleService.create(role);
		
		return "redirect:/roles";
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		this.roleService.delete(id);
		
		return "redirect:/roles";		
	}
}
