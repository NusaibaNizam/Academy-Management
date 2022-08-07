package com.bjit.nusaiba.final_project.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import com.bjit.nusaiba.final_project.entity.Role;
@EnableCaching
public interface RoleService {
	
	@CacheEvict
	void saveRole(Role role);
	
	@Cacheable
	Role getRoleByName(String name);
	
	@Cacheable
	Iterable<Role> getAllRoles();
	
	@Cacheable
	Role getRoleById(Integer id);
	
	@CacheEvict
	void deleteRole(Integer id);
}
