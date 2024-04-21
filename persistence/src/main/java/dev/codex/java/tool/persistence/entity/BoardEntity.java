package dev.codex.java.tool.persistence.entity;

import dev.codex.java.tool.persistence.converter.BoardTypeConverter;
import dev.codex.java.tool.persistence.type.BoardType;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "BoardEntity")
@Table(name = "t_board", catalog = "postgres", schema = "public")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_sequence")
    @SequenceGenerator(name = "board-sequence", sequenceName = "s_board_id", catalog = "postgres", schema = "public", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false, updatable = false, columnDefinition = "BIGINT", table = "t_board")
    private Long id;

    @Column(name = "name", unique = true, nullable = false, updatable = false, columnDefinition = "VARCHAR", table = "t_board")
    private String name;

    @Column(name = "board_type", nullable = false, columnDefinition = "CHAR", table = "t_board")
    @Convert(converter = BoardTypeConverter.class)
    private BoardType boardType;

    public BoardEntity() {
        super();
    }

    public BoardEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public BoardType getBoardType() {
        return this.boardType;
    }

    public void setBoardType(BoardType boardType) {
        this.boardType = boardType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof BoardEntity that)) return false;
        return Objects.equals(this.id, that.id)
                && Objects.equals(this.name, that.name)
                && this.boardType == that.boardType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.boardType);
    }

    @Override
    public String toString() {
        return "BoardEntity{" +
                "id=" + this.id.toString() +
                ", name='" + this.name + "'" +
                ", boardType=" + this.boardType.name() +
                "}";
    }
}