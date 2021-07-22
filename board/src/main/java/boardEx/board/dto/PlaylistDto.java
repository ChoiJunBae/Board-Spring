package boardEx.board.dto;

import boardEx.board.domain.entity.Playlist;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlaylistDto {
    private Long id;
    private String title;
    private String url;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd ' | ' HH:mm")
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Playlist toEntity(){
        Playlist build = Playlist.builder()
                .id(id)
                .title(title)
                .url(url)
                .build();
        return build;
    }

    @Builder
    public PlaylistDto(Long id, String title, String url, LocalDateTime createdDate){
        this.id=id;
        this.title=title;
        this.url=url;
        this.createdDate=createdDate;
    }
}
