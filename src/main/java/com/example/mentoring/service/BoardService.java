package com.example.mentoring.service;

import com.example.mentoring.entity.Board;
import com.example.mentoring.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service 는 기능을 구현하는 것에 초점을 둔 클래스입니다.
 * Repository 를 불러와서, 데이터베이스에 데이터를 넣거나 혹은 가져와서 기능을 구현합니다.
 * <p>
 * Service 클래스는 Controller 에서 불러와서 사용합니다.
 */

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public List<Board> getBoards() {
      List<Board> boards = boardRepository.findAll();
        return boards;
    }
    // 게시글 단건 조회
    @Transactional(readOnly = true)
    public Board getBoard(Long id) {
        Board board = boardRepository.findById(id).get();
        return board;
    }
    // 게시글 작성
    @Transactional
    public Board save(Board board) {
        return boardRepository.save(board);
    }
    // 게시글 수정
    @Transactional //더티체킹이 일어나서, 저장하지 않아도 메서드가 성공적으로 끝나면 저장됨.
    public Board editBoard(Long id, Board updateBoard) {
        //기존 게시물 꺼내오기
        Board board = boardRepository.findById(id).get();
        //기존 게시물에 updateBoard 정보를 덮어씌워주기
        board.setTitle(updateBoard.getTitle());
        board.setContent(updateBoard.getContent());

        return board;
    }
    // 게시글 삭제
    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}

