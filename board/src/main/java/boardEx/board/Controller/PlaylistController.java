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

    // 플레이 리스트 제목 변경
    @PutMapping("/mylist/{id}")
    public String update(@PathVariable("id")Long id, @RequestBody PlaylistDto playlistDto){
        playlistService.modify(id, playlistDto);
        return "플레이 리스트 추가 완료!!";
    }

    //플레이 리스트 항목 삭제
    @DeleteMapping("/mylist/{id}")
    public String delete(@PathVariable("id") Long id){
        playlistService.deletePlaylist(id);
        return "플레이 리스트 삭제 완료!!";
    }
}
