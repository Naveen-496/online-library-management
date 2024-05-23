package dev.reddy.olm.repository;

import dev.reddy.olm.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credentials, Long> {

    Optional<Credentials> findByUserId(Long userId);
}
