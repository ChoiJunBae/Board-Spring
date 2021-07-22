package boardEx.board.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Playlist {

    @Id
    @GeneratedValue
    @Column(name="playlist_id")
    private Long id;    //플레이 리스트 ID

    @Column(length = 100, nullable = false)
    private String title;  //노래 제목

    @Column(length = 100, nullable = false)
    private String url; //노래 URL

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Builder
    public Playlist(Long id, String title, String url){
        this.id=id;
        this.title=title;
        this.url=url;
    }
}

