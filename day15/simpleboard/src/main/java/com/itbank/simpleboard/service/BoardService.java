package com.itbank.simpleboard.service;

import com.itbank.simpleboard.domain.Board;
import com.itbank.simpleboard.domain.BoardDto;
import com.itbank.simpleboard.domain.Member;
import com.itbank.simpleboard.repository.BoardRepository;
import com.itbank.simpleboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    @Autowired
    private final BoardRepository boardRepository;


    public List<BoardDto> findAll() {

        List<Board> boards = boardRepository.findAll();
        List<BoardDto> dtos = new ArrayList<>(); // 반환할 빈 리스트

        for(Board board : boards) {
            BoardDto dto = new BoardDto();
            dto.setBoardId(board.getId());
            dto.setWriter(board.getMember());
            dto.setTitle(board.getTitle());
            dto.setContent(board.getContent());
            dto.setCreateDate(board.getCreateDate());
            dtos.add(dto);
        }

        return dtos;
    }


    @Transactional
    public Board save(BoardDto dto) {

        Board board = new Board();
        board.setContent(dto.getContent());
        board.setTitle(dto.getTitle());
        board.setMember(dto.getWriter());

        Board savedBoard = boardRepository.save(board);

        return savedBoard;
    }

    public Board findById(Long id) {
        return boardRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }



    @Transactional
    public Board update(Board board) {
        Board preBoard = findById(board.getId());
        preBoard.setContent(board.getContent());
        preBoard.setTitle(board.getTitle());

        return preBoard;
    }
}
