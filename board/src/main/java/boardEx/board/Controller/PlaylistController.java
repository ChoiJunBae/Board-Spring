package boardEx.board.Controller;

import boardEx.board.dto.PlaylistDto;
import boardEx.board.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    //리스트값 DB에서 꺼내서 출력하기
    @GetMapping("/mylist")
    public List<PlaylistDto> list(Model model){
        List<PlaylistDto> playlistDtoList = playlistService.getPlaylistList() ;
        model.addAttribute("showPlaylist", playlistDtoList);
        return playlistDtoList;
    }

    // playlist 추가하기 버튼 클릭 시 작동
    @PostMapping("/mylist")
    public Long listChange(@RequestBody PlaylistDto playlistDto){
        return playlistService.saveList(playlistDto);
    }
}
