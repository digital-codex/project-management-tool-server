package dev.digitalcodex.web.persistence.repository;

import dev.digitalcodex.web.persistence.PersistenceConstants;
import dev.digitalcodex.web.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(PersistenceConstants.USER_REPOSITORY_BEAN_NAME)
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    @Modifying
    @Query(
            "UPDATE UserEntity u SET u.enabled = TRUE WHERE u.id = :id"
    )
    int updateSetEnabledTrueById(@Param("id") Long id);

    @Query(
            "SELECT u.id FROM UserEntity u WHERE u.username = :username"
    )
    Long findIdByUsername(@Param("username") String username);
}
