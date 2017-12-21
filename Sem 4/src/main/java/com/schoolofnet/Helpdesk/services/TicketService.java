package com.schoolofnet.Helpdesk.services;

import java.util.List;

import org.springframework.ui.Model;

import com.schoolofnet.Helpdesk.models.Ticket;

public interface TicketService {

	public List<Ticket> findAll();
	public Model createTemplate(Model model);
	public Ticket create(Ticket ticket);
	public Boolean delete(Long id);
	public Boolean update(Long id, Ticket ticket);
	public Ticket show(Long id);
}
