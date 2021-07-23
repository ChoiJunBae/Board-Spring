package boardEx.board.Controller;

import boardEx.board.dto.BoardDto;
import boardEx.board.dto.SignUpDto;
import boardEx.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 메인화면 값 BoardDTO에서 값 받아서 Vue로 넘겨주가
    @GetMapping("/")
    public List<BoardDto> list(Model model) {
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList);
        return boardDtoList;
    }

    @GetMapping("/post")
    public String post() {
        return "board/post.html";
    }

    // Vue에서 게시글 작성한 값 받아서 savePost에 넘겨주기
    @PostMapping("/post")
    public Long write(@RequestBody BoardDto boardDto) {
        return boardService.savePost(boardDto);
    }

    //게시물 수정한 내용 받아서 돌려주기
    @PutMapping("/post/{id}")
    public String update(@PathVariable("id")Long id,@RequestBody BoardDto boardDto ){

                        // 찾을 게시물 아이디, 수정할 데이터 DTO
        boardService.modify(id,boardDto);
        return "수정 완료!!";
    }


    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id){
        boardService.deletePost(id);
        return "삭제 완료!!";
    }
}