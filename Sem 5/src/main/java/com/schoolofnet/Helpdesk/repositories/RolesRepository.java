package com.schoolofnet.Helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolofnet.Helpdesk.models.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
