package boardEx.board.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
