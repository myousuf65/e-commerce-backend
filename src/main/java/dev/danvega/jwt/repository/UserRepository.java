package dev.danvega.jwt.repository;

import dev.danvega.jwt.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional <UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);



}
