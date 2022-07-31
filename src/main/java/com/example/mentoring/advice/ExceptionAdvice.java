package com.example.mentoring.advice;

import com.example.mentoring.exception.BoardNotFoundException;
import com.example.mentoring.exception.WriterNotFoundException;
import com.example.mentoring.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // @RestController + @Advice
public class ExceptionAdvice {
    // ExceptionAdvice == Exception을 관리하는 통제소 == 실패한 경우 실패 메시지를 리턴해주기 위한 RestController

    // 404 NotFound 에러
    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response boardNotFoundException() {
        return Response.failure(404, "게시글을 찾을 수 없습니다.");
    }


    // 404 NotFound
    @ExceptionHandler(WriterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response writerNotFoundException() {
        return Response.failure(404, "작성자를 입력해주세요.");
    }



}