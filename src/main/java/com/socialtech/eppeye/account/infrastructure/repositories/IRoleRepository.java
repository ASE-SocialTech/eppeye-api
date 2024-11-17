package com.socialtech.eppeye.account.infrastructure.repositories;

import com.socialtech.eppeye.account.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role getRoleById (Long id);

}