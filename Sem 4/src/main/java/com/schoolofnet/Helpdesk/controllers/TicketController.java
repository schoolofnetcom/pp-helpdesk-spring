package com.schoolofnet.Helpdesk.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolofnet.Helpdesk.models.Ticket;
import com.schoolofnet.Helpdesk.services.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("list", this.ticketService.findAll());
		return "ticket/index";
	} 
	
	@GetMapping("{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ticket", this.ticketService.show(id));
		return "ticket/show";
	}
	
	@GetMapping("/new")
	public String create(Model model) {
		model = this.ticketService.createTemplate(model);
		return "ticket/create";
	}
	
	@PostMapping
	public String save(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "ticket/create";
		}
		
		this.ticketService.create(ticket);
		
		return "redirect:/tickets";
	}
}
