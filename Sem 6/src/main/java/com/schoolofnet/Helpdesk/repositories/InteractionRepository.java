package com.schoolofnet.Helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolofnet.Helpdesk.models.Interaction;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {

}
