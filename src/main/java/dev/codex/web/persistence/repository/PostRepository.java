package dev.codex.web.persistence.repository;

import dev.codex.web.persistence.entity.PostEntity;
import dev.codex.web.persistence.PersistenceConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(PersistenceConstants.POST_REPOSITORY_BEAN_NAME)
public interface PostRepository extends JpaRepository<PostEntity, Long> {
     int countByForumId(Long forumId);

     List<PostEntity> findAllByForumId(Long forumId);

     @Query(
             value = "SELECT p FROM PostEntity p JOIN UserEntity u ON p.insertedBy = u.id WHERE u.username = :username"
     )
     List<PostEntity> findAllByUserEntityUsername(@Param("username") String username);
}