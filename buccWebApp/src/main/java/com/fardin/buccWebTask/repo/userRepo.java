package com.fardin.buccWebTask.repo;

import com.fardin.buccWebTask.Model.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface userRepo extends JpaRepository<userEntity, Long> {
    Optional<userEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}
