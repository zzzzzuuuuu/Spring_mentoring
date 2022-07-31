package com.example.mentoring.controller;

import com.example.mentoring.entity.Board;
import com.example.mentoring.response.Response;
import com.example.mentoring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/boards")
    public Response getBoards() {
        return Response.success(boardService.getBoards());
    }

    // get 게시글 단건 조회 -> 게시글 상세 조회
    // ex)localhost:8888/boards/3
    @ResponseStatus(HttpStatus.OK) //상태코드는 컨트롤러에서 지정해주는 게 좋음.
    @GetMapping("/api/boards/{id}")
    public Response getBoard(@PathVariable("id") Long id) {
        return Response.success(boardService.getBoard(id));
    }

    // post 게시글 작성
    // HttpStatus.OK == 200, HttpStatus.CREATED == 201
    // localhost:8080/boards (only post)
    // @RequestBody 붙이는 이유: JSON 타입으로 데이터가 들어오는데, 이걸 자바에서 인식할 수 있게 자바 클래스로 매핑해줌.
    // Rest API-> JSON 형식으로 데이터를 받아야함.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/boards")
    public Response save(@RequestBody Board board) {
        return Response.success(boardService.save(board));
    }

    // put 게시글 수정
    // localhost::8080/boards/3
    // 게시글 수정 -> 완료 버튼 -> 백엔드 서버 요청 (id, updateBoard)
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/api/boards/{id}")
    public Response editBoard(@PathVariable("id") Long id, @RequestBody Board updateBoard) {
        return Response.success(boardService.editBoard(id, updateBoard));
    }


    // delete 게시글 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/api/boards/{id}")
    public Response deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return Response.success("게시글 삭제 완료");
    }
}

