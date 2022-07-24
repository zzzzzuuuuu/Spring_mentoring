package com.example.mentoring.controller;

import com.example.mentoring.entity.Board;
import com.example.mentoring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller 는 클라이언트(사용자)의 요청을 받는 클래스입니다.
 * Controller에서는 서비스 클래스 (기능 구현된 클래스) 를 불러서 사용자의 요청을 처리합니다.
 *
 * @Controller 는 템플릿 엔진(JSP, Thymeleaf 등등) 사용할 때 주로 쓰이고,
 * @RestController 는 API 서버를 만들 때 주로 사용됩니다. 저희는 API 서버를 만드니 RestController 로 진행합니다.
 */

@RequiredArgsConstructor // Non null + static 생성자
@RestController
public class BoardController {
    private final BoardService boardService;

    // get 게시글 전체 조회 -> 대략적인 게시물 정보 확인
    // ex)localhost:8888/boards
    @GetMapping("/api/boards")
    public ResponseEntity<?> getBoards() {
        return new ResponseEntity<>(boardService.getBoards(), HttpStatus.OK);
    }

    // get 게시글 단건 조회 -> 게시글 상세 조회
    // ex)localhost:8888/boards/3
    @GetMapping("/api/boards/{id}")
    public ResponseEntity<?> getBoard(@PathVariable("id") Long id) {
        return new ResponseEntity<>(boardService.getBoard(id), HttpStatus.OK);
    }

    // post 게시글 작성
    // HttpStatus.OK == 200, HttpStatus.CREATED == 201
    // localhost:8080/boards (only post)
    // @RequestBody 붙이는 이유: JSON 타입으로 데이터가 들어오는데, 이걸 자바에서 인식할 수 있게 자바 클래스로 매핑해줌.
    // Rest API-> JSON 형식으로 데이터를 받아야함.
    @PostMapping("/api/boards")
    public ResponseEntity<?> save(@RequestBody Board board) {
        return new ResponseEntity<>(boardService.save(board), HttpStatus.CREATED);
    }

    // put 게시글 수정
    // localhost::8080/boards/3
    // 게시글 수정 -> 완료 버튼 -> 백엔드 서버 요청 (id, updateBoard)
    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> editBoard(@PathVariable("id") Long id, @RequestBody Board updateBoard) {
        return new ResponseEntity<>(boardService.editBoard(id, updateBoard), HttpStatus.OK);
    }


    // delete 게시글 삭제
    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return new ResponseEntity<>("게시글 삭제 완료", HttpStatus.OK);
    }
}

