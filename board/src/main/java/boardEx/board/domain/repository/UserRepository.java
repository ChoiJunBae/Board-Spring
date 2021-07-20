package boardEx.board.domain.repository;


import boardEx.board.domain.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    // 회원정보 DB에 저장
    public Long save(User user) {
        em.persist(user);
        return user.getId();
    }

    // 이메일로 회원 찾기
    public List<User> findByEmail(String email){
        return em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
    }

    // 모든 회원 불러오기
    public List<User> findAll(){
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }
}
