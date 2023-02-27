package dev.danvega.jwt.repository;

import dev.danvega.jwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);
}
