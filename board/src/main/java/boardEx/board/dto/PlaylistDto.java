package boardEx.board.dto;

import boardEx.board.domain.entity.Playlist;
import boardEx.board.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlaylistDto {
    private Long id;
    private Long user;
    private String title;
    private String url;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd ' | ' HH:mm")
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Playlist toEntity(){
        User userTmp = new User();
        userTmp.setId(user);

        Playlist build = Playlist.builder()
                .id(id)
                .user(userTmp)
                .title(title)
                .url(url)
                .build();
        return build;
    }

    @Builder
    public PlaylistDto(Long id, Long user, String title, String url, LocalDateTime createdDate){
        this.id=id;
        this.user=user;
        this.title=title;
        this.url=url;
        this.createdDate=createdDate;
    }
}
