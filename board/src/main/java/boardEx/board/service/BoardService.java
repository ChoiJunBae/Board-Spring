package boardEx.board.service;

import boardEx.board.domain.entity.Board;
import boardEx.board.domain.repository.BoardRepository;

import boardEx.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    //게시물 등록
    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    //게시물 삭제
    @Transactional
    public void deletePost(Long id){
        boardRepository.deleteById(id);
    }

    //서비스의 게시물 목록을 가지고 와서 List에 담아둔다.
    @Transactional
    public List<BoardDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Transactional
    public BoardDto getPost(Long id) {
        Board board = boardRepository.findById(id).get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .build();
        return boardDto;
    }
    // 찾을 게시글 아이디, 수정할 데이터
    public void modify(Long id,BoardDto boardDto) {

        // 해당 아이디의 게시글 찾기
        Optional<Board> board = boardRepository.findById(id);
        if(board.isPresent()){ // 값이 로직이 있을 경우에 ,

            // 수정할 데이터를 담을 Board 객체 생성
            Board findBoard = board.get();
            // 수정할 데이터 삽입
            findBoard.setTitle(boardDto.getTitle());
            findBoard.setContent(boardDto.getContent());
            // 데이터 저장
            boardRepository.save(findBoard);
        }

    }

    //게시물 수정
//    @Transactional
//    public BoardDto changeBoard(Long boardId){
//        Board board = changeBoardRepository.findOne(boardId);
//
//        BoardDto boardDto = BoardDto.builder()
//                .author(board.getAuthor())
//                .title(board.getTitle())
//                .content(board.getContent())
//                .createdDate(board.getCreatedDate())
//                .build();
//        return boardDto;
//    }
//
//    @Transactional
//    public void deletePost(Long id) {
//        boardRepository.deleteById(id);
//    }
}