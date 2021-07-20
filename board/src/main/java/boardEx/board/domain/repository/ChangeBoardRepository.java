package boardEx.board.domain.repository;

import boardEx.board.domain.entity.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ChangeBoardRepository {

    private final EntityManager em;

    public Board findOne(Long id){
        return em.find(Board.class, id);
    }
}
