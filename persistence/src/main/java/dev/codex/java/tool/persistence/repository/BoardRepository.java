package dev.codex.java.tool.persistence.repository;

import dev.codex.java.tool.persistence.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("boardRepository")
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}