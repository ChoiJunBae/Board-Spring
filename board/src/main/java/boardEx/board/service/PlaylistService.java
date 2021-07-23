package boardEx.board.service;

import boardEx.board.domain.entity.Board;
import boardEx.board.domain.entity.Playlist;
import boardEx.board.domain.repository.PlaylistRepository;
import boardEx.board.dto.BoardDto;
import boardEx.board.dto.PlaylistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    //playlist 저장하는 부분
    @Transactional
    public Long saveList(PlaylistDto playlistDto){
        return playlistRepository.save(playlistDto.toEntity()).getId();
    }

    @Transactional
    public List<PlaylistDto> getPlaylistList(){
        List<Playlist> playlistList = playlistRepository.findAll();
        List<PlaylistDto> playlistDtoList = new ArrayList<>();

        for(Playlist playlist : playlistList){
            PlaylistDto playlistDto = PlaylistDto.builder()
                    .id(playlist.getId())
                    .title(playlist.getTitle())
                    .url(playlist.getUrl())
                    .createdDate(playlist.getCreateDate())
                    .build();
            playlistDtoList.add(playlistDto);
        }
        return playlistDtoList;
    }
    
    // 리스트 삭제하기
    @Transactional
    public void deletePlaylist(Long id){
        playlistRepository.deleteById(id);
    }

    // 리스트 제목 변경하기
    public void modify(Long id, PlaylistDto playlistDto) {

        Optional<Playlist> playlist = playlistRepository.findById(id);
        if (playlist.isPresent()) { // 값이 로직이 있을 경우에 ,
            Playlist findPlayList = playlist.get();
            findPlayList.setTitle(playlistDto.getTitle());
            playlistRepository.save(findPlayList);
        }
    }
}


