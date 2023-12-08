package study.datajpa.service2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.datajpa.domain.Board;
import study.datajpa.repository2.BoardDTO;
import study.datajpa.repository2.BoardRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    
    // 게시글 추가
    public BoardDTO save (Board board) {
        Board savedBoard = boardRepository.save(board);
        BoardDTO dto = new BoardDTO(savedBoard.getBoardCd(),savedBoard.getBoardNm());
        return dto;
    }

    // 전체 조회
    public List<BoardDTO> findAll() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDTO> dtos = new ArrayList<>();

        for(Board board : boards) {
            dtos.add(new BoardDTO(board.getBoardCd(), board.getBoardNm()));
        }

        return dtos;
    }

    // 갱신
    public BoardDTO update(Board board) {
        Board updatedBoard = boardRepository.save(board);
        BoardDTO dto = new BoardDTO(updatedBoard.getBoardCd(), updatedBoard.getBoardNm());
        return dto;
    }

    // 삭제
    public void delete(String id) {
        boardRepository.deleteById(id);
    }

}