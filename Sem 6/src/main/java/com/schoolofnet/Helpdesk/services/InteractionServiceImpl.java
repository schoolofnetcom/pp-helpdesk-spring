package com.schoolofnet.Helpdesk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.schoolofnet.Helpdesk.models.Interaction;
import com.schoolofnet.Helpdesk.models.Ticket;
import com.schoolofnet.Helpdesk.models.User;
import com.schoolofnet.Helpdesk.repositories.InteractionRepository;
import com.schoolofnet.Helpdesk.repositories.TicketRepository;
import com.schoolofnet.Helpdesk.repositories.UserRepository;

@Service
public class InteractionServiceImpl implements InteractionService {

	@Autowired
	private InteractionRepository interactionRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public InteractionServiceImpl(InteractionRepository interactionRepository, TicketRepository ticketRepository, UserRepository userRepository) {
		this.interactionRepository = interactionRepository;
		this.ticketRepository = ticketRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public Interaction create(Interaction interaction, Long ticketId) {
		Ticket ticket = this.ticketRepository.findOne(ticketId);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User userLogged = this.userRepository.findByEmail(username);
		
		interaction.setTicket(ticket);
		interaction.setUserInteraction(userLogged);
		
		return this.interactionRepository.save(interaction);
	}

	@Override
	public Boolean delete(Long id, Long ticketId) {
		Interaction interaction = this.interactionRepository.findOne(id);
		
		if (interaction != null) {
			this.interactionRepository.delete(interaction);
			return true;
		}
		
		return false;		
	}

}
