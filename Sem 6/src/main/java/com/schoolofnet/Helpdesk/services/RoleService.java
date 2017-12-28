package com.schoolofnet.Helpdesk.services;

import java.util.List;

import com.schoolofnet.Helpdesk.models.Role;

public interface RoleService {

	public List<Role> findAll();
	public Role create(Role role);
	public Boolean delete(Long id);
	public Role findByName(String name);
}
