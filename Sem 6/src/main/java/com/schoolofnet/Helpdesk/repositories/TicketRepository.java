package com.schoolofnet.Helpdesk.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.schoolofnet.Helpdesk.models.Ticket;

public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {

	@Query(value = "select ticket.* from tickets as ticket where ticket.created >= date(now()) - interval (:day) day", nativeQuery = true)
	public List<Ticket> findAllTicketsByDay(@Param("day") Integer day);
	
} 
