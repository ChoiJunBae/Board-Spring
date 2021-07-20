package boardEx.board.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;

    private String name;
    private String email;
    private String password;
    private String location;


}
