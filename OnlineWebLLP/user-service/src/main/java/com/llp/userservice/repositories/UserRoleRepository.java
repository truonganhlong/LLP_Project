package com.llp.userservice.repositories;

import com.llp.userservice.entities.UserRole;
import com.llp.userservice.entities.keys.UserRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleKey> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO userRole (userId,roleId) VALUES(:userId,:roleId)", nativeQuery = true)
    void create(@Param("userId") int userId, @Param("roleId") int roleId);
}
