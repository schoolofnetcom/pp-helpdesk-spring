package com.schoolofnet.Helpdesk.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.schoolofnet.Helpdesk.models.Role;
import com.schoolofnet.Helpdesk.models.Ticket;
import com.schoolofnet.Helpdesk.models.User;
import com.schoolofnet.Helpdesk.repositories.TicketRepository;
import com.schoolofnet.Helpdesk.repositories.UserRepository;

@Service
public class TicketServiceImpl implements TicketService {

	private final Long ROLE_ID = (long) 4;
	private final String ROLE_NAME = "ADMIN";
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository, RoleService roleService, UserService userService) {
		this.ticketRepository = ticketRepository;
		this.userRepository = userRepository;
		this.userService = userService;
		this.roleService = roleService;
	}
	
	@Override
	public List<Ticket> findAll() {
		return (List<Ticket>) this.ticketRepository.findAll();
	}

	@Override
	public Ticket create(Ticket ticket) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		
		User userLogged =  this.userRepository.findByEmail(userName);
		
		ticket.setUserOpen(userLogged);
		
		return this.ticketRepository.save(ticket); 
	}

	@Override
	public Boolean delete(Long id) {
		Ticket ticket = findById(id);
		
		if (ticket != null) {
			this.ticketRepository.delete(ticket);
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean update(Long id, Ticket ticket) {
		Ticket ticketExists = findById(id);
		
		if (ticketExists != null) {
			ticketExists.setId(ticket.getId());
			ticketExists.setName(ticket.getName());
			ticketExists.setDescription(ticket.getDescription());
			ticketExists.setFinished(ticket.getFinished());
			ticketExists.setTechnician(ticket.getTechnician());
			
			if (ticket.getFinished()) {
				ticketExists.setClosed(new Date());
			}
			
			this.ticketRepository.save(ticketExists);
			
			return true;
		}
		
		return false;
	}

	@Override
	public Ticket show(Long id) {
		return findById(id);
	}

	@Override
	public Model findAllTechinician(Model model) {
		Role adminRole = this.roleService.findByName(ROLE_NAME);

		//		model.addAttribute("techs", this.userService.findAllWhereRoleEquals(ROLE_ID));
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		
		User userLogged =  this.userRepository.findByEmail(userName);
		model.addAttribute("techs", this.userService.findAllWhereRoleEquals(adminRole.getId(), userLogged.getId()));
		
		return model;
	}
	
	private Ticket findById(Long id) {
		return this.ticketRepository.findOne(id);
	}

}
