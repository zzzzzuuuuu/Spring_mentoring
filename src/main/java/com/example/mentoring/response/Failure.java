package com.example.mentoring.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Failure extends Result {
    private String msg;
}