package com.yaredgidey.studentdatauiapp.repo;

import com.yaredgidey.studentdatauiapp.model.ERole;
import com.yaredgidey.studentdatauiapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
