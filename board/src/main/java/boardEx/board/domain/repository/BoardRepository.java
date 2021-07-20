package boardEx.board.domain.repository;

import boardEx.board.domain.entity.Board;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
