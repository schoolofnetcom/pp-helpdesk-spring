package com.schoolofnet.Helpdesk.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.schoolofnet.Helpdesk.models.Ticket;

public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {

}
